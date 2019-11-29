package juego;

import static org.junit.Assert.*;
import org.junit.Test;

public class CuatroEnLineaTest {
	
	private CuatroEnLinea juego;

	@Test
	public void crearUnTableroDeLongitud4por4(){
		
		
		juego = new CuatroEnLinea(4, 4, "Juan", "Pedro");
		assertEquals(4, juego.contarColumnas());
		assertEquals(4, juego.contarFilas());
	}
	
	@Test(expected = Error.class)
	public void crearUnTableroDeLongitud0por0(){
		
		new CuatroEnLinea(0, 0, "Juan", "Pedro");
	}
	
	@Test(expected = Error.class)
	public void crearUnTableroDeLongitud50por50(){
		
		new CuatroEnLinea(50, 50, "Juan", "Pedro");
	}
	
	@Test
	public void preguntarSiElJuegoTerminoAlComienzo(){

		
		juego = new CuatroEnLinea(4, 4, "Juan", "Pedro");
		assertEquals(false, juego.termino());
	}
	
	@Test
	public void preguntarSiHayGanadorAlComienzo(){
		
		juego = new CuatroEnLinea(4, 4, "Juan", "Pedro");
		assertEquals(false, juego.hayGanador());
	}
	
	@Test
	public void obtenerGanadorAlComienzo(){
		
		juego = new CuatroEnLinea(4, 4, "Juan", "Pedro");
		assertEquals(null, juego.obtenerGanador());
	}
	
	@Test
	public void soltarFichaEnColumna4(){
	
		juego = new CuatroEnLinea(4, 4, "Juan", "Pedro");
		juego.soltarFicha(3);
		
	}
	
	@Test(expected = Error.class)
	public void soltarFichaEnColumnaFueraDeRango(){
	
		juego = new CuatroEnLinea(4, 4, "Juan", "Pedro");
		juego.soltarFicha(5);
		
	}
	
	@Test(expected = Error.class)
	public void soltarFichaEnColumnaNegativa(){
	
		juego = new CuatroEnLinea(4, 4, "Juan", "Pedro");
		juego.soltarFicha(-1);
	}

	@Test
	public void obtenerGanadorJugadorUno(){
		

		juego = new CuatroEnLinea(4, 4, "Juan", "Pedro");
		juego.soltarFicha(1);
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(1);
		juego.soltarFicha(3);
		juego.soltarFicha(2);
		juego.soltarFicha(4);
		
		assertEquals("Juan", juego.obtenerGanador());
	
	}
	
	@Test
	public void obtenerGanadorJugadorDos(){
		

		juego = new CuatroEnLinea(4, 4, "Juan", "Pedro");
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(4);
		juego.soltarFicha(2);
		juego.soltarFicha(4);
		juego.soltarFicha(2);
		juego.soltarFicha(4);
		juego.soltarFicha(2);
		
		assertEquals("Pedro", juego.obtenerGanador());
	
	}
	
	@Test
	public void obtenerUnEmpate(){
		
		juego = new CuatroEnLinea(4, 4, "Juan", "Pedro");
		juego.soltarFicha(1);
		juego.soltarFicha(1);
		juego.soltarFicha(1);
		juego.soltarFicha(1);
		juego.soltarFicha(3);
		juego.soltarFicha(2);
		juego.soltarFicha(2);
		juego.soltarFicha(2);
		juego.soltarFicha(2);
		juego.soltarFicha(3);
		juego.soltarFicha(3);
		juego.soltarFicha(3);
		juego.soltarFicha(4);
		juego.soltarFicha(4);
		juego.soltarFicha(4);
		juego.soltarFicha(4);
		
		assertEquals(null, juego.obtenerGanador());
		
	}
	
	@Test(expected = Error.class)
	public void crearUnTableroSinNombresDeJugadores(){
		
		new CuatroEnLinea(4,4, "","");
	}
	
	@Test(expected = Error.class)
	public void crearUnTableroConNombreDeUnJugadorInvalido(){
		
		new CuatroEnLinea(4,4, "","Pedro");
	}
	
