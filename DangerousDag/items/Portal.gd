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
			#Global.goto_scene(level_sceen)
			get_tree().change_scene(level_sceen)
			
	pass
