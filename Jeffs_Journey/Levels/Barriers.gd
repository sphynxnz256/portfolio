extends Node2D

var HerbStage

func _ready():
	pass

func _process(delta):
	if global_states.herb1_collected == true:
		$Barrier01.queue_free()
	if global_states.herb2_collected == true:
		$Barrier02.queue_free()
	pass
