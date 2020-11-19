extends Panel

export (String, FILE, "*.txt") var story_line_file

onready var cbNode = get_node("ChatBox") # to add our story

# Called when the node enters the scene tree for the first time.
func _ready():
	cbNode._loadDialogFromFile(story_line_file)
	pass # Replace with function body.
