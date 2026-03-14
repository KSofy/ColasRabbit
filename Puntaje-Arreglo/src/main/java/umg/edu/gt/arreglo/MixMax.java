package umg.edu.gt.arreglo;


public class MixMax {

	public static void main(String[] args) {
		int [] numeros = {7,1,6,4,3,5,2};
		
		//INICIALIZACION DE VARIABLES, PRIMER Y SEGUNDO MAYOR, PRIMER Y SEGUNDO MENOR
		//Se utilizan valores extremos para que cualquier numero del arreglo los pueda reemplazar

		int max1 = Integer.MIN_VALUE;
		int max2 = Integer.MIN_VALUE;
		int min1 = Integer.MAX_VALUE;
		int min2 = Integer.MAX_VALUE;
		
		//Se utilizo for-each para recorrer cada numero n del arreglo numeros

		for (int n : numeros) {
			
		//Si n es mayor que el maximo actual (max1)
		//El valor anterior de max1 pasa a max2
		//max1 se actualiza con el nuevo numero
		//Si n no es el mayor pero es mayor que max2 (y distinto de max1) se actualiza max2

			if (n > max1) {
				 max2 = max1;
				 max1 = n;
			} else if (n > max2 && n != max1 ) {
				max2 = n;
			}
		//Si n es menor que el minimo actual (min1):
		//El valor anterior de min1 pasa a min2.
		//min1 se actualiza con el nuevo numero.
		//Si n no es el menor pero es menor que min2 (y distinto de min1) se actualiza min2.

			if (n < min1) {
				 min2 = min1;
				 min1 = n;
			} else if (n < min2 && n != min1 ) {
				min2 = n;
			}
		}
		
		System.out.println("	Ejercicio Algoritmico: \n");
		System.out.println("Segundo mayor: " + max2);
		System.out.println("Segundo menor: " + min2);
	}

}
