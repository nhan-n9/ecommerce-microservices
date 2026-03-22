sg_inbound_rules = {
  "public_web_sg" = {
    description = "Public Web Traffic"
    rules = [
      { from_port = 80,  to_port = 80,  proto = "tcp", cidr_ipv4 = "0.0.0.0/0" },
      { from_port = 443, to_port = 443, proto = "tcp", cidr_ipv4 = "0.0.0.0/0" },
      { from_port = 22,  to_port = 22,  proto = "tcp", cidr_ipv4 = "0.0.0.0/0" },
      { from_port = 22,  to_port = 22,  proto = "tcp", cidr_ipv6 = "::/0" }
    ]
  },
  "app_sg" = {
    description = "Application Web Traffic"
    rules = [
      { from_port = 22,  to_port = 22,  proto = "tcp", cidr_ipv4 = "0.0.0.0/0" },
      { from_port = 22,  to_port = 22,  proto = "tcp", cidr_ipv6 = "::/0" }
    ]
  },
  "db_sg" = {
    description = "Internal DB Traffic"
    rules = [
      { from_port = 0, to_port = 65535, proto = "tcp", cidr_ipv4 = "10.16.0.0/16" }
    ]
  },
  "eks_sg" = {
    description = "EKS Cluster SG"
    rules = [
      { from_port = 80,  to_port = 80,  proto = "tcp", cidr_ipv4 = "0.0.0.0/0" },
      { from_port = 443, to_port = 443, proto = "tcp", cidr_ipv4 = "0.0.0.0/0" },
      { from_port = 1024,  to_port = 65534,  proto = "tcp", cidr_ipv4 = "0.0.0.0/0" },
      { from_port = 53,  to_port = 53,  proto = "tcp", cidr_ipv4 = "0.0.0.0/0" }
      ]
  },
}

sg_names = ["public_web_sg", "app_sg", "db_sg", "eks_sg"]

# set of private subnet names
private_subnet_names = ["tf-private-sn-1", "tf-private-sn-2", "tf-private-sn-3"]

sg_outbound_rules = {}