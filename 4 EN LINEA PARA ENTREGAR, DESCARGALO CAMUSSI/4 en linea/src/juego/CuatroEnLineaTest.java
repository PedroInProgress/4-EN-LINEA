package juego;

import static org.junit.Assert.*;
import org.junit.Test;

public class CuatroEnLineaTest {

	@Test
	public void crearUnTableroDeLongitud4por4(){
		
		
		CuatroEnLinea c = new CuatroEnLinea(4, 4, "Juan", "Pedro");
		assertEquals(4, c.contarColumnas());
		assertEquals(4, c.contarFilas());
	}
	
	@Test(expected = Error.class)
	public void crearUnTableroDeLongitud0por0(){
		
		CuatroEnLinea c = new CuatroEnLinea(0, 0, "Juan", "Pedro");
	}
	
	@Test(expected = Error.class)
	public void crearUnTableroDeLongitud50por50(){
		
		CuatroEnLinea c = new CuatroEnLinea(50, 50, "Juan", "Pedro");
	}
	
	@Test
	public void preguntarSiElJuegoTerminoAlComienzo(){

		
		CuatroEnLinea c = new CuatroEnLinea(4, 4, "Juan", "Pedro");
		assertEquals(false, c.termino());
	}
	
	@Test
	public void preguntarSiHayGanadorAlComienzo(){
		
		CuatroEnLinea c = new CuatroEnLinea(4, 4, "Juan", "Pedro");
		assertEquals(false, c.hayGanador());
	}
	
	@Test
	public void obtenerGanadorAlComienzo(){
		
		CuatroEnLinea c = new CuatroEnLinea(4, 4, "Juan", "Pedro");
		assertEquals(null, c.obtenerGanador());
	}
	
	@Test
	public void soltarFichaEnColumna4(){
	
		CuatroEnLinea c = new CuatroEnLinea(4, 4, "Juan", "Pedro");
		c.soltarFicha(3);
		
	}
	
	@Test(expected = Error.class)
	public void soltarFichaEnColumnaFueraDeRango(){
	
		CuatroEnLinea c = new CuatroEnLinea(4, 4, "Juan", "Pedro");
		c.soltarFicha(5);
		
	}
	
	@Test(expected = Error.class)
	public void soltarFichaEnColumnaNegativa(){
	
		CuatroEnLinea c = new CuatroEnLinea(4, 4, "Juan", "Pedro");
		c.soltarFicha(-1);
	}

	@Test
	public void obtenerGanadorJugadorUno(){
		

		CuatroEnLinea c = new CuatroEnLinea(4, 4, "Juan", "Pedro");
		c.soltarFicha(1);
		c.soltarFicha(1);
		c.soltarFicha(2);
		c.soltarFicha(1);
		c.soltarFicha(3);
		c.soltarFicha(2);
		c.soltarFicha(4);
		
		assertEquals("Juan", c.obtenerGanador());
	
	}
	
	@Test
	public void obtenerGanadorJugadorDos(){
		

		CuatroEnLinea c = new CuatroEnLinea(4, 4, "Juan", "Pedro");
		c.soltarFicha(1);
		c.soltarFicha(2);
		c.soltarFicha(4);
		c.soltarFicha(2);
		c.soltarFicha(4);
		c.soltarFicha(2);
		c.soltarFicha(4);
		c.soltarFicha(2);
		
		assertEquals("Pedro", c.obtenerGanador());
	
	}
	
	@Test
	public void obtenerUnEmpate(){
		
		CuatroEnLinea c = new CuatroEnLinea(4, 4, "Juan", "Pedro");
		c.soltarFicha(1);
		c.soltarFicha(1);
		c.soltarFicha(1);
		c.soltarFicha(1);
		c.soltarFicha(3);
		c.soltarFicha(2);
		c.soltarFicha(2);
		c.soltarFicha(2);
		c.soltarFicha(2);
		c.soltarFicha(3);
		c.soltarFicha(3);
		c.soltarFicha(3);
		c.soltarFicha(4);
		c.soltarFicha(4);
		c.soltarFicha(4);
		c.soltarFicha(4);
		
		assertEquals(null, c.obtenerGanador());
		
	}
	
	@Test(expected = Error.class)
	public void crearUnTableroSinNombresDeJugadores(){
		
		CuatroEnLinea c = new CuatroEnLinea(4,4, "","");
	}
	
	@Test(expected = Error.class)
	public void crearUnTableroConNombreDeUnJugadorInvalido(){
		
		CuatroEnLinea c = new CuatroEnLinea(4,4, "","Pedro");
	}
	
	@Test
	public void crearUnTableroDelMaximoTamanoPermitido(){
		
		CuatroEnLinea c = new CuatroEnLinea(8,16, "Juan", "Pedro");
		
		assertEquals(8, c.contarFilas());
		assertEquals(16,c.contarColumnas());
		
	}
	
