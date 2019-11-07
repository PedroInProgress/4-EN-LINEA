package juego;

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
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
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
	

	@Override
	public void start(Stage escenarioPrincipal) {

		crearGrilla();

		Scene escena = new Scene(grilla, 400, 300);
		escenarioPrincipal.setScene(escena);
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

		Label labelBotonModoDeJuego = new Label();
		labelBotonModoDeJuego.setText("Activar modo varias\nfilas (DESACTIVADO)");
		
		Button botonAlMejorDeUno = new Button("  1  ");
		Button botonAlMejorDeTres = new Button("  3  ");
		Button botonAlMejorDeCinco = new Button("  5  ");
		
		Label labelbotonAlMejorDe = new Label();
		labelbotonAlMejorDe.setText("Jugar al mejor de: 1");

		grilla.add(textoTitulo, 0, 0, 2, 1);
		grilla.add(new Label("Jugador Rojo"), 0, 1);
		grilla.add(campoNombreJugadorRojo, 1, 1);
		grilla.add(new Label("Jugador Amarillo"), 0, 2);
		grilla.add(campoNombreJugadorAmarillo, 1, 2);
		grilla.add(new Label("Filas (8 maximo)"), 0, 3);
		grilla.add(campoFilas, 1, 3);
		grilla.add(new Label("Columnas (16 maximo)"), 0, 4);
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
		
		botonModoDeJuegoActivar.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				
				activado = true;
				
				if(activado)	labelBotonModoDeJuego.setText("Activar modo varias\nfilas (ACTIVADO)");

				else	labelBotonModoDeJuego.setText("Activar modo varias\nfilas (DESACTIVADO)");
			}
		});
		
		botonModoDeJuegoDesactivar.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				
				activado = false;
				
				if(activado)	labelBotonModoDeJuego.setText("Activar modo varias\nfilas (ACTIVADO)");

				else	labelBotonModoDeJuego.setText("Activar modo varias\nfilas (DESACTIVADO)");
			}
		});
		
		botonAlMejorDeUno.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				
				alMejorDe = 1;
				
				if(alMejorDe == 1)	labelbotonAlMejorDe.setText("Jugar al mejor de: 1");
				
				else if(alMejorDe == 3)	labelbotonAlMejorDe.setText("Jugar al mejor de: 3");
				
				else if(alMejorDe == 5)	labelbotonAlMejorDe.setText("Jugar al mejor de: 5");
				
				

			}
		});
		
		botonAlMejorDeTres.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				
				alMejorDe = 3;
				
				if(alMejorDe == 1)	labelbotonAlMejorDe.setText("Jugar al mejor de: 1");
				
				else if(alMejorDe == 3)	labelbotonAlMejorDe.setText("Jugar al mejor de: 3");
				
				else if(alMejorDe == 5)	labelbotonAlMejorDe.setText("Jugar al mejor de: 5");

			}
		});
		
		botonAlMejorDeCinco.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				
				alMejorDe = 5;
				
				if(alMejorDe == 1)	labelbotonAlMejorDe.setText("Jugar al mejor de: 1");
				
				else if(alMejorDe == 3)	labelbotonAlMejorDe.setText("Jugar al mejor de: 3");
				
				else if(alMejorDe == 5)	labelbotonAlMejorDe.setText("Jugar al mejor de: 5");

			}
		});
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
	
	public static void main(String[] args) {
		
		launch(args);
	}
}