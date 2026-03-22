# get the existing roles
data "aws_iam_role" "cluster" {
  name = "EKS-Cluster-Role"
}

data "aws_iam_role" "worker" {
  name = "EKS-Worker-Role"
}

# retrive existing subnets based 


# create the eks cluster
resource "aws_eks_cluster" "this" {
  name = "tf-eks-cluster"

  access_config {
    authentication_mode = "API"
  }

  # get the role through its arn
  role_arn = data.aws_iam_role.cluster.arn

  vpc_config {
    subnet_ids = var.eks_subnet_ids
    endpoint_private_access = true
    endpoint_public_access = true
    security_group_ids = var.eks_cluster_sg_ids
  }
}

# fetch the thumbprint_list value for the OIDC provider resource
data "tls_certificate" "eks_oidc" {
  depends_on = [aws_eks_cluster.this]
  url = aws_eks_cluster.this.identity[0].oidc[0].issuer
}

resource "aws_iam_openid_connect_provider" "eks_oidc_provider" {
  url = aws_eks_cluster.this.identity[0].oidc[0].issuer
  
  client_id_list = ["sts.amazonaws.com"]
  thumbprint_list = [data.tls_certificate.eks_oidc.certificates[0].sha1_fingerprint]
}


# create the node group
resource "aws_eks_node_group" "node_group" {
  cluster_name    = aws_eks_cluster.this.name
  node_group_name = "tf-node-group"
  node_role_arn   = data.aws_iam_role.worker.arn
  subnet_ids      = var.eks_subnet_ids

  scaling_config {
    desired_size = 3
    max_size     = 4
    min_size     = 2
  }

  instance_types = ["t3.small"]
  capacity_type  = "ON_DEMAND"
  disk_size      = 20

  update_config {
    max_unavailable = 1
  }
}


# add admin access to the cluster for the specified user
resource "aws_eks_access_entry" "admin" {
  cluster_name  = aws_eks_cluster.this.name
  principal_arn = "arn:aws:iam::102724112672:user/iamadmin"
  type          = "STANDARD"
}

resource "aws_eks_access_policy_association" "admin" {
  cluster_name  = aws_eks_cluster.this.name
  principal_arn = aws_eks_access_entry.admin.principal_arn

  policy_arn = "arn:aws:eks::aws:cluster-access-policy/AmazonEKSClusterAdminPolicy"

  access_scope {
    type = "cluster"
  }
}