	@Test
	public void colocarUnaFichaRoja(){
		
		CuatroEnLinea c = new CuatroEnLinea(4,4, "Juan", "Pedro");
		
		c.soltarFicha(1);
		
		assertEquals(Casillero.ROJO, c.obtenerCasillero(4, 1));
	}
	
	@Test
	public void colocarUnaFichaAmarilla(){
		
		CuatroEnLinea c = new CuatroEnLinea(4,4, "Juan", "Pedro");
		
		c.soltarFicha(1);
		c.soltarFicha(2);
		
		assertEquals(Casillero.AMARILLO, c.obtenerCasillero(4, 2));
	}
	
	@Test
	public void colocarUnaFichaAmarillaYQueLlegueHastaEncontrarseConOtraFicha(){
		
		CuatroEnLinea c = new CuatroEnLinea(4,4, "Juan", "Pedro");
		
		c.soltarFicha(1);
		c.soltarFicha(1);
		
		assertEquals(Casillero.AMARILLO, c.obtenerCasillero(3, 1));
		
	}
	
	@Test
	public void comprobarQueElJugadorRojoNoPuedeColocarFichasDosVeces(){
		
		CuatroEnLinea c = new CuatroEnLinea(4,4, "Juan", "Pedro");
		
		c.soltarFicha(1);
		
		assertEquals(false,c.obtenerJugador());
	}
	
	@Test
	public void comprobarQueElJugadorAmarilloNoPuedeColocarFichasDosVeces(){
		
		CuatroEnLinea c = new CuatroEnLinea(4,4, "Juan", "Pedro");
		
		c.soltarFicha(1);
		c.soltarFicha(1);
		
		assertEquals(true,c.obtenerJugador());
	}
	
	@Test
	public void comprobarQueElTableroEstaVacioAlInicioDelJuego(){
		
		CuatroEnLinea c = new CuatroEnLinea(4,4, "Juan", "Pedro");
		
		for(int i = 1; i<=4; i++){
			
			for(int j = 1; j<=4; j++){
			
				assertEquals(Casillero.VACIO, c.obtenerCasillero(i,j));
			}
			
		}
	
	}
	
	@Test
	
	public void comprobarQueSePintanDeVerdeLasFichasRojasUnaVezFinalizadoLaPartida(){
		
		CuatroEnLinea c = new CuatroEnLinea(4, 4, "Juan", "Pedro");
		
		c.soltarFicha(1);
		c.soltarFicha(1);
		c.soltarFicha(2);
		c.soltarFicha(1);
		c.soltarFicha(3);
		c.soltarFicha(2);
		c.soltarFicha(4);
		

		assertEquals(Casillero.VERDE, c.obtenerCasillero(4,1));
		assertEquals(Casillero.VERDE, c.obtenerCasillero(4,2));
		assertEquals(Casillero.VERDE, c.obtenerCasillero(4,3));
		assertEquals(Casillero.VERDE, c.obtenerCasillero(4,4));
		
	}
	
	@Test
	
	public void comprobarQueSePintanDeVerdeLasFichasAmarillasUnaVezFinalizadoLaPartida(){
		
		CuatroEnLinea c = new CuatroEnLinea(4, 4, "Juan", "Pedro");
		
		c.soltarFicha(1);
		c.soltarFicha(2);
		c.soltarFicha(4);
		c.soltarFicha(2);
		c.soltarFicha(4);
		c.soltarFicha(2);
		c.soltarFicha(4);
		c.soltarFicha(2);
		

		assertEquals(Casillero.VERDE, c.obtenerCasillero(1,2));
		assertEquals(Casillero.VERDE, c.obtenerCasillero(2,2));
		assertEquals(Casillero.VERDE, c.obtenerCasillero(3,2));
		assertEquals(Casillero.VERDE, c.obtenerCasillero(4,2));
		
	}
	
	@Test
	public void comprobarQueSePintanDeVerdeLasFichasAmarillasEnDiagonalUnaVezFinalizadoLaPartida(){
		
		CuatroEnLinea c = new CuatroEnLinea(4, 4, "Juan", "Pedro");
		
		c.soltarFicha(1);
		c.soltarFicha(4);
		c.soltarFicha(3);
		c.soltarFicha(3);
		c.soltarFicha(2);
		c.soltarFicha(2);
		c.soltarFicha(4);
		c.soltarFicha(2);
		c.soltarFicha(1);
		c.soltarFicha(1);
		c.soltarFicha(4);
		c.soltarFicha(1);
		

		assertEquals(Casillero.VERDE, c.obtenerCasillero(1,1));
		assertEquals(Casillero.VERDE, c.obtenerCasillero(2,2));
		assertEquals(Casillero.VERDE, c.obtenerCasillero(3,3));
		assertEquals(Casillero.VERDE, c.obtenerCasillero(4,4));
		
	}
	
