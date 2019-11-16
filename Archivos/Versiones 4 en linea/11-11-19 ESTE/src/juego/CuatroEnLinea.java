package juego;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

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
	private String jugador1 = null;
	private String jugador2 = null;
	private boolean turnoDeJugadorRojo = true;
	private boolean juegoTerminado = false;
	private String ganador;
	
	private Casillero colorJugador1;
	private Casillero colorJugador2;
	
	/*Array que contiene los valores de cada posicion del tablero*/
	private Casillero colocarFichas[][];

	/*Array que contiene 4 posiciones [fila][columna] correspondientes a las diagonales*/
	private Lista[] indicesDiagonales = new Lista[4];
	/*Array que contiene las 4 posiciones [fila][columna] correspondientes a las fichas que forman 4 en linea*/
	private Lista[] cuatroFichasSeguidas = new Lista[4];
	private boolean hayGanador = false;
	private Casillero colorAEnviar;


	public CuatroEnLinea(int filas, int columnas, String jugador1, String jugador2, Casillero colorJugador1, Casillero colorJugador2){

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
		if(erroresFilasColumnas[0] && erroresFilasColumnas[1])	throw new Error("Cantidad de columnas y filas invalidas");

		if(erroresFilasColumnas[0])	throw new Error("Cantidad de filas invalidas");

		if(erroresFilasColumnas[1])	throw new Error("Cantidad de columnas invalidas");

		/*
		 * Chequeamos si las strings ingresadas como nombres para cada jugador son validas
		 * y ademas si son distintas
		 */

		if(jugador1.equalsIgnoreCase(jugador2) && !jugador1.equals("") && !jugador2.equals(""))	erroresNombres[2] = true;


		if (!jugador1.equals("") && !jugador1.equalsIgnoreCase(jugador2))	this.jugador1 = jugador1;

		else	erroresNombres[0] = true;


		if (!jugador2.equals("") && !jugador1.equalsIgnoreCase(jugador2))	this.jugador2 = jugador2;

		else	erroresNombres[1] = true;

		/*
		 * Si es que existieron errores en el ingreso de los nombres mostramos cuales
		 * errores fueron cometidos
		 */

		if (erroresNombres[2])	throw new Error("Los nombres de los jugadores son iguales");

		if (erroresNombres[0] && erroresNombres[1])	throw new Error("Los nombres de ambos jugadores son invalidos");

		if (erroresNombres[0])	throw new Error("El nombre del jugador rojo es invalido");

		if (erroresNombres[1])	throw new Error("El nombre del jugador amarillo es invalido");

		/*
		 * Inicializamos el array de 2 dimesiones que va a contener los valores de cada casillero
		 * del tablero como VACIO para todos las posiciones
		 */



		colocarFichas = new Casillero[this.filas][this.columnas];

		for (int i = 0; i < colocarFichas.length; i++) {

			for (int j = 0; j < colocarFichas[i].length; j++)

				colocarFichas[i][j] = Casillero.VACIO;
		}

		this.colorJugador1 = colorJugador1;
		this.colorJugador2 = colorJugador2;
	}

	/**
	 * 
	 * post: si los valores ingresados son correctos, asigna el valor de los atributos
	 * 		 correspondientes
	 * 
	 * @param filas
	 * @param columnas
	 * @param jugador1
	 * @param jugador2
	 */

	public CuatroEnLinea(int filas, int columnas, String jugador1, String jugador2){

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
		if(erroresFilasColumnas[0] && erroresFilasColumnas[1])	throw new Error("Cantidad de columnas y filas invalidas");

		if(erroresFilasColumnas[0])	throw new Error("Cantidad de filas invalidas");

		if(erroresFilasColumnas[1])	throw new Error("Cantidad de columnas invalidas");

		/*
		 * Chequeamos si las strings ingresadas como nombres para cada jugador son validas
		 * y ademas si son distintas
		 */

		if(jugador1.equalsIgnoreCase(jugador2) && !jugador1.equals("") && !jugador2.equals(""))	erroresNombres[2] = true;


		if (!jugador1.equals("") && !jugador1.equalsIgnoreCase(jugador2))	this.jugador1 = jugador1;

		else	erroresNombres[0] = true;


		if (!jugador2.equals("") && !jugador1.equalsIgnoreCase(jugador2))	this.jugador2 = jugador2;

		else	erroresNombres[1] = true;

		/*
		 * Si es que existieron errores en el ingreso de los nombres mostramos cuales
		 * errores fueron cometidos
		 */

		if (erroresNombres[2])	throw new Error("Los nombres de los jugadores son iguales");

		if (erroresNombres[0] && erroresNombres[1])	throw new Error("Los nombres de ambos jugadores son invalidos");

		if (erroresNombres[0])	throw new Error("El nombre del jugador rojo es invalido");

		if (erroresNombres[1])	throw new Error("El nombre del jugador amarillo es invalido");

		/*
		 * Inicializamos el array de 2 dimesiones que va a contener los valores de cada casillero
		 * del tablero como VACIO para todos las posiciones
		 */

		colocarFichas = new Casillero[this.filas][this.columnas];

		for (int i = 0; i < colocarFichas.length; i++) {

			for (int j = 0; j < colocarFichas[i].length; j++)

				colocarFichas[i][j] = Casillero.VACIO;
		}

		this.colorJugador1 = Casillero.ROJO;
		this.colorJugador2 = Casillero.AMARILLO;
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

	/**
	 * pre: la fila y la columna esta dentro de los valores establecidos
	 * 
	 * post: devuelve el valor contenido en la posicion [fila][columna] del array de 2 dimesiones
	 * 		 que representa al tablero
	 * 
	 * @param fila
	 * @param columna
	 */

	public Casillero obtenerCasillero(int fila, int columna){

		return colocarFichas[fila - 1][columna - 1];
	}

	/**
	 * pre: la columna ingresada para colocar una ficha, esta dentro del rango: (1 - columnas-1)
	 * 
	 * post: coloca una ficha en la columna del tablero indicada, en la proxima posicion 
	 * 		 mas proxima al "fondo" del tablero
	 * 
	 * @param columna
	 */

	public void soltarFicha(int columna){

		boolean fichaPuesta = false;
		String colorAFuncion = null;

		if(!juegoTerminado && columna > 0 && columna <= columnas){

			for (int i=filas-1; i>=0 && !fichaPuesta; i--){

				if(colocarFichas[i][columna-1] == Casillero.VACIO){

					if(turnoDeJugadorRojo){

						colorAEnviar = colorJugador1;
						colorAFuncion = "rojo";
					}

					else if(!turnoDeJugadorRojo){

						colorAEnviar = colorJugador2;
						colorAFuncion = "amarillo";
					}

					colocarFichas[i][columna-1] = colorAEnviar;

					diagonalAbajo(colorAFuncion,i,columna-1);
					diagonalArriba(colorAFuncion,i,columna-1);
					diagonalHorizontal(colorAFuncion,i,columna-1);
					diagonalVertical(colorAFuncion,i,columna-1);

					//					mostrarPuntas();
					averiguarSiElTableroEstaLleno();

					turnoDeJugadorRojo = !turnoDeJugadorRojo;
					fichaPuesta = true;
				}
			}
		}

		else{

			Error error = new Error("Columna invalida");
			throw error;
		}
	}


	//	
	//	public void mostrarPuntas(){
	//		//diagonal abajo
	//		colocarFichas[indicesDiagonales[0].array[0]][indicesDiagonales[0].array[1]] = Casillero.NEGRO;
	//		//diagonal arriba
	//		colocarFichas[indicesDiagonales[1].array[0]][indicesDiagonales[1].array[1]] = Casillero.NEGRO;
	//		//horizontal
	//		colocarFichas[indicesDiagonales[2].array[0]][indicesDiagonales[2].array[1]] = Casillero.NEGRO;
	//		//vertical
	//		colocarFichas[indicesDiagonales[3].array[0]][indicesDiagonales[3].array[1]] = Casillero.NEGRO;
	//	}

	/*
	 * post: Cambia todos los valores del array a -1, significando esto que no
	 * 		 contiene ningun indice que pertenece al tablero
	 */


	private void vaciarArray(){

		for(int i = 0; i<4; i++){

			cuatroFichasSeguidas[i] = new Lista(-1,-1);
		}
	}

	/**
	 * 
	 * pre: un jugador consiguio alinear 4 fichas del mismo color
	 * 
	 * post: se "pinta" las 4 fichas alineadas del color verde
	 * 
	 * 
	 */

	private void pintarDeVerde(){

		for(int i = 0; i<4; i++){

			colocarFichas[cuatroFichasSeguidas[i].array[0]][cuatroFichasSeguidas[i].array[1]] = Casillero.VERDE;
		}

		juegoTerminado = true;

	}

	/**
	 * pre:
	 * 
	 * post: los 4 metodos llamados buscar4EnDiagonal... buscan si existen 4 fichas seguidas alineadas 
	 * 		 del color indicado como parametro, si pasa esto, llama al metodo pintar de verde y cambia 
	 * 		 el valor de la variable ganador por el jugador que consiguio alinear las fichas
	 * 
	 * @param color
	 * 
	 */

	private void buscar4enDiagonalAbajo(String color){

		int contador = 0;

		int fila = indicesDiagonales[0].array[0];
		int columna = indicesDiagonales[0].array[1];

		Casillero colorABuscar = null;
		Casillero colorAEvadir = null;
		String ganadorPotencial = null;

		vaciarArray();

		if(color == "rojo"){

			colorABuscar = colorJugador1;
			colorAEvadir = colorJugador2;
			ganadorPotencial = jugador1;

		}

		else if(color == "amarillo"){

			colorABuscar = colorJugador2;
			colorAEvadir = colorJugador1;
			ganadorPotencial = jugador2;
		}

		for(int i = 0; i<filas; i++){

			if(fila+i <= filas-1 && columna+i <= columnas-1){

				if(colocarFichas[fila+i][columna+i] == colorABuscar){

					cuatroFichasSeguidas[contador] = new Lista(fila+i,columna+i);

					contador++;

					if(contador == 4){

						pintarDeVerde();
						ganador = ganadorPotencial;
						hayGanador = true;
						contador = 0;
					}
				}
				if(colocarFichas[fila+i][columna+i] == colorAEvadir || 
					colocarFichas[fila+i][columna+i] == Casillero.VERDE || 
					colocarFichas[fila+i][columna+i] == Casillero.VACIO){

					vaciarArray();

					contador = 0;
				}
			}	
		}	

	}

	/**
	 * pre:
	 * 
	 * post: los 4 metodos llamados buscar4EnDiagonal... buscan si existen 4 fichas seguidas alineadas 
	 * 		 del color indicado como parametro, si pasa esto, llama al metodo pintar de verde y cambia 
	 * 		 el valor de la variable ganador por el jugador que consiguio alinear las fichas
	 * 
	 * @param color
	 * 
	 */

	private void buscar4enDiagonalArriba(String color){

		int contador = 0;

		int fila = indicesDiagonales[1].array[0];
		int columna = indicesDiagonales[1].array[1];

		Casillero colorABuscar = null;
		Casillero colorAEvadir = null;
		String ganadorPotencial = null;

		vaciarArray();

		if(color == "rojo"){

			colorABuscar = colorJugador1;
			colorAEvadir = colorJugador2;
			ganadorPotencial = jugador1;

		}

		else if(color == "amarillo"){

			colorABuscar = colorJugador2;
			colorAEvadir = colorJugador1;
			ganadorPotencial = jugador2;
		}

		for(int i = 0; i<filas; i++){

			if(fila-i >= 0 && columna+i <= columnas-1){

				if(colocarFichas[fila-i][columna+i] == colorABuscar){

					cuatroFichasSeguidas[contador] = new Lista(fila-i,columna+i);

					contador++;

					if(contador == 4){

						pintarDeVerde();
						ganador = ganadorPotencial;
						hayGanador = true;
						contador = 0;
					}
				}

				if(colocarFichas[fila-i][columna+i] == colorAEvadir ||
					colocarFichas[fila-i][columna+i] == Casillero.VERDE ||
					colocarFichas[fila-i][columna+i] == Casillero.VACIO){

					vaciarArray();
					contador = 0;
				}
			}		
		}	
	}

	/**
	 * pre:
	 * 
	 * post: los 4 metodos llamados buscar4EnDiagonal... buscan si existen 4 fichas seguidas alineadas 
	 * 		 del color indicado como parametro, si pasa esto, llama al metodo pintar de verde y cambia 
	 * 		 el valor de la variable ganador por el jugador que consiguio alinear las fichas
	 * 
	 * @param color
	 * 
	 */

	private void buscar4enDiagonalHorizontal(String color){

		int contador = 0;

		int fila = indicesDiagonales[2].array[0];
		int columna = indicesDiagonales[2].array[1];

		Casillero colorABuscar = null;
		Casillero colorAEvadir = null;
		String ganadorPotencial = null;

		vaciarArray();

		if(color == "rojo"){

			colorABuscar = colorJugador1;
			colorAEvadir = colorJugador2;
			ganadorPotencial = jugador1;

		}

		else if(color == "amarillo"){

			colorABuscar = colorJugador2;
			colorAEvadir = colorJugador1;
			ganadorPotencial = jugador2;
		}

		for(int i = 0; i<columnas; i++){

			if(columna+i <= columnas-1){

				if(colocarFichas[fila][columna+i] == colorABuscar){

					cuatroFichasSeguidas[contador] = new Lista(fila,columna+i);

					contador++;

					if(contador == 4){

						pintarDeVerde();
						ganador = ganadorPotencial;
						hayGanador = true;
						contador = 0;
					}
				}

				if(colocarFichas[fila][columna+i] == colorAEvadir || 
					colocarFichas[fila][columna+i] == Casillero.VERDE || 
					colocarFichas[fila][columna+i] == Casillero.VACIO){

					vaciarArray();
					contador = 0;
				}
			}
		}
	}

	/**
	 * pre:
	 * 
	 * post: los 4 metodos llamados buscar4EnDiagonal... buscan si existen 4 fichas seguidas alineadas 
	 * 		 del color indicado como parametro, si pasa esto, llama al metodo pintar de verde y cambia 
	 * 		 el valor de la variable ganador por el jugador que consiguio alinear las fichas
	 * 
	 * @param color
	 * 
	 */

	private void buscar4enDiagonalVertical(String color){

		int contador = 0;

		int fila = indicesDiagonales[3].array[0];
		int columna = indicesDiagonales[3].array[1];		

		Casillero colorABuscar = null;
		Casillero colorAEvadir = null;
		String ganadorPotencial = null;

		vaciarArray();

		if(color == "rojo"){

			colorABuscar = colorJugador1;
			colorAEvadir = colorJugador2;
			ganadorPotencial = jugador1;

		}

		else if(color == "amarillo"){

			colorABuscar = colorJugador2;
			colorAEvadir = colorJugador1;
			ganadorPotencial = jugador2;
		}

		for(int i = 0; i<filas; i++){

			if(fila+i <= filas-1){

				if(colocarFichas[fila+i][columna] == colorABuscar){

					cuatroFichasSeguidas[contador] = new Lista(fila+i,columna);

					contador++;

					if(contador == 4){

						pintarDeVerde();
						ganador = ganadorPotencial;
						hayGanador = true;
						contador = 0;
					}
				}

				if(colocarFichas[fila+i][columna] == colorAEvadir || 
					colocarFichas[fila+i][columna] == Casillero.VERDE || 
					colocarFichas[fila+i][columna] == Casillero.VACIO){

					vaciarArray();
					contador = 0;
				}
			}			
		}
	}

	/**
	 * 
	 * post: guarda en el indice del array indicesDiagonales, la posicion [fila][columna]
	 *		 que luego se usara como posicion inicial para chequear si existen 4 fichas
	 *		 alineadas en las diagonales 
	 *
	 * @param color
	 * @param comienzoFila
	 * @param comienzoColumna
	 *
	 */

	private void diagonalAbajo(String color, int comienzoFila, int comienzoColumna){

		for(int i=3; i>0 && comienzoFila - 1 >= 0 && comienzoColumna - 1 >= 0; i--){

			comienzoFila -= 1;
			comienzoColumna -= 1;
		}

		indicesDiagonales[0] = new Lista(comienzoFila,comienzoColumna);

		buscar4enDiagonalAbajo(color);

	}

	/**
	 * 
	 * post: guarda en el indice del array indicesDiagonales, la posicion [fila][columna]
	 *		 que luego se usara como posicion inicial para chequear si existen 4 fichas
	 *		 alineadas en las diagonales 
	 *
	 * @param color
	 * @param comienzoFila
	 * @param comienzoColumna
	 *
	 */

	private void diagonalArriba(String color, int comienzoFila, int comienzoColumna){

		for(int i=3; i>0 && comienzoFila + 1 <= filas-1 && comienzoColumna - 1 >= 0;i--){

			comienzoFila += 1;
			comienzoColumna -= 1;
		}

		indicesDiagonales[1] = new Lista(comienzoFila,comienzoColumna);

		buscar4enDiagonalArriba(color);
	}

	/**
	 * 
	 * post: guarda en el indice del array indicesDiagonales, la posicion [fila][columna]
	 *		 que luego se usara como posicion inicial para chequear si existen 4 fichas
	 *		 alineadas en las diagonales 
	 *
	 * @param color
	 * @param comienzoFila
	 * @param comienzoColumna
	 *
	 */

	private void diagonalHorizontal(String color, int comienzoFila, int comienzoColumna){

		for(int i=3; i>0 && comienzoColumna-1 >=0; i--){

			comienzoColumna -= 1;
		}

		indicesDiagonales[2] = new Lista(comienzoFila,comienzoColumna);

		buscar4enDiagonalHorizontal(color);
	}

	/**
	 * 
	 * post: guarda en el indice del array indicesDiagonales, la posicion [fila][columna]
	 *		 que luego se usara como posicion inicial para chequear si existen 4 fichas
	 *		 alineadas en las diagonales 
	 *
	 * @param color
	 * @param comienzoFila
	 * @param comienzoColumna
	 *
	 */

	private void diagonalVertical(String color, int comienzoFila, int comienzoColumna){

		for(int i=3; i>0 && comienzoFila-1 >=0; i--){

			comienzoFila -= 1;
		}

		indicesDiagonales[3] = new Lista(comienzoFila,comienzoColumna);

		buscar4enDiagonalVertical(color);
	}

	/*
	 * 
	 * post: reincia el juego
	 */

	public void reiniciarJuego(){

		for (int i = 0; i < colocarFichas.length; i++) {

			for (int j = 0; j < colocarFichas[i].length; j++)

				colocarFichas[i][j] = Casillero.VACIO;
		}

		juegoTerminado = false;
		hayGanador = false;
		turnoDeJugadorRojo = true;
		ganador = null;
	}

	/*
	 * pre:	ya no hay lugar en el tablero para poner otra ficha
	 * 
	 * post: termina el juego 
	 * 
	 */

	private void averiguarSiElTableroEstaLleno(){

		int contador = 0;

		if(!hayGanador){

			for(int i = 0; i<columnas; i++){

				if(colocarFichas[0][i] != Casillero.VACIO){

					contador++;
				}
			}

			if (contador == columnas){

				juegoTerminado = true;
				hayGanador = false;
			}
		}
	}

	/*
	 * post: retorna a quien le toca tirar la ficha
	 */

	public boolean obtenerJugador(){

		return turnoDeJugadorRojo;
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

		return hayGanador;
	}

	/*
	 * post: retorna el ganador del juego
	 */

	public String obtenerGanador() {	

		return ganador;
	}

	/*
	 * post: retorna el nombre del jugadorRojo
	 */

	public String obtenerNombreJugador1(){

		return jugador1;
	}

	/*
	 * post: retorna el nombre del jugadorAmarillo
	 */

	public String obtenerNombreJugador2(){

		return jugador2;
	}

	/*
	 * post: retorna el color seleccionado para el jugador1
	 */

	public Casillero colorDelJugador1(){

		return colorJugador1;
	}

	/*
	 * post: retorna el color seleccionado para el jugador2
	 */

	public Casillero colorDelJugador2(){

		return colorJugador2;
	}
}
