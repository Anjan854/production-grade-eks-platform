variable "vpc_cidr" {}
variable "public_subnets" {}
variable "private_subnets" {}
variable "vpc_name" {}
variable "cluster_name" {}
variable "ecr_repo_name" {
  type = set(string)
}