	@Test
	
	public void terminoUnJuegoYEsGanadorElJugadorRojo(){
		

		CuatroEnLinea c = new CuatroEnLinea(4, 4, "Juan", "Pedro");
		c.soltarFicha(1);
		c.soltarFicha(1);
		c.soltarFicha(2);
		c.soltarFicha(1);
		c.soltarFicha(3);
		c.soltarFicha(2);
		c.soltarFicha(4);
		
		assertEquals(true, c.termino());
	}
	
	@Test
	
	public void terminoUnJuegoYEsGanadorElJugadorAmarillo(){
		
		CuatroEnLinea c = new CuatroEnLinea(4, 4, "Juan", "Pedro");
		c.soltarFicha(1);
		c.soltarFicha(2);
		c.soltarFicha(4);
		c.soltarFicha(2);
		c.soltarFicha(4);
		c.soltarFicha(2);
		c.soltarFicha(4);
		c.soltarFicha(2);
		
		assertEquals(true, c.termino());
		
	}
	
	@Test
	
	public void terminoElJuegoYEmpataron(){
		
		CuatroEnLinea c = new CuatroEnLinea(4, 4, "Juan", "Pedro");
		c.soltarFicha(1);
		c.soltarFicha(1);
		c.soltarFicha(1);
		c.soltarFicha(1);
		c.soltarFicha(3);
		c.soltarFicha(2);
		c.soltarFicha(2);
		c.soltarFicha(2);
		c.soltarFicha(2);
		c.soltarFicha(3);
		c.soltarFicha(3);
		c.soltarFicha(3);
		c.soltarFicha(4);
		c.soltarFicha(4);
		c.soltarFicha(4);
		c.soltarFicha(4);
		
		assertEquals(true, c.termino());
	}
	
	@Test(expected = Error.class)
	
	public void dosJugadoresTienenElMismoNombre(){
		
		CuatroEnLinea c = new CuatroEnLinea(4,4, "Pedro", "Pedro");
		
	}
	
	@Test
	
	public void intentarPonerMasFichasDelTamanoDelTableroYQueNoSeSalgaDeRango(){
		
		CuatroEnLinea c = new CuatroEnLinea (4,4, "Pedro", "Juan");
		
		for(int i = 0; i<5; i++){
			
			c.soltarFicha(1);
		}
		
	}
	
	@Test
	
	public void soltarFichaEnColumnaCuatroEnUnTableroDe8por8(){
		
		CuatroEnLinea c = new CuatroEnLinea(8,8, "Pedro", "Juan");
		
		c.soltarFicha(4);
		
		assertEquals(Casillero.ROJO, c.obtenerCasillero(8, 4));
		
		
	}

	@Test
	
	public void soltarFichaEnColumna15EnUnTableroDe8por16(){
		
		CuatroEnLinea c = new CuatroEnLinea(8,16, "Pedro", "Juan");
		
		c.soltarFicha(15);
		
		assertEquals(Casillero.ROJO, c.obtenerCasillero(8, 15));
		
		
	}
	
	@Test
	
	public void soltarFichaAmarillaEnColumna15EnUnTableroDe8Por16(){
		
		CuatroEnLinea c = new CuatroEnLinea(8,16, "Pedro","Juan");
		
		c.soltarFicha(16);
		c.soltarFicha(15);
		
		assertEquals(Casillero.AMARILLO, c.obtenerCasillero(8, 15));
		
	}
	
	@Test
	
	public void obtenerUn4EnLineaEnLosBordes(){
		
		CuatroEnLinea c = new CuatroEnLinea(5,5, "Pedro","Juan");
		
		c.soltarFicha(1);
		c.soltarFicha(2);
		c.soltarFicha(1);
		c.soltarFicha(2);
		c.soltarFicha(1);
		c.soltarFicha(2);
		c.soltarFicha(1);
		
		assertEquals(Casillero.VERDE, c.obtenerCasillero(2, 1));
		assertEquals(Casillero.VERDE, c.obtenerCasillero(3, 1));
		assertEquals(Casillero.VERDE, c.obtenerCasillero(4, 1));
		assertEquals(Casillero.VERDE, c.obtenerCasillero(5, 1));
	
			
	}
	
	@Test
	
	public void probarQueElJugadorUnoEsDelColorRojo(){
		
		CuatroEnLinea c = new CuatroEnLinea(8, 16, "Pedro", "Juan");
		
		assertEquals(Casillero.ROJO, c.colorDelJugador1());
	}
	
	@Test
	
	public void probarQueElJugadorDosEsDelColorAmarillo(){
		
		CuatroEnLinea c = new CuatroEnLinea(8, 16, "Pedro", "Juan");
		
		assertEquals(Casillero.AMARILLO, c.colorDelJugador2());
	}
	
