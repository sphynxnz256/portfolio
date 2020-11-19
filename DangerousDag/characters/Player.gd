extends KinematicBody2D

export(PackedScene) var fireball

signal dead
signal fire

const UP = Vector2(0, -1)
const SPEED = 200
const GRAVITY = 20
const JUMP_HEIGHT = -500
var motion = Vector2() # moving in 2d space
var can_shoot = true
var no_shoot_level = false
var walk_sound_playing = false

func _physics_process(delta):
	motion.y += GRAVITY
	#makes the fire direction point where the players cursor is
	$FireDirection.look_at(get_global_mouse_position())
	
	#stops the walking sound if the player isnt pressing the movement keys
	if Input.is_action_just_released("ui_right") or Input.is_action_just_released("ui_left"):
		$Walking.stop()
		walk_sound_playing = false
	
	#handles player moving right
	if Input.is_action_pressed("ui_right"):
		$AnimatedSprite.flip_h = false #flip sprite to face direction
		$AnimatedSprite.play("run")
		if not walk_sound_playing and is_on_floor():
			$Walking.play()
			walk_sound_playing = true
		motion.x = SPEED
		
	#handles player moving left
	elif Input.is_action_pressed("ui_left"):
		$AnimatedSprite.flip_h = true #flip sprite to face direction
		$AnimatedSprite.play("run")
		if not walk_sound_playing and is_on_floor():
			$Walking.play()
			walk_sound_playing = true
		motion.x = -SPEED
	#if the player isnt moving
	else:
		motion.x = 0
		$AnimatedSprite.play("idle")
	
	#handles player jumping
	if is_on_floor():
		if Input.is_action_pressed("ui_up"):
			motion.y = JUMP_HEIGHT
	else:
		if motion.y < 0:
			$AnimatedSprite.play("jump")
		else:
			$AnimatedSprite.play("fall")
				
	# use move_and_slide to create movement on screen
	motion = move_and_slide(motion, UP)
	
	#runs the fire function when player left clicks
	if Input.is_action_pressed("fire"):
		fire()

#handles player shooting
func fire():
	if can_shoot and not no_shoot_level:
		can_shoot = false
		$FireCD.start()
		#$AnimatedSprite.play("fire") animation doesnt work as intended :(
		$FireballShoot.play()
		var dir = Vector2(1, 0).rotated($FireDirection.global_rotation)
		emit_signal("fire", fireball, $FireDirection/Position2D.global_position, dir)

#handles player death
func death():
	queue_free()
	emit_signal("dead")

#stops player from infinite fireballs
func _on_FireCD_timeout():
		can_shoot = true

#stops player shooting if they are viewing story box
func _on_StoryBoxActive():
	can_shoot = false

#lets player shoot again after viewing story box
func _on_StoryBoxInactive():
		can_shoot = true

#if the player shouldnt be shooting on a level
func _on_no_shooting():
	no_shoot_level = true

#used to stop the walking sound infinately playing over its self
func _on_Walking_finished():
	walk_sound_playing = false
