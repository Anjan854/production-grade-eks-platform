# Production-Grade AWS EKS Infrastructure using Terraform

## 📌 Project Overview

This project demonstrates the implementation of a production-style AWS infrastructure using Terraform modules and Amazon EKS.

The goal of this project is to build a scalable and reusable cloud platform following real-world DevOps and Infrastructure as Code (IaC) practices.

The infrastructure currently includes:

* Modular Terraform architecture
* Production-grade VPC
* Public & Private subnet architecture
* Internet Gateway & NAT Gateway
* Amazon EKS Cluster
* Managed Node Group
* Remote Terraform backend using S3 + DynamoDB
* Multi-environment ready structure

---

# 🏗️ Architecture

## Infrastructure Components

### Networking Layer

* Custom VPC
* Public Subnets
* Private Subnets
* Internet Gateway
* NAT Gateway
* Public Route Table
* Private Route Table

### Kubernetes Layer

* Amazon EKS Cluster
* Managed Node Group
* IAM Roles & Policies
* Worker Nodes inside private subnets

### Terraform Backend

* S3 Remote State Backend
* DynamoDB State Locking

---

# 📁 Project Structure

```text
terraform/
├── modules/
│   ├── vpc/
│   │   ├── main.tf
│   │   ├── variables.tf
│   │   └── outputs.tf
│   │
│   └── eks/
│       ├── main.tf
│       ├── variables.tf
│       └── outputs.tf
│
└── environments/
    └── dev/
        ├── backend.tf
        ├── provider.tf
        ├── main.tf
```

---

# ☁️ AWS Services Used

* Amazon VPC
* Amazon EKS
* EC2
* IAM
* S3
* DynamoDB
* CloudWatch

---

# ⚙️ Technologies Used

* Terraform
* Kubernetes
* AWS CLI
* kubectl

---

# 🚀 Features

* Modular Terraform codebase
* Reusable infrastructure modules
* Multi-AZ subnet architecture
* Public/private subnet separation
* Secure remote Terraform state
* State locking using DynamoDB
* Managed Kubernetes deployment
* Production-style infrastructure design

---

# 🧠 Key Concepts Demonstrated

* Infrastructure as Code (IaC)
* Terraform Modules
* AWS Networking
* Kubernetes on AWS
* EKS Cluster Provisioning
* IAM Role Management
* Remote State Management
* Production Infrastructure Architecture

---

# 🌐 Networking Architecture

```text
VPC
│
├── Public Subnets
│   ├── Internet Gateway
│   └── NAT Gateway
│
├── Private Subnets
│   └── EKS Worker Nodes
│
└── Route Tables
    ├── Public Route Table
    └── Private Route Table
```

---

# 🔐 Remote Backend Configuration

Terraform state is stored remotely in AWS S3.

```text
Bucket: ninza-terraform-state-prod
Key: eks-platform/dev/terraform.tfstate
Region: ap-south-1
```

Terraform state locking is enabled using DynamoDB.

```text
Table: terraform-locks
```

---

# 🛠️ Deployment Steps

## 1. Initialize Terraform

```bash
terraform init
```

---

## 2. Validate Configuration

```bash
terraform validate
```

---

## 3. Review Infrastructure Plan

```bash
terraform plan
```

---

## 4. Deploy Infrastructure

```bash
terraform apply
```

---

# ☸️ Configure Kubernetes Access

Update kubeconfig:

```bash
aws eks update-kubeconfig \
  --name dev-eks \
  --region ap-south-1
```

Verify cluster:

```bash
kubectl get nodes
```

---

# 📈 Current Progress

## ✅ Day 1 Completed

* VPC Module
* Public/Private Subnets
* Internet Gateway
* NAT Gateway
* Route Tables
* Remote Backend Setup

## ✅ Day 2 Completed

* Amazon EKS Cluster
* Managed Node Group
* IAM Roles & Policies
* Kubernetes Cluster Access

---

# 🔮 Upcoming Improvements

* Jenkins CI/CD Integration
* Amazon ECR Integration
* Dockerized Applications
* Kubernetes Deployments & Services
* Ingress Controller
* Monitoring with Prometheus & Grafana
* Helm-based Deployments

---

# 💰 Cost Optimization

To reduce AWS costs during learning:

* Small node groups are used
* Minimal scaling configuration is used
* Infrastructure can be destroyed when idle

Destroy infrastructure:

```bash
terraform destroy
```

---

# 👨‍💻 Author

DevOps Engineering Learning Project focused on:

* AWS
* Terraform
* Kubernetes
* CI/CD
* Cloud Infrastructure
* Production-grade DevOps practices

---

# 📌 Project Goal

The purpose of this project is to gain hands-on experience building production-grade cloud infrastructure similar to real-world DevOps and Platform Engineering environments.
