
public class Proceso extends Thread {
	
	private int identificador;
	private int tiempoEspera;
	private boolean tipoEnvio;
	private boolean tipoRecepcion;
	private Buzon buzonRecibir;
	private Buzon buzonEntregar;
	private int mensajes = 0;
	private boolean esProceso1;
	
	public Proceso (int identificador, int tiempoEspera, boolean tipoEnvio, boolean tipoRecepcion, Buzon buzonRecibir, Buzon buzonEntregar) {
		this.identificador = identificador;
		this.tiempoEspera = tiempoEspera;
		this.tipoEnvio = tipoEnvio;
		this.tipoRecepcion = tipoRecepcion;
		this.buzonEntregar = buzonEntregar;
		this.buzonRecibir = buzonRecibir;
		if (identificador == 1) {
			esProceso1 = true;
		}
		else {
			esProceso1 = false;
		}
	}
	
	
	public int getIdentificador() {
		return this.identificador;
	}

	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}

	public boolean isTipoRecepcion() {
		return tipoRecepcion;
	}


	public void setTipoRecepcion(boolean tipoRecepcion) {
		this.tipoRecepcion = tipoRecepcion;
	}


	public int getTiempoEspera() {
		return tiempoEspera;
	}

	public void setTiempoEspera(int tiempoEspera) {
		this.tiempoEspera = tiempoEspera;
	}

	public boolean isTipoEnvio() {
		return tipoEnvio;
	}

	public void setTipoEnvio(boolean tipoEnvio) {
		this.tipoEnvio = tipoEnvio;
	}

	public Buzon getBuzonRecibir() {
		return buzonRecibir;
	}

	public void setBuzonRecibir(Buzon buzonRecibir) {
		this.buzonRecibir = buzonRecibir;
	}

	public Buzon getBuzonEntregar() {
		return buzonEntregar;
	}

	public void setBuzonEntregar(Buzon buzonEntregar) {
		this.buzonEntregar = buzonEntregar;
	}
	
	public void setNumMensajes (int numMensajes) {
		mensajes = numMensajes;
	}

}
