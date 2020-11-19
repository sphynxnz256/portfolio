extends Area2D

signal StoryBoxActive
signal StoryBoxInactive

onready var sbNode = get_node("StoryBox")

func _ready():
	pass
	
#shows story box when player enters the trigger area
func _process(delta):
	var bodies = get_overlapping_bodies()
	
	for body in bodies:
		if body.name == "Player":
			$StoryBox.visible = true
			emit_signal("StoryBoxActive")
	pass

#hides story box when player exits the trigger area
func _on_Instructions1_body_shape_exited(body_id, body, body_shape, area_shape):
	if body.name == "Player":
		$StoryBox.visible = false
		emit_signal("StoryBoxInactive")
	pass
