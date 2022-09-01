import java.util.ArrayList;
import java.util.Scanner;

/*
	CASO 1 (2022-2)
	Daniela Uribe - 20
	Mateo Lopez - 20
	Veronica Escobar -201922197
*/
public class Main {


	/**
	 * El main esta encargado de recibir por consola los valores de el numero de subconjuntos a procesar (N), el tamaño de los buzones intermedios
	 * y el tamaño de los buzones extremos. Despues crea todos los mensajes a mandar, los buffers y los nodos. Una vez todos los elementos de la
	 * arquitectura estan creados e inicializados con los parametros adecuados se prosigue a inicializar todos los nodos y se comienza la ejecucion
	 * del map reduce.
	 */
	public static void main(String[] args) 
	{
		//-------------------------------------------------------------------
		//---------------------------- CONSOLA ------------------------------
		//-------------------------------------------------------------------

		Scanner lector = new Scanner(System.in);
		
		System.out.println("Ingrese la cantidad de subconjuntos (mensajes)");
		
		int N= Integer.parseInt(lector.next());
		
		System.out.println("Ingrese el tamaño de los buzones intermedios.");
		
		int inter= Integer.parseInt(lector.next());
		
		System.out.println("Ingrese el tamaño de los buzones extremos.");
		
		int extrem= Integer.parseInt(lector.next());

		Debug.print("Se ingresaron los datos");

		//-------------------------------------------------------------------
		//-------------------- CREACIÓN DE MENSAJES -------------------------
		//-------------------------------------------------------------------

		ArrayList<Mensaje> mensajes= new ArrayList<Mensaje>();
		
		for(int i=1; i<(N+1); i++){
			Mensaje mensaje= new Mensaje("M" + i);
			Debug.print(mensaje.darTexto());
			mensajes.add(mensaje);
		}
		mensajes.add(new Mensaje("FIN"));
		mensajes.add(new Mensaje("FIN"));
		mensajes.add(new Mensaje("FIN"));

		//-------------------------------------------------------------------
		//--------- CREACIÓN E INICIALIZACIÓN DE BUZONES Y NODOS ------------
		//-------------------------------------------------------------------

		Buzon buzonInicial= new Buzon(extrem);
		NodoInicio nodoInicial= new NodoInicio(buzonInicial, mensajes);
		Debug.print("Se creó el nodo de inicio");
		
		Buzon buzonFinal= new Buzon(extrem);
		NodoFin nodoFinal= new NodoFin(buzonFinal);
		Debug.print("Se creó el nodo de fin");
		
		ArrayList<Nodo> nodos= new ArrayList<Nodo>();
		
		Buzon buzonAnterior = buzonInicial;
		for(int i=0;i<3; i++) {
			buzonAnterior= buzonInicial;
			for(int j=0;j<3; j++) {
				if(j==0){
					Buzon buzon= new Buzon(inter);
					Nodo nodo= new Nodo(buzonInicial, buzon, i+1, j+1);
					nodos.add(nodo);
					buzonAnterior= buzon;
				}
				else if(j==2){
					Nodo nodo= new Nodo(buzonAnterior, buzonFinal, i+1, j+1);
					nodos.add(nodo);
				}
				else{
					Buzon buzon= new Buzon(inter);
					Nodo nodo= new Nodo(buzonAnterior, buzon, i+1, j+1);
					nodos.add(nodo);
					buzonAnterior= buzon;
				}
			}
		}

		Debug.print("Se crearon todos los nodos y los buzones");
		
		for(int i=0; i<nodos.size();i++){
			nodos.get(i).start();
		}
		nodoInicial.start();
		nodoFinal.start();

	}

}

