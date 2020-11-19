extends Area2D

onready var sprite = $Sprite

var lighted = false

func _on_Torch_area_entered(area):
	if area.name == "Fireball":
		sprite.play("light")
		lighted = true
		
