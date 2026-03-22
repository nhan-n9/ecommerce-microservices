# output private subnets ids for eks cluster
output "private_subnet_ids" {
  value = [
    aws_subnet.tf_private_sn_1.id,
    aws_subnet.tf_private_sn_3.id
  ]
}

output "eks_sg_ids" {
  value = [
    aws_security_group.all_sgs["eks_sg"].id
  ]
}

output "vpc_id" {
  value = aws_vpc.tf_vpc.id
}