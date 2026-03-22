
variable "sg_inbound_rules" {
  type = map(object({
    description = string
    rules = list(object({
      from_port        = number
      to_port          = number
      proto            = string
      cidr_ipv4        = optional(string)
      cidr_ipv6        = optional(string)
      ref_sg_id = optional(string)
    }))
  }))
}


variable "sg_outbound_rules" {
  type = map(object({
    description = string
    rules = list(object({
      from_port        = number
      to_port          = number
      proto            = string
      cidr_ipv4        = optional(string)
      cidr_ipv6        = optional(string)
      ref_sg_id = optional(string)
    }))
  }))
}

variable "sg_names" {
  type = set(string)
}

variable "private_subnet_names" {
  type = set(string)
}