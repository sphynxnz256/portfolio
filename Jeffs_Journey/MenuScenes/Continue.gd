extends Button

func _pressed():
	HUD.update_health(GlobalPlayerStats.max_health)	
	GlobalPlayerStats.health = GlobalPlayerStats.max_health
	get_tree().change_scene(global_states.currentLevel)	
