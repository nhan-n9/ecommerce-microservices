provider "aws" {
  region = "us-east-1"
}

module "database" {
  source = "./modules/database"
}

module "network" {
  source = "./modules/network"

  sg_inbound_rules = var.sg_inbound_rules
  sg_names = var.sg_names
  private_subnet_names = var.private_subnet_names
  sg_outbound_rules = var.sg_outbound_rules
}

module "eks" {
  source = "./modules/eks"

  eks_subnet_ids = module.network.private_subnet_ids
  ssh_key_name = "devops-project-key"
  eks_cluster_sg_ids = module.network.eks_sg_ids
  vpc_id = module.network.vpc_id
}


module "eks_addons" {
  source = "./modules/eks-addons"

  cluster_name              = module.eks.cluster_name
  cluster_endpoint          = module.eks.cluster_endpoint
  cluster_ca_certificate    = module.eks.cluster_certificate_authority_data
  oidc_provider_arn         = module.eks.oidc_provider_arn
  oidc_provider_url         = module.eks.oidc_provider_url
  vpc_id                    = module.network.vpc_id
  argo_oidc_provider_arn    = "arn:aws:sso:::instance/ssoins-722368290793476c"
}