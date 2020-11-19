extends Node2D

signal no_shooting

#disables shooting in the intro scene
func _ready():
	emit_signal("no_shooting")
	pass
