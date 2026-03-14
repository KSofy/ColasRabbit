package umg.edu.gt.arreglo;

public class Arreglo {

    public static void main(String[] args) {
        // Definimos los arreglos de prueba según los ejemplos de la imagen
        int[] entrada1 = {1, 2, 3, 4, 5};
        int[] entrada2 = {17, 19, 21};
        int[] entrada3 = {5, 5, 5};

        // Imprimimos el resultado de llamar al método score con cada arreglo
        System.out.println("Salida Ejemplo 1: " + score(entrada1)); 
        System.out.println("Salida Ejemplo 2: " + score(entrada2)); 
        System.out.println("Salida Ejemplo 3: " + score(entrada3)); 
    }

    /**
     * Método que calcula el puntaje total de un arreglo de enteros.
     * @param numbers Arreglo de entrada
     * @return El puntaje total acumulado
     */
    public static int score(int[] numbers) {
        // Variable acumuladora para guardar el puntaje total
        int totalScore = 0;

        // Recorremos cada número (n) dentro del arreglo 'numbers'
        for (int n : numbers) {
            
            // REGLA 1: Si el número es exactamente 5, suma 5 puntos.
            // (Se evalúa primero porque el 5 también es impar, pero tiene prioridad)
            if (n == 5) {
                totalScore += 5;
            } 
            
            // REGLA 2: Si el número es par, suma 1 punto.
            // El operador % (módulo) obtiene el residuo de la división. 
            // Si n % 2 es 0, el número es par (incluye al 0).
            else if (n % 2 == 0) {
                totalScore += 1;
            } 
            
            // REGLA 3: Si no es 5 y no es par, entonces es un número impar común.
            // Suma 3 puntos.
            else {
                totalScore += 3;
            }
        }

        return totalScore;
    }
}

//La complejidad temporal es lineal.

// La complejidad especial es constante.



