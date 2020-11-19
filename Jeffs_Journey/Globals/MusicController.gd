extends Node

var ForestBGM = load("res://Music and SFX/voyage (forest).ogg")
var LakeBGM = load("res://Music and SFX/field (lake).ogg")
var MenuBGM = load("res://Music and SFX/main-theme01.ogg")
var CaveBGM = load("res://Music and SFX/Dungeon-Cave.ogg")

func play_forest():
	$Music.stream = ForestBGM
	$Music.play()
	
func play_lake():
	$Music.stream = LakeBGM
	$Music.play()

func play_menu():
	$Music.stream = MenuBGM
	$Music.play()

func play_cave():
	$Music.stream = CaveBGM
	$Music.play()
