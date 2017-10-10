package com.jdcrespo.PantallaLWJGL2.testing;

import org.lwjgl.input.Keyboard;

import com.jdcrespo.PantallaLWJGL2.Fuente;
import com.jdcrespo.PantallaLWJGL2.Imagen;
import com.jdcrespo.PantallaLWJGL2.Pantalla;

public class PantallaDemo {
	
	public static void main(String[] args) {
		Pantalla pantalla = new Pantalla("Demo LWJGL2",480,600);
		Imagen imagen = new Imagen("img/protagonista.png");
		//Fuente fuente = new Fuente("resources/fonts/PixelOperator.ttf");
		//fuente.setTam(12);
		//pantalla.setFuente(fuente);
//		pantalla.setColorFondo(0, 255, 0);
		while(pantalla.activa()){
			pantalla.limpia();
			
			pantalla.setColorRelleno(255, 0, 0, 255);
			pantalla.dibujaRectangulo(pantalla.getMouseX()-25, pantalla.getMouseY()-25, 50, 50);
//			pantalla.dibujaLinea(pantalla.getMouseX(), pantalla.getMouseY(), 0, 0);
//			pantalla.dibujaTriangulo(pantalla.getMouseX(), pantalla.getMouseY(), pantalla.getMouseX()+50, pantalla.getMouseY(), pantalla.getMouseX(), pantalla.getMouseY()+50);
			pantalla.dibujaPunto(pantalla.getMouseX()-5, pantalla.getMouseY());			
//			pantalla.dibujaImagen(imagen, 40, 50, 400, 300);
//			pantalla.setColorRelleno(255, 0, 0,0);
			pantalla.dibujaTriangulo(pantalla.getMouseX(), pantalla.getMouseY(), pantalla.getMouseX()+50, pantalla.getMouseY(), pantalla.getMouseX(), pantalla.getMouseY()+50);
//			pantalla.setColorRelleno(255, 0, 255);
			if(pantalla.hayTecla(Keyboard.KEY_A)) pantalla.dibujaTexto("Holiii", 8, 50);
			
			pantalla.espera(50);
		}
		pantalla.libera();
	}

}
