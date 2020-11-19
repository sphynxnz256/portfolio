extends Node2D

func _process(delta):
	pass

#when recieving the open gate signal remove the gate so player can get through
func _on_Level1_openGate():
	queue_free()
