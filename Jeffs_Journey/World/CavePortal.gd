extends Area2D

export(String, FILE, "*.tscn") var next_world

#func _process(delta):
#	var torch = torch_scene.instance()
#	if torch.sig_num == 0:
#		sprite.play("show")

func _physics_process(delta):
	var bodies = get_overlapping_bodies()
	for body in bodies:
		if body.name == "Player":
			get_tree().change_scene(next_world)