	@Test
	
	public void obtenerNombreDelJugadorUno(){
		
		CuatroEnLinea c = new CuatroEnLinea(8, 16, "Pedro", "Juan");
		
		assertEquals("Pedro", c.obtenerNombreJugador1());
		
	}
	
	@Test
	
	public void obtenerNombreDelJugadorDos(){
		
		CuatroEnLinea c = new CuatroEnLinea(8, 16, "Pedro", "Juan");
		
		assertEquals("Juan", c.obtenerNombreJugador2());
		
	}
	
	@Test
	
	public void reiniciarElJuego(){
		
		CuatroEnLinea c = new CuatroEnLinea(8, 16, "Pedro", "Juan");
		
		c.reiniciarJuego();
	
		assertEquals(null, c.obtenerGanador());
		assertFalse(c.hayGanador());
		assertFalse(c.termino());
		assertTrue(c.obtenerJugador());
		
	}
	
	@Test
	
	public void obtenerUnCuatroEnLineaEnLaPrimerFila(){
		
		CuatroEnLinea c = new CuatroEnLinea(4, 5, "Pedro", "Juan");
		
		c.soltarFicha(1);
		c.soltarFicha(1);
		c.soltarFicha(1);
		c.soltarFicha(1);
		c.soltarFicha(2);
		c.soltarFicha(2);
		c.soltarFicha(2);
		c.soltarFicha(2);
		c.soltarFicha(3);
		c.soltarFicha(3);
		c.soltarFicha(3);
		c.soltarFicha(3);
		c.soltarFicha(5);
		c.soltarFicha(4);
		c.soltarFicha(4);
		c.soltarFicha(4);
		c.soltarFicha(5);
		c.soltarFicha(4);
		
		assertEquals(Casillero.VERDE, c.obtenerCasillero(1, 1));
		assertEquals(Casillero.VERDE, c.obtenerCasillero(1, 2));
		assertEquals(Casillero.VERDE, c.obtenerCasillero(1, 3));
		assertEquals(Casillero.VERDE, c.obtenerCasillero(1, 4));
	}
	
	@Test
	
	public void obtenerUnCuatroEnLineaEnLaMitadDelTablero(){
		
		CuatroEnLinea c = new CuatroEnLinea(5, 4, "Pedro", "Juan");
		
		c.soltarFicha(1);
		c.soltarFicha(2);
		c.soltarFicha(1);
		c.soltarFicha(1);
		c.soltarFicha(2);
		c.soltarFicha(2);
		c.soltarFicha(3);
		c.soltarFicha(3);
		c.soltarFicha(4);
		c.soltarFicha(3);
		c.soltarFicha(4);
		c.soltarFicha(4);
		
		assertEquals(Casillero.VERDE, c.obtenerCasillero(3, 1));
		assertEquals(Casillero.VERDE, c.obtenerCasillero(3, 2));
		assertEquals(Casillero.VERDE, c.obtenerCasillero(3, 3));
		assertEquals(Casillero.VERDE, c.obtenerCasillero(3, 4));
		
	}
	
	@Test
	
	public void obtenerUnCuatroEnLineaEnLaDiagonalDerechaDelTablero(){
		
		CuatroEnLinea c = new CuatroEnLinea(5, 4, "Pedro", "Juan");
		
		c.soltarFicha(1);
		c.soltarFicha(2);
		c.soltarFicha(2);
		c.soltarFicha(3);
		c.soltarFicha(4);
		c.soltarFicha(4);
		c.soltarFicha(4);
		c.soltarFicha(3);
		c.soltarFicha(3);
		c.soltarFicha(1);
		c.soltarFicha(4);
		
		
		assertEquals(Casillero.VERDE, c.obtenerCasillero(2, 4));
		assertEquals(Casillero.VERDE, c.obtenerCasillero(3, 3));
		assertEquals(Casillero.VERDE, c.obtenerCasillero(4, 2));
		assertEquals(Casillero.VERDE, c.obtenerCasillero(5, 1));
	
	}
	
	@Test
	
	public void obtenerUnCuatroEnLineaEnLaDiagonalIzquierdaDelTablero(){
		
		CuatroEnLinea c = new CuatroEnLinea(5, 4, "Pedro", "Juan");
		
		c.soltarFicha(4);
		c.soltarFicha(3);
		c.soltarFicha(3);
		c.soltarFicha(2);
		c.soltarFicha(2);
		c.soltarFicha(1);
		c.soltarFicha(2);
		c.soltarFicha(1);
		c.soltarFicha(1);
		c.soltarFicha(4);
		c.soltarFicha(1);
		
		
		assertEquals(Casillero.VERDE, c.obtenerCasillero(2, 1));
		assertEquals(Casillero.VERDE, c.obtenerCasillero(3, 2));
		assertEquals(Casillero.VERDE, c.obtenerCasillero(4, 3));
		assertEquals(Casillero.VERDE, c.obtenerCasillero(5, 4));
	
	}
	
