variable "eks_subnet_ids" {
  description = "Subnet IDs for EKS cluster ENIs (at least 2 AZs)"
  type        = list(string)

  validation {
    condition     = length(var.eks_subnet_ids) >= 2
    error_message = "Provide at least 2 subnet IDs in different AZs."
  }
}

variable "ssh_key_name" {
  description = "Name of the SSH key pair for EC2 instances"
  type        = string
}

variable "eks_cluster_sg_ids" {
  description = "List of security group IDs to attach to the EKS cluster"
  type        = list(string)
}

variable "vpc_id" {
  description = "VPC ID where the EKS cluster will be deployed"
  type        = string
}