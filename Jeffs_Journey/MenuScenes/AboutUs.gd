extends Button

export (String, FILE, "*.tscn") var level_sceen

func _pressed():
	get_tree().change_scene(level_sceen)