	@Test
	
	public void ponerUnaFichaEnCadaExtremoDelTableroDe5x5(){
		
		CuatroEnLinea c = new CuatroEnLinea(5, 5, "Pedro", "Juan");
		
		c.soltarFicha(1);
		c.soltarFicha(5);
		
		assertEquals(Casillero.ROJO, c.obtenerCasillero(5, 1));
		assertEquals(Casillero.AMARILLO, c.obtenerCasillero(5, 5));
	}
	
	@Test
	
	public void ponerUnaFichaEnCadaExtremoDelTableroDe6x6(){
		
		CuatroEnLinea c = new CuatroEnLinea(6, 6, "Pedro", "Juan");
		
		c.soltarFicha(1);
		c.soltarFicha(6);
		
		assertEquals(Casillero.ROJO, c.obtenerCasillero(6, 1));
		assertEquals(Casillero.AMARILLO, c.obtenerCasillero(6, 6));
	}
	
	@Test
	
	public void ponerUnaFichaEnCadaExtremoDelTableroDe7x7(){
		
		CuatroEnLinea c = new CuatroEnLinea(7, 7, "Pedro", "Juan");
		
		c.soltarFicha(1);
		c.soltarFicha(7);
		
		assertEquals(Casillero.ROJO, c.obtenerCasillero(7, 1));
		assertEquals(Casillero.AMARILLO, c.obtenerCasillero(7, 7));
	}
	
	@Test
	public void ponerUnaFichaEnCadaExtremoDelTableroDe8x8(){
		
		CuatroEnLinea c = new CuatroEnLinea(8, 8, "Pedro", "Juan");
		
		c.soltarFicha(1);
		c.soltarFicha(8);
		
		assertEquals(Casillero.ROJO, c.obtenerCasillero(8, 1));
		assertEquals(Casillero.AMARILLO, c.obtenerCasillero(8, 8));
	}
	
	@Test
	
	public void ponerUnaFichaEnCadaExtremoDelTableroDe8x9(){
		
		CuatroEnLinea c = new CuatroEnLinea(8, 9, "Pedro", "Juan");
		
		c.soltarFicha(1);
		c.soltarFicha(9);
		
		assertEquals(Casillero.ROJO, c.obtenerCasillero(8, 1));
		assertEquals(Casillero.AMARILLO, c.obtenerCasillero(8, 9));
	}
	
	@Test
	
	public void ponerUnaFichaEnCadaExtremoDelTableroDe8x10(){
		
		CuatroEnLinea c = new CuatroEnLinea(8, 10, "Pedro", "Juan");
		
		c.soltarFicha(1);
		c.soltarFicha(10);
		
		assertEquals(Casillero.ROJO, c.obtenerCasillero(8, 1));
		assertEquals(Casillero.AMARILLO, c.obtenerCasillero(8, 10));
	}
	
	@Test
	
	public void ponerUnaFichaEnCadaExtremoDelTableroDe8x11(){
		
		CuatroEnLinea c = new CuatroEnLinea(8, 11, "Pedro", "Juan");
		
		c.soltarFicha(1);
		c.soltarFicha(11);
		
		assertEquals(Casillero.ROJO, c.obtenerCasillero(8, 1));
		assertEquals(Casillero.AMARILLO, c.obtenerCasillero(8, 11));
	}
	
	@Test
	
	public void ponerUnaFichaEnCadaExtremoDelTableroDe8x12(){
		
		CuatroEnLinea c = new CuatroEnLinea(8, 12, "Pedro", "Juan");
		
		c.soltarFicha(1);
		c.soltarFicha(12);
		
		assertEquals(Casillero.ROJO, c.obtenerCasillero(8, 1));
		assertEquals(Casillero.AMARILLO, c.obtenerCasillero(8, 12));
	}
	
	@Test
	
	public void ponerUnaFichaEnCadaExtremoDelTableroDe8x13(){
		
		CuatroEnLinea c = new CuatroEnLinea(8, 13, "Pedro", "Juan");
		
		c.soltarFicha(1);
		c.soltarFicha(13);
		
		assertEquals(Casillero.ROJO, c.obtenerCasillero(8, 1));
		assertEquals(Casillero.AMARILLO, c.obtenerCasillero(8, 13));
	}
	
	@Test
	
	public void ponerUnaFichaEnCadaExtremoDelTableroDe8x14(){
		
		CuatroEnLinea c = new CuatroEnLinea(8, 14, "Pedro", "Juan");
		
		c.soltarFicha(1);
		c.soltarFicha(14);
		
		assertEquals(Casillero.ROJO, c.obtenerCasillero(8, 1));
		assertEquals(Casillero.AMARILLO, c.obtenerCasillero(8, 14));
	}
	
