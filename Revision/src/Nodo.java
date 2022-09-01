import java.util.concurrent.ThreadLocalRandom;

/**
 * Representa a un nodo basico, el cual es responsable de procesar y transformar un mensaje dado.
 *
 * Un nodo esta a la espera de un mensaje dado por su buzon de entrada, una vez recibido un mensaje
 * este se procesa de tal forma que se le concatena al mensaje original el nombre del nodo con la notacion T+nivel+proceso.
 * Una vez terminada la transformacion se pasa el mensaje al buzon de salida y se repite el proceso hasta que el nodo
 * recibe un thread "FIN" el cual indica mata al thread.
 */
public class Nodo extends Thread{

	//-------------------------------------------------------------------
	//------------------------- ATRIBUTOS -------------------------------
	//-------------------------------------------------------------------
	private Buzon buzonEntrada;
	
	private Buzon buzonSalida;
	
	private int nivel;
	
	private int proceso;
	
	private Mensaje mensaje;
	
	private boolean finRecibido;

	//-------------------------------------------------------------------
	//----------------------- CONSTRUCTOR -------------------------------
	//-------------------------------------------------------------------

	public Nodo(Buzon buzonEntrada, Buzon buzonSalida, int nivel, int proceso) {
		this.buzonEntrada = buzonEntrada;
		this.buzonSalida = buzonSalida;
		this.nivel=nivel;
		this.proceso= proceso;
		this.finRecibido = false;
		this.mensaje= new Mensaje("MensajeInicio");
	}

	//-------------------------------------------------------------------
	//----------------------------- RUN ---------------------------------
	//-------------------------------------------------------------------

	@Override
	public void run()
	{
		while(!finRecibido){
			
			synchronized(buzonEntrada){
				//Mirar si hay mensajes
				
				Debug.print("Voy a mirar si hay mensajes" + nivel + proceso);
				while(! buzonEntrada.hayMensaje())
				{
					try {
						buzonEntrada.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				Debug.print(Boolean.toString(buzonEntrada.hayMensaje()));
				Debug.print("Me enviaron un mensaje" + nivel + proceso);
			}
			
			mensaje= buzonEntrada.enviar();
			
			synchronized(buzonEntrada){
				buzonEntrada.notify();
			}
			
			//Esperar
			esperar();
			
			//Transformar
			transformar(mensaje);
	
			//Envio un mensaje, recibe el mensaje
			synchronized(buzonSalida)
			{
				Debug.print("Voy a ver si hay capacidad" + nivel + proceso);
				while(! buzonSalida.hayCapacidad())
				{
					try {
						buzonSalida.wait();
					} catch (InterruptedException e){
						e.printStackTrace();
					}
				}
				Debug.print("Mandé un mensaje" + nivel + proceso);
				
			}
			
			buzonSalida.recibir(mensaje);
			
			synchronized(buzonSalida)
			{
				buzonSalida.notify();
			}
		}
		
	}

	//-------------------------------------------------------------------
	//--------------------------- METODOS -------------------------------
	//-------------------------------------------------------------------

	public void esperar(){
		try {
			int tiempo=ThreadLocalRandom.current().nextInt(50, 500); 
			Thread.sleep(tiempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void transformar(Mensaje mensaje)
	{
		if(!mensaje.darTexto().equals("FIN"))
		{
			mensaje.agregar("T" + nivel + proceso);
		}
		else{
			finRecibido = true;
		}
	}
	
	public int darNivel()
	{
		return nivel;
	}
	
	
	public int darProceso()
	{
		return proceso;
	}
	
	public Buzon darBuzonEntrada()
	{
		return buzonEntrada;
	}
	
	public Buzon darBuzonSalida()
	{
		return buzonSalida;
	}

}
