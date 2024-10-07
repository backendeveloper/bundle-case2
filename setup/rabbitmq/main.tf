resource "docker_image" "rabbitmq" {
  name         = "rabbitmq:3-management"
}

resource "docker_container" "rabbitmq" {
  image = docker_image.rabbitmq.image_id
  name  = "rabbitmq"
  ports {
    internal = 5672
    external = 5672
  }
  ports {
    internal = 15672
    external = 15672
  }
  env = [
    "RABBITMQ_DEFAULT_USER=bundle",
    "RABBITMQ_DEFAULT_PASS=bundle"
  ]
}