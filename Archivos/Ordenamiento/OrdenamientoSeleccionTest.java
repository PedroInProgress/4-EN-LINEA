import static org.junit.Assert.*;

import org.junit.Test;


public class OrdenamientoSeleccionTest {

	@Test
	public void probarUnVectorDesordenadoYPositivo() {
		
		int [] vector = new int [] {1,2,0,9,6,2};
		
		OrdenamientoSeleccion o = new OrdenamientoSeleccion(vector);

		assertEquals({0,1,2,2,6,9}, )
	}

}
