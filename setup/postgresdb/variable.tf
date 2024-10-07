variable "db_user" {
  type        = string
  default     = "myuser"
}

variable "db_password" {
  type        = string
  sensitive   = true
  default     = "mypassword"
}

variable "db_name" {
  type        = string
  default     = "mydatabase"
}