package juego;

import java.io.File;
import java.nio.file.Paths;











import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;
import javafx.application.Application;  
import javafx.scene.Group;  

/**
 * Representación gráfica del Tablero del Juego Cuatro en Lí­nea.
 * 
 */
public class Tablero {

	private static final int ALTO_FILA = 80;
	private static final int ANCHO_COLUMNA = 80;
	private static final int ALTURA_BOTON = 40;
	private static final double RADIO = Math.min(ALTO_FILA - 1, ANCHO_COLUMNA - 1) / 2;
	
	private Image imagenFondo = new Image(Paths.get("./res/fondo.jpg").toUri().toString());
	
	private CuatroEnLinea juego;
	private GridPane grilla;
	private Stage escenario;
	
	private String colorRojo = "#CB2929";
	private String colorAmarillo = "#E5C41E";

	/**
	 * post: asocia el Tablero a 'nuevoJuego' y lo inicializa a partir de su estado. 
	 * 
	 * @param nuevoJuego
	 */
	public Tablero(CuatroEnLinea nuevoJuego) {
		
		juego = nuevoJuego;
		escenario = new Stage();
		grilla = new GridPane();
	
	}
	
	/**
	 * post: muestra el Tablero en pantalla.
	 */
	public void mostrar() {
		
		dibujarBotones();
		/*
		 * alineo toda la grilla en el medio arriba para que me quede espacio para poner el turno del
		 * jugador que le corresponde
		 */
		grilla.setAlignment(Pos.TOP_CENTER);
		
		double ancho = juego.contarColumnas() * ANCHO_COLUMNA;
		double alto = (juego.contarFilas() * ALTO_FILA) + ALTURA_BOTON;
		
		Scene escena = new Scene(grilla, ancho, alto+40);
		
//		grilla.setGridLinesVisible(true);
		
		escenario.setScene(escena);
		escenario.setResizable(false);
		escenario.setTitle(Aplicacion.TITULO);
		
//		grilla.styleProperty().set("-fx-background-color: #0003E0");
			
		dibujar();
		
		escenario.show();
	}
	
	/**
	 * post: agrega los botones para soltar una ficha en cada columna del Tablero.
	 */
	
	/*
	 * Con este metodo 
	 */
	private void dibujarBotones() {
		
		for (int columna = 1; columna <= juego.contarColumnas(); columna++) {

			Button botonSoltarFicha = new Button("soltar");
			botonSoltarFicha.setMinHeight(ALTURA_BOTON);

			botonSoltarFicha.setOnAction(new SoltarFicha(this, juego, columna));
			botonSoltarFicha.setMinWidth(ANCHO_COLUMNA);
			grilla.add(botonSoltarFicha, columna - 1, 0);			
		}
		
		
		
		
//		impact
//		labelbotonAlMejorDe.setFont(Font.loadFont("file:C:/Windows/Fonts/DkHeadlockRegular-o5XA.otf", 25));
//		labelbotonAlMejorDe.setFont(Font.loadFont("file:C:/Windows/Fonts/GreatFighter-xaar.otf", 31));
//		labelbotonAlMejorDe.setFont(Font.loadFont("file:C:/Windows/Fonts/impact.ttf", 31));
//		labelbotonAlMejorDe.setStyle("-fx-font-size: 25px;");
//		labelbotonAlMejorDe.setStyle("-fx-opacity: 1;"+ "-fx-font-weight: bold;"+"-fx-text-fill:"+colorAmarillo+";");
//		labelbotonAlMejorDe.setStyle("-fx-opacity: 1;"+ "-fx-font-weight: bold;"+"-fx-text-fill: #E5C41E;");
//		labelbotonAlMejorDe.setStyle("-fx-font-size: 25px;" + "-fx-opacity: 1;"+ "-fx-font-weight: bold;");
//		labelbotonAlMejorDe.setStyle("-fx-font-size: 25px;" + "-fx-opacity: 1;"+ "-fx-font-weight: bold;"+"-fx-text-fill: #E5C41E;");
//		grilla.add(labelbotonAlMejorDe,0,juego.contarFilas()+4,juego.contarColumnas(),2);
//		GridPane.setHalignment(labelbotonAlMejorDe, HPos.CENTER);
		
	}
	
//	
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
		
