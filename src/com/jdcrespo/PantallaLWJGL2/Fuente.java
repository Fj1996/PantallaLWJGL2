package com.jdcrespo.PantallaLWJGL2;

import java.awt.Font;
import java.io.InputStream;

import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.util.ResourceLoader;

public class Fuente {
	
	private TrueTypeFont fuente;
	private Font awt;
	private int tam = 24;
	
	public Fuente(String ruta){
		if(ruta == null){
			awt = new Font("Times New Roman", Font.PLAIN, tam);
			fuente = new TrueTypeFont(awt, true);
		}else{
			InputStream is = ResourceLoader.getResourceAsStream(ruta);
			try {
				awt = Font.createFont(Font.TRUETYPE_FONT, is);
				awt.deriveFont((float)tam);
				fuente = new TrueTypeFont(awt, true);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public void setTam(int tam){
		this.tam = tam;
		awt.deriveFont((float)tam);
	}
	
	public int getTam(){
		return tam;
	}
	
	public Fuente(){
		this(null);
	}
	
	public void print(String texto, int x, int y, Color color){
		fuente.drawString(x, y, texto, color);
	}

}
