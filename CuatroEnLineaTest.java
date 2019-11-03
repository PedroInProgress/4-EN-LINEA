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

}
