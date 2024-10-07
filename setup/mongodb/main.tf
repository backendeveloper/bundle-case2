resource "docker_image" "mongodb" {
  name = "mongo:latest"
  keep_locally = false
}

resource "docker_volume" "mongodata" {
  name = "mongodata"
}

resource "docker_container" "mongodb" {
  name  = "mongo-db"
  image = docker_image.mongodb.image_id

  ports {
    internal = 27017
    external = 27017
  }

  # env = [
  #   "MONGO_INITDB_ROOT_USERNAME=${var.db_user}",
  #   "MONGO_INITDB_ROOT_PASSWORD=${var.db_password}"
  # ]

  volumes {
    host_path      = "${path.cwd}/mongodb/mongo"
    container_path = "/data/db"
  }

  volumes {
    host_path      = "${path.cwd}/mongodb/initdb"
    container_path = "/docker-entrypoint-initdb.d"
  }

  restart = "unless-stopped"

  depends_on = [docker_volume.mongodata]
}