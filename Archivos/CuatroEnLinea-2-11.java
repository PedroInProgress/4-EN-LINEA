package juego;

import jdk.nashorn.internal.runtime.ListAdapter;

///**
// * Juego Cuatro en Lí­nea
// * 
// * Reglas:
// * 
// * 		...
// *
// */
//public class CuatroEnLinea {
//
//	/**
//	 * pre : 'filas' y 'columnas' son mayores o iguales a 4.
//	 * post: empieza el juego entre el jugador que tiene fichas rojas, identificado como 
//	 * 		 'jugadorRojo' y el jugador que tiene fichas amarillas, identificado como
//	 * 		 'jugadorAmarillo'. 
//	 * 		 Todo el tablero está vacío.
//	 * 
//	 * @param filas : cantidad de filas que tiene el tablero.
//	 * @param columnas : cantidad de columnas que tiene el tablero.
//	 * @param jugadorRojo : nombre del jugador con fichas rojas.
//	 * @param jugadorAmarillo : nombre del jugador con fichas amarillas.
//	 */
//	public CuatroEnLinea(int filas, int columnas, String jugadorRojo, String jugadorAmarillo) {
//
//	}
//
//	/**
//	 * post: devuelve la cantidad máxima de fichas que se pueden apilar en el tablero.
//	 */
//	public int contarFilas() {
//		
//		return 4;
//	}
//
//	/**
//	 * post: devuelve la cantidad máxima de fichas que se pueden alinear en el tablero.
//	 */
//	public int contarColumnas() {
//		
//		return 4;
//	}
//
//	/**
//	 * pre : fila está en el intervalo [1, contarFilas()],
//	 * 		 columnas está en el intervalo [1, contarColumnas()].
//	 * post: indica qué ocupa el casillero en la posición dada por fila y columna.
//	 * 
//	 * @param fila
//	 * @param columna
//	 */
//	public Casillero obtenerCasillero(int fila, int columna) {
//		
//		return Casillero.VACIO;
//	}
//	
//	/**
//	 * pre : el juego no terminó, columna está en el intervalo [1, contarColumnas()]
//	 * 		 y aún queda un Casillero.VACIO en la columna indicada. 
//	 * post: deja caer una ficha en la columna indicada.
//	 * 
//	 * @param columna
//	 */
//	public void soltarFicha(int columna) {
//		
//	}
//	
//	/**
//	 * post: indica si el juego terminó porque uno de los jugadores
//	 * 		 ganó o no existen casilleros vacíos.
//	 */
//	public boolean termino() {
//		
//		return false;
//	}
//
//	/**
//	 * post: indica si el juego terminó y tiene un ganador.
//	 */
//	public boolean hayGanador() {
//		
//		return false;
//	}
//
//	/**
//	 * pre : el juego terminó.
//	 * post: devuelve el nombre del jugador que ganó el juego.
//	 */
//	public String obtenerGanador() {
//		
//		return null;
//	}
//}
//
//package juego;
//
//
////Referenced classes of package juego:
////         Casillero

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

		if (turnoDeJugadorRojo){
			
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
	}

	public boolean termino() {
		
		return true;
	}

	public boolean hayGanador() {
		
		return false;
	}

	public String obtenerGanador() {	
		return null;
	}
}
