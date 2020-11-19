extends CanvasLayer

#preload status bars
var bar_green = preload("res://assets/status_bars/barHorizontal_green_mid 200.png")
var bar_blue = preload("res://assets/status_bars/barHorizontal_blue_mid 200_edited.png")


func _ready():
	#if a herb hasn't been collected, don't show it on the HUD
	if not global_states.herb1_collected:
		$Herbs/Herb1.hide()
	if not global_states.herb2_collected:
		$Herbs/Herb2.hide()
	if not global_states.herb3_collected:
		$Herbs/Herb3.hide()


#function to update healthbar in HUD
func update_health(value):
	$Bars/NodeHealth/HealthBar2.max_value = GlobalPlayerStats.max_health
	$Bars/NodeHealth/HealthBar2.value = value
	$Bars/BarValues/health_value.text = str(value)

#function to update manabar in HUD
func update_mana(value):
	$Bars/NodeMana/ManaBar2.max_value = GlobalPlayerStats.max_mana
	$Bars/NodeMana/ManaBar2.value = value
	$Bars/BarValues/mana_value.text = str(value)
	
#function to update xpbar in HUD
func update_xp(value):
	$Bars/NodeXP/XPBar2.max_value = GlobalPlayerStats.max_player_xp
	$Bars/NodeXP/XPBar2.value = value
	$Bars/BarValues/xp_value.text = str(value)

#function to update level
func update_level(value):
	$Bars/PlayerLevel.text = "Player Level: " + str(value)
	
func show_levelup_msg():
	$LevelUpMessage.show()
	$LevelUpTimer.start()
	
func show_nomana_msg():
	$NoManaMessage.show()
	$NoManaTimer.start()

#funtions to show herbs that are collect on the HUD
func _on_Herb1_herb_collected():
	global_states.herb1_collected = true
	global_states.DialogStage = 1
	$Herbs/Herb1.show()

func _on_Herb2_herb_collected():
	global_states.herb2_collected = true
	global_states.DialogStage = 2
	$Herbs/Herb2.show()

func _on_Herb3_herb_collected():
	global_states.herb3_collected = true
	global_states.DialogStage = 4
	$Herbs/Herb3.show()


#hide the message after timer runs out
func _on_LevelUpTimer_timeout():
	$LevelUpMessage.hide()	

func _on_NoManaTimer_timeout():
	$NoManaMessage.hide()

	
func hide_all():
	$Bars.hide()
	$Herbs.hide()
	
func show_all():
	$Bars.show()
	$Herbs.show()

func reset():
	$Herbs/Herb1.hide()
	$Herbs/Herb2.hide()
	$Herbs/Herb3.hide()







