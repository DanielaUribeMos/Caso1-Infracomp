public class NodoFin extends Thread{
	
	public NodoFin(Buzon buzon) {
		this.buzon = buzon;
		unificado="";
		contador=0;
	}

	private Buzon buzon;
	
	private int contador;
	
	private String unificado;
	
	@Override
	public void run()
	{
		//System.out.println("Voy a recibir los mensajes");
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
				//System.out.println("Nodo fin recbio un FIN");
				contador++;
			}
		}
		
		System.out.println(unificado);
			
	}

}
