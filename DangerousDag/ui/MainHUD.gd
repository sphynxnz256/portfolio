extends Node2D

#sets the pies collected on starting the second level
func _on_Level2_ready():
	$HUD/Pies.set_text("Pies: " + str(Global.pies))

#functions to update the pies collected on the HUD
func _on_Pie1_pieCollected():
	if not Global.p1Collected:
		Global.p1Collected = true
		Global.pies += 1
		$HUD/Pies.set_text("Pies: " + str(Global.pies))
		
func _on_Pie2_pieCollected():
	if not Global.p2Collected:
		Global.p2Collected = true
		Global.pies += 1
		$HUD/Pies.set_text("Pies: " + str(Global.pies))


func _on_Pie3_pieCollected():
	if not Global.p3Collected:
		Global.p3Collected = true
		Global.pies += 1
		$HUD/Pies.set_text("Pies: " + str(Global.pies))