	@Test
	public void crearUnTableroDelMaximoTamanoPermitido(){
		
		juego = new CuatroEnLinea(8,16, "Juan", "Pedro");
		
		assertEquals(8, juego.contarFilas());
		assertEquals(16,juego.contarColumnas());
		
	}
	
	@Test
	public void colocarUnaFichaRoja(){
		
		juego = new CuatroEnLinea(4,4, "Juan", "Pedro");
		
		juego.soltarFicha(1);
		
		assertEquals(Casillero.ROJO, juego.obtenerCasillero(4, 1));
	}
	
	@Test
	public void colocarUnaFichaAmarilla(){
		
		CuatroEnLinea juego = new CuatroEnLinea(4,4, "Juan", "Pedro");
		
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		
		assertEquals(Casillero.AMARILLO, juego.obtenerCasillero(4, 2));
	}
	
	@Test
	public void colocarUnaFichaAmarillaYQueLlegueHastaEncontrarseConOtraFicha(){
		
		CuatroEnLinea juego = new CuatroEnLinea(4,4, "Juan", "Pedro");
		
		juego.soltarFicha(1);
		juego.soltarFicha(1);
		
		assertEquals(Casillero.AMARILLO, juego.obtenerCasillero(3, 1));
		
	}
	
	@Test
	public void comprobarQueElJugadorRojoNoPuedeColocarFichasDosVeces(){
		
		CuatroEnLinea juego = new CuatroEnLinea(4,4, "Juan", "Pedro");
		
		juego.soltarFicha(1);
		
		assertEquals(false,juego.obtenerJugador());
	}
	
	@Test
	public void comprobarQueElJugadorAmarilloNoPuedeColocarFichasDosVeces(){
		
		juego = new CuatroEnLinea(4,4, "Juan", "Pedro");
		
		juego.soltarFicha(1);
		juego.soltarFicha(1);
		
		assertEquals(true,juego.obtenerJugador());
	}
	
	@Test
	public void comprobarQueElTableroEstaVacioAlInicioDelJuego(){
		
		CuatroEnLinea juego = new CuatroEnLinea(4,4, "Juan", "Pedro");
		
		for(int i = 1; i<=4; i++){
			
			for(int j = 1; j<=4; j++){
			
				assertEquals(Casillero.VACIO, juego.obtenerCasillero(i,j));
			}
			
		}
	
	}
	
	@Test
	
	public void comprobarQueSePintanDeVerdeLasFichasRojasUnaVezFinalizadoLaPartida(){
		
		juego = new CuatroEnLinea(4, 4, "Juan", "Pedro");
		
		juego.soltarFicha(1);
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(1);
		juego.soltarFicha(3);
		juego.soltarFicha(2);
		juego.soltarFicha(4);
		

		assertEquals(Casillero.VERDE, juego.obtenerCasillero(4,1));
		assertEquals(Casillero.VERDE, juego.obtenerCasillero(4,2));
		assertEquals(Casillero.VERDE, juego.obtenerCasillero(4,3));
		assertEquals(Casillero.VERDE, juego.obtenerCasillero(4,4));
		
	}
	
	@Test
	
	public void comprobarQueSePintanDeVerdeLasFichasAmarillasUnaVezFinalizadoLaPartida(){
		
		juego = new CuatroEnLinea(4, 4, "Juan", "Pedro");
		
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(4);
		juego.soltarFicha(2);
		juego.soltarFicha(4);
		juego.soltarFicha(2);
		juego.soltarFicha(4);
		juego.soltarFicha(2);
		

		assertEquals(Casillero.VERDE, juego.obtenerCasillero(1,2));
		assertEquals(Casillero.VERDE, juego.obtenerCasillero(2,2));
		assertEquals(Casillero.VERDE, juego.obtenerCasillero(3,2));
		assertEquals(Casillero.VERDE, juego.obtenerCasillero(4,2));
		
	}
	
