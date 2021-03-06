package GUI;

import Juego.Juego;

public class HiloTiempo extends Thread{
	private Juego juego;
	private volatile boolean hayJuego;
	
	public HiloTiempo(Juego j) {
		juego=j;
		hayJuego=true;
	}
	
	public void run() {
		while(hayJuego){
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			juego.getJugador().mover();
			juego.getJugador().disparar();
			juego.mover();
			juego.agregarEntidades();
			juego.colisionar();
			juego.verificarMapa();
			juego.eliminarEntidades();	
		}
	}
	public void finalizar() {
		hayJuego=false;
	}
}
