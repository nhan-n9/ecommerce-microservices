resource "aws_db_instance" "tf_db" {
  allocated_storage    = 10
  db_name              = "product_db"
  engine               = "postgres"
  engine_version       = "17.6"
  instance_class       = "db.t4g.micro"
  username             = "postgres"
  password             = "postgres"
  parameter_group_name = "default.postgres17"
  skip_final_snapshot  = true
}