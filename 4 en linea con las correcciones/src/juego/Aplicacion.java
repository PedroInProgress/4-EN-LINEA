package juego;

import java.nio.file.Paths;

import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
//import javafx.beans.value.ChangeListener;
//import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
//import javafx.scene.control.Spinner;
//import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
//import javafx.scene.effect.GaussianBlur;
//import javafx.scene.effect.InnerShadow;
//import javafx.scene.effect.Light;
//import javafx.scene.effect.Lighting;
//import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
//import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
//import javafx.scene.control.ButtonBase;
import javafx.util.Duration;

/**
 * Aplicación del juego Cuatro en Lí­nea.
 * 
 * Punto de entrada del programa.
 * 
 */
public class Aplicacion extends Application {

	public static final String TITULO = "Cuatro en Línea";
	
	private GridPane grilla;
	
	private TextField campoNombreJugadorRojo;
	private TextField campoNombreJugadorAmarillo;

	private TextField campoColumnas;
	private TextField campoFilas;

	private Button botonIniciar;
	
	/* Creo 2 variables una que guarda el sonido que se reproduce cuando se apreta un boton
	 * y la otra es la imagen de fondo del menu
	 */
	private Media sonidoBoton = new Media(Paths.get("./res/boton.mp3").toUri().toString());
	private Image imagenFondo = new Image(Paths.get("./res/fondo.jpg").toUri().toString());
	
	/*Esta lista contiene los colores de fichas que se pueden elegir*/
	private ChoiceBox<String> listaDeColores = new ChoiceBox<String>();

	public int alMejorDe = 1;
	private boolean mostrarDropdown = true;
	private Casillero colorSeleccionadoJugador1 = Casillero.ROJO;
	private Casillero colorSeleccionadoJugador2 = Casillero.AMARILLO;
	
	
	/*
	 *	post: muestra la ventana inicial del juego
	 */
	@Override
	public void start(Stage escenarioPrincipal) {

		crearGrilla();
		
		Scene escena = new Scene(grilla, 400, 400);

		escenarioPrincipal.setScene(escena);
		escenarioPrincipal.setResizable(false);
		
		escenarioPrincipal.setTitle(TITULO);
		escenarioPrincipal.show();
		
	}

	/**
	 * post: crea la disposicion de los elementos que seran visibles en la pantalla principal del juego
	 */
	private void crearGrilla() {
 
		/*Creo la grilla para poder colcar en la pantalla todo el texto, botones y demas*/
		grilla = new GridPane();
		grilla.setAlignment(Pos.CENTER);
		grilla.setHgap(10);
		grilla.setVgap(10);

		/*Creo todas las "Label" que muestran el texto en la columna de la izquierda*/
		/*Creo todos los botones que voy a utilizar*/
		Label labelJugador1 = new Label("Jugador 1");
		Label labelJugador2 = new Label("Jugador 2");
		Label labelFilas = new Label("Filas (8 maximo)");
		Label labelColumnas = new Label("Columnas (16 maximo)");
		Label labelBotonModoDeJuego = new Label("Activar modo varias\nfilas (DESACTIVADO)");
		Label labelbotonAlMejorDe = new Label("Jugar al mejor de: 1");
		Label labelBox = new Label("Cambiar colores");
		
		String estiloDeBotonesVerde = "-fx-background-color: linear-gradient(#444444, #9CD672); -fx-text-fill: white;";
		String estiloDeBotonesNegro = "-fx-background-color: linear-gradient(#000000, #ffffff); -fx-text-fill: white;";
		String estiloDeLabels = "-fx-font-family: 'Roboto Regular';" + "-fx-font-size: 15px;" + "-fx-opacity: 1;"+ "-fx-font-weight: bold;";
		
		Button botonModoDeJuegoActivar = new Button("Activar");
		Button botonModoDeJuegoDesactivar = new Button("Desactivar");
		Button botonAlMejorDeUno = new Button("  1  ");
		Button botonAlMejorDeTres = new Button("  3  ");
		Button botonAlMejorDeCinco = new Button("  5  ");
		
		CheckBox casilleroTilde = new CheckBox(" ");
		
		/*Creo cargo el fondo en la variable fondo*/
		BackgroundImage fondo = new BackgroundImage(imagenFondo,BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.DEFAULT,
				new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, true, true));
		
