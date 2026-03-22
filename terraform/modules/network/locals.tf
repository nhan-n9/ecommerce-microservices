
locals {
  sgs_in_list = flatten([
    for sg, sg_values in var.sg_inbound_rules : [
      for rule in sg_values.rules : {
        sg_name     = sg
        from_port   = rule.from_port
        to_port     = rule.to_port
        protocol    = rule.proto
        cidr_ipv4   = rule.cidr_ipv4
        cidr_ipv6   = rule.cidr_ipv6
        ref_sg_id   = rule.ref_sg_id
        description = sg_values.description
      }
    ]
  ])

  sg_in_rules_map = {
    for idx, s in local.sgs_in_list :
    "${s.sg_name}-${idx}" => s
  }

}