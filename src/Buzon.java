
public class Buzon {
	
	private String id;
	private int tamanio;
	private int[] contador;
	
	public Buzon (String id, int tamanio) {
		this.id = id;
		this.tamanio = tamanio;
		contador = new int[tamanio];
	}
	
	public int[] getContador() {
		return contador;
	}

	public void setContador(int[] numero) {
		contador = numero;
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
	
	public synchronized void anadirMensaje(String mensaje) {
		
	}

}
