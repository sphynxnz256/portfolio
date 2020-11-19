extends Area2D

signal hit

export (int) var speed

var velocity = Vector2()
var hit = false

#this determines which direction and speed the fireball goes
func start(_position, _direction):
	position = _position
	rotation = _direction.angle()
	velocity = _direction * speed
	
#this updates the fireballs location
func _process(delta):
	position += velocity * delta

#function to remove fireball when it hits bodies
func _on_Fireball_body_entered(body):
	if not hit:
		hit = true
		$AnimatedSprite.hide()
		$FireballHit.play()
		#this is currently for the colored block puzzle
		if body.has_method('hit'):
			body.hit()

func _on_FireballHit_finished():
	queue_free()
