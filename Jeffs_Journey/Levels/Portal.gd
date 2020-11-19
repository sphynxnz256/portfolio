extends Area2D

export (String, FILE, "*.tscn") var level_sceen

func _ready():
	pass

func _process(delta):
	#Get all bodies on the current scene
	var bodies = get_overlapping_bodies()
	
	#Check for player body if it overlaps
	for body in bodies:
		if body.name == "Player":
			#gets the new scene the player is moving to
			get_tree().change_scene(level_sceen)
