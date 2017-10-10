package com.jdcrespo.PantallaLWJGL2;

public class Color {

	private float r;
	private float g;
	private float b;
	private float a;
	
	public Color(int r, int g, int b, int a){
		if(r > 255 || r < 0 || g > 255 || g < 0 || b > 255 || b < 0 || a > 255 || a < 0 )
			throw new IllegalArgumentException("Color RGBA inválido");
		this.r = (float)r/255;
		this.g = (float)g/255;
		this.b = (float)b/255;
		this.a = (float)1f-(a/255f);
		
		//System.out.println(this);
	}
	public float getR() {
		return r;
	}
	public float getG() {
		return g;
	}
	public float getB() {
		return b;
	}
	public float getA() {
		return a;
	}
	@Override
	public String toString() {
		return "Color [r=" + r + ", g=" + g + ", b=" + b + ", a=" + a + "]";
	}
	
	
	
}
