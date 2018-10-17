package Inteligencias;

import Personajes.DisparoJugador;

public class InteligenciaDisparoJugador extends InteligenciaDisparo {
	
	public InteligenciaDisparoJugador(DisparoJugador d) {
		super(d);
		velocidad=25;   
	}
	
	public void mover() {
		pos.setLocation(pos.x + velocidad, pos.y);
	}
}
