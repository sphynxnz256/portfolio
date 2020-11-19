extends Node2D

signal openGate

#used for the colored block puzzle
var sb1active = true
var sb2active = true
var sb3active = true
var sb4active = true
var sb5active = true

func _ready():
	#sets the colored block colors
	$ShootableBlocks/ShootableBlock1/Sprite.modulate = Color(1,0,0)
	$ShootableBlocks/ShootableBlock2/Sprite.modulate = Color(1,1,0)
	$ShootableBlocks/ShootableBlock3/Sprite.modulate = Color(0,1,0)
	$ShootableBlocks/ShootableBlock4/Sprite.modulate = Color(0,1,1)
	$ShootableBlocks/ShootableBlock5/Sprite.modulate = Color(0,0,1)

#when all collored blocks have been shot, signal to remove the gateblock
func _process(delta):
	if not sb1active and not sb2active and not sb3active and not sb4active and not sb5active:
		emit_signal("openGate")

#spawns a fireball when the player shoots
func _on_Player_fire(fireball, _position, _direction):
	var fb = fireball.instance()
	add_child(fb)
	fb.start(_position, _direction)

#functions for indicating to player that the colored blocks have been hit
func _on_ShootableBlock1_hit():
	$ShootableBlocks/ShootableBlock1/Sprite.modulate = Color(1,1,1)
	sb1active = false

func _on_ShootableBlock2_hit():
	$ShootableBlocks/ShootableBlock2/Sprite.modulate = Color(1,1,1)
	sb2active = false

func _on_ShootableBlock3_hit():
	$ShootableBlocks/ShootableBlock3/Sprite.modulate = Color(1,1,1)
	sb3active = false


func _on_ShootableBlock4_hit():
	$ShootableBlocks/ShootableBlock4/Sprite.modulate = Color(1,1,1)
	sb4active = false


func _on_ShootableBlock5_hit():
	$ShootableBlocks/ShootableBlock5/Sprite.modulate = Color(1,1,1)
	sb5active = false
