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

	private int filas = 0;
	private int columnas = 0;
	private String jugadorRojo = null;
	private String jugadorAmarillo = null;
	private boolean turnoDeJugadorRojo = true;
	private boolean juegoTerminado = false;
	private String ganador;
	
	/*Array que contiene los valores de cada posicion del tablero*/
	private Casillero colocarFichas[][];
	
	
//	private int[] lugarFicha = new int[2];
	/*Array que contiene 4 posiciones [fila][columna] correspondientes a las diagonales*/
	private Lista[] indicesDiagonales = new Lista[4];
	/*Array que contiene las 4 posiciones [fila][columna] correspondientes a las fichas que forman 4 en linea*/
	private Lista[] cuatroFichasSeguidas = new Lista[4];
	
	
	
	/*
	 * 
	 * post: si los valores ingresados son correctos, asigna el valor de los atributos
	 * 		 correspondientes
	 */
	
	public CuatroEnLinea(int filas, int columnas, String jugadorRojo, String jugadorAmarillo){
		
		boolean erroresFilasColumnas[] = new boolean[2];
		boolean erroresNombres[] = new boolean[3];

		/*
		 * Chequeamos que las filas y las columnas esten dentro de los valores establecidos
		 */
		
		if(filas >= 4 && filas <= 8)	this.filas = filas;
	
		else	erroresFilasColumnas[0] = true;

		if(columnas >= 4 && columnas <= 16)		this.columnas = columnas;
		
		else	erroresFilasColumnas[1] = true;
		
		/*
		 * Si es que existieron errores en el ingreso de los valores de filas y columnas
		 * mostramos cuales errores fueron cometidos
		 */
		if(erroresFilasColumnas[0] && erroresFilasColumnas[1]){
			
			Error error = new Error("Cantidad de columnas y filas invalidas");
			throw error;
		}
		
		if(erroresFilasColumnas[0]){
			
			Error error = new Error("Cantidad de filas invalidas");
			throw error;
		}
		
		if(erroresFilasColumnas[1]){
			
			Error error = new Error("Cantidad de columnas invalidas");
			throw error;
		}
	
		/*
		 * Chequeamos si las strings ingresadas como nombres para cada jugador son validas
		 * y ademas si son distintas
		 */
		
		if(jugadorRojo.equalsIgnoreCase(jugadorAmarillo) && !jugadorRojo.equals("") && !jugadorAmarillo.equals(""))	erroresNombres[2] = true;

		
		if (!jugadorRojo.equals("") && !jugadorRojo.equalsIgnoreCase(jugadorAmarillo))	this.jugadorRojo = jugadorRojo;
			
		else	erroresNombres[0] = true;

			
		if (!jugadorAmarillo.equals("") && !jugadorRojo.equalsIgnoreCase(jugadorAmarillo))	this.jugadorAmarillo = jugadorAmarillo;

		else	erroresNombres[1] = true;
			
		/*
		 * Si es que existieron errores en el ingreso de los nombres mostramos cuales
		 * errores fueron cometidos
		 */
		
		if (erroresNombres[2]){
			
			Error error = new Error("Los nombres de los jugadores son iguales");
			throw error;
		}
		
		if (erroresNombres[0] && erroresNombres[1]){
			
			Error error = new Error("Los nombres de ambos jugadores son invalidos");
			throw error;
		}
		
		if (erroresNombres[0]){
			
			Error error = new Error("El nombre del jugador rojo es invalido");
			throw error;
		}
		
		if (erroresNombres[1]){
			
			Error error = new Error("El nombre del jugador amarillo es invalido");
			throw error;
		}
		
		/*
		 * Inicializamos el array de 2 dimesiones que va a contener los valores de cada casillero
		 * del tablero como VACIO para todos las posiciones
		 */
		
		colocarFichas = new Casillero[this.filas][this.columnas];
		
		for (int i = 0; i < colocarFichas.length; i++) {
			
			for (int j = 0; j < colocarFichas[i].length; j++)
				
				colocarFichas[i][j] = Casillero.VACIO;
		}
	}

	/*
	 * pre: las filas ingresadas por el usuario estan dentro de los valores establecidos
	 * 
	 * post: devuelve el numero de filas del tablero
	 */
	public int contarFilas(){
		
		return filas;
	}

	/*
	 * pre: las columnas ingresadas por el usuario estan dentro de los valores establecidos
	 * 
	 * post: devuelve el numero de columnas del tablero
	 */
	public int contarColumnas(){
		
		return columnas;
	}

	/*
	 * pre: ?
	 * 
	 * post: devuelve el valor contenido en la posicion [fila][columna] del array de 2 dimesiones
	 * 		 que representa al tablero
	 */
	
	public Casillero obtenerCasillero(int fila, int columna){
		
		return colocarFichas[fila - 1][columna - 1];
	}

	/*
	 * pre: la columna ingresada para colocar una ficha, esta dentro del rango: (1 - columnas-1)
	 * 
	 * post: coloca una ficha en la columna del tablero indicada, en la proxima posicion 
	 * 		 mas proxima al "fondo" del tablero
	 */
	
	public void soltarFicha(int columna){

		if (turnoDeJugadorRojo && columna <= columnas && columna >= 0 && !juegoTerminado){
			
			for (int i = 0; i < filas && turnoDeJugadorRojo; i++) {
				
				if (((colocarFichas[i][columna - 1] == Casillero.ROJO || 
					  colocarFichas[i][columna - 1] == Casillero.AMARILLO || colocarFichas[i][columna - 1] == Casillero.VERDE) &&  i != 0 )
				   && colocarFichas[i - 1][columna - 1] == Casillero.VACIO) {
						
					colocarFichas[i - 1][columna - 1] = Casillero.ROJO;
					
//					lugarFicha[0] = i - 1;
//					lugarFicha[1] = columna - 1;
					
					diagonalVertical("rojo",i-1,columna-1);
					diagonalHorizontal("rojo",i-1,columna-1);
					diagonalArriba("rojo",i-1,columna-1);
					diagonalAbajo("rojo",i-1,columna-1);

//					lugarFicha[0] = 0;
//					lugarFicha[1] = 0;
					
					turnoDeJugadorRojo = false;
				}
				
				if (i == filas-1 && colocarFichas[i][columna - 1] == Casillero.VACIO){
						
					colocarFichas[i][columna - 1] = Casillero.ROJO;
					
//					lugarFicha[0] = i;
//					lugarFicha[1] = columna - 1;
					
					diagonalVertical("rojo",i,columna-1);
					diagonalHorizontal("rojo",i,columna-1);
					diagonalArriba("rojo",i,columna-1);
					diagonalAbajo("rojo",i,columna-1);
					
//					lugarFicha[0] = 0;
//					lugarFicha[1] = 0;
					
					turnoDeJugadorRojo = false;
				}
				
			}
		}	
		
		else if (!turnoDeJugadorRojo && columna <= columnas && columna >= 0 && !juegoTerminado) {
			
			for (int i = 0; i < filas && !turnoDeJugadorRojo; i++) {
				
				if (((colocarFichas[i][columna - 1] == Casillero.ROJO || 
						colocarFichas[i][columna - 1] == Casillero.AMARILLO|| 
						colocarFichas[i][columna - 1] == Casillero.VERDE)
						&& i != 0) && colocarFichas[i - 1][columna - 1] == Casillero.VACIO) {
						
					colocarFichas[i - 1][columna - 1] = Casillero.AMARILLO;
					
//					lugarFicha[0] = i - 1;
//					lugarFicha[1] = columna - 1;
					
					diagonalVertical("amarillo",i - 1,columna - 1);
					diagonalHorizontal("amarillo",i - 1,columna - 1);
					diagonalArriba("amarillo",i - 1,columna - 1);
					diagonalAbajo("amarillo",i - 1,columna - 1);
					
					turnoDeJugadorRojo = true;
				
				}
				
				if (i == filas-1 && colocarFichas[i][columna - 1] == Casillero.VACIO){
			
					colocarFichas[i][columna - 1] = Casillero.AMARILLO;
					
//					lugarFicha[0] = i;
//					lugarFicha[1] = columna - 1;
					
					diagonalVertical("amarillo",i,columna - 1);
					diagonalHorizontal("amarillo",i,columna - 1);
					diagonalArriba("amarillo",i,columna - 1);
					diagonalAbajo("amarillo",i,columna - 1);
					
					turnoDeJugadorRojo = true;
				}
			}
		}
		
		else{
			
			Error error = new Error("Columna invalida");
			throw error;
		}
	}
	
	/*
	 * pre: un jugador consiguio alinear 4 fichas del mismo color
	 * 
	 * post: se "pinta" las 4 fichas alineadas del color verde
	 * 
	 */
	
	public void pintarDeVerde(){
		
		for(int i = 0; i<4; i++){
			
			colocarFichas[cuatroFichasSeguidas[i].array[0]][cuatroFichasSeguidas[i].array[1]] = Casillero.VERDE;
		}
		
		juegoTerminado = true;
		
	}

	/*
	 * pre:
	 * 
	 * post: los 4 metodos llamados buscar4EnDiagonal... buscan si existen 4 fichas seguidas alineadas 
	 * 		 del color indicado como parametro, si pasa esto, llama al metodo pintar de verde y cambia 
	 * 		 el valor de la variable ganador por el jugador que consiguio alinear las fichas
	 * 
	 */
	
	public void buscar4enDiagonalAbajo(String color){
		
		int contador = 0;
		
		int fila = indicesDiagonales[0].array[0];
		int columna = indicesDiagonales[0].array[1];
				
		for(int i = 0; i<4; i++){
			
			cuatroFichasSeguidas[i] = new Lista(-1,-1);
		}
		
		for(int i = 0; i<filas; i++){

			if(color == "rojo"){
				
				if(fila+i <= filas-1 && columna+i <= columnas-1){
					
					if(colocarFichas[fila+i][columna+i] == Casillero.ROJO){

						cuatroFichasSeguidas[contador] = new Lista(fila+i,columna+i);
						
						contador++;

						if(contador == 4){
							
							System.out.println("gane");
							pintarDeVerde();
							ganador = jugadorRojo;
							contador = 0;
						}
					}
					if(colocarFichas[fila+i][columna+i] == Casillero.AMARILLO || colocarFichas[fila+i][columna+i] == Casillero.VERDE || colocarFichas[fila+i][columna+i] == Casillero.VACIO){
						
						for(int m = 0; m<4; m++){
							
							cuatroFichasSeguidas[m] = new Lista(-1,-1);
						}
						
						contador = 0;
					}
				}
			}
			
			else if(color == "amarillo"){
				
				if(fila+i <= filas-1 && columna+i <= columnas-1){
					
					if(colocarFichas[fila+i][columna+i] == Casillero.AMARILLO){

						cuatroFichasSeguidas[contador] = new Lista(fila+i,columna+i);
						
						contador++;

						if(contador == 4){
							
							System.out.println("gane");
							pintarDeVerde();
							ganador = jugadorAmarillo;
							contador = 0;
						}
					}
					if(colocarFichas[fila+i][columna+i] == Casillero.ROJO || colocarFichas[fila+i][columna+i] == Casillero.VERDE || colocarFichas[fila+i][columna+i] == Casillero.VACIO){
						
						for(int m = 0; m<4; m++){
							
							cuatroFichasSeguidas[m] = new Lista(-1,-1);
						}
						
						contador = 0;
					}
				}
			}
		}		
	}
	
	public void buscar4enDiagonalArriba(String color){
		
		int contador = 0;
		
		int fila = indicesDiagonales[1].array[0];
		int columna = indicesDiagonales[1].array[1];
				
		for(int i = 0; i<4; i++){
			
			cuatroFichasSeguidas[i] = new Lista(-1,-1);
		}
		
		for(int i = 0; i<filas; i++){
			
			if(color == "rojo"){
				
				if(fila-i >= 0 && columna+i <= columnas-1){
					
					if(colocarFichas[fila-i][columna+i] == Casillero.ROJO){
						
						cuatroFichasSeguidas[contador] = new Lista(fila-i,columna+i);
						
						contador++;
						
						System.out.println(contador+" contador");
						
						for(int k = 0; k<4; k++){
							
							for(int l = 0; l<2; l++){
								
								System.out.println(cuatroFichasSeguidas[k].array[l]+" si");
							}
							System.out.println("\n");
							
						}
						
						if(contador == 4){
							
							pintarDeVerde();
							ganador = jugadorRojo;
							contador = 0;
						}
					}
					
					if(colocarFichas[fila-i][columna+i] == Casillero.AMARILLO || colocarFichas[fila-i][columna+i] == Casillero.VERDE || colocarFichas[fila-i][columna+i] == Casillero.VACIO){
						
						for(int m = 0; m<4; m++){
							
							cuatroFichasSeguidas[m] = new Lista(-1,-1);
						}
						contador = 0;
					}
				}
			}
			else if(color == "amarillo"){
				
				if(fila-i >= 0 && columna+i <= columnas-1){
					
					if(colocarFichas[fila-i][columna+i] == Casillero.AMARILLO){
						
						cuatroFichasSeguidas[contador] = new Lista(fila-i,columna+i);
						
						contador++;
						
						System.out.println(contador+" contador");
						
						for(int k = 0; k<4; k++){
							
							for(int l = 0; l<2; l++){
								
								System.out.println(cuatroFichasSeguidas[k].array[l]+" si");
							}
							System.out.println("\n");
							
						}
						
						if(contador == 4){
							
							pintarDeVerde();
							ganador = jugadorAmarillo;
							contador = 0;
						}
					}
					
					if(colocarFichas[fila-i][columna+i] == Casillero.ROJO || colocarFichas[fila-i][columna+i] == Casillero.VERDE || colocarFichas[fila-i][columna+i] == Casillero.VACIO){
						
						for(int m = 0; m<4; m++){
							
							cuatroFichasSeguidas[m] = new Lista(-1,-1);
						}
						contador = 0;
					}
				}
			}
		}		
	}
	
	public void buscar4enDiagonalHorizontal(String color){
		
		int contador = 0;
		
		int fila = indicesDiagonales[2].array[0];
		int columna = indicesDiagonales[2].array[1];
				
		for(int i = 0; i<4; i++){
			
			cuatroFichasSeguidas[i] = new Lista(-1,-1);
		}
		
		for(int i = 0; i<columnas; i++){
			
			if(color == "rojo"){
				
				if(columna+i <= columnas-1){
					
					if(colocarFichas[fila][columna+i] == Casillero.ROJO){
						
						cuatroFichasSeguidas[contador] = new Lista(fila,columna+i);
						
						contador++;
					
						if(contador == 4){
							
							pintarDeVerde();
							ganador = jugadorRojo;
							contador = 0;
						}
					}
					
					if(colocarFichas[fila][columna+i] == Casillero.AMARILLO || colocarFichas[fila][columna+i] == Casillero.VERDE || colocarFichas[fila][columna+i] == Casillero.VACIO){
						
						for(int m = 0; m<4; m++){
							
							cuatroFichasSeguidas[m] = new Lista(-1,-1);
						}
						
						contador = 0;
					}
				}
			}			
			else if(color == "amarillo"){
				
				if(columna+i <= columnas-1){
					
					if(colocarFichas[fila][columna+i] == Casillero.AMARILLO){
						
						cuatroFichasSeguidas[contador] = new Lista(fila,columna+i);
						
						contador++;
					
						if(contador == 4){
							
							pintarDeVerde();
							ganador = jugadorAmarillo;
							contador = 0;
						}
					}
					
					if(colocarFichas[fila][columna+i] == Casillero.ROJO || colocarFichas[fila][columna+i] == Casillero.VERDE || colocarFichas[fila][columna+i] == Casillero.VACIO){
						
						for(int m = 0; m<4; m++){
							
							cuatroFichasSeguidas[m] = new Lista(-1,-1);
						}
						
						contador = 0;
					}
				}
			}
		}	
	}

	public void buscar4enDiagonalVertical(String color){
		
		int contador = 0;
		
		int fila = indicesDiagonales[3].array[0];
		int columna = indicesDiagonales[3].array[1];
				
		for(int i = 0; i<4; i++){
			
			cuatroFichasSeguidas[i] = new Lista(-1,-1);
		}
		
		for(int i = 0; i<filas; i++){
			
			if(color == "rojo"){
				
				if(fila+i <= filas-1){
					
					if(colocarFichas[fila+i][columna] == Casillero.ROJO){
										
						cuatroFichasSeguidas[contador] = new Lista(fila+i,columna);
						
						contador++;
					
						if(contador == 4){
							
							pintarDeVerde();
							ganador = jugadorRojo;
							contador = 0;
						}
					}
					
					if(colocarFichas[fila+i][columna] == Casillero.AMARILLO || colocarFichas[fila+i][columna] == Casillero.VERDE || colocarFichas[fila+i][columna] == Casillero.VACIO){
						
						for(int m = 0; m<4; m++){
							
							cuatroFichasSeguidas[m] = new Lista(-1,-1);
						}
						contador = 0;
					}
				}
			}
			
			else if(color == "amarillo"){
				
				if(fila+i <= filas-1){
					
					if(colocarFichas[fila+i][columna] == Casillero.AMARILLO){
										
						cuatroFichasSeguidas[contador] = new Lista(fila+i,columna);
						
						contador++;
					
						if(contador == 4){
							
							pintarDeVerde();
							ganador = jugadorAmarillo;
							contador = 0;
						}
					}
					
					if(colocarFichas[fila+i][columna] == Casillero.ROJO || colocarFichas[fila+i][columna] == Casillero.VERDE || colocarFichas[fila+i][columna] == Casillero.VACIO){
						
						for(int m = 0; m<4; m++){
							
							cuatroFichasSeguidas[m] = new Lista(-1,-1);
						}
						contador = 0;
					}
				}
			}
		}	
	}

	/*
	 * 
	 * post: guarda en el indice del array indicesDiagonales, la posicion [fila][columna]
	 *		 que luego se usara como posicion inicial para chequear si existen 4 fichas
	 *		 alineadas en las diagonales 
	 */
	
	public void diagonalAbajo(String color, int comienzoFila, int comienzoColumna){
		
		while(comienzoFila - 1 >= 0 && comienzoColumna - 1 >= 0){
			
			comienzoFila -= 1;
			comienzoColumna -= 1;
		}
		
		System.out.println(comienzoFila);
		System.out.println(comienzoColumna);
		
		
		indicesDiagonales[0] = new Lista(comienzoFila,comienzoColumna);
		
		buscar4enDiagonalAbajo(color);
	}
	
	public void diagonalArriba(String color, int comienzoFila, int comienzoColumna){
		
		while(comienzoFila + 1 <= filas-1 && comienzoColumna - 1 >= 0){
			
			comienzoFila += 1;
			comienzoColumna -= 1;
		}
		
		indicesDiagonales[1] = new Lista(comienzoFila,comienzoColumna);
		
		buscar4enDiagonalArriba(color);
	}
	
	public void diagonalHorizontal(String color, int comienzoFila, int comienzoColumna){
		
		while(comienzoColumna - 1 >=0){
			
			comienzoColumna -= 1;
		}
		
		indicesDiagonales[2] = new Lista(comienzoFila,comienzoColumna);
		
		buscar4enDiagonalHorizontal(color);
	}
	
	public void diagonalVertical(String color, int comienzoFila, int comienzoColumna){
		
		while(comienzoFila - 1 >= 0){
			
			comienzoFila -= 1;
		}
		
		indicesDiagonales[3] = new Lista(comienzoFila,comienzoColumna);
		
		buscar4enDiagonalVertical(color);
	}
	
	/*
	 * post: retorna si el juego ha finalizado
	 */
	
	public boolean termino() {
		
		return juegoTerminado;
	}

	/*
	 * post: retorna si existe un ganador 
	 */
	public boolean hayGanador() {
		
		return true;
	}
	
	/*
	 * post: retorna el ganador del juego
	 */
	public String obtenerGanador() {	
		return ganador;
	}
}