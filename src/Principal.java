
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class Principal {

	public static void main(String[] args) {
		try {
			File file = new File("./src/casoPrueba.txt");
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new FileReader(file));
			
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
					
					if(lineaActual < 7)
						line = br.readLine();
					lineaActual++;
				} catch (Exception e) {
					System.out.println("Formato incorrecto" + e.getMessage());
				}
				
			}
			
			System.out.println("Lectura realizada correctamente.");
			
			@SuppressWarnings("resource")
			Scanner userInputScanner = new Scanner(System.in);
			System.out.println("\nIngrese el número de mensajes que quiere que envíe el proceso 1:");
			int numMensajes = userInputScanner.nextInt();
			
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
				if(i==0)
					procesos[0].setNumMensajes(numMensajes);
			}
			
			int sumaTamanioBuzones = buzones[0].getTamanio() + buzones[1].getTamanio() + buzones[2].getTamanio() + buzones[3].getTamanio();
			if(numMensajes > sumaTamanioBuzones) 
				throw new Exception("El número de mensajes es mayor a la suma de los tamanios de los buzones.");
			
			for (int i = 0; i < 4; i++) 
				procesos[i].start();
			
			
		}
		catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
	
}
