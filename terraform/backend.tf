terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = ">= 6.37.0"
    }
    helm = {
      source  = "hashicorp/helm"
      version = "~> 2.12"
    }
    kubernetes = {
      source  = "hashicorp/kubernetes"
      version = "~> 2.25.0"
    }
  }

  backend "s3" {
    bucket         = "tf-state-s3-bucket-hiy8zf2jn9zufgh"
    key            = "terraform/terraform.tfstate"
    region         = "us-east-1"
    dynamodb_table = "tf-state-locking"
    encrypt        = true
  }

  required_version = ">= 1.2"
}