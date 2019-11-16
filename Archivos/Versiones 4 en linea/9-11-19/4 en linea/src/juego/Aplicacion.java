package juego;

import java.nio.file.Paths;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.InnerShadow;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Aplicación del juego Cuatro en Lí­nea.
 * 
 * Punto de entrada del programa.
 * 
 */
public class Aplicacion extends Application {

	public static final String TITULO = "Cuatro en Lí­nea";
	
	private GridPane grilla;
	
	private TextField campoNombreJugadorRojo;
	private TextField campoNombreJugadorAmarillo;

	private TextField campoColumnas;
	private TextField campoFilas;

	private Button botonIniciar;
	
	private boolean activado = true;
	public int alMejorDe = 1;
	
	private Media sonidoBoton = new Media(Paths.get("./res/boton.mp3").toUri().toString());
	
	private Image imagenFondo = new Image(Paths.get("./res/fondo.jpg").toUri().toString());
	
	@Override
	public void start(Stage escenarioPrincipal) {

		crearGrilla();
		
		Scene escena = new Scene(grilla, 400, 300);

		escenarioPrincipal.setScene(escena);
		escenarioPrincipal.setResizable(false);
		escenarioPrincipal.setTitle(TITULO);
		escenarioPrincipal.show();
		
	}

	private void crearGrilla() {

		grilla = new GridPane();
		grilla.setAlignment(Pos.CENTER);
		grilla.setHgap(10);
		grilla.setVgap(10);

		Text textoTitulo = new Text(TITULO);
		textoTitulo.setFont(new Font(16));
		
		crearControles();
		
		Button botonModoDeJuegoActivar = new Button("Activar");
		Button botonModoDeJuegoDesactivar = new Button("Desactivar");
		
		botonModoDeJuegoActivar.setStyle("-fx-background-color: linear-gradient(#444444, #9CD672); -fx-text-fill: white;");
		botonModoDeJuegoDesactivar.setStyle("-fx-background-color: linear-gradient(#444444, #9CD672); -fx-text-fill: white;");
		
//		botonModoDeJuegoActivar.setStyle("-fx-background-color: slateblue; -fx-text-fill: white;");
//		botonModoDeJuegoActivar.setStyle("-fx-text-fill: #006464;"+"-fx-background-color: #DFB951;"+"-fx-border-radius: 20;"+"-fx-background-radius: 20;"+"-fx-padding: 5;");
		
//		Reflection r = new Reflection();
//		
//		Light.Distant light = new Light.Distant();
//        light.setAzimuth(-135.0f);
//        
//        InnerShadow is = new InnerShadow();
//        is.setOffsetX(4.0f);
//        is.setOffsetY(4.0f);
//		
//     
//        
//		Lighting l = new Lighting();
//		l.setLight(light);
		
		
		Label labelBotonModoDeJuego = new Label();
		
//		labelBotonModoDeJuego.setEffect(is);
		
//		FX_BACKGROUND_COLOR_WHITE
//		labelBotonModoDeJuego.setStyle("-fx-min-height: 128;-fx-background-color: white; " + "-fx-border-color: transparent transparent;" + "-fx-border-width: 0 0 0.2 6;"
//		   + "-fx-alignment:CENTER;" + "-fx-padding:16;" + "-fx-effect: dropshadow(three-pass-box, derive(grey, 50%), 2, 0.2, 0, 0);");
		labelBotonModoDeJuego.setStyle("-fx-font-family: 'Roboto Regular';" + "-fx-font-size: 15px;" + "-fx-opacity: 1;"+ "-fx-font-weight: bold;");
//		labelBotonModoDeJuego.setStyle("-fx-font-family: 'Roboto Regular';" + "-fx-font-size: 15px;" + "-fx-opacity: 1;"+ "-fx-font-weight: bold;");

//		labelBotonModoDeJuego.setStyle("-fx-padding: 0 0 0 -1em;");
		labelBotonModoDeJuego.setStyle("-fx-border-color: #aaaaaa; -fx-background-color: #dddddd;");
//		labelBotonModoDeJuego.setStyle("-fx-text-fill: white;");
//		labelBotonModoDeJuego.setStyle("-fx-background-color: linear-gradient(#444444, #9CD672);");
//		labelBotonModoDeJuego.setStyle("-fx-background-color: linear-gradient(#444444, #9CD672);");
//		labelBotonModoDeJuego.setStyle("-fx-background-color: linear-gradient(#444444, #9CD672);");
//		labelBotonModoDeJuego.setStyle("-fx-background-color: linear-gradient(#444444, #9CD672);");
//		labelBotonModoDeJuego.setStyle("-fx-background-color: linear-gradient(#444444, #9CD672);");
//		labelBotonModoDeJuego.setStyle("-fx-background-color: linear-gradient(#444444, #9CD672);");
//		labelBotonModoDeJuego.setStyle("-fx-background-color:POWDERBLUE");
//		labelBotonModoDeJuego.setStyle("-fx-font-size:15px;");
		labelBotonModoDeJuego.setText("Activar modo varias\nfilas (DESACTIVADO)");
		
		Button botonAlMejorDeUno = new Button("  1  ");
		Button botonAlMejorDeTres = new Button("  3  ");
		Button botonAlMejorDeCinco = new Button("  5  ");
		
		Label labelJugadorRojo = new Label("Jugador Rojo");
		Label labelJugadorAmarillo = new Label("Jugador Amarillo");
		Label labelFilas = new Label("Filas (8 maximo)");
		Label labelColumnas = new Label("Columnas (16 maximo)");
		
		botonAlMejorDeUno.setStyle("-fx-background-color: linear-gradient(#444444, #9CD672); -fx-text-fill: white;");
		botonAlMejorDeTres.setStyle("-fx-background-color: linear-gradient(#444444, #9CD672); -fx-text-fill: white;");
		botonAlMejorDeCinco.setStyle("-fx-background-color: linear-gradient(#444444, #9CD672); -fx-text-fill: white;");
		
		
		labelJugadorRojo.setStyle("-fx-font-family: 'Roboto Regular';" + "-fx-font-size: 15px;" + "-fx-opacity: 1;"+ "-fx-font-weight: bold;");
		labelJugadorAmarillo.setStyle("-fx-font-family: 'Roboto Regular';" + "-fx-font-size: 15px;" + "-fx-opacity: 1;"+ "-fx-font-weight: bold;");
		labelFilas.setStyle("-fx-font-family: 'Roboto Regular';" + "-fx-font-size: 15px;" + "-fx-opacity: 1;"+ "-fx-font-weight: bold;");
		labelColumnas.setStyle("-fx-font-family: 'Roboto Regular';" + "-fx-font-size: 15px;" + "-fx-opacity: 1;"+ "-fx-font-weight: bold;");
		
		
		
		campoNombreJugadorRojo.setStyle("-fx-background-color: linear-gradient(#000000, #ffffff); -fx-text-fill: white;");
		campoNombreJugadorAmarillo.setStyle("-fx-background-color: linear-gradient(#000000, #ffffff); -fx-text-fill: white;");
		campoFilas.setStyle("-fx-background-color: linear-gradient(#000000, #ffffff); -fx-text-fill: white;");
		campoColumnas.setStyle("-fx-background-color: linear-gradient(#000000, #ffffff); -fx-text-fill: white;");
		
//		labelBotonModoDeJuego.setStyle("-fx-border-color: #aaaaaa; -fx-background-color: #dddddd;"+"-fx-font-family: 'Roboto Regular';" + "-fx-font-size: 15px;" + "-fx-opacity: 1;"+ "-fx-font-weight: bold;"+"-fx-background-color: linear-gradient(#E5702C, #973B06);"+"-fx-border-radius: 5;"+"-fx-background-radius: 5;");
		labelBotonModoDeJuego.setStyle("-fx-font-family: 'Roboto Regular';" + "-fx-font-size: 15px;" + "-fx-opacity: 1;"+ "-fx-font-weight: bold;");
		
		Label labelbotonAlMejorDe = new Label("Jugar al mejor de: 1");
		
		labelbotonAlMejorDe.setStyle("-fx-font-family: 'Roboto Regular';" + "-fx-font-size: 15px;" + "-fx-opacity: 1;"+ "-fx-font-weight: bold;");

		BackgroundImage fondo = new BackgroundImage(imagenFondo,BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.DEFAULT,
				new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, true, true));
		
