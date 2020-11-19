extends Node2D

signal blockremoved

func _ready():
	HUD.show_all()
	global_states.currentLevel = "res://Cave Levels/CaveLevel2.tscn"

func _process(delta):
	if $Torches/Torch.lighted == true and $Torches/Torch2.lighted == true and $Torches/Torch3.lighted == true and $Torches/Torch4.lighted == true and $Torches/Torch5.lighted == true:
		emit_signal("blockremoved")
