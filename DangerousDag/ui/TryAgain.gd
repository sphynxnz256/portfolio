extends Button

export (String, FILE, "*.tscn") var level_sceen

#resets the level after the player dies if they want to try again
func _pressed():
	get_tree().change_scene(level_sceen)
	pass
