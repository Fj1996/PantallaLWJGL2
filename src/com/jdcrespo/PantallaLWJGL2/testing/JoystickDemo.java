package com.jdcrespo.PantallaLWJGL2.testing;

import com.jdcrespo.PantallaLWJGL2.Joystick;

public class JoystickDemo {
	
	public static void main(String[] args) {
		Joystick joystick = new Joystick();
		joystick.conecta(0);
		while(true)
		System.out.println(joystick.getX());
	}

}
