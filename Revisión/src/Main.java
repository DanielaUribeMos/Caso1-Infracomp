import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) 
	{
		Scanner lector = new Scanner(System.in);
		
		System.out.println("Ingrese la cantidad de subconjuntos (mensajes)");
		
		int N= Integer.parseInt(lector.next());
		
		System.out.println("Ingrese el tamaño de los buzones intermedios.");
		
		int inter= Integer.parseInt(lector.next());
		
		System.out.println("Ingrese el tamaño de los buzones extremos.");
		
		int extrem= Integer.parseInt(lector.next());
		
		//System.out.println("Se ingresaron los datos");
		
		ArrayList<Mensaje> mensajes= new ArrayList<Mensaje>();
		
		for(int i=1; i<(N+1); i++)
		{
			Mensaje mensaje= new Mensaje("M" + i);
			System.out.println(mensaje.darTexto());
			mensajes.add(mensaje);
		}
		mensajes.add(new Mensaje("FIN"));
		mensajes.add(new Mensaje("FIN"));
		mensajes.add(new Mensaje("FIN"));
		
		Buzon buzonInicial= new Buzon(extrem);
		NodoInicio nodoInicial= new NodoInicio(buzonInicial, mensajes);
		//System.out.println("Se creó el nodo de inicio");
		
		Buzon buzonFinal= new Buzon(extrem);
		NodoFin nodoFinal= new NodoFin(buzonFinal);
		//System.out.println("Se creó el nodo de fin");
		
		ArrayList<Nodo> nodos= new ArrayList<Nodo>();
		
		Buzon buzonAnterior = buzonInicial;
		for(int i=0;i<3; i++)
		{
			buzonAnterior= buzonInicial;
			for(int j=0;j<3; j++)
			{
				if(j==0)
				{
					Buzon buzon= new Buzon(inter);
					Nodo nodo= new Nodo(buzonInicial, buzon, i+1, j+1);
					nodos.add(nodo);
					buzonAnterior= buzon;
				}
				else if(j==2)
				{
					Nodo nodo= new Nodo(buzonAnterior, buzonFinal, i+1, j+1);
					nodos.add(nodo);
				}
				else
				{
					Buzon buzon= new Buzon(inter);
					Nodo nodo= new Nodo(buzonAnterior, buzon, i+1, j+1);
					nodos.add(nodo);
					buzonAnterior= buzon;
				}
			}
		}
		
		//System.out.println("Se crearon los nodos y los buzones");
		
//		for(int i=0; i<nodos.size();i++)
//		{
//			System.out.println(nodos.get(i).darNivel() + " " + nodos.get(i).darProceso());
//			System.out.println(nodos.get(i).darBuzonEntrada() + " " + nodos.get(i).darBuzonSalida());
//		}
		
		for(int i=0; i<nodos.size();i++)
		{
			nodos.get(i).start();
		}
		nodoInicial.start();
		nodoFinal.start();

	}

}

