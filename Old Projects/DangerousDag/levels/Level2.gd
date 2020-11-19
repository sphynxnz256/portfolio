extends Node2D

signal no_shooting

var level_sceen = "res://levels/DeathScene.tscn"

func _ready():
	#stops player shooting on this level
	emit_signal("no_shooting")
	
	#if the player died after collecting the pie, despawn it so they cant collect it again
	if Global.p3Collected:
		get_node("Pie").queue_free()

#if player dies, show the death scene
func _on_Player_dead():
	get_tree().change_scene(level_sceen)
	pass
