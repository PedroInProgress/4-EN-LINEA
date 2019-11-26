package juego;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 * Representación gráfica del Tablero del Juego Cuatro en Lí­nea.
 * 
 */
public class Tablero {

	private static final int ALTO_FILA = 80;
	private static final int ANCHO_COLUMNA = 80;
	private static final int ALTURA_BOTON = 40;
	private static final double RADIO = Math.min(ALTO_FILA - 1, ANCHO_COLUMNA - 1) / 2;

	private CuatroEnLinea juego;
	private GridPane grilla;
	private Stage escenario;

	private String colorRojo = "#CB2929";
	private String colorAmarillo = "#E5C41E";
	private String colorAzul = "#2152D3";
	private String colorNaranja = "#FA771D";
	private String colorNegro = "#000000";
	private String colorGris = "#8C8C8C";

	private String colorTextoJugador1 = colorRojo;
	private String colorTextoJugador2 = colorAmarillo;

	/**
	 * post: asocia el Tablero a 'nuevoJuego' y lo inicializa a partir de su estado. 
	 * 
	 * @param nuevoJuego
	 */
	public Tablero(CuatroEnLinea nuevoJuego) {

		juego = nuevoJuego;
		escenario = new Stage();
		grilla = new GridPane();

		/*Asigno los colores que le corresponden a cada jugador segun su eleccion*/
		if(juego.colorDelJugador1() == Casillero.ROJO && juego.colorDelJugador2() == Casillero.AMARILLO){

			colorTextoJugador1 = colorRojo;
			colorTextoJugador2 = colorAmarillo;
		}		
		else if (juego.colorDelJugador1() == Casillero.AZUL && juego.colorDelJugador2() == Casillero.NARANJA){
			
			colorTextoJugador1 = colorAzul;
			colorTextoJugador2 = colorNaranja;
		}
		else if (juego.colorDelJugador1() == Casillero.NEGRO && juego.colorDelJugador2() == Casillero.GRIS){
			
			colorTextoJugador1 = colorNegro;
			colorTextoJugador2 = colorGris;
		}		
	}

	/**
	 * post: muestra el Tablero en pantalla.
	 */
	public void mostrar() {

		escenario.initModality(Modality.APPLICATION_MODAL);

		dibujarBotones();
		/*
		 * alineo toda la grilla en el medio arriba para que me quede espacio para poner el turno del
		 * jugador que le corresponde
		 */
		grilla.setAlignment(Pos.TOP_CENTER);

		double ancho = juego.contarColumnas() * ANCHO_COLUMNA;
		double alto = (juego.contarFilas() * ALTO_FILA) + ALTURA_BOTON;

		Scene escena = new Scene(grilla, ancho, alto+40);


		escenario.setScene(escena);
		escenario.setResizable(false);
		escenario.setTitle(Aplicacion.TITULO);

		/*Le cambio de color el fondo*/
		grilla.styleProperty().set("-fx-background-color: #EDE4B0");

		dibujar();		
		escenario.showAndWait();
	}
	
	/**
	 * post: agrega los botones para soltar una ficha en cada columna del Tablero.
	 */
	private void dibujarBotones() {
		
		for (int columna = 1; columna <= juego.contarColumnas(); columna++) {

			Button botonSoltarFicha = new Button("soltar");
			botonSoltarFicha.setMinHeight(ALTURA_BOTON-4);
			
			botonSoltarFicha.setStyle("-fx-background-color: linear-gradient(#000000, #ffffff); -fx-text-fill: white; -fx-border-color: black; -fx-background-radius:3 ;");
			
			botonSoltarFicha.setOnAction(new SoltarFicha(this, juego, columna));
			botonSoltarFicha.setMinWidth(ANCHO_COLUMNA);
			grilla.add(botonSoltarFicha, columna - 1, 0);			
		}	
	}

