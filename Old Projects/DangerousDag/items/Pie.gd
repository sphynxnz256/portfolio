extends "res://ui/MainHUD.gd"

signal pieCollected

enum Pies {ButterChicken, Mince, SteakNCheese}

var animations = ["ButterChicken", "Mince", "SteakNCheese"]

export (Pies) var type = Pies.ButterChicken

func _ready():
	$AnimatedSprite.animation = animations[type]

#handles when the player picks up the pie
func _on_Pie_body_entered(body):
	if body.name == "Player":
		emit_signal("pieCollected")
		$AnimatedSprite.hide()
		$PieCollected.play()

#removes the pie when the pick up sound ends
func _on_PieCollected_finished():
	queue_free()