	@Test
	public void comprobarQueSePintanDeVerdeLasFichasAmarillasEnDiagonalUnaVezFinalizadoLaPartida(){
		
		juego = new CuatroEnLinea(4, 4, "Juan", "Pedro");
		
		juego.soltarFicha(1);
		juego.soltarFicha(4);
		juego.soltarFicha(3);
		juego.soltarFicha(3);
		juego.soltarFicha(2);
		juego.soltarFicha(2);
		juego.soltarFicha(4);
		juego.soltarFicha(2);
		juego.soltarFicha(1);
		juego.soltarFicha(1);
		juego.soltarFicha(4);
		juego.soltarFicha(1);
		

		assertEquals(Casillero.VERDE, juego.obtenerCasillero(1,1));
		assertEquals(Casillero.VERDE, juego.obtenerCasillero(2,2));
		assertEquals(Casillero.VERDE, juego.obtenerCasillero(3,3));
		assertEquals(Casillero.VERDE, juego.obtenerCasillero(4,4));
		
	}
	
	@Test
	
	public void terminoUnJuegoYEsGanadorElJugadorRojo(){
		

		juego = new CuatroEnLinea(4, 4, "Juan", "Pedro");
		juego.soltarFicha(1);
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(1);
		juego.soltarFicha(3);
		juego.soltarFicha(2);
		juego.soltarFicha(4);
		
		assertEquals(true, juego.termino());
	}
	
	@Test
	
	public void terminoUnJuegoYEsGanadorElJugadorAmarillo(){
		
		juego = new CuatroEnLinea(4, 4, "Juan", "Pedro");
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(4);
		juego.soltarFicha(2);
		juego.soltarFicha(4);
		juego.soltarFicha(2);
		juego.soltarFicha(4);
		juego.soltarFicha(2);
		
		assertEquals(true, juego.termino());
		
	}
	
	@Test
	
	public void terminoElJuegoYEmpataron(){
		
		juego = new CuatroEnLinea(4, 4, "Juan", "Pedro");
		juego.soltarFicha(1);
		juego.soltarFicha(1);
		juego.soltarFicha(1);
		juego.soltarFicha(1);
		juego.soltarFicha(3);
		juego.soltarFicha(2);
		juego.soltarFicha(2);
		juego.soltarFicha(2);
		juego.soltarFicha(2);
		juego.soltarFicha(3);
		juego.soltarFicha(3);
		juego.soltarFicha(3);
		juego.soltarFicha(4);
		juego.soltarFicha(4);
		juego.soltarFicha(4);
		juego.soltarFicha(4);
		
		assertEquals(true, juego.termino());
	}
	
	@Test(expected = Error.class)
	
	public void dosJugadoresTienenElMismoNombre(){
		
		new CuatroEnLinea(4,4, "Pedro", "Pedro");
		
	}
	
	@Test
	
	public void intentarPonerMasFichasDelTamanoDelTableroYQueNoSeSalgaDeRango(){
		
		juego = new CuatroEnLinea (4,4, "Pedro", "Juan");
		
		for(int i = 0; i<5; i++){
			
			juego.soltarFicha(1);
		}
		
	}
	
	@Test
	
	public void soltarFichaEnColumnaCuatroEnUnTableroDe8por8(){
		
		juego = new CuatroEnLinea(8,8, "Pedro", "Juan");
		
		juego.soltarFicha(4);
		
		assertEquals(Casillero.ROJO, juego.obtenerCasillero(8, 4));
		
		
	}

	@Test
	
	public void soltarFichaEnColumna15EnUnTableroDe8por16(){
		
		juego = new CuatroEnLinea(8,16, "Pedro", "Juan");
		
		juego.soltarFicha(15);
		
		assertEquals(Casillero.ROJO, juego.obtenerCasillero(8, 15));
		
		
	}
	
	@Test
	
	public void soltarFichaAmarillaEnColumna15EnUnTableroDe8Por16(){
		
		juego = new CuatroEnLinea(8,16, "Pedro","Juan");
		
		juego.soltarFicha(16);
		juego.soltarFicha(15);
		
		assertEquals(Casillero.AMARILLO, juego.obtenerCasillero(8, 15));
		
	}
	
