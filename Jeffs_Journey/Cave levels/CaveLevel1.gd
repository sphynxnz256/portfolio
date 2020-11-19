extends Node2D

signal blockremoved

func _ready():
	HUD.show_all()
	global_states.currentLevel = "res://Cave Levels/CaveLevel1.tscn"
	MusicController.play_cave()

func _process(delta):
	if $Torches/Torch.lighted == true and $Torches/Torch2.lighted == true and $Torches/Torch3.lighted == true:
		#$PortalAppear.play()
		emit_signal("blockremoved")
		
