variable "db_user" {
  type        = string
  default     = "myuser"
}

variable "db_password" {
  type        = string
  sensitive   = true
  default     = "mypassword"
}