	@Test
	
	public void obtenerUn4EnLineaEnLosBordes(){
		
		juego = new CuatroEnLinea(5,5, "Pedro","Juan");
		
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(1);
		
		assertEquals(Casillero.VERDE, juego.obtenerCasillero(2, 1));
		assertEquals(Casillero.VERDE, juego.obtenerCasillero(3, 1));
		assertEquals(Casillero.VERDE, juego.obtenerCasillero(4, 1));
		assertEquals(Casillero.VERDE, juego.obtenerCasillero(5, 1));
	
			
	}
	
	@Test
	
	public void probarQueElJugadorUnoEsDelColorRojo(){
		
		juego = new CuatroEnLinea(8, 16, "Pedro", "Juan");
		
		assertEquals(Casillero.ROJO, juego.colorDelJugador1());
	}
	
	@Test
	
	public void probarQueElJugadorDosEsDelColorAmarillo(){
		
		juego = new CuatroEnLinea(8, 16, "Pedro", "Juan");
		
		assertEquals(Casillero.AMARILLO, juego.colorDelJugador2());
	}
	
	@Test
	
	public void obtenerNombreDelJugadorUno(){
		
		juego = new CuatroEnLinea(8, 16, "Pedro", "Juan");
		
		assertEquals("Pedro", juego.obtenerNombreJugador1());
		
	}
	
	@Test
	
	public void obtenerNombreDelJugadorDos(){
		
		juego = new CuatroEnLinea(8, 16, "Pedro", "Juan");
		
		assertEquals("Juan", juego.obtenerNombreJugador2());
		
	}
	
	@Test
	
	public void reiniciarElJuego(){
		
		juego = new CuatroEnLinea(8, 16, "Pedro", "Juan");
		
		juego.reiniciarJuego();
	
		assertEquals(null, juego.obtenerGanador());
		assertFalse(juego.hayGanador());
		assertFalse(juego.termino());
		assertTrue(juego.obtenerJugador());
		
	}
	
	@Test
	
	public void obtenerUnCuatroEnLineaEnLaPrimerFila(){
		
		juego = new CuatroEnLinea(4, 5, "Pedro", "Juan");
		
		juego.soltarFicha(1);
		juego.soltarFicha(1);
		juego.soltarFicha(1);
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(2);
		juego.soltarFicha(2);
		juego.soltarFicha(2);
		juego.soltarFicha(3);
		juego.soltarFicha(3);
		juego.soltarFicha(3);
		juego.soltarFicha(3);
		juego.soltarFicha(5);
		juego.soltarFicha(4);
		juego.soltarFicha(4);
		juego.soltarFicha(4);
		juego.soltarFicha(5);
		juego.soltarFicha(4);
		
		assertEquals(Casillero.VERDE, juego.obtenerCasillero(1, 1));
		assertEquals(Casillero.VERDE, juego.obtenerCasillero(1, 2));
		assertEquals(Casillero.VERDE, juego.obtenerCasillero(1, 3));
		assertEquals(Casillero.VERDE, juego.obtenerCasillero(1, 4));
	}
	
	@Test
	
	public void obtenerUnCuatroEnLineaEnLaMitadDelTablero(){
		
		juego = new CuatroEnLinea(5, 4, "Pedro", "Juan");
		
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(1);
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(2);
		juego.soltarFicha(3);
		juego.soltarFicha(3);
		juego.soltarFicha(4);
		juego.soltarFicha(3);
		juego.soltarFicha(4);
		juego.soltarFicha(4);
		
		assertEquals(Casillero.VERDE, juego.obtenerCasillero(3, 1));
		assertEquals(Casillero.VERDE, juego.obtenerCasillero(3, 2));
		assertEquals(Casillero.VERDE, juego.obtenerCasillero(3, 3));
		assertEquals(Casillero.VERDE, juego.obtenerCasillero(3, 4));
		
	}
	
