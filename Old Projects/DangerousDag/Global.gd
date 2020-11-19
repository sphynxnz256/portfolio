extends Node

var pies = 0				#global for tracking pies collected

#globals to determine if pies have already been collected
#needed to stop player killing themselves then getting another pie
var p1Collected = false	
var p2Collected = false
var p3Collected = false

func _ready():
	pies = 0
