data "http" "myip" {
  url = "https://ipv4.icanhazip.com"
}

resource "aws_vpc" "tf_vpc" {
  cidr_block       = "10.16.0.0/16"
  assign_generated_ipv6_cidr_block = true
  enable_dns_hostnames = true
  enable_dns_support   = true

  tags = {
    Name = "tf-vpc"
  }
}

# ====== Subnets ======

resource "aws_subnet" "tf_public_sn_1" {
  vpc_id     = aws_vpc.tf_vpc.id
  cidr_block = "10.16.0.0/24"
  ipv6_cidr_block = cidrsubnet(aws_vpc.tf_vpc.ipv6_cidr_block, 8, 0)

  availability_zone = "us-east-1a"
  map_public_ip_on_launch = true

  tags = {
    Name = "tf-public-sn-1"
    "kubernetes.io/role/elb" = "1"
  }
}

resource "aws_subnet" "tf_public_sn_2" {
  vpc_id     = aws_vpc.tf_vpc.id
  cidr_block = "10.16.1.0/24"
  ipv6_cidr_block = cidrsubnet(aws_vpc.tf_vpc.ipv6_cidr_block, 8, 1)

  availability_zone = "us-east-1b"
  map_public_ip_on_launch = true

  tags = {
    Name = "tf-public-sn-2"
    "kubernetes.io/role/elb" = "1"
  }
}

resource "aws_subnet" "tf_private_sn_1" {
  vpc_id     = aws_vpc.tf_vpc.id
  cidr_block = "10.16.2.0/24"
  ipv6_cidr_block = cidrsubnet(aws_vpc.tf_vpc.ipv6_cidr_block, 8, 2)
  availability_zone = "us-east-1a"

  tags = {
    Name = "tf-private-sn-1"
  }
}

resource "aws_subnet" "tf_private_sn_2" {
  vpc_id     = aws_vpc.tf_vpc.id
  cidr_block = "10.16.3.0/24"
  ipv6_cidr_block = cidrsubnet(aws_vpc.tf_vpc.ipv6_cidr_block, 8, 3)
  availability_zone = "us-east-1a"

  tags = {
    Name = "tf-private-sn-2"
  }
}

resource "aws_subnet" "tf_private_sn_3" {
  vpc_id     = aws_vpc.tf_vpc.id
  cidr_block = "10.16.4.0/24"
  ipv6_cidr_block = cidrsubnet(aws_vpc.tf_vpc.ipv6_cidr_block, 8, 4)
  availability_zone = "us-east-1b"

  tags = {
    Name = "tf-private-sn-3"
  }
}

# ====== Internet Gateway ======

resource "aws_internet_gateway" "tf_igw" {
  vpc_id = aws_vpc.tf_vpc.id

  tags = {
    Name = "tf-igw"
  }
}


# ===== NAT Gateway ======

resource "aws_eip" "tf_nat_eip" {
  domain   = "vpc"

  tags = {
    Name = "tf-nat-eip"
  }
}

resource "aws_nat_gateway" "tf_nat_gw" {
  allocation_id = aws_eip.tf_nat_eip.id
  subnet_id     = aws_subnet.tf_public_sn_1.id

  tags = {
    Name = "tf-nat-gw"
  }

  depends_on = [aws_internet_gateway.tf_igw]
}

# ====== Security groups ======

resource "aws_security_group" "all_sgs" {
  for_each = var.sg_names

  name   = each.value
  description = "Security group for ${each.value}"
  vpc_id = aws_vpc.tf_vpc.id

  tags = {
    Name = "tf-${each.value}-sg"
  }
}

# dynamic inbound rules
resource "aws_vpc_security_group_ingress_rule" "inbound_rules" {
  for_each = local.sg_in_rules_map
  security_group_id = aws_security_group.all_sgs[each.value.sg_name].id

  from_port         = each.value.from_port
  to_port           = each.value.to_port
  ip_protocol       = each.value.protocol
  description       = each.value.description

  # only use the one that isn't null
  cidr_ipv4                    = each.value.cidr_ipv4
  cidr_ipv6                    = each.value.cidr_ipv6
  referenced_security_group_id = each.value.ref_sg_id
}

resource "aws_vpc_security_group_egress_rule" "allow_all_traffic_ipv4" {
  for_each = var.sg_names
  security_group_id = aws_security_group.all_sgs[each.value].id

  cidr_ipv4         = "0.0.0.0/0"
  ip_protocol       = "-1" # equivalent to all ports
}

resource "aws_vpc_security_group_egress_rule" "allow_all_traffic_ipv6" {
  for_each = var.sg_names
  security_group_id = aws_security_group.all_sgs[each.value].id

  cidr_ipv6         = "::/0"
  ip_protocol       = "-1"
}


# ====== Route Tables ======
resource "aws_route_table" "tf_public_rt" {
  vpc_id = aws_vpc.tf_vpc.id

  route {
    cidr_block = "0.0.0.0/0"
    gateway_id = aws_internet_gateway.tf_igw.id
  }

  tags = {
    Name = "tf-public-rt"
  }
}

resource "aws_route_table" "tf_private_rt" {
  vpc_id = aws_vpc.tf_vpc.id

  route {
    cidr_block = "0.0.0.0/0"
    nat_gateway_id = aws_nat_gateway.tf_nat_gw.id
  }

  tags = {
    Name = "tf-private-rt"
  }
}


# ====== Route Table Associations ======

resource "aws_route_table_association" "public" {
  for_each = {
    public_sn_1 = aws_subnet.tf_public_sn_1.id
    public_sn_2 = aws_subnet.tf_public_sn_2.id
  }

  subnet_id      = each.value
  route_table_id = aws_route_table.tf_public_rt.id
}

resource "aws_route_table_association" "private" {
  for_each = {
    private_sn_1 = aws_subnet.tf_private_sn_1.id
    private_sn_2 = aws_subnet.tf_private_sn_2.id
    private_sn_3 = aws_subnet.tf_private_sn_3.id
  }

  subnet_id = each.value
  route_table_id = aws_route_table.tf_private_rt.id
}