	@Test
	
	public void obtenerUnCuatroEnLineaEnLaDiagonalDerechaDelTablero(){
		
		juego = new CuatroEnLinea(5, 4, "Pedro", "Juan");
		
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(2);
		juego.soltarFicha(3);
		juego.soltarFicha(4);
		juego.soltarFicha(4);
		juego.soltarFicha(4);
		juego.soltarFicha(3);
		juego.soltarFicha(3);
		juego.soltarFicha(1);
		juego.soltarFicha(4);
		
		
		assertEquals(Casillero.VERDE, juego.obtenerCasillero(2, 4));
		assertEquals(Casillero.VERDE, juego.obtenerCasillero(3, 3));
		assertEquals(Casillero.VERDE, juego.obtenerCasillero(4, 2));
		assertEquals(Casillero.VERDE, juego.obtenerCasillero(5, 1));
	
	}
	
	@Test
	
	public void obtenerUnCuatroEnLineaEnLaDiagonalIzquierdaDelTablero(){
		
		juego = new CuatroEnLinea(5, 4, "Pedro", "Juan");
		
		juego.soltarFicha(4);
		juego.soltarFicha(3);
		juego.soltarFicha(3);
		juego.soltarFicha(2);
		juego.soltarFicha(2);
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(1);
		juego.soltarFicha(1);
		juego.soltarFicha(4);
		juego.soltarFicha(1);
		
		
		assertEquals(Casillero.VERDE, juego.obtenerCasillero(2, 1));
		assertEquals(Casillero.VERDE, juego.obtenerCasillero(3, 2));
		assertEquals(Casillero.VERDE, juego.obtenerCasillero(4, 3));
		assertEquals(Casillero.VERDE, juego.obtenerCasillero(5, 4));
	
	}
	
	@Test
	
	public void ponerUnaFichaEnCadaExtremoDelTableroDe5x5(){
		
		juego = new CuatroEnLinea(5, 5, "Pedro", "Juan");
		
		juego.soltarFicha(1);
		juego.soltarFicha(5);
		
		assertEquals(Casillero.ROJO, juego.obtenerCasillero(5, 1));
		assertEquals(Casillero.AMARILLO, juego.obtenerCasillero(5, 5));
	}
	
	@Test
	
	public void ponerUnaFichaEnCadaExtremoDelTableroDe6x6(){
		
		juego = new CuatroEnLinea(6, 6, "Pedro", "Juan");
		
		juego.soltarFicha(1);
		juego.soltarFicha(6);
		
		assertEquals(Casillero.ROJO, juego.obtenerCasillero(6, 1));
		assertEquals(Casillero.AMARILLO, juego.obtenerCasillero(6, 6));
	}
	
	@Test
	
	public void ponerUnaFichaEnCadaExtremoDelTableroDe7x7(){
		
		juego = new CuatroEnLinea(7, 7, "Pedro", "Juan");
		
		juego.soltarFicha(1);
		juego.soltarFicha(7);
		
		assertEquals(Casillero.ROJO, juego.obtenerCasillero(7, 1));
		assertEquals(Casillero.AMARILLO, juego.obtenerCasillero(7, 7));
	}
	
	@Test
	public void ponerUnaFichaEnCadaExtremoDelTableroDe8x8(){
		
		CuatroEnLinea juego = new CuatroEnLinea(8, 8, "Pedro", "Juan");
		
		juego.soltarFicha(1);
		juego.soltarFicha(8);
		
		assertEquals(Casillero.ROJO, juego.obtenerCasillero(8, 1));
		assertEquals(Casillero.AMARILLO, juego.obtenerCasillero(8, 8));
	}
	
	@Test
	
	public void ponerUnaFichaEnCadaExtremoDelTableroDe8x9(){
		
		juego = new CuatroEnLinea(8, 9, "Pedro", "Juan");
		
		juego.soltarFicha(1);
		juego.soltarFicha(9);
		
		assertEquals(Casillero.ROJO, juego.obtenerCasillero(8, 1));
		assertEquals(Casillero.AMARILLO, juego.obtenerCasillero(8, 9));
	}
	
