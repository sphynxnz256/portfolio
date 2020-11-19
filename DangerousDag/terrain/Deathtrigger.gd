extends Area2D

#function to drown player and kill them when the moving water covers them
func _on_Deathtrigger_body_entered(body):
	if body.has_method('death'):
		body.death()
