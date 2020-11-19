extends Node2D

#shows or hides the hint when the player is close enough to the rock
func _on_MovableRock_hide_hint():
	visible = false

func _on_MovableRock_show_hint():
	visible = true
