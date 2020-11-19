extends Area2D

export (String, FILE, "*.tscn") var level_sceen

func _ready():
	pass

func _process(delta):
	#Get all bodies on the current scene
	var bodies = get_overlapping_bodies()
	
	#Check for player body if it overlaps
	for body in bodies:
		if body.name == "Player" && self.visible:
			#gets the new scene the player is moving to
			get_tree().change_scene(level_sceen)


func _on_Herb2_herb_collected():
	get_node("/root/LakeLevel2/Portal").visible = true


func _on_Herb1_herb_collected():
	get_node("/root/ForestLevel2/Portal").visible = true


func _on_Herb3_herb_collected():
	get_node("/root/CaveLevel3/Portal").visible = true
