import java.util.ArrayList;

public class Buzon {
	
	private int capacidad;
	
	private int cantidad;
	
	private ArrayList<Mensaje> mensajes;
	
	public Buzon(int capacidad)
	{
		this.capacidad=capacidad;
		mensajes= new ArrayList<Mensaje>();
		cantidad=0;
	}
	
	public void recibir(Mensaje mensaje)
	{
		mensajes.add(mensaje);
		cantidad++;
	}
	
	public Mensaje enviar()
	{
//		System.out.println("Se envió un mensaje");
		cantidad--;
		return mensajes.remove(0);
		
	}
	
	public boolean hayCapacidad()
	{
		return cantidad < capacidad;
	}
	
	public boolean hayMensaje()
	{
		return cantidad>0;
	}

}
