extends StaticBody2D

signal hit

#sends a signal for the level to then handle what happens to the block when hit
func hit():
	emit_signal("hit")
