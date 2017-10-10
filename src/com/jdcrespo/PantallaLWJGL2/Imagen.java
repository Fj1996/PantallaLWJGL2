package com.jdcrespo.PantallaLWJGL2;

import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Imagen {
	
	private Texture texture;
	
	public Imagen(String ruta){
		String formato = ruta.toUpperCase().substring(ruta.length()-3, ruta.length());
		try {
			texture = TextureLoader.getTexture(formato, ResourceLoader.getResourceAsStream(ruta));
			//texture.bind();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void set(){
		texture.bind();
	}
	
	public int getAncho(){
		return texture.getImageWidth();
	}
	
	public int getAlto(){
		return texture.getImageHeight();
	}

}
