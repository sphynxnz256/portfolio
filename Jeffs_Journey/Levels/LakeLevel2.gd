extends Node2D

func _ready():
	HUD.show_all()
	#if herb2 has already been collected, remove from the level, set
	#puzzle as complete and show the portal
	if global_states.herb2_collected:
		$Herb2.queue_free()
		$MovableRocks.queue_free()
		$SpecialWater/WaterNoRock.queue_free()
		$Portal.visible = true
		global_states.currentLevel = "res://Levels/LakeLevel2.tscn"
		
	#tracks level player is on
	global_states.currentLevel = "res://Levels/LakeLevel2.tscn"
