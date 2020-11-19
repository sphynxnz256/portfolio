extends Area2D

func _on_Teleport1_body_entered(body):
	if body.name == "Player":
		get_tree().change_scene("res://Levels/ForestLevel1.tscn")
	pass # Replace with function body.
