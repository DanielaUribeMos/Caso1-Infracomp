import java.util.ArrayList;

public class NodoInicio extends Thread{

	public NodoInicio(Buzon buzon, ArrayList<Mensaje> lista) {
		this.buzon = buzon;
		this.lista= lista;
	}

	private Buzon buzon;

	private ArrayList<Mensaje> lista;

	@Override
	public void run(){
		Debug.print("Voy a mandar los mensajes");
		for(int i=0; i<lista.size(); i++)
		{
			while( !buzon.hayCapacidad())
			{
				Thread.yield();
			}

			Debug.print("Mandé mensaje" + i);

			buzon.recibir(lista.get(i));

			synchronized(buzon)
			{
				buzon.notifyAll();
			}
		}


	}



}