		herramienta.setFill(Color.web("#f4f4f4"));
		herramienta.fillRect(0, 0, 300, 46);
		
		Label labelTurnoDelJugador = new Label("Jugar al mejor de: 1");
		labelTurnoDelJugador.setFont(Font.loadFont("file:./res/impact.ttf", 20));
		
		for (int fila = 1; fila <= juego.contarFilas(); fila++) {

			for (int columna = 1; columna <= juego.contarColumnas(); columna++) {

				Casillero casillero = juego.obtenerCasillero(fila, columna);
				
				Circle dibujoCasillero = dibujarCasillero(casillero);
				
				grilla.add(dibujoCasillero, columna - 1, fila);
			}
		}
		
		
		if(juego.obtenerJugador()){
			
			labelTurnoDelJugador.setText("Turno del jugador: rojo");
			labelTurnoDelJugador.setStyle("-fx-opacity: 1;"+ "-fx-font-weight: bold;"+"-fx-text-fill:"+colorRojo+";");			
		}
		
		else if(!juego.obtenerJugador()){
			
			labelTurnoDelJugador.setText("Turno del jugador: amarillo");
			labelTurnoDelJugador.setStyle("-fx-opacity: 1;"+ "-fx-font-weight: bold;"+"-fx-text-fill:"+colorAmarillo+";");
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
//		dibujoCasillero.setEffect(new DropShadow(+25d, 0d, +2d, Color.DARKSEAGREEN));
//		dibujoCasillero.setFill(new ImagePattern(imagenFondo));
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
				
			default:
				pintura = Color.WHITE;
		}

		return pintura;
	}

	/**
	 * pre : el juego asociado terminó.
	 * post: muestra un mensaje indicando el resultado del juego y pregunta si se quiere
	 * 		 volver a empezar la partida o salir..
	 */
	public void mostrarResultado() {

		Stage dialogo = new Stage();
		
		Button botonJugarDeNuevo = new Button("Si");
		Button botonSalirDelJuego = new Button("Salir");
		
		int ancho = 41; 
	
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
			}
		});
		

		
		Text textoJugarDeNuevo = new Text();
		Text textoResultado = new Text();
		Font fuente = new Font(28.0);
		
		textoJugarDeNuevo.setText("Jugar de nuevo?");
		textoResultado.setText("Ganó el jugador: Mariano Tugnarelli");
		
		if (juego.hayGanador()) {
		
			textoResultado = new Text("Ganó el jugador: " + juego.obtenerGanador());
			
		} else {
			
			textoResultado = new Text("Empataron");
		}
		
		GridPane grilla = new GridPane();

		grilla.setAlignment(Pos.CENTER);
//		grilla.setHgap(0);
//		grilla.setVgap(10);
		//
		grilla.setGridLinesVisible(true);
		//
		grilla.getRowConstraints().add(new RowConstraints(ancho));
		grilla.getRowConstraints().add(new RowConstraints(ancho));
		grilla.getRowConstraints().add(new RowConstraints(ancho));

		grilla.getColumnConstraints().add(new ColumnConstraints(ancho));
		grilla.getColumnConstraints().add(new ColumnConstraints(ancho));
		grilla.getColumnConstraints().add(new ColumnConstraints(ancho));

		grilla.add(botonJugarDeNuevo,0,2,1,1);
		grilla.add(botonSalirDelJuego,2,2,1,1);
		grilla.add(textoJugarDeNuevo,0,1,3,1);
		grilla.add(textoResultado,0,0,3,1);
		
		GridPane.setHalignment(botonJugarDeNuevo, HPos.CENTER);
		GridPane.setHalignment(botonSalirDelJuego, HPos.CENTER);
		GridPane.setHalignment(textoResultado, HPos.CENTER);
		GridPane.setHalignment(textoJugarDeNuevo, HPos.CENTER);
	
		textoResultado.setFont(fuente);
		textoJugarDeNuevo.setFont(fuente);

		Scene escenaGanador = new Scene(grilla,400,200);

		dialogo.setScene(escenaGanador);
		dialogo.show();
	}
	
	public void reiniciarTablero(){
		
		juego.reiniciarJuego();
		dibujar();
	}
}
