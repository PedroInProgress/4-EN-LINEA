package juego;

/**
 * Juego Cuatro en Lí­nea
 * 
 * Reglas:
 * 
 * 		...
 *
 */
public class CuatroEnLinea {

	private int filas;
	private int columnas;
	private String jugadorRojo;
	private String jugadorAmarillo;
	private Casillero colocarFichas[][];
	private boolean turnoDeJugadorRojo;
	private int[] lugarFicha;
	
	
	public CuatroEnLinea(int filas, int columnas, String jugadorRojo, String jugadorAmarillo){
		
		turnoDeJugadorRojo = true;
		int erroresFilasColumnas[] = new int[2];
		int erroresNombres[] = new int[2];
		
		if(filas >= 4 && filas <= 8){
			
			this.filas = filas;
		}
			
		else{
			
			erroresFilasColumnas[0] = 1;
		}
			
		if(columnas >= 4 && columnas <= 16){
			
			this.columnas = columnas;
			lugarFicha = new int[columnas];
			
			System.out.println("COMIENZA");
			
			for(int x=0; x<lugarFicha.length; x++){
				
				System.out.println(lugarFicha[x]);
			}
			System.out.println("TERMINA");
		}
			
		else{
			
			erroresFilasColumnas[1] = 1;
		}
			
		if(erroresFilasColumnas[0] == 1 && erroresFilasColumnas[1] == 1){
			
			Error error = new Error("Cantidad de columnas y filas invalidas");
			throw error;
		}
		
		if(erroresFilasColumnas[0] == 1){
			
			Error error = new Error("Cantidad de filas invalidas");
			throw error;
		}
		
		if(erroresFilasColumnas[1] == 1){
			
			Error error = new Error("Cantidad de columnas invalidas");
			throw error;
		}
		
		if (!jugadorRojo.equals("")){
			
			this.jugadorRojo = jugadorRojo;
		}
			
		else{
			
			erroresNombres[0] = 1;
		}
			
		if (!jugadorAmarillo.equals("")){
			
			this.jugadorAmarillo = jugadorAmarillo;
		}
		
		else{
			
			erroresNombres[1] = 1;
		}
			
		if (erroresNombres[0] == 1 && erroresNombres[1] == 1){
			
			Error error = new Error("Los nombres de ambos jugadores son invalidos");
			throw error;
		}
		
		if (erroresNombres[0] == 1){
			
			Error error = new Error("El nombre del jugador rojo es invalido");
			throw error;
		}
		
		if (erroresNombres[1] == 1){
			
         Error error = new Error("El nombre del jugador amarillo es invalido");
         throw error;
		}
		
		colocarFichas = new Casillero[this.filas][this.columnas];
		
		for (int i = 0; i < colocarFichas.length; i++) {
			
			for (int j = 0; j < colocarFichas[i].length; j++)
				
				colocarFichas[i][j] = Casillero.VACIO;
		}
		
		
	}

	public int contarFilas(){
		
		return filas;
	}

	public int contarColumnas(){
		
		return columnas;
	}

	public Casillero obtenerCasillero(int fila, int columna){
		
		return colocarFichas[fila - 1][columna - 1];
	}

	public void soltarFicha(int columna){

		if (turnoDeJugadorRojo && columna < colocarFichas.length && columna >= 0){
			
			for (int i = 0; i < filas && turnoDeJugadorRojo; i++) {
				
				if (((colocarFichas[i][columna - 1] == Casillero.ROJO || 
					  colocarFichas[i][columna - 1] == Casillero.AMARILLO) &&  i != 0 )
				   && colocarFichas[i - 1][columna - 1] == Casillero.VACIO) {
						
					colocarFichas[i - 1][columna - 1] = Casillero.ROJO;
					turnoDeJugadorRojo = false;
				}
				
				if (i == filas-1 && colocarFichas[i][columna - 1] == Casillero.VACIO){
						
					colocarFichas[i][columna - 1] = Casillero.ROJO;
					turnoDeJugadorRojo = false;
				}
				
			}
		}	
		
		else if (!turnoDeJugadorRojo) {
			
			for (int i = 0; i < filas && !turnoDeJugadorRojo; i++) {
				
				if (((colocarFichas[i][columna - 1] == Casillero.ROJO || colocarFichas[i][columna - 1] == Casillero.AMARILLO)
						&& i != 0) && colocarFichas[i - 1][columna - 1] == Casillero.VACIO) {
						
					colocarFichas[i - 1][columna - 1] = Casillero.AMARILLO;
					turnoDeJugadorRojo = true;
				
				}
				
				if (i == filas-1 && colocarFichas[i][columna - 1] == Casillero.VACIO){
			
					colocarFichas[i][columna - 1] = Casillero.AMARILLO;
					turnoDeJugadorRojo = true;
				}
			}
		}
		
		else{
			
			Error error = new Error("Columna invalida");
			throw error;
		}
	}

	public boolean termino() {
		
		return false;
	}

	public boolean hayGanador() {
		
		return false;
	}

	public String obtenerGanador() {	
		return null;
	}
}