	@Test
	
	public void ponerUnaFichaEnCadaExtremoDelTableroDe8x10(){
		
		juego = new CuatroEnLinea(8, 10, "Pedro", "Juan");
		
		juego.soltarFicha(1);
		juego.soltarFicha(10);
		
		assertEquals(Casillero.ROJO, juego.obtenerCasillero(8, 1));
		assertEquals(Casillero.AMARILLO, juego.obtenerCasillero(8, 10));
	}
	
	@Test
	
	public void ponerUnaFichaEnCadaExtremoDelTableroDe8x11(){
		
		juego = new CuatroEnLinea(8, 11, "Pedro", "Juan");
		
		juego.soltarFicha(1);
		juego.soltarFicha(11);
		
		assertEquals(Casillero.ROJO, juego.obtenerCasillero(8, 1));
		assertEquals(Casillero.AMARILLO, juego.obtenerCasillero(8, 11));
	}
	
	@Test
	
	public void ponerUnaFichaEnCadaExtremoDelTableroDe8x12(){
		
		juego = new CuatroEnLinea(8, 12, "Pedro", "Juan");
		
		juego.soltarFicha(1);
		juego.soltarFicha(12);
		
		assertEquals(Casillero.ROJO, juego.obtenerCasillero(8, 1));
		assertEquals(Casillero.AMARILLO, juego.obtenerCasillero(8, 12));
	}
	
	@Test
	
	public void ponerUnaFichaEnCadaExtremoDelTableroDe8x13(){
		
		juego = new CuatroEnLinea(8, 13, "Pedro", "Juan");
		
		juego.soltarFicha(1);
		juego.soltarFicha(13);
		
		assertEquals(Casillero.ROJO, juego.obtenerCasillero(8, 1));
		assertEquals(Casillero.AMARILLO, juego.obtenerCasillero(8, 13));
	}
	
	@Test
	
	public void ponerUnaFichaEnCadaExtremoDelTableroDe8x14(){
		
		juego = new CuatroEnLinea(8, 14, "Pedro", "Juan");
		
		juego.soltarFicha(1);
		juego.soltarFicha(14);
		
		assertEquals(Casillero.ROJO, juego.obtenerCasillero(8, 1));
		assertEquals(Casillero.AMARILLO, juego.obtenerCasillero(8, 14));
	}
	
	@Test
	
	public void ponerUnaFichaEnCadaExtremoDelTableroDe8x15(){
		
		juego = new CuatroEnLinea(8, 15, "Pedro", "Juan");
		
		juego.soltarFicha(1);
		juego.soltarFicha(15);
		
		assertEquals(Casillero.ROJO, juego.obtenerCasillero(8, 1));
		assertEquals(Casillero.AMARILLO, juego.obtenerCasillero(8, 15));
	}
	
	@Test
	
	public void ponerUnaFichaEnCadaExtremoDelTableroDe8x16(){
		
		juego = new CuatroEnLinea(8, 16, "Pedro", "Juan");
		
		juego.soltarFicha(1);
		juego.soltarFicha(16);
		
		assertEquals(Casillero.ROJO, juego.obtenerCasillero(8, 1));
		assertEquals(Casillero.AMARILLO, juego.obtenerCasillero(8, 16));
	}
	
	@Test
	
	public void llenarUnTableroDe5x5(){
		

		juego = new CuatroEnLinea(5, 5, "Pedro", "Juan");
		
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(2);
		juego.soltarFicha(3);
		juego.soltarFicha(4);
		juego.soltarFicha(4);
		juego.soltarFicha(4);
		juego.soltarFicha(3);
		juego.soltarFicha(3);
		juego.soltarFicha(1);
		juego.soltarFicha(5);
		juego.soltarFicha(5);
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(5);
		juego.soltarFicha(4);
		juego.soltarFicha(4);
		juego.soltarFicha(3);
		juego.soltarFicha(5);
		juego.soltarFicha(5);
		juego.soltarFicha(1);
		juego.soltarFicha(3);
		juego.soltarFicha(2);
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		
		assertEquals(null, juego.obtenerGanador());
		assertEquals(true, juego.termino());
		
		
	}
	
