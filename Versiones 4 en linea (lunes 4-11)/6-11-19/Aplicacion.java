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
	
	private int activado = 1;
	

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
//		root = new FlowPane();
		grilla.setAlignment(Pos.CENTER);
		grilla.setHgap(10);
		grilla.setVgap(10);

		Text textoTitulo = new Text(TITULO);
		textoTitulo.setFont(new Font(16));
		
		crearControles();
		
//		final Spinner<Integer> spinner = new Spinner<Integer>();
//		final int initialValue = 3;
//		
//		SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5, initialValue);
//		
//		spinner.setValueFactory(valueFactory);
		
		Button modo1 = new Button("Cambiar");
		Label modo1Label = new Label();
		modo1Label.setText("Activar modo varias\nfilas (DESACTIVADO)");
		
//		root.getChildren().addAll(modo1Label,spinner);
		
		grilla.add(textoTitulo, 0, 0, 2, 1);
		grilla.add(new Label("Jugador Rojo"), 0, 1);
		grilla.add(campoNombreJugadorRojo, 1, 1);
		grilla.add(new Label("Jugador Amarillo"), 0, 2);
		grilla.add(campoNombreJugadorAmarillo, 1, 2);
		grilla.add(new Label("Filas (8 maximo)"), 0, 3);
		grilla.add(campoFilas, 1, 3);
		grilla.add(new Label("Columnas (16 maximo)"), 0, 4);
		grilla.add(campoColumnas, 1, 4);
		grilla.add(modo1Label,0,5);
		grilla.add(modo1, 1, 5, 1, 1);
		grilla.add(botonIniciar, 0, 6, 2, 1);
		
//		grilla.setGridLinesVisible(true);
		
		GridPane.setHalignment(modo1, HPos.CENTER);
		GridPane.setHalignment(botonIniciar, HPos.CENTER);
		GridPane.setHalignment(textoTitulo, HPos.CENTER);
		
		modo1.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				
				if(activado == 1){
					
					modo1Label.setText("Activar modo varias\nfilas (ACTIVADO)");
				}
				else{
					
					modo1Label.setText("Activar modo varias\nfilas (DESACTIVADO)");
				}
				activado *= -1;
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
