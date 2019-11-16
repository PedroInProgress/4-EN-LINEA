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
		
		@SuppressWarnings("unused")
		CuatroEnLinea c = new CuatroEnLinea(0, 0, "Juan", "Pedro");
	}
	
	@Test(expected = Error.class)
	public void crearUnTableroDeLongitud50por50(){
		
		@SuppressWarnings("unused")
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
		
		@SuppressWarnings("unused")
		CuatroEnLinea c = new CuatroEnLinea(4,4, "","");
	}
	
	@Test(expected = Error.class)
	public void crearUnTableroConNombreDeUnJugadorInvalido(){
		
		@SuppressWarnings("unused")
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
		
//		assertEquals(Casillero.VACIO, c.obtenerCasillero(1,1));
//		assertEquals(Casillero.VACIO, c.obtenerCasillero(1,2));
//		assertEquals(Casillero.VACIO, c.obtenerCasillero(1,3));
//		assertEquals(Casillero.VACIO, c.obtenerCasillero(1,4));
//		assertEquals(Casillero.VACIO, c.obtenerCasillero(2,1));
//		assertEquals(Casillero.VACIO, c.obtenerCasillero(2,2));
//		assertEquals(Casillero.VACIO, c.obtenerCasillero(2,3));
//		assertEquals(Casillero.VACIO, c.obtenerCasillero(2,4));
//		assertEquals(Casillero.VACIO, c.obtenerCasillero(3,1));
//		assertEquals(Casillero.VACIO, c.obtenerCasillero(3,2));
//		assertEquals(Casillero.VACIO, c.obtenerCasillero(3,3));
//		assertEquals(Casillero.VACIO, c.obtenerCasillero(3,4));
//		assertEquals(Casillero.VACIO, c.obtenerCasillero(4,1));
//		assertEquals(Casillero.VACIO, c.obtenerCasillero(4,2));
//		assertEquals(Casillero.VACIO, c.obtenerCasillero(4,3));
//		assertEquals(Casillero.VACIO, c.obtenerCasillero(4,4));
	
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
	
//	@Test
//	
//	public void terminoElJuegoYEmpataron(){
//		
//		CuatroEnLinea c = new CuatroEnLinea(4, 4, "Juan", "Pedro");
//		c.soltarFicha(1);
//		c.soltarFicha(1);
//		c.soltarFicha(1);
//		c.soltarFicha(1);
//		c.soltarFicha(3);
//		c.soltarFicha(2);
//		c.soltarFicha(2);
//		c.soltarFicha(2);
//		c.soltarFicha(2);
//		c.soltarFicha(3);
//		c.soltarFicha(3);
//		c.soltarFicha(3);
//		c.soltarFicha(4);
//		c.soltarFicha(4);
//		c.soltarFicha(4);
//		c.soltarFicha(4);
//		
//		assertEquals(true, c.termino());
//	}
	
	@Test(expected = Error.class)
	
	public void dosJugadoresTienenElMismoNombre(){
		
		@SuppressWarnings("unused")
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
	
	public void obtenerUn5EnLinea(){
		
		CuatroEnLinea c = new CuatroEnLinea(5,5, "Pedro","Juan");
		
		c.soltarFicha(1);
		c.soltarFicha(1);
		c.soltarFicha(2);
		c.soltarFicha(2);
		c.soltarFicha(3);
		c.soltarFicha(3);
		c.soltarFicha(5);
		c.soltarFicha(2);
		c.soltarFicha(4);
		
		assertEquals(Casillero.VERDE, c.obtenerCasillero(5, 1));
		assertEquals(Casillero.VERDE, c.obtenerCasillero(5, 2));
		assertEquals(Casillero.VERDE, c.obtenerCasillero(5, 3));
		assertEquals(Casillero.VERDE, c.obtenerCasillero(5, 4));
//		assertEquals(Casillero.VERDE, c.obtenerCasillero(5, 5));
	
			
		}
		
}
