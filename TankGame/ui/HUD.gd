extends CanvasLayer

var bar_red = preload("res://assets/UI/barHorizontal_red_mid 200.png")
var bar_green = preload("res://assets/UI/barHorizontal_green_mid 200.png")
var bar_yellow = preload ("res://assets/UI/barHorizontal_yellow_mid 200.png")
var bar_texture

onready var HealthBarTween = $Margin/Container/HeathBar/Tween

func update_ammo(value):
	$Margin/Container/AmmoGauge.value = value
	

func update_healthbar(value):
	bar_texture = bar_green
	if value < 60:
		bar_texture = bar_yellow
	if value < 25:
		bar_texture = bar_red
	$Margin/Container/HeathBar.texture_progress = bar_texture
	$Margin/Container/HeathBar/Tween.interpolate_property($Margin/Container/HeathBar,
			"value", $Margin/Container/HeathBar.value, value,
			0.2, Tween.TRANS_LINEAR, Tween.EASE_IN_OUT)
	$Margin/Container/HeathBar/Tween.start()
	$AnimationPlayer.play("healthbar_flash")

func _on_AnimationPlayer_animation_finished(anim_name):
	if anim_name == "healthbar_flash":
		$Margin/Container/HeathBar.texture_progress = bar_texture
