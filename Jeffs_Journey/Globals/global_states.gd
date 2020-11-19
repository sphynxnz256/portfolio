extends Node

#tells the game the herbs have been picked up
var herb1_collected = false
var herb2_collected = false
var herb3_collected = false

var DialogStage = 0

#used to stop reloading the HUD
var hud_loaded = false

#used for when player wants to continue after death
var currentLevel = "res://MenuScenes/TitleScene.tscn"

#for tracking if a player returns from level2 to level1
var goingBack = false

#function to reset all states
func reset():	
	var herb1_collected = false
	var herb2_collected = false
	var herb3_collected = false
	var hud_loaded = false	
	var currentLevel = "res://MenuScenes/TitleScene.tscn"
