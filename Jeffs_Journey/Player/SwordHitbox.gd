extends "res://Overlap/Hitbox.gd"

var knockback_vector = Vector2.ZERO

#function will overide the hitbox damage with the correct damage for players level
func _ready():
	damage = GlobalPlayerStats.sword_damage
