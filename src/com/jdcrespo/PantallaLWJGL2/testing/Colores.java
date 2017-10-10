package com.jdcrespo.PantallaLWJGL2.testing;

import com.jdcrespo.PantallaLWJGL2.Pantalla;

public class Colores {
	
	public static void main(String[] args) {
		Pantalla pantalla = new Pantalla("Pruebas colores", 680, 400);
		while(pantalla.activa()){
			pantalla.limpia();
			
			pantalla.setColorRelleno(200, 200, 200);
			pantalla.dibujaRectangulo(100, 100, 480, 200);
			
			pantalla.espera(50);
		}
	}

}
