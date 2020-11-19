extends KinematicBody2D

const EnemyDeathEffect = preload("res://Effect/EnemyDeathEffect.tscn")
# Reference to potion scene
var potion_scene = preload("res://World/Potion.tscn")

# Random number generator
var rng = RandomNumberGenerator.new()

export var ACCELERATION = 300
export var MAX_SPEED = 50
export var FRICTION = 200
export var WANDER_TARGET_RANGE = 4
export var XP_VALUE = 20
export var DROP_RATE = 0.8


enum{
	IDLE,
	WANDER,
	CHASE
}

var velocity = Vector2.ZERO
var knockback = Vector2.ZERO
var state = CHASE

onready var sprite = $AnimatedSprite
onready var stats = $Stats
onready var playerDetectionZone = $PlayerDetectionZone
onready var hurtbox = $Hurtbox
onready var softCollision = $SoftCollision
onready var wanderController = $WanderController
onready var animationPlayer = $AnimationPlayer

#at the start of scene, the enemy will randomly idle or wander
func _ready():
	state = pick_random_state([IDLE, WANDER])
	get_node("HPBar").value = int(float($Stats.health) / $Stats.max_health * 100)
	
	rng.randomize()

func _physics_process(delta):
	#when enemy get hurted it will knock back for a little distance
	knockback = knockback.move_toward(Vector2.ZERO, 200 * delta)
	knockback = move_and_slide(knockback)
	
	match state:
		IDLE:
			velocity = velocity.move_toward(Vector2.ZERO, 200 * delta)
			seek_player()
			if wanderController.get_time_left() == 0:
				update_wander()
				
		WANDER:
			seek_player()
			if wanderController.get_time_left() == 0:
				update_wander()
				
			accelerate_towards_point(wanderController.target_position, delta)
			
			if global_position.distance_to(wanderController.target_position) <= WANDER_TARGET_RANGE:
				update_wander()
				
		CHASE:
			var player = playerDetectionZone.player
			if player != null:
				accelerate_towards_point(player.global_position, delta)
			else:
				state = IDLE
			
	if softCollision.is_colliding():
		velocity += softCollision.get_push_vector() * delta * 400
	velocity = move_and_slide(velocity)

func accelerate_towards_point(point, delta):
	var direction = global_position.direction_to(point)
	velocity = velocity.move_toward(direction * MAX_SPEED, ACCELERATION * delta)
	sprite.flip_h = velocity.x < 0

func update_wander():
	state = pick_random_state([IDLE, WANDER])
	wanderController.start_wander_timer(rand_range(1, 3))

#if player is in the detect area of enemy, it will come to player and attack
func seek_player():
	if playerDetectionZone.can_see_player():
		state = CHASE

func pick_random_state(state_list):
	state_list.shuffle()
	return state_list.pop_front()

func _on_Hurtbox_area_entered(area):
	stats.health -= area.damage
	get_node("HPBar").value = int(float($Stats.health) / $Stats.max_health * 100)
	knockback = area.knockback_vector * 120
	hurtbox.create_hit_effect()
	hurtbox.start_invincibility(0.4) #add the blink effect interval to the enemies when hitted

#when enemy's health go to zero, it disappeared and show a death animation
func _on_Stats_no_health():
	GlobalPlayerStats.increase_xp(XP_VALUE)
	queue_free()
	var enemyDeathEffect = EnemyDeathEffect.instance()
	get_parent().add_child(enemyDeathEffect)
	enemyDeathEffect.global_position = global_position
	
	# 80% probability to drop a potion on death
	if rng.randf() <= DROP_RATE:
		var potion = potion_scene.instance()
		potion.type = rng.randi() % 2
		#get_tree().root.get_node("Level1")
		get_parent().call_deferred("add_child", potion)
		potion.position = position

#add the blink effect to the enemies when hitted
func _on_Hurtbox_invincibility_started():
	animationPlayer.play("Start")

func _on_Hurtbox_invincibility_ended():
	animationPlayer.play("Stop")
