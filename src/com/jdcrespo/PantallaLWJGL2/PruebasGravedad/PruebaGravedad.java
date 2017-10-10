package com.jdcrespo.PantallaLWJGL2.PruebasGravedad;

import org.lwjgl.input.Keyboard;

import com.jdcrespo.PantallaLWJGL2.Pantalla;

public class PruebaGravedad {
	
	public static final int x0 = 200;
	public static int y0 = 300;	// altura del suelo
	public static int size = 50;
	public static int posx = x0;
	public static int posy = y0-1;
	public static int fuerza0 = 18;
	public static int fuerza = fuerza0;
	public static boolean salto = false;
	public static float velocidad = 0;
	public static float gravedad = 0.5f;
	
	public static void actualizaAltura(){
			if((posy+(velocidad+gravedad)) < y0-1){		// Si está en el aire mueve hacia abajo 
				velocidad += gravedad;
				posy += velocidad;
			}else{
				if(salto) salto = false;
				posy = y0 - 1;
				velocidad = 0;
				fuerza = fuerza0;
			}
	}
	
	public static void main(String[] args) {
		Pantalla pantalla = new Pantalla("Pruebas de gravedad", 600, 480);
		
		
		while(pantalla.activa()){
			pantalla.limpia();

			actualizaAltura();
			
			pantalla.setColorRelleno(255, 0, 0);
			pantalla.dibujaRectangulo(posx-(size/2), posy-size, size, size);
			pantalla.setColorRelleno(255, 255, 0);
			pantalla.dibujaTexto("V: "+velocidad, posx+(size/2)+5, posy-size-5);
			pantalla.setColorRelleno(0, 0, 255);
			pantalla.dibujaLinea(0, y0, pantalla.getAncho(),y0);
			
			pantalla.setColorRelleno(255, 255, 255);
			pantalla.dibujaTexto("ESCPACIO: Saltar", 5, 5);
			pantalla.dibujaTexto("NUM8: Mover tierra hacia arriba", 5, 30);
			pantalla.dibujaTexto("NUM2: Mover tierra hacia abajo", 5, 55);
			
			if(pantalla.hayTecla(Keyboard.KEY_SPACE) && posy-20-size > 0 && !salto){
				fuerza -= gravedad;
				posy -= fuerza;
				if(fuerza == 0) salto = true;
			}
			if(pantalla.hayTecla(Keyboard.KEY_NUMPAD2) && y0+5 < pantalla.getAlto()) y0 += 10;
			if(pantalla.hayTecla(Keyboard.KEY_NUMPAD8) && y0-5 > 0) y0 -= 10;
			
			pantalla.espera(50);
		}
	}

}
