extends Area2D

func _on_Teleport3_body_entered(body):
	if body.name == "Player":
		get_tree().change_scene("res://Cave levels/CaveLevel1.tscn")
	pass # Replace with function body.