	@Test
	public void jugarAlMejorDeUnaPartida(){
		
		juego = new CuatroEnLinea(5, 5, "Pedro", "Juan",Casillero.ROJO,Casillero.ROJO,1);
		
		assertEquals(1, juego.obtenerJugarAlMejorDe());
	}
	
	@Test
	public void jugarAlMejorDeTresPartidas(){
		
		juego = new CuatroEnLinea(5, 5, "Pedro", "Juan",Casillero.ROJO,Casillero.ROJO,3);
		
		assertEquals(3, juego.obtenerJugarAlMejorDe());
	}
	
	@Test
	public void jugarAlMejorDeCincoPartidas(){
		
		juego = new CuatroEnLinea(5, 5, "Pedro", "Juan",Casillero.ROJO,Casillero.ROJO,5);
		
		assertEquals(5, juego.obtenerJugarAlMejorDe());
	}
	
	@Test
	public void	elJugador1EligioFichasNegrasYtiraFichasNegras(){
		
		juego = new CuatroEnLinea(5, 5, "Pedro", "Juan",Casillero.NEGRO,Casillero.ROJO);
		
		assertEquals(Casillero.NEGRO, juego.colorDelJugador1());
	}
	
	@Test
	public void	elJugador2EligioFichasNaranjasYtiraFichasNaranjas(){
		
		juego = new CuatroEnLinea(5, 5, "Pedro", "Juan",Casillero.NEGRO,Casillero.NARANJA);
		
		assertEquals(Casillero.NARANJA, juego.colorDelJugador2());
	}
	
	@Test
	public void	siNoSeEspecificanColoresDeFichasLosDefaultSonRojoYAmarillo(){
		
		juego = new CuatroEnLinea(5, 5, "Pedro", "Juan");
		
		assertEquals(Casillero.ROJO, juego.colorDelJugador1());
		assertEquals(Casillero.AMARILLO, juego.colorDelJugador2());
	}
	
	@Test
	public void	siSeJuegaAlMejorDe3CuandoHace4EnLineaJugador1NoSeTerminoElJuego(){
		
		juego = new CuatroEnLinea(5, 5, "Pedro", "Juan",Casillero.NEGRO,Casillero.NARANJA,3);
		
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(1);

		
		assertEquals(1, juego.obtenerGanadasDel1());
		assertEquals(false, juego.obtenerGanadorFinal());
	}
	
	@Test
	public void	siSeJuegaAlMejorDe3CuandoHace2Veces4EnLineaJugador1SeTerminoElJuego(){
		
		juego = new CuatroEnLinea(5, 5, "Pedro", "Juan",Casillero.NEGRO,Casillero.NARANJA,3);
		
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(1);

		juego.reiniciarJuego();
		
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(3);
		juego.soltarFicha(2);
		
		assertEquals(2, juego.obtenerGanadasDel1());
		assertEquals(true, juego.obtenerGanadorFinal());
	}
	
	@Test
	public void	siSeJuegaAlMejorDe3YCadaJugadorGano1NoSeTerminaElJuego(){
		
		juego = new CuatroEnLinea(5, 5, "Pedro", "Juan",Casillero.NEGRO,Casillero.NARANJA,3);
		
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(1);

		juego.reiniciarJuego();
		
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(1);
		
		
		assertEquals(1, juego.obtenerGanadasDel1());
		assertEquals(1, juego.obtenerGanadasDel2());
		assertEquals(false, juego.obtenerGanadorFinal());
	}
	
