
public class OrdenamientoBurbujeo {
	
	private int variableAuxiliar;
	private int [] vector = new int [] {-3,5,-1,-8,9,4,6,7,1,2,3};
	
	
	public OrdenamientoBurbujeo(){
		
		ordenarVector();
		
		for(int i = 0; i < vector.length; i++){
			System.out.println(vector[i]);
		}
	}
	
	public void ordenarVector(){
		
		for(int i = 0; i< vector.length -1; i++){
		
			for(int j = 0; j < vector.length - i-1; j++){
				

				if(vector[j] > vector[j+1]){
				
					variableAuxiliar = vector[j+1];
					vector[j+1] = vector[j];
					vector[j] = variableAuxiliar;
				}
			}
	
		}
		
	}
	
}
