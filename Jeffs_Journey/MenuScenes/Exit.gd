extends Button

export (String, FILE, "*.tscn") var level_sceen

func _pressed():
	GlobalPlayerStats.reset()
	global_states.reset()
	HUD.reset()
	get_tree().change_scene(level_sceen)
	
