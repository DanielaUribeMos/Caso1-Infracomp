import java.util.concurrent.ThreadLocalRandom;

public class Nodo extends Thread{
	

	private Buzon buzonEntrada;
	
	private Buzon buzonSalida;
	
	private int nivel;
	
	private int proceso;
	
	private Mensaje mensaje;
	
	private boolean finRecibido;
	
	public Nodo(Buzon buzonEntrada, Buzon buzonSalida, int nivel, int proceso) {
		this.buzonEntrada = buzonEntrada;
		this.buzonSalida = buzonSalida;
		this.nivel=nivel;
		this.proceso= proceso;
		this.finRecibido = false;
	}
	
	@Override
	public void run()
	{
		while(!finRecibido){
			
			synchronized(buzonEntrada)
			{
				//Mirar si hay mensajes
				
				//System.out.println("Voy a mirar si hay mensajes" + nivel + proceso);
				while(! buzonEntrada.hayMensaje())
				{
					try {
						buzonEntrada.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				//System.out.println("Me enviaron un mensaje" + nivel + proceso);
				//Me envian mensaje, recibo un mensaje
				mensaje= buzonEntrada.enviar();
				buzonEntrada.notify();
				
			}
			
			//Esperar
			esperar();
			
			//System.out.println("Estoy transformando el mensaje" + nivel + proceso);
			//Transformarse
			transformar(mensaje);
	
			//Envio un mensaje, recibe el mensaje
			synchronized(buzonSalida)
			{
				//System.out.println("Voy a ver si hay capacidad" + nivel + proceso);
				while(! buzonSalida.hayCapacidad())
				{
					try {
						buzonSalida.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				//System.out.println("Mandé un mensaje" + nivel + proceso);
				buzonSalida.recibir(mensaje);
				buzonSalida.notify();
				
			}
		}
		
	}
	
	public void esperar()
	{
		try {
			int tiempo=ThreadLocalRandom.current().nextInt(50, 500); 
			Thread.sleep(tiempo);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public synchronized void transformar(Mensaje mensaje)
	{
		if(! mensaje.darTexto().equals("FIN"))
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
