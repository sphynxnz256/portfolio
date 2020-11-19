extends Control

export (String, FILE, "*.tscn") var level_sceen

func _ready():
	HUD.hide_all()

#when player left clicks on screen returns to level_sceen
func _input(event):
	if event.is_action_pressed('attack'):
		get_tree().change_scene(level_sceen)