	@Test
	
	public void ponerUnaFichaEnCadaExtremoDelTableroDe8x15(){
		
		CuatroEnLinea c = new CuatroEnLinea(8, 15, "Pedro", "Juan");
		
		c.soltarFicha(1);
		c.soltarFicha(15);
		
		assertEquals(Casillero.ROJO, c.obtenerCasillero(8, 1));
		assertEquals(Casillero.AMARILLO, c.obtenerCasillero(8, 15));
	}
	
	@Test
	
	public void ponerUnaFichaEnCadaExtremoDelTableroDe8x16(){
		
		CuatroEnLinea c = new CuatroEnLinea(8, 16, "Pedro", "Juan");
		
		c.soltarFicha(1);
		c.soltarFicha(16);
		
		assertEquals(Casillero.ROJO, c.obtenerCasillero(8, 1));
		assertEquals(Casillero.AMARILLO, c.obtenerCasillero(8, 16));
	}
	
	@Test
	
	public void llenarUnTableroDe5x5(){
		

		CuatroEnLinea c = new CuatroEnLinea(5, 5, "Pedro", "Juan");
		
		c.soltarFicha(1);
		c.soltarFicha(2);
		c.soltarFicha(2);
		c.soltarFicha(3);
		c.soltarFicha(4);
		c.soltarFicha(4);
		c.soltarFicha(4);
		c.soltarFicha(3);
		c.soltarFicha(3);
		c.soltarFicha(1);
		c.soltarFicha(5);
		c.soltarFicha(5);
		c.soltarFicha(1);
		c.soltarFicha(2);
		c.soltarFicha(5);
		c.soltarFicha(4);
		c.soltarFicha(4);
		c.soltarFicha(3);
		c.soltarFicha(5);
		c.soltarFicha(5);
		c.soltarFicha(1);
		c.soltarFicha(3);
		c.soltarFicha(2);
		c.soltarFicha(1);
		c.soltarFicha(2);
		
		assertEquals(null, c.obtenerGanador());
		assertEquals(true, c.termino());
		
		
	}
	
	@Test
	public void jugarAlMejorDeUnaPartida(){
		
		CuatroEnLinea c = new CuatroEnLinea(5, 5, "Pedro", "Juan",Casillero.ROJO,Casillero.ROJO,1);
		
		assertEquals(1, c.obtenerJugarAlMejorDe());
	}
	
	@Test
	public void jugarAlMejorDeTresPartidas(){
		
		CuatroEnLinea c = new CuatroEnLinea(5, 5, "Pedro", "Juan",Casillero.ROJO,Casillero.ROJO,3);
		
		assertEquals(3, c.obtenerJugarAlMejorDe());
	}
	
	@Test
	public void jugarAlMejorDeCincoPartidas(){
		
		CuatroEnLinea c = new CuatroEnLinea(5, 5, "Pedro", "Juan",Casillero.ROJO,Casillero.ROJO,5);
		
		assertEquals(5, c.obtenerJugarAlMejorDe());
	}
	
	@Test
	public void	elJugador1EligioFichasNegrasYtiraFichasNegras(){
		
		CuatroEnLinea c = new CuatroEnLinea(5, 5, "Pedro", "Juan",Casillero.NEGRO,Casillero.ROJO);
		
		assertEquals(Casillero.NEGRO, c.colorDelJugador1());
	}
	
	@Test
	public void	elJugador2EligioFichasNaranjasYtiraFichasNaranjas(){
		
		CuatroEnLinea c = new CuatroEnLinea(5, 5, "Pedro", "Juan",Casillero.NEGRO,Casillero.NARANJA);
		
		assertEquals(Casillero.NARANJA, c.colorDelJugador2());
	}
	
	@Test
	public void	siNoSeEspecificanColoresDeFichasLosDefaultSonRojoYAmarillo(){
		
		CuatroEnLinea c = new CuatroEnLinea(5, 5, "Pedro", "Juan");
		
		assertEquals(Casillero.ROJO, c.colorDelJugador1());
		assertEquals(Casillero.AMARILLO, c.colorDelJugador2());
	}
	
	@Test
	public void	siSeJuegaAlMejorDe3CuandoHace4EnLineaJugador1NoSeTerminoElJuego(){
		
		CuatroEnLinea c = new CuatroEnLinea(5, 5, "Pedro", "Juan",Casillero.NEGRO,Casillero.NARANJA,3);
		
		c.soltarFicha(1);
		c.soltarFicha(2);
		c.soltarFicha(1);
		c.soltarFicha(2);
		c.soltarFicha(1);
		c.soltarFicha(2);
		c.soltarFicha(1);

		
		assertEquals(1, c.obtenerGanadasDel1());
		assertEquals(false, c.obtenerGanadorFinal());
	}
	
