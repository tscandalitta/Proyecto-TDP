package Personajes;

import java.util.Random;

import javax.swing.ImageIcon;
import Colisionadores.ColEnemigo;
import Colisionadores.Colisionador;
import Inteligencias.Inteligencia;
import Inteligencias.InteligenciaEnemigo;
import Personajes.PowerUps.*;
public class Enemigo extends Entidad{

	public Enemigo(int x, int y) {
		super(x,y);
		vida=100;
		damage=10;
		width=40;
		height=55;
		puntosDeMuerte=100;
		this.imagen = new ImageIcon(this.getClass().getResource("/Sprites/enemigo1.gif"));
		col= new ColEnemigo(this);
		inteligencia= new InteligenciaEnemigo(this);
	}

	public void mover() {
		inteligencia.mover();
		actualizarGrafico();
		if(pos.x<-10)
			this.vida=0;
	}

	public void disminuirVida(int damage) {
		super.disminuirVida(damage);
		inteligencia.verificarInteligencia();
		if(vida==0)
			lanzarPowerUp();
	}
	
	protected void lanzarPowerUp() {
		Random r= new Random();
		int n= r.nextInt(25);
		PowerUp powerup=null;
		switch(n) {
		case 1: 
			powerup= Congelar.getInstancia(pos.x, pos.y);
			break;
		case 2:
			powerup= new Curacion(pos.x,pos.y);
			break;
		case 3:
			powerup= new SuperDisparo(pos.x,pos.y);
			break;
		case 4:
			powerup= new SuperEscudo(pos.x,pos.y);
			break;
		case 5:
			powerup= new DisparoRapido(pos.x,pos.y);
			break;
		case 6:
			powerup= new HiperDisparo(pos.x,pos.y);
			break;	
		}
		if(powerup!=null) {
			powerup.setJuego(juego);
			juego.agregarEntidad(powerup);
		}
	}
	
	public void serColisionado(Colisionador col) {
		col.afectarEnemigo(this);
	}
	
	public void golpearJugador(Entidad e) {
		e.disminuirVida(damage);
		this.vida=0;
	}
	
	public void golpearEnemigo(Entidad e) {
	}
	
	public void golpearObstaculoDestruible(Entidad e) {
		e.disminuirVida(damage);
		this.vida=0;
	}
	
	public void golpearPowerUp(Entidad e) {
	}

	public void golpearObstaculoBarricada(Entidad o) {
	}

	public void golpearDisparoJugador(Entidad d) {
	}

	public void golpearDisparoEnemigo(Entidad d) {
	}

	public void setInteligenciaDummy(Inteligencia i) {
		inteligencia=i;
	}
}
