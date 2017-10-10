package com.jdcrespo.PantallaLWJGL2;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import com.jdcrespo.PantallaLWJGL2.Color;

public class Pantalla {
	
	private int ancho;
	private int alto;
	private int posX;
	private int posY;
	private Color colorRelleno;
	private Color colorFondo;
	private Fuente fuente;
	
	/**
	 * Constructor de la clase
	 * @param titulo titulo de la ventana 
	 * @param ancho ancho en pixeles
	 * @param alto alto en pixeles
	 */
	public Pantalla(String titulo, int ancho, int alto){
		this.ancho = ancho;
		this.alto = alto;
		try{
			Display.setTitle(titulo);
			Display.setDisplayMode(new DisplayMode(ancho, alto));
			Display.create();
		}catch(LWJGLException e){
			e.printStackTrace();
		}
		colorFondo = new Color(0, 0, 0, 255);
		colorRelleno = new Color(255, 255, 255, 255);		
		
		// Inicializa OpenGL
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, this.ancho,this.alto, 0, -1, 1);
		glMatrixMode(GL_MODELVIEW);		
		glClearColor(colorFondo.getR(), colorFondo.getG(), colorFondo.getB(), colorFondo.getA());
		glColor4f(colorRelleno.getR(), colorRelleno.getG(), colorRelleno.getB(),colorRelleno.getA());

		GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
//        GL11.glEnable(GL11.GL_TEXTURE_2D);
//        GL11.glEnable(GL11.GL_2D);
        
		actualizaMouse();
		
