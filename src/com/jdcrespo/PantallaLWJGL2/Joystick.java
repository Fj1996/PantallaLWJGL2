package com.jdcrespo.PantallaLWJGL2;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Controller;
import org.lwjgl.input.Controllers;

public class Joystick {
	
	private Controller control;
	
	public static Joystick[] getAvailable(){
		try {
			Controllers.create();
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
		
	}
	
	public void conecta(int index){
		try {
			Controllers.create();
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			
		}
		this.control = Controllers.getController(index);
	}
	
	private int float2int(float a){
		return (int)((a+0.5f) * 255.0f);
	}
	
	public int getX(){
		control.poll();
		return float2int(control.getXAxisValue());
//		return control.getXAxisValue();
	}

}
