extends KinematicBody2D

const SPEED = 100
var motion = Vector2() # moving in 2d space

var bodyEntered = false

#controls the movement of the block when player uses interact key and is close enough
func _physics_process(delta):
	
	if Input.is_action_pressed("ui_right") && Input.is_action_pressed("interact") && bodyEntered == true:
		motion.x = SPEED
	elif Input.is_action_pressed("ui_left") && Input.is_action_pressed("interact") && bodyEntered == true:
		motion.x = -SPEED
	elif Input.is_action_pressed("ui_up") && Input.is_action_pressed("interact") && bodyEntered == true:
		motion.y = -SPEED
	elif Input.is_action_pressed("ui_down") && Input.is_action_pressed("interact") && bodyEntered == true:
		motion.y = SPEED
	else:
		motion.x = 0
		motion.y = 0
		
	motion = move_and_slide(motion)

#functions to determine if the player is close enough to interact with the rock
func _on_PickupArea_body_entered(body):
	if body.name == "Player":
		bodyEntered = true
		get_node("/root/LakeLevel2/puzzleHint").visible = true

func _on_PickupArea_body_exited(body):
	if body.name == "Player":
		bodyEntered = false
		get_node("/root/LakeLevel2/puzzleHint").visible = false

