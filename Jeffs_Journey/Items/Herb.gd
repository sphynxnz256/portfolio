tool
extends Area2D

signal herb_collected

#var sprites = ["res://icon.png", "res://icon.png", "res://icon.png"] #will enable when sprites are added.

enum Herbs {herb1, herb2, herb3}

#use this to select which herb this is. Will be more useful once we have
#different sprites for the herbs.
export (Herbs) var type = Herbs.herb1

func _ready():
	if type == Herbs.herb3:
		$Sprite.play("herb3")
	elif type == Herbs.herb2:
		$Sprite.play("herb2")
	else:
		$Sprite.play("herb1")

func _process(delta):
	if Engine.editor_hint:
		if type == Herbs.herb3:
			$Sprite.play("herb3")
		elif type == Herbs.herb2:
			$Sprite.play("herb2")
		else:
			$Sprite.play("herb1")

	#$Sprite.texture = sprites[type] #will enable when sprites are added.
	pass


#sends signal to tell HUD and global states that the herb has been picked up	
func _on_Herb_body_entered(body):
	if body.name == "Player":
		emit_signal("herb_collected")
		queue_free()

