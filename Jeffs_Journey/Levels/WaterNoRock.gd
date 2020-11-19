extends StaticBody2D

#controls which movable rock is removed and lets player move onto tile
func _on_Area2D_body_entered(body):
	if body.name == "MovableRock1":
		get_node("/root/LakeLevel2/MovableRocks/MovableRock1").queue_free()
		queue_free()
		
	if body.name == "MovableRock2":
		get_node("/root/LakeLevel2/MovableRocks/MovableRock2").queue_free()
		queue_free()
		
	if body.name == "MovableRock3":
		get_node("/root/LakeLevel2/MovableRocks/MovableRock3").queue_free()
		queue_free()
