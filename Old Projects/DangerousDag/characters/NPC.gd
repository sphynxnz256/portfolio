extends Area2D

signal StoryBoxActive
signal StoryBoxInactive

onready var sbNode = get_node("StoryBox")

func _ready():
	pass

#functions to determine if the storybox is visible when the player is close to the NPC
func _process(delta):
	var bodies = get_overlapping_bodies()
	
	for body in bodies:
		if body.name == "Player":
			$StoryBox.visible = true
			emit_signal("StoryBoxActive")
	pass

func _on_NPC_body_shape_exited(body_id, body, body_shape, area_shape):
	if body.name == "Player":
		$StoryBox.visible = false
		emit_signal("StoryBoxInactive")
	pass
