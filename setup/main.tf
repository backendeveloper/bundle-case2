module "rabbitmq" {
  source = "./rabbitmq"
}

module "postgresdb" {
  source = "./postgresdb"

  db_user = "bundle"
  db_password = "bundle"
  db_name = "messagedb"
}

module "mongodb" {
  source = "./mongodb"

  db_user = "bundle"
  db_password = "bundle"
}