package data;

import java.util.Random;
import java.util.concurrent.RecursiveTask;


//--- Framework o modelo Fork-join ---

//Para este algoritmo, en cuanto a lo que es el manejo de lo paralelo o concurrente
//se utiliza el modelo o framework llamado
//Fork-join donse se realiza una bifurcacion del flujo de ejecucion
//en varios tareas, que se ejecutaran en paraleo

//Una vez las tareas son realizadas estan son reunidas y el flujo se vuelve
//secuencial. Este proceso se repite las veces que se necesite


// --- RecursiveTask ---
//Esta es una clase abstracta que permite la realizar tareas recursivas
//subdiviendolas en sub tareas que podran ser ejecutas en forma
//concurrente o paralela
//Esta clase es una sub clase de la clase ForkJoinTask.
//Esto significa que esta clase forma parte del framework o modelo 
//ForkJoin



public class QuickSortMutliThreading extends RecursiveTask<Integer> {

    int start, end;
    int[] arr;

    private int partition(int start, int end,
                        int[] arr)
    {

		//inicializamos las variable i e j con los valores que vengan
		//en start y end
        int i = start, j = end;

      //Se selecciona un pivote de forma random
        int pivoted = new Random()
                         .nextInt(j - i)
                     + i;

		//Se toma la ultima posicion del array
		//y se intercambia su lugar con el pivote
		//dejando a este en la ultima posicion
        int t = arr[j];
        arr[j] = arr[pivoted];
        arr[pivoted] = t;
        
      //se reduce j en 1 porque no queremos evaluar o trabajar con el pivote
        j--;

        while (i <= j) {

			//Si el elemento en la posicion i es menor o igual al ultimo elemento
			//que es el pivote de momento, se incrementa i en uno
            if (arr[i] <= arr[end]) {
                i++;
				//si entra en el if, usando el continue evitamos que 
				//el resto del codigo se ejecute
                continue;
            }

			//Si el elemento en la posicion j es mayor o igual al ultimo elemento
			//que es pivote de momento, se decrementa j
            if (arr[j] >= arr[end]) {
                j--;
				//si entra en el if, usando el continue evitamos que 
				//el resto del codigo se ejecute
                continue;
            }

			//realiza el intercambio de los elementos
			//cuando no se entra en ninguno de los if
            t = arr[j];
            arr[j] = arr[i];
            arr[i] = t;
            j--;
            i++;
        }

      //Devuelve al pivote a su posicion original
        t = arr[j + 1];
        arr[j + 1] = arr[end];
        arr[end] = t;
      //retorna la posicion del pivote
        return j + 1;
    }

    //Definimos el constructor de la clase
    public QuickSortMutliThreading(int start,
                                   int end,
                                   int[] arr)
    {
        this.arr = arr;
        this.start = start;
        this.end = end;
    }

    //Computer es un metodo abstracto donde defineremos la tarea que 
    //sera subdivida en sub tareas y luego unica dichas subtareas
    
    @Override
    protected Integer compute()
    {
    	//Cuando start sea mayor o igual a end se corta la recursividad
        if (start >= end)
            return null;

    	//Se particiona el array en dos mitades
    	//dejando de un lado los mas pequeños al pivote
    	//y del otro a los mas grandes al pivote
        
        int p = partition(start, end, arr);

        //Dividimos el array resultante en uno izquierdo que 
        //contendra todos los elementos menores al pivote
        QuickSortMutliThreading left
            = new QuickSortMutliThreading(start,
                                          p - 1,
                                          arr);

        //y en otro, que contendra todos los elementos mayores al pivote
        QuickSortMutliThreading right
            = new QuickSortMutliThreading(p + 1,
                                          end,
                                          arr);

        //Lanza la ejecucion paralela de una tarea
        left.fork();
        
        right.compute(); //Se ejecuta en el hilo principal

        //Se toma el resultado de la tarea que se ejecuto en paralelo
        left.join();
        
        //El patron fork -> compute es una estrategia que se puede elegir
        //en este framework donde solo una parte o una serie de tareas
        //seran ejecutadas de forma concurrente o paralela
        
        //mientras que usando compute() desde la otra parte o tareas
        //se ejecutara de forma secuencial
        
        return null;
    }
    
}

