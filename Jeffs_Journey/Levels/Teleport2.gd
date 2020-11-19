extends Area2D

func _on_Teleport2_body_entered(body):
	if body.name == "Player":
		get_tree().change_scene("res://Levels/LakeLevel1.tscn")
	pass # Replace with function body.
