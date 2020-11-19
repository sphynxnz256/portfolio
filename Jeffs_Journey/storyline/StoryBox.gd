extends Panel

var SkipDialog = "res://Levels/JadeCity.tscn"
var StoryFile
var stage = global_states.DialogStage
var person = load("res://assets/sprites/Male/Male 07-1.png")
var personName = "Old Man Jenkins"


onready var stsNode = get_node("SkipDialog") # to skip dialog
onready var cbNode = get_node("ChatBox") # to add our story
onready var pNode = get_node("Person") # to add a person sprite

# Called when the node enters the scene tree for the first time.
func _ready():
	if stage == 0:
		StoryFile = "res://storyline/textfiles/intro.txt"
	elif stage == 1:
		StoryFile = "res://storyline/textfiles/herb1.txt"
	elif stage == 2:
		StoryFile = "res://storyline/textfiles/herb2.txt"
	elif stage == 3:
		person = load("res://assets/sprites/Male/Male 09-2.png")
		personName = "Prince Julius"
		StoryFile = "res://storyline/textfiles/herb3.txt"
	elif stage == 4:
		StoryFile = "res://storyline/textfiles/outro.txt"

	$Person.texture = person
	$PersonName.text = personName
	stsNode._loadSkipToScene(SkipDialog)
	cbNode._loadDialogFromFile(StoryFile)
	
	pass # Replace with function body.
