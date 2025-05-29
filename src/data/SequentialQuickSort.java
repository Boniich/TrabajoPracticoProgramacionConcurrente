package data;

import java.util.Random;

public class SequentialQuickSort {
    public void quickSort(int start, int end, int[] arr) {
    	//cuando el start sea mayor o igual al end
    	//se corta la recursividad, donde el array estara completamente
    	//ordenado
        if (start < end) {
            
        	//Se particiona el array en dos mitades
        	//dejando de un lado los mas pequeños al pivote
        	//y del otro a los mas grandes al pivote
            int pi = partition(start, end,arr);

            //Se realiza un proceso recursivo donde se va
            //ordenando el lado izquierdo y despues el lado derecho
            quickSort(start, pi - 1,arr);
            quickSort(pi + 1, end,arr);
        }
    }
    
	private int partition(int start, int end, int[] arr) {

		//inicializamos las variable i e j con los valores que vengan
		//en start y end
		int i = start, j = end;

		//Se selecciona un pivote de forma random
		int pivoted = new Random().nextInt(j - i) + i;

		//Se toma la ultima posicion del array
		//y se intercambia su lugar con el pivote
		//dejando a este en la ultima posicion
		swap(arr, j, pivoted);
		
		//se reduce j en 1
		j--;

		while (i <= j) {

			//Si el elemento en la posicion i es menor o igual al ultimo elemento
			//que es el pivote de momento, se incrementa i en uno
			if (arr[i] <= arr[end]) {
				i++;
				//si entra en el if, usando el continue evitamos que 
				//el resto del codigo no se ejecute
				continue;
			}

			//Si el elemento en la posicion j es mayor o igual al ultimo elemento
			//que es pivote de momento, se decrementa j
			
			if (arr[j] >= arr[end]) {
				j--;
				//si entra en el if, usando el continue evitamos que 
				//el resto del codigo no se ejecute
				continue;
			}

			//realiza el intercambio de los elementos
			//cuando no se entra en ninguno de los if
			swap(arr, j, i);
			j--;
			i++;
		}

		//Devuelve al pivote a su posicion original
		swap(arr, j+1, end);

		//retorna la posicion del pivote
		return j + 1;
	} 
    
    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
