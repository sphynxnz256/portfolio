extends Area2D
onready var d = get_node("StoryBox")


func _ready():
	pass

func _process(delta):
	var bodies = get_overlapping_bodies()
	
	for body in bodies:
		if body.name == "Player":
			get_node("/root/JadeCastle/HUD/StoryBox").visible = true
	pass