		/*Pongo la imagen que esta guardada en "fondo" como fondo de la aplicacion*/
		grilla.setBackground(new Background(fondo)); 
		
		Text textoTitulo = new Text("CUATRO EN LINEA");
		textoTitulo.setFont(Font.loadFont("file:./res/GreatFighter-xaar.otf", 32));
		
		/*La animacion de el titulo 4 en linea*/
		ScaleTransition transition1 = new ScaleTransition(Duration.seconds(5),textoTitulo);
		transition1.setToX(0.7);
		transition1.setToY(0.8);
		transition1.setAutoReverse(true);
		transition1.setCycleCount(TranslateTransition.INDEFINITE);
		transition1.play();

		
		crearControles();
		
	    /*Cambio el estilo de todas las label y los botones*/
		botonModoDeJuegoActivar.setStyle(estiloDeBotonesVerde);
		botonModoDeJuegoDesactivar.setStyle(estiloDeBotonesVerde);
		botonAlMejorDeUno.setStyle(estiloDeBotonesVerde);
		botonAlMejorDeTres.setStyle(estiloDeBotonesVerde);
		botonAlMejorDeCinco.setStyle(estiloDeBotonesVerde);
		
		labelJugador1.setStyle(estiloDeLabels);
		labelJugador2.setStyle(estiloDeLabels);
		labelFilas.setStyle(estiloDeLabels);
		labelColumnas.setStyle(estiloDeLabels);
		labelBotonModoDeJuego.setStyle(estiloDeLabels);

		labelbotonAlMejorDe.setStyle(estiloDeLabels);
		labelBox.setStyle(estiloDeLabels);
		
		campoNombreJugadorRojo.setStyle(estiloDeBotonesNegro);
		campoNombreJugadorAmarillo.setStyle(estiloDeBotonesNegro);
		campoFilas.setStyle(estiloDeBotonesNegro);
		campoColumnas.setStyle(estiloDeBotonesNegro);
		
		listaDeColores.setStyle(estiloDeBotonesNegro);
		
