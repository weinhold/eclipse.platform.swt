package org.eclipse.swt.effects;


//WPF additionally has EdgeProfile and Relief
//Flex additionally has highlightColor, highlightAlpha, shadowColor, shadowAlpha, blurX, blurY, quality, type and knockout
public class BevelEffect extends Effect {
	void createHandle() {
	}
	
	//WPF: LightAngle
	//Flex: angle
	public void setAngle(double angle) {
		checkEffect();
	}
	
	//WPF: Smoothness
	//Flex: Strength (value 0-255)
	public void setSmoothness(double smoothness) {
		checkEffect();
	}
	
	//WPF: BevelWidth
	//Flex: distance
	public void setWidth(double width) {
		checkEffect();
	}
}
