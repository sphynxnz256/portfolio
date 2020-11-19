extends KinematicBody2D

const UP = Vector2(0, -1)
const SPEED = 200
const GRAVITY = 20
var motion = Vector2() # moving in 2d space

var bodyEntered = false

#controls the movement of the block when player right clicks and is close enough
func _physics_process(delta):
	motion.y += GRAVITY
	
	if Input.is_action_pressed("ui_right") && Input.is_action_pressed("interact") && bodyEntered == true:
		motion.x = SPEED
	elif Input.is_action_pressed("ui_left") && Input.is_action_pressed("interact") && bodyEntered == true:
		motion.x = -SPEED
	else:
		motion.x = 0
		
	motion = move_and_slide(motion, UP)

#functions to determine if the player is close enough to interact with the block
func _on_Area2D_body_entered(body):
	if body.name == "Player":
		bodyEntered = true
	pass

func _on_Area2D_body_exited(body):
	if body.name == "Player":
		bodyEntered = false
	pass