		fuente = new Fuente();
	}
	
	/**
	 * Actualiza la posicion actual del mouse
	 */
	private void actualizaMouse(){
		posX = Mouse.getX();
		posY = alto-Mouse.getY();
	}
	
	public boolean hayTecla(int tecla){
		return Keyboard.isKeyDown(tecla);
	}
	
	public boolean hayBotonMouse(int boton){
		return Mouse.isButtonDown(boton);
	}
	
	
	/**
	 * Devuelve si la pantalla sigue activa o debe cerrarse
	 * @return true si la pantalla está activa
	 */
	public boolean activa(){
		if(!Display.isCloseRequested()){
			actualizaMouse();
			return true;
		}else return false;
	}
	
	
	
	public int getAncho() {
		return ancho;
	}

	public int getAlto() {
		return alto;
	}

	/**
	 * Actualiza la pantalla despues de dibujar y espera
	 * para cumplir con el framerate especificado
	 * @param fps framerate para la aplicacion
	 */
	public void espera(int fps){
		Display.update();
		Display.sync(fps);
	}

	/**
	 * Limpia la pantalla y deja el fondo del color especificado por colorFondo
	 */
	public void limpia() {
		glClearColor(colorFondo.getR(), colorFondo.getG(), colorFondo.getB(), colorFondo.getA());
		glClear(GL_COLOR_BUFFER_BIT);
		glEnable(GL11.GL_TEXTURE_2D);
		glColor4f(colorRelleno.getR(), colorRelleno.getG(), colorRelleno.getB(),colorRelleno.getA());
		glDisable(GL11.GL_TEXTURE_2D);
	}
	
	/**
	 * Establece la fuente que se usará para imprimir cadenas en pantalla
	 * @param fuente fuente preparada
	 */
	public void setFuente(Fuente fuente){
		this.fuente = fuente;
	}
	
	/**
	 * Devuelve la fuente actual
	 */
	public Fuente getFuente(){
		return this.fuente;
	}
	
	/**
	 * Libera los recursos empleados por la pantalla y termina
	 */
	public void libera() {
		Display.destroy();		
	}
	
	/**
	 * Establece el color de relleno
	 * @param r componente rojo
	 * @param g componente verde
	 * @param b componente azul
	 * @param a canal alfa
	 */
	public void setColorRelleno(int r, int g, int b, int a){
		setColorRelleno(new Color(r, g, b, 255-a));
	}
	public void setColorRelleno(int r, int g, int b){
		setColorRelleno(r, g, b, 255);
	}
	public void setColorRelleno(Color color){
		this.colorRelleno = color;
		//System.out.println(color);
		glColor4f(colorRelleno.getR(), colorRelleno.getG(), colorRelleno.getB(),colorRelleno.getA());
	}
	
	/**
	 * Establece el color de fondo que se pinta al dejar limpia la pantalla
	 * @param r componente rojo
	 * @param g componente verde
	 * @param b componente azul
	 */
	public void setColorFondo(int r, int g, int b){
		this.colorFondo = new Color(r, g, b, 255);
		glClearColor(colorFondo.getR(), colorFondo.getG(), colorFondo.getB(), colorFondo.getA());
	}

	/**
	 * Devuelve la posicion del puntero
	 * @return coordenada Y
	 */
	public int getMouseY() {
		return posY;
	}

	/**
	 * Devuelve la posicion del puntero
	 * @return coordenada X
	 */
	public int getMouseX() {
		return posX;
	}
	
	/**
	 * Dibuja un rectangulo en las coordenadas (x,y) de ancho x alto pixeles
	 * @param x coordenada X
	 * @param y coordenada Y
	 * @param ancho ancho del rectangulo en pixeles
	 * @param alto alto del recatangulo en pixeles
	 */
	public void dibujaRectangulo(int x, int y, int ancho, int alto) {
		GL11.glBegin(GL_2D);
			GL11.glRecti(x, y, x+ancho, y+alto);
		GL11.glEnd();
	}
	
	/**
	 * Dibuja una linea entre los dos puntos dados
	 * @param x1 coordenada X del primer punto
	 * @param y1 coordenada Y del primer punto
	 * @param x2 coordenada X del segundo punto
	 * @param y2 coordenada Y del segundo punto
	 */
	public void dibujaLinea(int x1, int y1, int x2, int y2){
		glBegin(GL_LINE_STRIP);
			glVertex2i(x1, y1);
			glVertex2i(x2, y2);
		glEnd();
	}
	
	/**
	 * Dibuja un triangulo definido por tres puntos 
	 * @param x1 coordenada X del primer punto
	 * @param y1 coordenada Y del primer punto
	 * @param x2 coordenada X del segundo punto
	 * @param y2 coordenada Y del segundo punto
	 * @param x3 coordenada X del tercer punto
	 * @param y3 coordenada Y del tercer punto
	 */
	public void dibujaTriangulo(int x1, int y1, int x2, int y2, int x3, int y3){
		glBegin(GL_TRIANGLES);
			glVertex2i(x1, y1);
			glVertex2i(x2, y2);
			glVertex2i(x3, y3);
		glEnd();
	}
	
	/**
	 * Dibuja un punto
	 * @param x coordenada X del punto
	 * @param y coordenada Y del punto
	 */
	public void dibujaPunto(int x, int y){
		glBegin(GL_POINTS);
			glVertex2i(x, y);
		glEnd();
	}
	
	/**
	 * Dibuja una imagen en las coordenadas dadas y al tamaño especificado
	 * @param ruta ruta absoluta o relativa hacia la imagen
	 * @param x coordenada X
	 * @param y coordenada Y
	 * @param ancho ancho en pixeles
	 * @param alto alto en pixeles
	 */
	public void dibujaImagen(Imagen img, int x, int y, int ancho, int alto){
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		/*
		if(!(new File(ruta)).exists()) throw new IllegalArgumentException("El fichero ["+ruta+"] no existe");*/
		org.newdawn.slick.Color.white.bind();
		img.set();
		GL11.glBegin(GL11.GL_QUADS);
            GL11.glTexCoord2f(0,0);
            GL11.glVertex2f(x,y);
            GL11.glTexCoord2f(1,0);
            GL11.glVertex2f(x+ancho,y);
            GL11.glTexCoord2f(1,1);
            GL11.glVertex2f(x+ancho,y+alto);
            GL11.glTexCoord2f(0,1);
            GL11.glVertex2f(x,y+alto);
        GL11.glEnd();
		glColor4f(colorRelleno.getR(), colorRelleno.getG(), colorRelleno.getB(),colorRelleno.getA());
		GL11.glDisable(GL11.GL_TEXTURE_2D);
	}	
	
	
	/**
	 * Dibuja una cadena de texto del color de relleno en la posicion dada
	 * @param texto cadena a imprimir
	 * @param x coordenada X
	 * @param y coordenada Y
	 */
	public void dibujaTexto(String texto, int x, int y){
		glEnable(GL11.GL_TEXTURE_2D);
//		glColor4f(colorRelleno.getR(), colorRelleno.getG(), colorRelleno.getB(),colorRelleno.getA());
		
		fuente.print(texto, x, y, new org.newdawn.slick.Color(colorRelleno.getR(), colorRelleno.getG(), colorRelleno.getB(), colorRelleno.getA()));
		glDisable(GL11.GL_TEXTURE_2D);
	}
}
