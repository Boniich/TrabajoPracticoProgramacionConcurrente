package test;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

import data.QuickSortMutliThreading;
import data.SequentialQuickSort;


//Test comparativo del algoritmo QuickSort secuencial vs
//su version concurrente/paralela (10.000.000 Elementos)

//Algunas cosas a mencionar son:

//1- Se generar 5 test diferentes cada uno arrays de diferentes tamaños
//los tamaños son los siguientes
//1000-> 10.000 -> 100.000 -> 1.000.000 -> 10.000.000


//2- En cada test se cuenta con dos arrays (de diferente tamaño segun el test)
//Donde uno sera la copia de otro. Esto con el fin de trabajar exactamente
//con los mismos datos para que las pruebas sean mas consistentes

//3- Unicamente en el primer test se muestra los array pre y post ordenamientos
//simplemente para demostrar que los algoritmos funcionan correctamente
//mas adelante intentar mostrar tantos valores genera problemas con la consola
//ademas el mayor proposito es mostrar cuanto tarda en realizar el trabajo cada uno



public class TestDiezMillonesElementos {


	private static int tam = 10000000;
	//Se crean dos array una para el ordenamiento secuencial y otro para
		//el concurrente/paralelo
		private static int[] arraySecuencial = new int[tam];
		private static int[] arrayParalelo;
		
		public static void main(String[] args) {
			System.out.println("--- 10.000.000 Elementos ---");
			double arrancoAlgoritmo, finAlgoritmo;
			
			//Se genera la instancia de un objeto de la clase SequentialQuickSort
			//que es quien contiene al metodo secuencial de quick sort
			SequentialQuickSort sequentialAlgorithm = new SequentialQuickSort();
			
			//Se carga de forma aleatorio el array secuencial
			cargarArray();
			//Se genera una copia del array secuencial y se guarda en el array
			//paralelo
			arrayParalelo = Arrays.copyOf(arraySecuencial, tam);

	        //Se mide el tiempo y ordena el array usando el algoritmo secuencial
			 arrancoAlgoritmo = System.nanoTime(); 
			 sequentialAlgorithm.quickSort(0, tam -1,arraySecuencial); 
			 finAlgoritmo = System.nanoTime() - arrancoAlgoritmo;
			
		        //Se muestra el tiempo que se tardo el algoritmo secuencial
	        System.out.println("\n Tiempo secuencial (MicroSeg): "+finAlgoritmo/1000);
	        
	        //La clase ForkJoinPool es el core del framework o modelo ForkJoint
	        //que permite ejecutar las tareas de forma paralela
	        //Dicha clase gestiona un conjuto de hilos trabajadores que
	        //pueden ejecutar tareas
	        ForkJoinPool pool = ForkJoinPool.commonPool();

	        arrancoAlgoritmo = System.nanoTime(); 
	      //Se ejecuta una recursive task
	        pool.invoke(new QuickSortMutliThreading(0, tam - 1, arrayParalelo));
	        finAlgoritmo = System.nanoTime() - arrancoAlgoritmo;
	        
	  	  //Se muestra el tiempo que tardo el algoritmo concurrente
		  System.out.println("\n Tiempo Concurrente (MicroSeg): "+finAlgoritmo/1000);
		  
		}
	
	
	public static void cargarArray() {
		Random random = new Random();
		for (int i = 0; i < tam; i++) {
			arraySecuencial[i] = random.nextInt(tam);
		}
	} 


}
