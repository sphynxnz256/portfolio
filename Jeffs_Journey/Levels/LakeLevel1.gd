extends Node2D

func _ready():
	HUD.show_all()
	#tracks level player is on
	global_states.currentLevel = "res://Levels/LakeLevel1.tscn"
	MusicController.play_lake() #play BGM
	#if player came from level2
	if global_states.goingBack:
		global_states.goingBack = false
		$Player.position = Vector2(192, 20)
