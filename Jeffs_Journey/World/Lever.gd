extends StaticBody2D

var switch_off = true
var body_entered = false

func _ready():
	pass # Replace with function body.

func _on_Lever_body_entered(body):
	if body.name == "Player":
		body_entered = true

func _physics_process(delta):
	if body_entered && Input.is_action_pressed("interact"):
		$FlipOff.hide()
		$FlipOn.show()
		
		$LeverSfx.play()
		if switch_off == true:
			get_node("/root/ForestLevel2/YSort/Hedge/DoorwayHedge").queue_free()
			switch_off = false

func _on_Lever_body_exited(body):
	if body.name == "Player":
		body_entered = false
