vpc_cidr = "30.0.0.0/16"
vpc_name = "vpc-dev"
public_subnets = {
  a = {
    cidr_block = "30.0.0.0/20"
    az         = "ap-south-1a"
    name       = "dev-public-1"
  }
  b = {
    cidr_block = "30.0.16.0/20"
    az         = "ap-south-1b"
    name       = "dev-public-2"
  }
}

private_subnets = {
  a = {
    cidr_block = "30.0.32.0/20"
    az         = "ap-south-1a"
    name       = "dev-private-1"
  }
  b = {
    cidr_block = "30.0.48.0/20"
    az         = "ap-south-1b"
    name       = "dev-private-2"
  }
}

cluster_name = "dev-eks"

ecr_repo_name = [
  "frontend",
  "productcatalogservice",
  "cartservice",
  "checkoutservice",
  "currencyservice",
  "recommendationservice",
  "shippingservice",
  "paymentservice",
  "emailservice",
  "adservice",
  "loadgenerator",
  "shoppingassistantservice"
]