package juego;


/**
 * Juego Cuatro en Lí­nea
 * 
 * Reglas:
 * 
 * 		Apreta el boton para dejar caer una ficha en esa columna.				
 * 		Cuando formes cuatro fichas en horizontal, vertical o diagonal, ganas.
 *
 */
public class CuatroEnLinea {
	
	private int filas = 0;
	private int columnas = 0;
	private String jugador1 = null;
	private String jugador2 = null;
	private boolean turnoDeJugador1 = true;
	private boolean juegoTerminado = false;
	private String ganador;
	private boolean hayGanador = false;
	
	private Casillero colorJugador1;
	private Casillero colorJugador2;
	
	/*Array que contiene los valores de cada posicion del tablero*/
	private Casillero tableroDeFichas[][];

	/*Array que contiene las 4 posiciones [fila][columna] correspondientes a las fichas que forman 4 en linea*/
	private IndicesQueForman4EnLinea[] cuatroFichasSeguidas = new IndicesQueForman4EnLinea[4];
	
	private int alMejorDe = 1;
	private int partidasGanadasJugador1 = 0;
	private int partidasGanadasJugador2 = 0;
	
	/*Esta variable la uso para saber si cuando tengo que reiniciar el tablero, tengo que reiniciar las rondas ganadas de cada jugador o no*/
	private boolean resultadoFinal = false;
	
	/*Esta variable la uso para saber si tengo que mostrar en pantalla que se gano una ronda o una partida en el metodo mostrarGanador() de la clase Tablero*/
	private boolean ganadorFinal = false;
	
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
	