		grilla.setBackground(new Background(fondo));
		
		grilla.add(textoTitulo, 0, 0, 2, 1);
		grilla.add(labelJugadorRojo, 0, 1);
		grilla.add(campoNombreJugadorRojo, 1, 1);
		grilla.add(labelJugadorAmarillo, 0, 2);
		grilla.add(campoNombreJugadorAmarillo, 1, 2);
		grilla.add(labelFilas, 0, 3);
		grilla.add(campoFilas, 1, 3);
		grilla.add(labelColumnas, 0, 4);
		grilla.add(campoColumnas, 1, 4);
		grilla.add(labelBotonModoDeJuego,0,5);
		grilla.add(botonModoDeJuegoActivar, 1, 5, 1, 1);
		grilla.add(botonModoDeJuegoDesactivar, 1, 5, 1, 1);
		grilla.add(labelbotonAlMejorDe,0,6);
		grilla.add(botonAlMejorDeUno, 1, 6, 1, 1);
		grilla.add(botonAlMejorDeTres, 1, 6, 1, 1);
		grilla.add(botonAlMejorDeCinco, 1, 6, 1, 1);
		grilla.add(botonIniciar, 0, 7, 2, 1);
		
		
//		grilla.setGridLinesVisible(true);
		GridPane.setHalignment(botonAlMejorDeUno, HPos.LEFT);
		GridPane.setHalignment(botonAlMejorDeTres, HPos.CENTER);
		GridPane.setHalignment(botonAlMejorDeCinco, HPos.RIGHT);

