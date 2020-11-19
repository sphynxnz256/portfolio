extends Node

var max_health = 100
var max_mana = 100
var max_player_xp = 100
var player_level = 1
var player_xp = 0
var health = max_health
var mana = max_mana
var sword_damage = 5
var spell_damage = 10

signal no_health
signal no_mana

#function to calc new health after taking damage
func take_damage(value):
	health -= value
	HUD.update_health(health)
	if health <= 0:
		get_tree().change_scene("res://MenuScenes/DeathScene.tscn")
		#emit_signal("no_health")

#function to set initial values		
func _ready():
	if not global_states.hud_loaded:
		global_states.hud_loaded = true
		HUD.update_health(max_health)
		HUD.update_mana(max_mana)
		HUD.update_xp(player_xp)
		HUD.update_level(player_level)

#function to calc new mana after casting	
func spend_mana(value):
	#if there is enough mana let the spell cast and remove the mana
	if mana - value >= 0:
		mana -= value
		HUD.update_mana(mana)
	#else if not enough mana let the player know and dont cast
	else:
		emit_signal("no_mana")

#function controls players xp and level up	
func increase_xp(value):
	player_xp += value
	while player_xp >= max_player_xp && player_level != 10:
		player_level += 1			
		HUD.update_level(player_level)
		if player_level == 10:
			player_xp = max_player_xp
		else:
			player_xp -= max_player_xp 
			max_player_xp = 100 * player_level
			max_health = 100 * player_level
			health = max_health
			max_mana += 50
			mana = max_mana
			sword_damage = 5 * player_level
			spell_damage = 10 * player_level
			HUD.update_health(max_health)
			HUD.update_mana(max_mana)
			HUD.show_levelup_msg()
	HUD.update_xp(player_xp)

#function to reset stats if player desides to exit game and start over	
func reset():	
	max_health = 100
	max_mana = 100
	max_player_xp = 100
	player_level = 1
	player_xp = 0
	health = max_health
	mana = max_mana
	sword_damage = 5
	spell_damage = 10
	HUD.update_health(health)
	HUD.update_level(player_level)
	HUD.update_mana(mana)
	HUD.update_xp(player_xp)
