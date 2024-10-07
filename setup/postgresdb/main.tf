resource "docker_image" "postgres" {
  name = "postgres:latest"
  keep_locally = true
}

resource "docker_volume" "pgdata" {
  name = "pgdata"
}

resource "docker_container" "postgres" {
  name  = "postgres-db"
  image = docker_image.postgres.image_id
  ports {
    internal = 5432
    external = 5432
  }

  env = [
    "POSTGRES_USER=${var.db_user}",
    "POSTGRES_PASSWORD=${var.db_password}",
    "POSTGRES_DB=${var.db_name}"
  ]

  volumes {
    host_path      = "${path.cwd}/postgresdb/pgdata"
    container_path = "/var/lib/postgresql/data"
  }

  volumes {
    host_path      = "${path.cwd}/postgresdb/init-sql"
    container_path = "/docker-entrypoint-initdb.d"
  }

  healthcheck {
    test = ["CMD-SHELL", "pg_isready -U postgres"]
    interval = "10s"
    timeout  = "5s"
    retries  = 5
  }

  restart = "unless-stopped"

  depends_on = [docker_volume.pgdata]
}