		/* Uso un manejador de eventos para ocultar y mostrar la lista de colores al tildar
		 * o no una casilla
		 */ 
		EventHandler<ActionEvent> cambiarVisibilidad = new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent event) {
		    	
		        if (event.getSource() instanceof CheckBox) {
		        	
		            CheckBox chk = (CheckBox) event.getSource();
		            
		            if (" ".equals(chk.getText())) {
		            	
		            	listaDeColores.setVisible(mostrarDropdown);
		            	mostrarDropdown = !mostrarDropdown;
		            }
		        }
		    }
		};
		
		/*Cuando se tilde o no mi casillero, voy a mostrar o no la lista de colores*/
		casilleroTilde.setOnAction(cambiarVisibilidad);
		
		/*Cuando empieza el programa, no muestro la lista, eso cambia si tildo mi casillero*/
		listaDeColores.setVisible(false);
		
		/*Cargo de valores mi lista de colores */
		listaDeColores.getItems().addAll("Rojo y amarillo","Azul y naranja", "Negro y gris");
		listaDeColores.setValue("Rojo y amarillo");

		/*Añado todo a mi grilla para poder verlo en pantalla*/
		grilla.add(textoTitulo, 0, 0, 2, 1);
		grilla.add(labelJugador1, 0, 1);
		grilla.add(campoNombreJugadorRojo, 1, 1);
		grilla.add(labelJugador2, 0, 2);
		grilla.add(campoNombreJugadorAmarillo, 1, 2);
		grilla.add(labelFilas, 0, 3);
		grilla.add(campoFilas, 1, 3);
		grilla.add(labelColumnas, 0, 4);
		grilla.add(campoColumnas, 1, 4);
		grilla.add(labelbotonAlMejorDe,0,5);
		grilla.add(botonAlMejorDeUno, 1, 5, 1, 1);
		grilla.add(botonAlMejorDeTres, 1, 5, 1, 1);
		grilla.add(botonAlMejorDeCinco, 1, 5, 1, 1);
		grilla.add(labelBox, 0, 6);
		grilla.add(casilleroTilde, 0, 6);
		grilla.add(listaDeColores, 1, 6);
		grilla.add(botonIniciar, 0, 7, 2, 1);
		
		/*Alineo todo correspondientemente*/
		GridPane.setHalignment(botonAlMejorDeUno, HPos.LEFT);
		GridPane.setHalignment(botonAlMejorDeTres, HPos.CENTER);
		GridPane.setHalignment(botonAlMejorDeCinco, HPos.RIGHT);

		GridPane.setHalignment(botonModoDeJuegoActivar, HPos.LEFT);
		GridPane.setHalignment(botonModoDeJuegoDesactivar, HPos.RIGHT);
		GridPane.setHalignment(botonIniciar, HPos.CENTER);
		GridPane.setHalignment(textoTitulo, HPos.CENTER);
		
		GridPane.setHalignment(labelBox, HPos.LEFT);
		GridPane.setHalignment(listaDeColores, HPos.CENTER);
		GridPane.setHalignment(casilleroTilde, HPos.RIGHT);
				
		botonAlMejorDeUno.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {

				alMejorDe = 1;
				
				labelbotonAlMejorDe.setText("Jugar al mejor de: 1");

				reproducir();
			}
		});
		
		botonAlMejorDeTres.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {

				alMejorDe = 3;
				
				labelbotonAlMejorDe.setText("Jugar al mejor de: 3");
				
				reproducir();
			}
		});
		
		botonAlMejorDeCinco.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {

				alMejorDe = 5;
				
				labelbotonAlMejorDe.setText("Jugar al mejor de: 5");

				reproducir();
			}
		});
	}
	
	/**
	 * pre: se apreto un boton en el menu principal
	 * 
	 * post: reproduce un sonido
	 */
	private void reproducir(){
			
		MediaPlayer mediaPlayer = new MediaPlayer(sonidoBoton);

		mediaPlayer.play();
	}
	
	/**
	 * post: crea los casilleros para poder escribir y el boton para inciar el juego
	 */
	private void crearControles() {

		campoNombreJugadorRojo = new TextField("Jugador 1");
		
		campoNombreJugadorAmarillo = new TextField("Jugador 2");

		campoFilas = new TextField("4");
		
		campoColumnas = new TextField("4");	
		
		botonIniciar = new Button("Iniciar");
		
		botonIniciar.setOnAction(new IniciarJuego(this));	
	}

	/**
	 * post: crea un juego CuatroEnLinea, lo asocia a una Tablero 
	 * 		 y comienza su ejecución.
	 * 
	 */
	public void iniciar() {
		
		String nombreJugadorRojo = campoNombreJugadorRojo.getText();
		String nombreJugadorAmarillo = campoNombreJugadorAmarillo.getText();
		int filas = Integer.parseInt(campoFilas.getText());
		int columnas = Integer.parseInt(campoColumnas.getText());
		
		if (listaDeColores.isVisible()){
			
			if(listaDeColores.getValue().equalsIgnoreCase("Rojo y amarillo")){
				
				colorSeleccionadoJugador1 = Casillero.ROJO;
				colorSeleccionadoJugador2 = Casillero.AMARILLO;
			}
			
			else if(listaDeColores.getValue().equalsIgnoreCase("Azul y naranja")){
				
				colorSeleccionadoJugador1 = Casillero.AZUL;
				colorSeleccionadoJugador2 = Casillero.NARANJA;
			}
			
			else if(listaDeColores.getValue().equalsIgnoreCase("Negro y gris")){

				colorSeleccionadoJugador1 = Casillero.NEGRO;
				colorSeleccionadoJugador2 = Casillero.GRIS;
			}
			
		}
		
		else{
			
			colorSeleccionadoJugador1 = Casillero.ROJO;
			colorSeleccionadoJugador2 = Casillero.AMARILLO;
		}
		
		CuatroEnLinea juego = new CuatroEnLinea(filas, columnas, 
												nombreJugadorRojo, nombreJugadorAmarillo,
												colorSeleccionadoJugador1,colorSeleccionadoJugador2,
												alMejorDe);
		
		Tablero tablero = new Tablero(juego);
		tablero.mostrar();
	}
	
	/**
	 * post: inicializa la aplicacion y posteriormente llama al metodo start()
	 * @param args
	 */
	public static void main(String[] args) {
		
		launch(args);
	}
}