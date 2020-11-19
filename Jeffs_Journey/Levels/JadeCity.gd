extends Node2D

func _ready():
	HUD.show_all()
	get_node("/root/MusicController/Music").stop()
	#$Portal.play()
