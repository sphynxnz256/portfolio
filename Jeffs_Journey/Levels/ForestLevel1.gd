extends Node2D

func _ready():
	HUD.show_all() #show HUD
	global_states.currentLevel = "res://Levels/ForestLevel1.tscn" #sets current state to current level
	MusicController.play_forest() #playmusic
	
	#if player came back from level 2
	if global_states.goingBack:
		global_states.goingBack = false
		#$Player.position = Vector2(192, 20)
