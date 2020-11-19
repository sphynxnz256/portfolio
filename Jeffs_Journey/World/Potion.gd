tool
extends Area2D

enum Potion { HEALTH, MANA }
export(Potion) var type = Potion.HEALTH

var player_scene = preload("res://Player/Player.tscn")

func _ready():
	if type == Potion.MANA:
		$AnimatedSprite.play("mana")
	else:
		$AnimatedSprite.play("health")

func _process(delta):
	if Engine.editor_hint:
		if type == Potion.MANA:
			$AnimatedSprite.play("mana")
		else:
			$AnimatedSprite.play("health")


func _on_Potion_body_entered(body):
	var bodies = get_overlapping_bodies()
	for body in bodies:
		if body.name == "Player":
			if type == Potion.MANA:
				GlobalPlayerStats.mana = min(GlobalPlayerStats.mana + 50, GlobalPlayerStats.max_mana)
				HUD.update_mana(GlobalPlayerStats.mana)
			else:
				GlobalPlayerStats.health = min(GlobalPlayerStats.health + 50, GlobalPlayerStats.max_health)
				HUD.update_health(GlobalPlayerStats.health)
			queue_free()
