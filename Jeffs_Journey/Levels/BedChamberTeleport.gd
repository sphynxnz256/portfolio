extends Area2D

#var isAlive = weakref()

#func _process(delta):
#	if !isAlive.get_ref():
#		$CollisionShape2D.disabled = true

func _on_BedChamberTeleport_body_entered(body):
	if body.name == "Player" :
		get_tree().change_scene("res://Levels/BedChamber.tscn")
	pass # Replace with function body.
