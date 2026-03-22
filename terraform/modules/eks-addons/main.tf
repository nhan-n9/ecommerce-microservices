data "aws_eks_cluster" "this" {
  name = var.cluster_name
}

data "aws_eks_cluster_auth" "this" {
  name = var.cluster_name
}


provider "helm" {
  kubernetes {
    host                   = var.cluster_endpoint
    cluster_ca_certificate = base64decode(var.cluster_ca_certificate)
    token                  = data.aws_eks_cluster_auth.this.token
  }
}

provider "kubernetes" {
  host                   = var.cluster_endpoint
  cluster_ca_certificate = base64decode(var.cluster_ca_certificate)
  token                  = data.aws_eks_cluster_auth.this.token
}


# create iam policy for the load balancer controller
resource "aws_iam_policy" "lb_policy" {
  name  = "TF-AWSLoadBalancerControllerIAMPolicy"
  policy = file("modules/eks-addons/lbc/iam_policy.json")
}

data "aws_iam_policy_document" "lbc_assume_role" {
  statement {
    actions = ["sts:AssumeRoleWithWebIdentity"]

    principals {
      type        = "Federated"
      identifiers = [var.oidc_provider_arn]
    }

    condition {
      test     = "StringEquals"
      variable = "${replace(var.oidc_provider_url, "https://", "")}:sub"
      values   = ["system:serviceaccount:kube-system:aws-iam-lbc-sa"]
    }
  }
}

# create iam role for the load balancer controller
resource "aws_iam_role" "lb_role" {
  name = "TF-LoadBalancerControllerRole"

  assume_role_policy = data.aws_iam_policy_document.lbc_assume_role.json
}

resource "aws_iam_role_policy_attachment" "lb_role_attachment" {
  role       = aws_iam_role.lb_role.name
  policy_arn = aws_iam_policy.lb_policy.arn
}

resource "helm_release" "lb_controller" {
  name       = "tf-lb-controller"
  repository = "https://aws.github.io/eks-charts"
  chart      = "aws-load-balancer-controller"
  version    = "1.14.0"
  create_namespace = true
  namespace  = "kube-system"

  set {
      name  = "clusterName"
      value = var.cluster_name
    }

  set {
      name  = "serviceAccount.create"
      value = "true"
    }

  set {
      name  = "serviceAccount.name"
      value = "aws-iam-lbc-sa"
    }

# attach the iam role to the service account
  set {
      name  = "serviceAccount.annotations.eks\\.amazonaws\\.com/role-arn"
      value = aws_iam_role.lb_role.arn
    }
  
  set {
      name  = "region"
      value = "us-east-1"
    }
  
  set {
      name  = "vpcId"
      value = var.vpc_id
    }
}


resource "helm_release" "argocd" {
  name       = "tf-argocd"
  repository = "https://argoproj.github.io/argo-helm"
  chart      = "argo-cd"
  version    = "9.4.10"
  create_namespace = true
  namespace  = "argocd"
  timeout    = 600  # 10 minutes instead of default 5

  force_update = true
  recreate_pods = true

  values = [
    file("modules/eks-addons/argocd/values.yaml")
  ]

  reset_values = true
}