	@Test
	public void	siSeJuegaAlMejorDe3CuandoHace2Veces4EnLineaJugador1SeTerminoElJuego(){
		
		CuatroEnLinea c = new CuatroEnLinea(5, 5, "Pedro", "Juan",Casillero.NEGRO,Casillero.NARANJA,3);
		
		c.soltarFicha(1);
		c.soltarFicha(2);
		c.soltarFicha(1);
		c.soltarFicha(2);
		c.soltarFicha(1);
		c.soltarFicha(2);
		c.soltarFicha(1);

		c.reiniciarJuego();
		
		c.soltarFicha(1);
		c.soltarFicha(2);
		c.soltarFicha(1);
		c.soltarFicha(2);
		c.soltarFicha(1);
		c.soltarFicha(2);
		c.soltarFicha(1);
		
		assertEquals(2, c.obtenerGanadasDel1());
		assertEquals(true, c.obtenerGanadorFinal());
	}
	
	@Test
	public void	siSeJuegaAlMejorDe3YCadaJugadorGano1NoSeTerminaElJuego(){
		
		CuatroEnLinea c = new CuatroEnLinea(5, 5, "Pedro", "Juan",Casillero.NEGRO,Casillero.NARANJA,3);
		
		c.soltarFicha(1);
		c.soltarFicha(2);
		c.soltarFicha(1);
		c.soltarFicha(2);
		c.soltarFicha(1);
		c.soltarFicha(2);
		c.soltarFicha(1);

		c.reiniciarJuego();
		
		c.soltarFicha(1);
		c.soltarFicha(2);
		c.soltarFicha(1);
		c.soltarFicha(2);
		c.soltarFicha(1);
		c.soltarFicha(2);
		c.soltarFicha(3);
		c.soltarFicha(2);
		
		
		assertEquals(1, c.obtenerGanadasDel1());
		assertEquals(1, c.obtenerGanadasDel2());
		assertEquals(false, c.obtenerGanadorFinal());
	}
	
	@Test
	public void	siSeJuegaAlMejorDe3SePuedeObtenerUnEmpateYNoAfectaLasPartidasGanadasDeCadaJugador(){
		
		CuatroEnLinea c = new CuatroEnLinea(4, 4, "Pedro", "Juan",Casillero.NEGRO,Casillero.NARANJA,3);
		
		c.soltarFicha(1);
		c.soltarFicha(2);
		c.soltarFicha(1);
		c.soltarFicha(2);
		c.soltarFicha(1);
		c.soltarFicha(2);
		c.soltarFicha(1);

		c.reiniciarJuego();
		
		c.soltarFicha(1);
		c.soltarFicha(2);
		c.soltarFicha(1);
		c.soltarFicha(2);
		c.soltarFicha(1);
		c.soltarFicha(2);
		c.soltarFicha(3);
		c.soltarFicha(2);
		
		c.reiniciarJuego();
		
		c.soltarFicha(1);
		c.soltarFicha(2);
		c.soltarFicha(3);
		c.soltarFicha(4);
		c.soltarFicha(1);
		c.soltarFicha(2);
		c.soltarFicha(3);
		c.soltarFicha(4);
		c.soltarFicha(4);
		c.soltarFicha(3);
		c.soltarFicha(2);
		c.soltarFicha(1);
		c.soltarFicha(4);
		c.soltarFicha(3);
		c.soltarFicha(2);
		c.soltarFicha(1);
	
		
		assertEquals(1, c.obtenerGanadasDel1());
		assertEquals(1, c.obtenerGanadasDel2());
		assertEquals(false, c.obtenerGanadorFinal());
	}
	
	@Test
	public void	empatarConUnResultadoDe1a1YLuegoConseguirOtro4EnLineaJugandoAlMejorDe3(){
		
		CuatroEnLinea c = new CuatroEnLinea(4, 4, "Pedro", "Juan",Casillero.NEGRO,Casillero.NARANJA,3);
		
		c.soltarFicha(1);
		c.soltarFicha(2);
		c.soltarFicha(1);
		c.soltarFicha(2);
		c.soltarFicha(1);
		c.soltarFicha(2);
		c.soltarFicha(1);

		c.reiniciarJuego();
		
		c.soltarFicha(1);
		c.soltarFicha(2);
		c.soltarFicha(1);
		c.soltarFicha(2);
		c.soltarFicha(1);
		c.soltarFicha(2);
		c.soltarFicha(3);
		c.soltarFicha(2);
		
		c.reiniciarJuego();
		
		c.soltarFicha(1);
		c.soltarFicha(2);
		c.soltarFicha(3);
		c.soltarFicha(4);
		c.soltarFicha(1);
		c.soltarFicha(2);
		c.soltarFicha(3);
		c.soltarFicha(4);
		c.soltarFicha(4);
		c.soltarFicha(3);
		c.soltarFicha(2);
		c.soltarFicha(1);
		c.soltarFicha(4);
		c.soltarFicha(3);
		c.soltarFicha(2);
		c.soltarFicha(1);
	
		c.reiniciarJuego();
		
		c.soltarFicha(1);
		c.soltarFicha(2);
		c.soltarFicha(1);
		c.soltarFicha(2);
		c.soltarFicha(1);
		c.soltarFicha(2);
		c.soltarFicha(3);
		c.soltarFicha(2);
		
		assertEquals(1, c.obtenerGanadasDel1());
		assertEquals(2, c.obtenerGanadasDel2());
		assertEquals(true, c.obtenerGanadorFinal());
	}
	
