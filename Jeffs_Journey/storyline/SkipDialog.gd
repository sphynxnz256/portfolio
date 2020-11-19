extends Button

var nextScene #scene to skip to

# Custom method called by StoryBox parent
func _loadSkipToScene(scene):
	nextScene = scene
	pass
	
# Button press action when pressed
func _pressed():
	get_tree().change_scene(nextScene)
	pass
