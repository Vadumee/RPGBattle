package Boutons;

import javax.swing.JButton;

import Game.IntegerField;

public class ButtonCardExp extends JButton{
	
	public IntegerField field;
	
	public ButtonCardExp(String s,IntegerField i) {
		super(s);
		this.field = i;
	}
}