	/**
	 * post: actualiza el Tablero a partir del estado del juego asociado.
	 */
	public void dibujar() {
		
		/*
		 * Uso la clase Canvas que es una superficie sobre la que puedo dibujar usando GraphicsContext
		 * que me da las herramientas para "dibujar"
		 */
		Canvas superficie = new Canvas(300, 46);
		GraphicsContext herramienta = superficie.getGraphicsContext2D();
		
		/*
		 * setFill selecciona el color con el que se va a pintar cuando se llame a un metodo que pinte
		 * en este caso fillRect pinta el rectangulo del color que esta seteado previamente
		 */
		
		herramienta.setFill(Color.web("#EDE4B0"));
		herramienta.fillRect(0, 0, 300, 46);
		
		Label labelTurnoDelJugador = new Label();
		labelTurnoDelJugador.setFont(Font.loadFont("file:./res/impact.ttf", 20));
		
		for (int fila = 1; fila <= juego.contarFilas(); fila++) {

			for (int columna = 1; columna <= juego.contarColumnas(); columna++) {

				Casillero casillero = juego.obtenerCasillero(fila, columna);
				
				Circle dibujoCasillero = dibujarCasillero(casillero);
				
				grilla.add(dibujoCasillero, columna - 1, fila);
			}
		}
		
		
		if(juego.obtenerJugador()){
			
			labelTurnoDelJugador.setText("Turno del jugador: "+juego.obtenerNombreJugador1());
			labelTurnoDelJugador.setStyle("-fx-opacity: 1;"+ "-fx-font-weight: bold;"+"-fx-text-fill:"+colorTextoJugador1+";");			
		}
		
		else if(!juego.obtenerJugador()){
			
			labelTurnoDelJugador.setText("Turno del jugador: "+juego.obtenerNombreJugador2());
			labelTurnoDelJugador.setStyle("-fx-opacity: 1;"+ "-fx-font-weight: bold;"+"-fx-text-fill:"+colorTextoJugador2+";");
		}
		/*
		 * añado a la grilla mi superfice, en la columna 0 y en la fila total del tablero + 1 (para que quede separado de las fichas)
		 * hago que se expanda por la totalidad de las columnas (para que lo pueda centrar) y que se extienda 1 fila para abajo(es decir en la misma)
		 */
		grilla.add(superficie,0,juego.contarFilas()+1,juego.contarColumnas(),1);
		grilla.add(labelTurnoDelJugador,0,juego.contarFilas()+1,juego.contarColumnas(),1);
		
		/*
		 * centro la superficie y el texto en el centro de la cantidad de columnas y filas que pase como parametros
		 * grilla.add(labelTurnoDelJugador,0,juego.contarFilas()+1,--->juego.contarColumnas() , 1<---);
		 */
		GridPane.setHalignment(superficie, HPos.CENTER);
		GridPane.setHalignment(labelTurnoDelJugador, HPos.CENTER);

		
	}

	/**
	 * post: dibuja y devuelve el casillero dado.
	 * 
	 * 
	 * @param casillero
	 * @return representación gráfica del Casillero.
	 */

	private Circle dibujarCasillero(Casillero casillero) {
		
		Circle dibujoCasillero = new Circle(RADIO, obtenerPintura(casillero));
		
		dibujoCasillero.setStroke(new Color(0.5, 0.5, 0.5, 1.0));
		dibujoCasillero.setScaleX(0.95);
		dibujoCasillero.setScaleY(0.95);

		return dibujoCasillero;
	}

	/**
	 * post: determina la pintura a utilizar para 'casillero'.
	 * @param casillero
	 * @return pintura a utilizar para identificar el Casillero.
	 */
	private Paint obtenerPintura(Casillero casillero) {

		Paint pintura;

		switch (casillero) {
		
			case AMARILLO:
				pintura = Color.YELLOW;
				break;
				
			case ROJO:
				pintura = Color.RED;
				break;
			
			case VERDE:
				pintura = Color.GREEN;
				break;
			
			case AZUL:
				pintura = Color.BLUE;
				break;
				
			case NARANJA:
				pintura = Color.ORANGE;
				break;
				
			case NEGRO:
				pintura = Color.BLACK;
				break;
				
			case GRIS:
				pintura = Color.GREY;
				break;
			
			default:
				// usar en tema
				pintura = Color.WHITESMOKE;
		}

		return pintura;
	}

