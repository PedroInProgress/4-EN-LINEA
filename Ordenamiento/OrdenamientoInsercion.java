 
public class OrdenamientoInsercion {

	private int variableAuxiliar;
	private int [] vector = new int [] {5,3,6,4,1,-4,0,1,-6};
	
	public OrdenamientoInsercion(){
		
		ordenarVector();
		for(int i = 0; i < vector.length; i++){
			System.out.println(vector[i]);
		}
		
	}
	
	public void ordenarVector(){
		
		int indiceMovil =0;
		int indiceNumero = 0;
		
		
		for(int i = 1; i<vector.length; i++){
		
			indiceNumero = i;
			variableAuxiliar = vector[i];
			
			for(int j = 0; j < i; j++){

				if(vector[i] < vector[j]){
					
					indiceMovil = j;
					
					for(int k = indiceNumero; k > indiceMovil; k --){
						
						vector[k] = vector[k-1];
						
					}
					
					vector[indiceMovil] = variableAuxiliar;
					break;
				}
				
			}
	
		}
	}
	
	
	
}
