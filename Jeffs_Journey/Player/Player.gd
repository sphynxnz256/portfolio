extends KinematicBody2D

const PlayerHertSound = preload("res://Player/PlayerHurtSound.tscn")

#signal player_stats_changed

#fireball variable
var fireball_scene = preload("res://Spell/Fireball.tscn")
var fireball_damage = 20
var fireball_cooldown_time = 1000
var next_fireball_time = 0
var not_enough_mana = false
var spell_cost = 30

#some player data which can input in godot
export var ACCELERATION = 500
export var MAX_SPEED = 100
export var ROLL_SPEED = 140
export var FRICTION = 600

var velocity = Vector2.ZERO
#var roll_vector = Vector2.DOWN
#var stats = PlayerStats
var input_vector = Vector2.ZERO
var last_direction = Vector2(0, 1)
var attack_playing = false

var next_attack_time = 0
var attack_cooldown_time = 500
var attack_damage = 30

## Player stats
#var health = 100
#var health_max = 100
#var health_regeneration = 1
#var mana = 100
#var mana_max = 100
#var mana_regeneration = 2

onready var animationPlayer = $AnimationPlayer
onready var swordHitbox = $HitboxPivot/SwordHitbox
onready var hurtbox = $Hurtbox
onready var blinkAnimationPlayer = $BlinkAnimationPlayer #get BlinkAnimationPlayer

func _ready():
	randomize()
	GlobalPlayerStats.connect("no_health", self, "queue_free")
	#animationTree.active = true # active the animation tree
	swordHitbox.knockback_vector = Vector2.DOWN
	
	#GlobalPlayerStats.connect("no_mana", self, "no_mana")

#func _process(delta):
	#SkillLoop()

#func no_mana():
#	not_enough_mana = true
#	HUD.show_nomana_msg()

#func _process(delta):
#	# Regenerates mana
#	var new_mana = min(mana + mana_regeneration * delta, mana_max)
#	if new_mana != mana:
#		mana = new_mana
#		emit_signal("player_stats_changed", self)
#
#	# Regenerates health
#	var new_health = min(health + health_regeneration * delta, health_max)
#	if new_health != health:
#		health = new_health
#		emit_signal("player_stats_changed", self)

func _physics_process(delta):
	# player movement
	input_vector = Vector2.ZERO
	input_vector.x = Input.get_action_strength("ui_right") - Input.get_action_strength("ui_left")
	input_vector.y = Input.get_action_strength("ui_down") - Input.get_action_strength("ui_up")
	input_vector = input_vector.normalized()
	
	if not attack_playing:
		animates_player(input_vector, delta)
	
	if attack_playing:
		velocity = 0.3 * velocity
	move()

func move():
	velocity = move_and_slide(velocity)
		
func animates_player(direction: Vector2, delta):
	if direction != Vector2.ZERO:
		swordHitbox.knockback_vector = input_vector
		# update last_direction
		last_direction = 0.5 * last_direction + 0.5 * direction
		
		# Choose walk animation based on movement direction
		var animation = "Run" + get_animation_direction(last_direction)

		# Play the walk animation
		animationPlayer.play(animation)
		velocity = velocity.move_toward(input_vector * MAX_SPEED, ACCELERATION * delta)
	else:
		# Choose idle animation based on last movement direction and play it
		var animation = "Idle" + get_animation_direction(last_direction)

		animationPlayer.play(animation)
		velocity = velocity.move_toward(Vector2.ZERO, FRICTION * delta)

func get_animation_direction(direction: Vector2):
	var norm_direction = direction.normalized()
	if norm_direction.y >= 0.707:
		return "Down"
	elif norm_direction.y <= -0.707:
		return "Up"
	elif norm_direction.x <= -0.707:
		return "Left"
	elif norm_direction.x >= 0.707:
		return "Right"
	return "Down"

func _input(event):
	if event.is_action_pressed("attack"):
		# Check if player can attack
		var now = OS.get_ticks_msec()
		if now >= next_attack_time:
			# Play attack animation
			attack_playing = true
			var animation = "Attack" + get_animation_direction(last_direction)
			animationPlayer.play(animation)
			# Add cooldown time to current time
			next_attack_time = now + attack_cooldown_time
	elif event.is_action_pressed("shoot"):
		var now = OS.get_ticks_msec()
		if GlobalPlayerStats.mana >= spell_cost and now >= next_fireball_time:
				# Update mana
			GlobalPlayerStats.spend_mana(spell_cost)
			#GlobalPlayerStats.mana = GlobalPlayerStats.mana - spell_cost
			#emit_signal("player_stats_changed", self)
				# Play fireball animation
			attack_playing = true
			var animation = get_animation_direction(last_direction) + "Shoot"
			animationPlayer.play(animation)
			# Add cooldown time to current time
			next_fireball_time = now + fireball_cooldown_time
			
	
func _on_Shoot_animation_finished():
	attack_playing = false
	#add shoot sound
	$AudioStreamPlayerShoot.play()
	
	#if animationPlayer.current_animation.ends_with("Shoot"):
		# Instantiate Fireball
	var fireball = fireball_scene.instance()
	fireball.attack_damage = fireball_damage
	fireball.direction = last_direction.normalized()
	fireball.position = position + last_direction.normalized() * 4
	get_parent().add_child(fireball)

func _on_Attack_animation_finished():
	attack_playing = false

# when player get hurted reduce health, give 0.5 seconds invincibility, add hit effect
# add sound 
func _on_Hurtbox_area_entered(area):
	GlobalPlayerStats.take_damage(area.damage)
	#stats.health -= area.damage #change damage 1 to specific damage
	hurtbox.start_invincibility(0.5)
	hurtbox.create_hit_effect()
	var playerHurtSound = PlayerHertSound.instance()
	get_tree().current_scene.add_child(playerHurtSound)

#when player get hurted player become blink to white black
func _on_Hurtbox_invincibility_started():
	blinkAnimationPlayer.play("Start")

func _on_Hurtbox_invincibility_ended():
	blinkAnimationPlayer.play("Stop")
