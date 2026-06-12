output "ecr_repo_url" {
  value = values(aws_ecr_repository.ecr_repo)[*].repository_url
}