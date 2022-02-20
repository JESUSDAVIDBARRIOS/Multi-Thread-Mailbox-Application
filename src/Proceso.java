import java.util.concurrent.CyclicBarrier;

public class Proceso extends Thread {

	private int identificador;
	private int tiempoEspera;
	private boolean tipoEnvio;
	private boolean tipoRecepcion;
	private Buzon buzonRecibir;
	private Buzon buzonEntregar;
	private int mensajes = 0;
	private boolean esProceso1;
	private CyclicBarrier barrera;

	public Proceso (int identificador, int tiempoEspera, boolean tipoEnvio, boolean tipoRecepcion, Buzon buzonRecibir, Buzon buzonEntregar, CyclicBarrier barrera) {
		this.identificador = identificador;
		this.tiempoEspera = tiempoEspera;
		this.tipoEnvio = tipoEnvio;
		this.tipoRecepcion = tipoRecepcion;
		this.buzonEntregar = buzonEntregar;
		this.buzonRecibir = buzonRecibir;
		this.barrera = barrera;
		if (identificador == 1) {
			esProceso1 = true;
		}
		else {
			esProceso1 = false;
		}
	}

	@Override
	public void run() {

		if(esProceso1) {
			for (int i = 0; i < mensajes; i++) {
				int numMensaje = i+1;
				String mensaje = transformarMensajes("Mensaje " + numMensaje + ": ");
				
				if(tipoEnvio) {
					buzonEntregar.anadirMensajeActivo(mensaje);
				}
					
				else
					buzonEntregar.anadirMensajePasivo(mensaje);
			}
			if(tipoEnvio)
				buzonEntregar.anadirMensajeActivo("FIN");
			else
				buzonEntregar.anadirMensajePasivo("FIN");
			
			String mensajeRecibido = "";
			while (true) {
				if(tipoRecepcion)
					try {
						mensajeRecibido = buzonRecibir.retirarMensajeActivo();
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				else
					try {
						mensajeRecibido = buzonRecibir.retirarMensajePasivo();
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				if(mensajeRecibido.contains("FIN"))
					break;
			}

		}
		else {
			boolean terminarEjecucion = false;
			String mensajeRecibido = "";
			while(!terminarEjecucion) {
				if(tipoRecepcion)
					try {
						mensajeRecibido = buzonRecibir.retirarMensajeActivo();
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				else
					try {
						mensajeRecibido = buzonRecibir.retirarMensajePasivo();
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				
				mensajeRecibido = transformarMensajes(mensajeRecibido);
				
				if(tipoEnvio)
					buzonEntregar.anadirMensajeActivo(mensajeRecibido);
				else
					buzonEntregar.anadirMensajePasivo(mensajeRecibido);
				
				if(mensajeRecibido.contains("FIN"))
					terminarEjecucion = true;
			}
		}
		
		System.out.println("Thread " + identificador + " terminó su ejecución.");
		
		try {
			barrera.await();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
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

	public String transformarMensajes (String mensajeOriginal) {
				
		char tipoRecepcion = 'A';
		char tipoEnvio = 'A';

		if (this.tipoRecepcion == true)
			tipoRecepcion = 'S';
		if (this.tipoEnvio == true)
			tipoEnvio = 'S';
		
		String mensajeProcesado = mensajeOriginal + " " + identificador + tipoRecepcion + tipoEnvio + " -";
		System.out.println("Transformando mensaje - Thread: " + identificador + " - Tiempo de espera: " + tiempoEspera + "ms - Mensaje: " + mensajeProcesado);
		
		try {
			sleep(tiempoEspera);
		} catch (InterruptedException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		return mensajeProcesado;
		
	}

}
