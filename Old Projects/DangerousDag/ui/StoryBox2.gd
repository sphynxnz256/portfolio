extends Panel

export (String, FILE, "*.txt") var story_line_file
export (Texture) var person

onready var cbNode = get_node("ChatBox") # to add our story
onready var pNode = get_node("Person") # to add a person spite

func _ready():
	cbNode._loadDialogFromFile(story_line_file)
	pNode.texture = person
	pass
