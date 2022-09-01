/**
 * Representa a un nodo final, el cual es responsable de recibir y agrupar
 * todos los mensajes en el orden que fueron recibidos
 */
public class NodoFin extends Thread{

	//-------------------------------------------------------------------
	//------------------------- ATRIBUTOS -------------------------------
	//-------------------------------------------------------------------


	private Buzon buzon;

	private int contador;

	private String unificado;

	//-------------------------------------------------------------------
	//----------------------- CONSTRUCTOR -------------------------------
	//-------------------------------------------------------------------

	public NodoFin(Buzon buzon) {
		this.buzon = buzon;
		unificado = "";
		contador = 0;
	}

	//-------------------------------------------------------------------
	//----------------------------- RUN ---------------------------------
	//-------------------------------------------------------------------

	@Override
	public void run()
	{
		Debug.print("Voy a recibir los mensajes");
		while(contador<3)
		{
			while( !buzon.hayMensaje())
			{
				Thread.yield();
			}
			
			Mensaje mensaje= buzon.enviar();
			unificado+= mensaje.darTexto() + " ";
			
			if( mensaje.darTexto().equals("FIN"))
			{
				Debug.print("Nodo fin recbio un FIN");
				contador++;
			}
		}
		
		System.out.println(String.format("Mensaje unificado: %s",unificado));
			
	}

}
