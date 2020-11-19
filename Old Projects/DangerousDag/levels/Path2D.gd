extends Path2D

export var speed = 4.0
onready var follow = get_node("PathFollow2D")
var tween
var pas

# Called when the node enters the scene tree for the first time.
func _ready():
	set_process(true)
	tween = Tween.new()
	add_child(tween)
	tween.interpolate_property(follow, "unit_offset", 0, 1, speed, 
								tween.TRANS_LINEAR, tween.EASE_IN_OUT)
	tween.set_repeat(false)
	tween.start()

# Called every frame. 'delta' is the elapsed time since the previous frame.
func _process(delta):
	#follow.set_offset(follow.get_offset() + speed * delta)
	pass

#pauses the movement of the moving water when the player is viewing the story box
func _on_StoryBoxActive():
	tween.set_active(false)

func _on_StoryBoxInactive():
	tween.set_active(true)
