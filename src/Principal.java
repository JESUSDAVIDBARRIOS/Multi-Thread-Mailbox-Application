
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Principal {

	public static void main(String[] args) {
		try {
			InputStreamReader is= new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(is);
			
			String[] idBuzones = new String[4];
			int[] tamanoBuzones = new int[4];
			int[] idThreads = new int[4];
			int[] tiempoEspera = new int[4];
			boolean[][] condicionesThreads = new boolean [4][2];
			
			String line = br.readLine();
			int lineaActual = 0;
			while(line!=null && lineaActual < 8) {
				final String [] respuesta = line.split(" ");
				try {

					if(lineaActual < 4)
					{
						idBuzones[lineaActual] = respuesta[0];
						tamanoBuzones[lineaActual] = Integer.parseInt(respuesta[1]);
					}
					else if (lineaActual >= 4) {
						idThreads[lineaActual-4] = Integer.parseInt(respuesta[0]);
						tiempoEspera[lineaActual-4] = Integer.parseInt(respuesta[1]);
						condicionesThreads[lineaActual-4][0] = Boolean.parseBoolean(respuesta[2]);
						condicionesThreads[lineaActual-4][1] = Boolean.parseBoolean(respuesta[3]);

					}
					
					System.out.println(lineaActual);
					System.out.println(line);
					if(lineaActual < 7)
						line = br.readLine();
					System.out.println("Si");
					lineaActual++;
				} catch (Exception e) {
					System.out.println("Formato incorrecto" + e.getMessage());
				}
				
			}
			
			System.out.println("Lectura realizada correctamente.");
			
			Proceso[] procesos = new Proceso[4];
			Buzon[] buzones = new Buzon[4];
			
			for(int i = 0; i < 4; i++) {
				buzones[i] = new Buzon(idBuzones[i], tamanoBuzones[i]);
			}
			for (int i = 0; i < 4; i++) {
				int buzonRecibir = i-1;
				int buzonEntregar = i;
				if(i == 0)
					buzonRecibir = 3;
				procesos[i] = new Proceso(idThreads[i], tiempoEspera[i], condicionesThreads[i][0], condicionesThreads[i][1], buzones[buzonRecibir], buzones[buzonEntregar]);
			}
			
			for (int i = 0; i < 4; i++) {
				System.out.println("Buzon del que recibe: " + procesos[i].getBuzonRecibir().getId() + "- id Proceso: " + procesos[i].getIdentificador() + "- Buzon al que envia: " + procesos[i].getBuzonEntregar().getId());
			}
			
		}
		catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
	
}
