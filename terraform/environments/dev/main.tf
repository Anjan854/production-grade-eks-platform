module "vpc" {
  source = "../../modules/vpc"

  vpc_cidr = var.vpc_cidr
  vpc_name = var.vpc_name

  public_subnets = var.public_subnets

  private_subnets = var.private_subnets
}

module "eks" {
  source = "../../modules/eks"

  cluster_name       = var.cluster_name
  vpc_id             = module.vpc.vpc_id
  private_subnet_ids = module.vpc.private_subnet_ids
}