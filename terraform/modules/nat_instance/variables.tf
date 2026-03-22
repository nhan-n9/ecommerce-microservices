variable "region" {
  description = "Default region for provider"
  type        = string
  default     = "us-east-1"
}


variable "environment_name" {
  description = "Deployment environment (dev/staging/production)"
  type        = string
  default     = "dev"
}