
public class Buzon {
	
	private String id;
	private int tamanio;
	private int numElementosActual = 0;
	private String[] arregloMensajes;
	
	public Buzon (String id, int tamanio) {
		this.id = id;
		this.tamanio = tamanio;
		arregloMensajes = new String[tamanio];
	}
	
	public String[] getArregloMensajes() {
		return arregloMensajes;
	}

	public void setArregloMensajes(String[] numero) {
		arregloMensajes = numero;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getTamanio() {
		return tamanio;
	}

	public void setTamanio(int tamanio) {
		this.tamanio = tamanio;
	}
	
	public synchronized void anadirMensajePasivo(String mensaje) {
		while (numElementosActual == tamanio)
			try {
				wait();
			} catch (InterruptedException e) {
				System.out.println("Error: " + e.getMessage());
			}
		arregloMensajes[numElementosActual] = mensaje;
		numElementosActual++;
		notify();
	}
	
	public void anadirMensajeActivo(String mensaje) {
		while (numElementosActual == tamanio) {
			continue;
		}
		synchronized(this) {
			arregloMensajes[numElementosActual] = mensaje;
			numElementosActual++;
			notify();
		}
		
	}
	
	public synchronized String retirarMensajePasivo () throws Exception {
		while (numElementosActual == 0)
			try {
				wait();
			} catch (InterruptedException e) {
				System.out.println("Error: " + e.getMessage());
			}
		String respuesta = arregloMensajes[0];
		if(respuesta == null) {
			throw new Exception ("Error: mensaje nulo. NumElementos: " + numElementosActual);
		}
		for (int i = 0; i < numElementosActual; i++) {
			if(i == arregloMensajes.length-1)
				arregloMensajes[i] = null;
			else
				arregloMensajes[i] = arregloMensajes[i+1];
		}
		if(numElementosActual < arregloMensajes.length)
			arregloMensajes[numElementosActual] = null;
		numElementosActual--;
		notify();
		return respuesta;
	}
	
	public  String retirarMensajeActivo () throws Exception {
		while (numElementosActual == 0){
			System.out.print("");
			continue;
		}
		
		synchronized(this) {
			String respuesta = arregloMensajes[0];
			if(respuesta == null) {
				throw new Exception ("Error: mensaje nulo. NumElementos: " + numElementosActual);
			}
			for (int i = 0; i < numElementosActual; i++) {
				if(i == arregloMensajes.length-1)
					arregloMensajes[i] = null;
				else
					arregloMensajes[i] = arregloMensajes[i+1];
			}
			if(numElementosActual < arregloMensajes.length)
				arregloMensajes[numElementosActual] = null;
			numElementosActual--;
			notify();
			return respuesta;
		}
	}

}