		GridPane.setHalignment(botonModoDeJuegoActivar, HPos.LEFT);
		GridPane.setHalignment(botonModoDeJuegoDesactivar, HPos.RIGHT);
		GridPane.setHalignment(botonIniciar, HPos.CENTER);
		GridPane.setHalignment(textoTitulo, HPos.CENTER);
		
		
		
//		grilla.styleProperty().set("-fx-background-color: #C5EFE2");
		
		botonModoDeJuegoActivar.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {

				activado = true;
				
				if(activado)	labelBotonModoDeJuego.setText("Activar modo varias\nfilas (ACTIVADO)");

				else	labelBotonModoDeJuego.setText("Activar modo varias\nfilas (DESACTIVADO)");
				
				reproducir();
			}
		});
		
		botonModoDeJuegoDesactivar.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				
				activado = false;
				
				if(activado)	labelBotonModoDeJuego.setText("Activar modo varias\nfilas (ACTIVADO)");

				else	labelBotonModoDeJuego.setText("Activar modo varias\nfilas (DESACTIVADO)");
				
				reproducir();
			
			}
		});
		
		botonAlMejorDeUno.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {

				alMejorDe = 1;
				
				if(alMejorDe == 1)	labelbotonAlMejorDe.setText("Jugar al mejor de: 1");
				
				else if(alMejorDe == 3)	labelbotonAlMejorDe.setText("Jugar al mejor de: 3");
				
				else if(alMejorDe == 5)	labelbotonAlMejorDe.setText("Jugar al mejor de: 5");
				
				reproducir();

			}
		});
		
		botonAlMejorDeTres.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {

				alMejorDe = 3;
				
				if(alMejorDe == 1)	labelbotonAlMejorDe.setText("Jugar al mejor de: 1");
				
				else if(alMejorDe == 3)	labelbotonAlMejorDe.setText("Jugar al mejor de: 3");
				
				else if(alMejorDe == 5)	labelbotonAlMejorDe.setText("Jugar al mejor de: 5");
				
				reproducir();
			}
		});
		
		botonAlMejorDeCinco.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {

				alMejorDe = 5;
				
				if(alMejorDe == 1)	labelbotonAlMejorDe.setText("Jugar al mejor de: 1");
				
				else if(alMejorDe == 3)	labelbotonAlMejorDe.setText("Jugar al mejor de: 3");
				
				else if(alMejorDe == 5)	labelbotonAlMejorDe.setText("Jugar al mejor de: 5");
				
				reproducir();
			}
		});
	}

	private void reproducir(){
			
		MediaPlayer mediaPlayer = new MediaPlayer(sonidoBoton);

		mediaPlayer.setVolume(1);
		
		mediaPlayer.play();
	}
	
	private void crearControles() {

		campoNombreJugadorRojo = new TextField("Jugador 1");
		campoNombreJugadorAmarillo = new TextField("Jugador 2");
		
		campoColumnas = new TextField("4");		
		
		campoFilas = new TextField("4");
		
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
		
		CuatroEnLinea juego = new CuatroEnLinea(filas, columnas, 
												nombreJugadorRojo, nombreJugadorAmarillo);
		
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