	public CuatroEnLinea(int filas, int columnas, String jugador1, String jugador2, Casillero colorJugador1, Casillero colorJugador2, int jugarAlMejorDe){

		/*
		 * Chequeamos que las filas y las columnas esten dentro de los valores establecidos
		 */	
		comprobarFilasYColumnas(filas, columnas);		
		/*
		 * Chequeamos si las strings ingresadas como nombres para cada jugador son validas
		 * y ademas si son distintas
		 */
		comprobarNombresJugadores(jugador1,jugador2);		
		/*
		 * Inicializamos el array de 2 dimesiones que va a contener los valores de cada casillero
		 * del tablero como VACIO para todos las posiciones
		 * Asignamos los colores que le corresponden a las fichas de cada jugador y a cuantas partidas se va a jugar
		 */
		crearEInicializarElTableroEnVacio();	
		inicializarColoresYPartidas(colorJugador1, colorJugador2, jugarAlMejorDe);
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
	
	public CuatroEnLinea(int filas, int columnas, String jugador1, String jugador2, Casillero colorJugador1, Casillero colorJugador2){

		/*
		 * Chequeamos que las filas y las columnas esten dentro de los valores establecidos
		 */	
		comprobarFilasYColumnas(filas, columnas);		
		/*
		 * Chequeamos si las strings ingresadas como nombres para cada jugador son validas
		 * y ademas si son distintas
		 */
		comprobarNombresJugadores(jugador1,jugador2);		
		/*
		 * Inicializamos el array de 2 dimesiones que va a contener los valores de cada casillero
		 * del tablero como VACIO para todos las posiciones
		 * Asignamos los colores que le corresponden a las fichas de cada jugador y a cuantas partidas se va a jugar
		 */
		crearEInicializarElTableroEnVacio();	
		inicializarColoresYPartidas(colorJugador1, colorJugador2, 1);	
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

		/*
		 * Chequeamos que las filas y las columnas esten dentro de los valores establecidos
		 */
		comprobarFilasYColumnas(filas, columnas);
		/*
		 * Chequeamos si las strings ingresadas como nombres para cada jugador son validas
		 * y ademas si son distintas
		 */
		comprobarNombresJugadores(jugador1,jugador2);
		/*
		 * Inicializamos el array de 2 dimesiones que va a contener los valores de cada casillero
		 * del tablero como VACIO para todos las posiciones
		 * Asignamos los colores que le corresponden a las fichas de cada jugador y a cuantas partidas se va a jugar
		 */
		crearEInicializarElTableroEnVacio();
		inicializarColoresYPartidas(Casillero.ROJO, Casillero.AMARILLO, 1);	
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

		return tableroDeFichas[fila - 1][columna - 1];
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

		Casillero colorAEnviar = (turnoDeJugador1)? colorJugador1 : colorJugador2;
		
		boolean fichaPuesta = false;
		
		if(juegoTerminado || columna <= 0 || columna > columnas){
			
			throw new Error("Columna invalida");
		}
	
		for (int i=filas-1; i>=0 && !fichaPuesta; i--){

			if(tableroDeFichas[i][columna-1] == Casillero.VACIO){

				tableroDeFichas[i][columna-1] = colorAEnviar;

				diagonalAbajo(i,columna-1);
				diagonalArriba(i,columna-1);
				diagonalHorizontal(i,columna-1);
				diagonalVertical(i,columna-1);

				averiguarSiElTableroEstaLleno();

				turnoDeJugador1 = !turnoDeJugador1;
				fichaPuesta = true;
			}
		}
	}

	/*
	 * post: Cambia todos los valores del array a -1, significando esto que no
	 * 		 contiene ningun indice que pertenece al tablero
	 */

	private void vaciarArrayDeIndicesQueForman4EnLinea(){

		for(int i = 0; i<4; i++){

			cuatroFichasSeguidas[i] = new IndicesQueForman4EnLinea(-1,-1);
		}
	}

	/**
	 * 
	 * pre: un jugador consiguio alinear 4 fichas del mismo color
	 * 
	 * post: se "pinta" las 4 fichas alineadas del color verde
	 *
	 */

	private void pintarDeVerdeFichasQueForman4EnLinea(){

		for(int i = 0; i<4; i++){

			tableroDeFichas[cuatroFichasSeguidas[i].obtenerIndices()[0]][cuatroFichasSeguidas[i].obtenerIndices()[1]] = Casillero.VERDE;
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
	 * @param filaFicha
	 * @param columnaFicha 
	 */

	private void buscar4enDiagonalAbajo(int filaFicha, int columnaFicha){

		int contador = 0;

		Casillero colorABuscar = (turnoDeJugador1)? colorJugador1 : colorJugador2;
		Casillero colorAEvadir = (turnoDeJugador1)? colorJugador2 : colorJugador1;
		String ganadorPotencial = (turnoDeJugador1)? jugador1 : jugador2;

		vaciarArrayDeIndicesQueForman4EnLinea();
		
		for(int i = 0; i<7; i++){

			if(filaFicha+i <= filas-1 && columnaFicha+i <= columnas-1){

				if(tableroDeFichas[filaFicha+i][columnaFicha+i] == colorABuscar){

					cuatroFichasSeguidas[contador] = new IndicesQueForman4EnLinea(filaFicha+i,columnaFicha+i);

					contador++;

					if(contador == 4){

						pintarDeVerdeFichasQueForman4EnLinea();
						
						asignarGanadorYChequearLimiteDePartidas(ganadorPotencial);
						
						contador = 0;
					}
				}
				else if(tableroDeFichas[filaFicha+i][columnaFicha+i] == colorAEvadir || 
					tableroDeFichas[filaFicha+i][columnaFicha+i] == Casillero.VERDE || 
					tableroDeFichas[filaFicha+i][columnaFicha+i] == Casillero.VACIO){

					vaciarArrayDeIndicesQueForman4EnLinea();

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
	 * @param filaFicha
	 * @param columnaFicha 
	 */

	private void buscar4enDiagonalArriba(int filaFicha, int columnaFicha){

		int contador = 0;

		Casillero colorABuscar = (turnoDeJugador1)? colorJugador1 : colorJugador2;
		Casillero colorAEvadir = (turnoDeJugador1)? colorJugador2 : colorJugador1;
		String ganadorPotencial = (turnoDeJugador1)? jugador1 : jugador2;

		vaciarArrayDeIndicesQueForman4EnLinea();

		for(int i = 0; i<7; i++){

			if(filaFicha-i >= 0 && columnaFicha+i <= columnas-1){

				if(tableroDeFichas[filaFicha-i][columnaFicha+i] == colorABuscar){

					cuatroFichasSeguidas[contador] = new IndicesQueForman4EnLinea(filaFicha-i,columnaFicha+i);

					contador++;

					if(contador == 4){
						
						pintarDeVerdeFichasQueForman4EnLinea();
						
						asignarGanadorYChequearLimiteDePartidas(ganadorPotencial);
						
						contador = 0;
					}
				}

				else if(tableroDeFichas[filaFicha-i][columnaFicha+i] == colorAEvadir ||
					tableroDeFichas[filaFicha-i][columnaFicha+i] == Casillero.VERDE ||
					tableroDeFichas[filaFicha-i][columnaFicha+i] == Casillero.VACIO){

					vaciarArrayDeIndicesQueForman4EnLinea();
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
	 * @param filaFicha
	 * @param columnaFicha
	 */

	private void buscar4enDiagonalHorizontal(int filaFicha, int columnaFicha){

		int contador = 0;

		Casillero colorABuscar = (turnoDeJugador1)? colorJugador1 : colorJugador2;
		Casillero colorAEvadir = (turnoDeJugador1)? colorJugador2 : colorJugador1;
		String ganadorPotencial = (turnoDeJugador1)? jugador1 : jugador2;

		vaciarArrayDeIndicesQueForman4EnLinea();

		for(int i = 0; i<7; i++){

			if(columnaFicha+i <= columnas-1){

				if(tableroDeFichas[filaFicha][columnaFicha+i] == colorABuscar){

					cuatroFichasSeguidas[contador] = new IndicesQueForman4EnLinea(filaFicha,columnaFicha+i);

					contador++;

					if(contador == 4){

						pintarDeVerdeFichasQueForman4EnLinea();
						
						asignarGanadorYChequearLimiteDePartidas(ganadorPotencial);
						
						contador = 0;
					}
				}

				else if(tableroDeFichas[filaFicha][columnaFicha+i] == colorAEvadir || 
					tableroDeFichas[filaFicha][columnaFicha+i] == Casillero.VERDE || 
					tableroDeFichas[filaFicha][columnaFicha+i] == Casillero.VACIO){

					vaciarArrayDeIndicesQueForman4EnLinea();
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
	 * @param filaFicha
	 * @param columnaFicha
	 */

	private void buscar4enDiagonalVertical(int filaFicha, int columnaFicha){

		int contador = 0;
		
		Casillero colorABuscar = (turnoDeJugador1)? colorJugador1 : colorJugador2;
		Casillero colorAEvadir = (turnoDeJugador1)? colorJugador2 : colorJugador1;
		String ganadorPotencial = (turnoDeJugador1)? jugador1 : jugador2;

		vaciarArrayDeIndicesQueForman4EnLinea();

		for(int i = 0; i<7; i++){

			if(filaFicha+i <= filas-1){

				if(tableroDeFichas[filaFicha+i][columnaFicha] == colorABuscar){

					cuatroFichasSeguidas[contador] = new IndicesQueForman4EnLinea(filaFicha+i,columnaFicha);

					contador++;

					if(contador == 4){

						pintarDeVerdeFichasQueForman4EnLinea();
						
						asignarGanadorYChequearLimiteDePartidas(ganadorPotencial);
						
						contador = 0;
					}
				}

				else if(tableroDeFichas[filaFicha+i][columnaFicha] == colorAEvadir || 
					tableroDeFichas[filaFicha+i][columnaFicha] == Casillero.VERDE || 
					tableroDeFichas[filaFicha+i][columnaFicha] == Casillero.VACIO){

					vaciarArrayDeIndicesQueForman4EnLinea();
					contador = 0;
				}
			}			
		}
	}

	/**
	 * 
	 * post: encuentra la posicion [fila][columna] en direccion abajo a la izquierda
	 *		 que luego se usara como posicion inicial para chequear si existen 4 fichas
	 *		 alineadas en las diagonales 
	 *
	 * @param color
	 * @param comienzoFila
	 * @param comienzoColumna
	 *
	 */

	private void diagonalAbajo(int comienzoFila, int comienzoColumna){

		for(int i=3; i>0 && comienzoFila - 1 >= 0 && comienzoColumna - 1 >= 0; i--){

			comienzoFila -= 1;
			comienzoColumna -= 1;
		}
		
		buscar4enDiagonalAbajo(comienzoFila,comienzoColumna);
	}

	/**
	 * 
	 * post: encuentra la posicion [fila][columna] en direccion arriba a la izquierda (en relacion al ficha)
	 *		 que luego se usara como posicion inicial para chequear si existen 4 fichas
	 *		 alineadas en las diagonales 
	 *
	 * @param color
	 * @param comienzoFila
	 * @param comienzoColumna
	 *
	 */

	private void diagonalArriba(int comienzoFila, int comienzoColumna){

		for(int i=3; i>0 && comienzoFila + 1 <= filas-1 && comienzoColumna - 1 >= 0;i--){

			comienzoFila += 1;
			comienzoColumna -= 1;
		}

		buscar4enDiagonalArriba(comienzoFila,comienzoColumna);
	}

	/**
	 * 
	 * post: encuentra la posicion [fila][columna] en direccion izquierda (en relacion al ficha)
	 *		 que luego se usara como posicion inicial para chequear si existen 4 fichas
	 *		 alineadas en las diagonales 
	 *
	 * @param color
	 * @param comienzoFila
	 * @param comienzoColumna
	 *
	 */

	private void diagonalHorizontal(int comienzoFila, int comienzoColumna){

		for(int i=3; i>0 && comienzoColumna-1 >=0; i--){

			comienzoColumna -= 1;
		}

		buscar4enDiagonalHorizontal(comienzoFila,comienzoColumna);
	}

	/**
	 * 
	 * post: encuentra la posicion [fila][columna] en direccion arriba (en relacion al ficha)
	 *		 que luego se usara como posicion inicial para chequear si existen 4 fichas
	 *		 alineadas en las diagonales 
	 *
	 * @param color
	 * @param comienzoFila
	 * @param comienzoColumna
	 *
	 */

	private void diagonalVertical(int comienzoFila, int comienzoColumna){

		for(int i=3; i>0 && comienzoFila-1 >=0; i--){

			comienzoFila -= 1;
		}

		buscar4enDiagonalVertical(comienzoFila,comienzoColumna);
	}

	/*
	 * 
	 * post: reincia el juego
	 */

	public void reiniciarJuego(){

		for (int i = 0; i < tableroDeFichas.length; i++) {

			for (int j = 0; j < tableroDeFichas[i].length; j++)

				tableroDeFichas[i][j] = Casillero.VACIO;
		}

		juegoTerminado = false;
		hayGanador = false;
		turnoDeJugador1 = true;
		ganador = null;
		
		if(resultadoFinal){
			
			partidasGanadasJugador1 = 0;
			partidasGanadasJugador2 = 0;
			resultadoFinal = false;
			ganadorFinal = false;
		}
	
	}

	/**
	 * pre:	ya no hay lugar en el tablero para poner otra ficha
	 * 
	 * post: termina el juego 
	 * 
	 */

	private void averiguarSiElTableroEstaLleno(){

		int contador = 0;

		if(!hayGanador){

			for(int i = 0; i<columnas; i++){

				if(tableroDeFichas[0][i] != Casillero.VACIO){

					contador++;
				}
			}
			
			if (contador == columnas){

				juegoTerminado = true;
				hayGanador = false;
			}
		}
	}
	
	/**
	 * post: retorna a quien le toca tirar la ficha
	 */

	public boolean obtenerJugador(){

		return turnoDeJugador1;
	}

	/**
	 * post: retorna si el juego ha finalizado
	 */

	public boolean termino() {

		return juegoTerminado;
	}

	/**
	 * post: retorna si existe un ganador 
	 */

	public boolean hayGanador() {

		return hayGanador;
	}

	/**
	 * post: retorna el ganador del juego
	 */

	public String obtenerGanador() {	

		return ganador;
	}

	/**
	 * post: retorna el nombre del jugadorRojo
	 */

	public String obtenerNombreJugador1(){

		return jugador1;
	}

	/**
	 * post: retorna el nombre del jugadorAmarillo
	 */

	public String obtenerNombreJugador2(){

		return jugador2;
	}

	/**
	 * post: retorna el color seleccionado para el jugador1
	 */

	public Casillero colorDelJugador1(){

		return colorJugador1;
	}

	/**
	 * post: retorna el color seleccionado para el jugador2
	 */

	public Casillero colorDelJugador2(){

		return colorJugador2;
	}

	/**
	 * post: si las partidas ganadas que tiene algun jugador son las necesarias para ganar el juego
	 * 		 cambio estas 2 variables que cambian el texto que se muestra en la ventana de mostrarResultado()
	 */
	
	private void verificarLimiteDePartidas(){
		
		if(partidasGanadasJugador1 == (alMejorDe/2)+1){
			
			resultadoFinal = true;
			ganadorFinal = true;
		}
		
		else if(partidasGanadasJugador2 == (alMejorDe/2)+1){
			
			resultadoFinal = true;
			ganadorFinal = true;
		}
	}

	/**
	 * post: desvuelve si se gano el juego (no la partida)
	 * @return ganadorFinal
	 */
	
	public boolean obtenerGanadorFinal(){
		
		return ganadorFinal;
	}
	
	/**
	 * post: devuelve si el resultado de los jugadores hizo que el juego se termine
	 * @return resultadoFinal
	 */
	public boolean obtenerResultadoFinal(){
		
		return resultadoFinal;
	}
	
	/**
	 * post: devuelve a cuantas rondas se juega
	 * @return alMejorDe
	 */
	
	public int obtenerJugarAlMejorDe(){
		
		return alMejorDe;
	}
	
	/**
	 * post: devuelve cuantas rondas gano el jugador 1
	 * @return partidasGanadasJugador1
	 */
	
	public int obtenerGanadasDel1(){

		return partidasGanadasJugador1;
	}

	/**
	 * post: devuelve cuantas rondas gano el jugador 1
	 * @return partidasGanadasJugador2
	 */
	
	public int obtenerGanadasDel2(){

		return partidasGanadasJugador2;
	}
	
	/**
	 * post: inicializa las columnas y las filas con los valores ingresados por el usuario 
	 * @param filas
	 * @param columnas
	 */
	
	private void comprobarFilasYColumnas(int filas, int columnas){
		
		if(filas < 4 || filas > 8){
			
			throw new Error("Cantidad de filas invalidas");
		}

		this.filas = filas;
		
		if(columnas < 4 || columnas > 16){
			
			throw new Error("Cantidad de columnas invalidas");
		}
		
		this.columnas = columnas;
	}

	/**
	 * post: inicializa los nombres de los jugadores con los nombres ingresados por el usuario
	 * @param jugador1
	 * @param jugador2
	 */
	
	private void comprobarNombresJugadores(String jugador1, String jugador2){
		
		if(jugador1.equalsIgnoreCase(jugador2) && !jugador1.equals("") && !jugador2.equals("")){
			
			throw new Error("Los nombres de los jugadores son iguales");
		}


		if (jugador1.equals("") || jugador1.equalsIgnoreCase(jugador2)){
			
			throw new Error("El nombre del jugador rojo es invalido");//jugador 1
		}

		this.jugador1 = jugador1;


		if (jugador2.equals("") || jugador1.equalsIgnoreCase(jugador2)){
			
			throw new Error("El nombre del jugador amarillo es invalido");//jugador 2 
		}

		this.jugador2 = jugador2;
	}
	
	/**
	 * post: Crea e inicializa todas las posiciones del tablero en VACIO 
	 */
	
	private void crearEInicializarElTableroEnVacio(){
		
		tableroDeFichas = new Casillero[this.filas][this.columnas];
		
		for (int i = 0; i < tableroDeFichas.length; i++) {

			for (int j = 0; j < tableroDeFichas[i].length; j++)

				tableroDeFichas[i][j] = Casillero.VACIO;
		}
	}
	
	/**
	 * post: asigna el color que le corresponde a las fichas de cada jugador
	 * y la cantidad de rondas a la que se va a jugar la partida
	 * @param rojo
	 * @param amarillo
	 * @param alMejorDe
	 */
	
	private void inicializarColoresYPartidas(Casillero rojo, Casillero amarillo, int alMejorDe) {
		
		this.colorJugador1 = rojo;
		this.colorJugador2 = amarillo;
		this.alMejorDe = alMejorDe;
	}
	
	/**
	 * post: asigna quien gano la partida y verifica si ese jugador gano el juego
	 * @param ganador
	 */
	
	private void asignarGanadorYChequearLimiteDePartidas(String ganador){
		
		this.ganador = ganador;
		
		if(ganador == jugador1){

			partidasGanadasJugador1 += 1;
			verificarLimiteDePartidas();
		}

		else if(ganador == jugador2){

			partidasGanadasJugador2 += 1;
			verificarLimiteDePartidas();
		}
		
		hayGanador = true;
	}
	
}
