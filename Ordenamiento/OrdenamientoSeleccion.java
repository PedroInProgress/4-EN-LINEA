
class OrdenamientoSeleccion {
	
	private int variableAuxiliar;
	
	private int [] vector;
	
	public OrdenamientoSeleccion(int [] vector){
		
		this.vector = vector;
		
		ordenarVector();
	}

	private int [] ordenarVector() {
		
		int indiceMasChico = 0;
		
		for(int i = 0; i < vector.length; i++){
			
			indiceMasChico = i;
			
			for(int j = i+1; j < vector.length; j++){
				
				if(vector[indiceMasChico] > vector [j]){
					
					indiceMasChico = j;
				}				
			}

			variableAuxiliar = vector[indiceMasChico];
			
			vector[indiceMasChico] = vector[i];
			
			vector[i] = variableAuxiliar;				
		}
		
		return vector;
		
	}	
}

