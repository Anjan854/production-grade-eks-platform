# Production-Grade EKS Platform

## Overview

This project demonstrates a complete Production-Grade Kubernetes Platform built on Amazon EKS using Infrastructure as Code (Terraform), GitOps (ArgoCD), Continuous Integration/Continuous Delivery (Jenkins), and Kubernetes-native deployment practices.

The platform automatically provisions cloud infrastructure, configures Kubernetes components, deploys applications through GitOps, and supports automated CI/CD workflows.

---

## Architecture

### Infrastructure Layer

* Amazon EKS Cluster
* VPC and Networking
* Public and Private Subnets
* Internet Gateway
* Route Tables
* Security Groups
* IAM Roles and Policies

### Platform Layer

* AWS Load Balancer Controller
* ArgoCD
* Kubernetes Namespaces
* Ingress Management

### CI/CD Layer

* Jenkins Infrastructure Pipeline
* Jenkins Application Pipeline
* GitHub Webhooks
* Shared Jenkins Libraries

### GitOps Layer

* ArgoCD Application Management
* Automatic Kubernetes Synchronization
* Declarative Deployments

---

## Repository Structure

```text
production-grade-eks-platform/
│
├── terraform/
│   ├── modules/
│   └── environments/
│       └── dev/
│
├── applications/
│   ├── frontend/
│   ├── backend/
│   └── manifests/
│
├── argocd/
│   ├── applications/
│   └── projects/
│
├── alb-controller/
│   └── eks/
│
├── jenkins/
│   └── shared-library/
│
├── Jenkinsfile.infrastructure
├── Jenkinsfile.application
│
└── README.md
```

---

# CI/CD Workflow

## Infrastructure Pipeline

The infrastructure pipeline is responsible for provisioning and maintaining platform infrastructure.

### Pipeline Stages

1. Checkout SCM
2. Detect Infrastructure Changes
3. Terraform Init
4. Terraform Format Validation
5. Terraform Validate
6. Terraform Plan
7. Terraform Apply
8. Update Kubeconfig
9. Install AWS Load Balancer Controller
10. Install ArgoCD
11. Register Git Repository in ArgoCD
12. Create ArgoCD Applications
13. Trigger Application Pipeline

### Trigger Conditions

The infrastructure pipeline executes when changes occur in:

```text
terraform/
alb-controller/
argocd/
Jenkinsfile.infrastructure
```

---

## Application Pipeline

The application pipeline manages application delivery.

### Pipeline Stages

1. Checkout Source Code
2. Detect Application Changes
3. Build Docker Images
4. Push Images to Registry
5. Update Kubernetes Manifests
6. Commit Manifest Changes
7. Push Changes to GitHub

### Trigger Conditions

The application pipeline executes when changes occur in:

```text
applications/
Dockerfiles
Jenkinsfile.application
```

The pipeline can also be triggered automatically by the Infrastructure Pipeline after successful cluster provisioning.

---

# GitOps Workflow

This platform follows GitOps principles using ArgoCD.

## Deployment Flow

```text
Developer
    |
    v
GitHub Repository
    |
    v
Jenkins Pipeline
    |
    v
Update Kubernetes Manifests
    |
    v
GitHub Repository
    |
    v
ArgoCD
    |
    v
Amazon EKS Cluster
```

ArgoCD continuously monitors Git repositories and synchronizes desired state with the Kubernetes cluster.

---

# Infrastructure Provisioning

Terraform provisions:

### Networking

* VPC
* Public Subnets
* Private Subnets
* Internet Gateway
* Route Tables

### Kubernetes

* Amazon EKS Control Plane
* Managed Node Groups
* IAM Roles
* Security Groups

### Integrations

* OIDC Provider
* IAM Roles for Service Accounts (IRSA)

---

# AWS Load Balancer Controller

The AWS Load Balancer Controller is installed automatically during infrastructure bootstrap.

Features:

* Application Load Balancer provisioning
* Ingress support
* Target Group management
* AWS-native traffic routing

---

# ArgoCD Installation

ArgoCD is installed automatically after EKS cluster creation.

Features:

* GitOps deployment management
* Automatic synchronization
* Rollback support
* Application health monitoring

---

# Jenkins Shared Library

Reusable pipeline logic is implemented through Jenkins Shared Libraries.

Examples:

```text
terraformInit()
terraformValidate()
terraformPlan()
terraformApply()

updateKubeconfig()

installALBController()

installArgoCD()

registerArgoRepo()

createArgoApps()
```

This approach reduces duplication and improves maintainability.

---

# Prerequisites

Before running the platform:

* AWS Account
* IAM User with Administrator Access
* Terraform
* AWS CLI
* kubectl
* Helm
* Jenkins
* GitHub Repository
* Docker

---

# Jenkins Configuration

Required Credentials:

```text
github-creds
aws-credentials
```

Required Tools:

```text
Terraform
AWS CLI
kubectl
Helm
Git
Docker
```

---

# Deployment Steps

## Infrastructure Deployment

Push changes to:

```text
terraform/
argocd/
alb-controller/
```

or manually execute:

```bash
Build -> infrastructure-bootstrap
```

The pipeline will:

* Create EKS cluster
* Configure Kubernetes access
* Install AWS Load Balancer Controller
* Install ArgoCD
* Register Git repository
* Create ArgoCD applications

---

## Application Deployment

Push application code changes.

The Application Pipeline will:

* Build container images
* Push images
* Update deployment manifests
* Commit changes
* Trigger GitOps deployment

ArgoCD will automatically synchronize the cluster state.

---

# Security Considerations

This platform implements:

* IAM Roles for Service Accounts (IRSA)
* Kubernetes RBAC
* GitHub Credential Management through Jenkins Credentials
* Private Subnets for Worker Nodes
* Least Privilege Access Principles

---

# Future Improvements

Planned enhancements:

* Prometheus Monitoring
* Grafana Dashboards
* Loki Log Aggregation
* KEDA Event-Driven Autoscaling
* External Secrets Operator
* HashiCorp Vault Integration
* Multi-Environment Support (Dev, Stage, Prod)
* Blue/Green Deployments
* Canary Releases

---

# Key Technologies

* Terraform
* Amazon EKS
* Kubernetes
* Jenkins
* ArgoCD
* Helm
* Docker
* GitHub Actions
* AWS Load Balancer Controller

---

# Author

Ashraf Mahmud Anjan

DevOps Engineer | Kubernetes | AWS | Terraform | Jenkins | GitOps