	/**
	 * pre : el juego asociado terminó.
	 * post: muestra un mensaje indicando el resultado del juego y pregunta si se quiere
	 * 		 volver a empezar la partida o salir..
	 */
	public void mostrarResultado() {
		
		int ancho = 41; 
		
		Stage dialogo = new Stage();
		
		GridPane grilla = new GridPane();
		
		Button botonJugarDeNuevo = new Button("Si");
		Button botonSalirDelJuego = new Button("Salir");
		
		Text textoJugarDeNuevo = new Text("Jugar de nuevo?");
		Text textoAlMejorDe = new Text("Se juega al mejor de");
		
		Text textoResultado = new Text("Ganó el jugador: Mariano Tugnarelli");

		Text textoResultadoParcial = new Text("Resultado:\n     0 : 0");

		Font fuente = new Font(28.0);
		
		dialogo.initModality(Modality.APPLICATION_MODAL);

		botonSalirDelJuego.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				((Stage)(((Button)arg0.getSource()).getScene().getWindow())).close();
				Platform.exit();
			}
		});
		botonJugarDeNuevo.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				
				reiniciarTablero();
				((Stage)(((Button)arg0.getSource()).getScene().getWindow())).close();
				dialogo.close();
			}
		});
	
		if (juego.hayGanador()) {
			
			if(!juego.obtenerGanadorFinal()){
				
				textoResultado = new Text("La ronda la ganó el jugador: " + juego.obtenerGanador());
				textoJugarDeNuevo.setText("Continuar?");
			}
			
			else if(juego.obtenerGanadorFinal()){
				
				textoResultado.setText("Ganó el jugador: " + juego.obtenerGanador());
				textoJugarDeNuevo.setText("Jugar de nuevo?");
			}
		} 
		else {
			
			textoResultado = new Text("Empataron");
		}
		
		grilla.setAlignment(Pos.CENTER);

		grilla.getRowConstraints().add(new RowConstraints(ancho));
		grilla.getRowConstraints().add(new RowConstraints(ancho));
		grilla.getRowConstraints().add(new RowConstraints(ancho));

		grilla.getColumnConstraints().add(new ColumnConstraints(ancho));
		grilla.getColumnConstraints().add(new ColumnConstraints(ancho));
		grilla.getColumnConstraints().add(new ColumnConstraints(ancho));
		
		grilla.add(textoAlMejorDe,0,0,3,1);
		grilla.add(textoResultadoParcial,0,1,3,2);
		grilla.add(textoResultado,0,3,3,1);
		grilla.add(textoJugarDeNuevo,0,4,3,1);
		grilla.add(botonJugarDeNuevo,0,5,1,1);
		grilla.add(botonSalirDelJuego,2,5,1,1);

		textoAlMejorDe.setText("Se juega al mejor de: "+ juego.obtenerJugarAlMejorDe());
		textoResultadoParcial.setText("Resultado:\n     "+juego.obtenerGanadasDel1()+" : "+juego.obtenerGanadasDel2());
		
		GridPane.setHalignment(textoResultadoParcial, HPos.CENTER);
		GridPane.setHalignment(textoAlMejorDe, HPos.CENTER);
		GridPane.setHalignment(botonJugarDeNuevo, HPos.CENTER);
		GridPane.setHalignment(botonSalirDelJuego, HPos.CENTER);
		GridPane.setHalignment(textoResultado, HPos.CENTER);
		GridPane.setHalignment(textoJugarDeNuevo, HPos.CENTER);
		
		textoAlMejorDe.setFont(fuente);
		textoResultado.setFont(fuente);
		textoJugarDeNuevo.setFont(fuente);
		textoResultadoParcial.setFont(fuente);
		
		Scene escenaGanador = new Scene(grilla,500,250);
		dialogo.setScene(escenaGanador);
		dialogo.showAndWait();
	}
	
	/**
	 * post: limpia el tablero de fichas para poder volver a jugar.
	 */
	public void reiniciarTablero(){
		
		juego.reiniciarJuego();
		dibujar();
	}
}