	@Test
	public void	llegarAUnResultadoDe2a2JugandoAlMejorDe5(){
		
		CuatroEnLinea c = new CuatroEnLinea(4, 4, "Pedro", "Juan",Casillero.NEGRO,Casillero.NARANJA,5);
		
		c.soltarFicha(1);
		c.soltarFicha(2);
		c.soltarFicha(1);
		c.soltarFicha(2);
		c.soltarFicha(1);
		c.soltarFicha(2);
		c.soltarFicha(1);

		c.reiniciarJuego();
		
		c.soltarFicha(1);
		c.soltarFicha(2);
		c.soltarFicha(1);
		c.soltarFicha(2);
		c.soltarFicha(1);
		c.soltarFicha(2);
		c.soltarFicha(3);
		c.soltarFicha(2);
		
		c.reiniciarJuego();
		
		c.soltarFicha(1);
		c.soltarFicha(2);
		c.soltarFicha(1);
		c.soltarFicha(2);
		c.soltarFicha(1);
		c.soltarFicha(2);
		c.soltarFicha(1);

		c.reiniciarJuego();
		
		c.soltarFicha(1);
		c.soltarFicha(2);
		c.soltarFicha(1);
		c.soltarFicha(2);
		c.soltarFicha(1);
		c.soltarFicha(2);
		c.soltarFicha(3);
		c.soltarFicha(2);
		
		c.reiniciarJuego();
		
		
		assertEquals(2, c.obtenerGanadasDel1());
		assertEquals(2, c.obtenerGanadasDel2());
	}
	
	@Test
	public void	probarQueTodaviaNoGanoNadieCuandoElResultadoEs2a2JugandoAlMejorDe5(){
		
		CuatroEnLinea c = new CuatroEnLinea(4, 4, "Pedro", "Juan",Casillero.NEGRO,Casillero.NARANJA,5);
		
		c.soltarFicha(1);
		c.soltarFicha(2);
		c.soltarFicha(1);
		c.soltarFicha(2);
		c.soltarFicha(1);
		c.soltarFicha(2);
		c.soltarFicha(1);

		c.reiniciarJuego();
		
		c.soltarFicha(1);
		c.soltarFicha(2);
		c.soltarFicha(1);
		c.soltarFicha(2);
		c.soltarFicha(1);
		c.soltarFicha(2);
		c.soltarFicha(3);
		c.soltarFicha(2);
		
		c.reiniciarJuego();
		
		c.soltarFicha(1);
		c.soltarFicha(2);
		c.soltarFicha(1);
		c.soltarFicha(2);
		c.soltarFicha(1);
		c.soltarFicha(2);
		c.soltarFicha(1);

		c.reiniciarJuego();
		
		c.soltarFicha(1);
		c.soltarFicha(2);
		c.soltarFicha(1);
		c.soltarFicha(2);
		c.soltarFicha(1);
		c.soltarFicha(2);
		c.soltarFicha(3);
		c.soltarFicha(2);
		
		c.reiniciarJuego();
		
		
		assertEquals(2, c.obtenerGanadasDel1());
		assertEquals(2, c.obtenerGanadasDel2());
		assertEquals(false, c.obtenerGanadorFinal());
	}
	
	@Test
	public void	obtenerUnGanadorCuandoNoSeTiroNingunaFicha(){
		
		CuatroEnLinea c = new CuatroEnLinea(4, 4, "Pedro", "Juan",Casillero.NEGRO,Casillero.NARANJA,5);
	
		assertEquals(false, c.obtenerGanadorFinal());
	}
	
	@Test
	public void	elJugador1EligioLasFichasAmarillasYPoneEnElTableroFichasAmarillas(){
		
		CuatroEnLinea c = new CuatroEnLinea(4, 4, "Pedro", "Juan",Casillero.AMARILLO,Casillero.NARANJA,5);
	
		c.soltarFicha(1);
		
		assertEquals(Casillero.AMARILLO, c.obtenerCasillero(4,1));
	}	
}
