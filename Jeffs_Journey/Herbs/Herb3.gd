extends Area2D

export(String, FILE, "*.tscn") var next_world

func _physics_process(delta):
	var bodies = get_overlapping_bodies()
	for body in bodies:
		if body.name == "Player":
			HUD._on_Herb3_herb_collected()
			get_tree().change_scene(next_world)
			queue_free()
