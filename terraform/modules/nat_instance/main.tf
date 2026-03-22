
# gather the public fck-nat instance ami
data "aws_ami" "fck-nat" {
  # most_recent = true

  filter {
    name   = "name"
    values = ["fck-nat-nat64-al2023-hvm-1.4.0-20260126-arm64-ebs"]
  }

  filter {
    name   = "architecture"
    values = ["arm64"]
  }

}

resource "aws_instance" "fck-nat-public-vpc-1" {
  ami           = data.aws_ami.fck-nat.id
  instance_type = "t4g.micro"

  tags = {
    Name = "tf-fck-nat-public-vpc-1"
  }
}