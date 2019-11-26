package juego;

/**
 * Esta clase sirve para guardar los indices de las fichas que voy a pintar de color verde
 * @author Pedro
 *
 */
class IndicesQueForman4EnLinea {
	
	private int[] indiceFicha = {0,0};
	
	public IndicesQueForman4EnLinea(int x, int y) {
		
		indiceFicha[0] = x;
		indiceFicha[1] = y;
	}
	
	public int[] obtenerIndices(){
		
		return indiceFicha;
	}
		
}