	@Test
	public void	siSeJuegaAlMejorDe3SePuedeObtenerUnEmpateYNoAfectaLasPartidasGanadasDeCadaJugador(){
		
		juego = new CuatroEnLinea(4, 4, "Pedro", "Juan",Casillero.NEGRO,Casillero.NARANJA,3);
		
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(1);

		juego.reiniciarJuego();
		
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(1);
		
		juego.reiniciarJuego();
		
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(3);
		juego.soltarFicha(4);
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(3);
		juego.soltarFicha(4);
		juego.soltarFicha(4);
		juego.soltarFicha(3);
		juego.soltarFicha(2);
		juego.soltarFicha(1);
		juego.soltarFicha(4);
		juego.soltarFicha(3);
		juego.soltarFicha(2);
		juego.soltarFicha(1);
	
		
		assertEquals(1, juego.obtenerGanadasDel1());
		assertEquals(1, juego.obtenerGanadasDel2());
		assertEquals(false, juego.obtenerGanadorFinal());
	}
	
	@Test
	public void	empatarConUnResultadoDe1a1YLuegoConseguirOtro4EnLineaJugandoAlMejorDe3(){
		
		juego = new CuatroEnLinea(4, 4, "Pedro", "Juan",Casillero.NEGRO,Casillero.NARANJA,3);
		
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(1);

		juego.reiniciarJuego();
		
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(1);
		
		juego.reiniciarJuego();
		
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(3);
		juego.soltarFicha(4);
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(3);
		juego.soltarFicha(4);
		juego.soltarFicha(4);
		juego.soltarFicha(3);
		juego.soltarFicha(2);
		juego.soltarFicha(1);
		juego.soltarFicha(4);
		juego.soltarFicha(3);
		juego.soltarFicha(2);
		juego.soltarFicha(1);
	
		juego.reiniciarJuego();
		
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(1);
		
		assertEquals(2, juego.obtenerGanadasDel1());
		assertEquals(1, juego.obtenerGanadasDel2());
		assertEquals(true, juego.obtenerGanadorFinal());
	}
	
	@Test
	public void	llegarAUnResultadoDe2a2JugandoAlMejorDe5(){
		
		juego = new CuatroEnLinea(4, 4, "Pedro", "Juan",Casillero.NEGRO,Casillero.NARANJA,5);
		
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(1);

		juego.reiniciarJuego();
		
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(3);
		juego.soltarFicha(2);
		
		juego.reiniciarJuego();
		
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(1);

		juego.reiniciarJuego();
		
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(3);
		juego.soltarFicha(2);
		
		juego.reiniciarJuego();
		
		
		assertEquals(2, juego.obtenerGanadasDel1());
		assertEquals(2, juego.obtenerGanadasDel2());
	}
	
	@Test
	public void	probarQueTodaviaNoGanoNadieCuandoElResultadoEs2a2JugandoAlMejorDe5(){
		
		juego = new CuatroEnLinea(4, 4, "Pedro", "Juan",Casillero.NEGRO,Casillero.NARANJA,5);
		
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(1);

		juego.reiniciarJuego();
		
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(3);
		juego.soltarFicha(2);
		
		juego.reiniciarJuego();
		
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(1);

		juego.reiniciarJuego();
		
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(3);
		juego.soltarFicha(2);
		
		juego.reiniciarJuego();
		
		
		assertEquals(2, juego.obtenerGanadasDel1());
		assertEquals(2, juego.obtenerGanadasDel2());
		assertEquals(false, juego.obtenerGanadorFinal());
	}
	
	@Test
	public void	obtenerUnGanadorCuandoNoSeTiroNingunaFicha(){
		
		juego = new CuatroEnLinea(4, 4, "Pedro", "Juan",Casillero.NEGRO,Casillero.NARANJA,5);
	
		assertEquals(false, juego.obtenerGanadorFinal());
	}
	
	@Test
	public void	elJugador1EligioLasFichasAmarillasYPoneEnElTableroFichasAmarillas(){
		
		juego = new CuatroEnLinea(4, 4, "Pedro", "Juan",Casillero.AMARILLO,Casillero.NARANJA,5);
	
		juego.soltarFicha(1);
		
		assertEquals(Casillero.AMARILLO, juego.obtenerCasillero(4,1));
	}	
}
