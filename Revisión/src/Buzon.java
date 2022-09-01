import java.util.ArrayList;

/**
 * La clase buzon permite crear buzones que permiten almacenar mensajes.
 *
 * Cada buzon tiene una capacidad maxima fija con la que es creado.
 * Al insertar un mensaje este se añade en la ultima posicion del arreglo (n-1) y cuando se desea remover un mensaje este se extrae desde el indice 1 (FIFO).
 * En este caso no se implemento la logica de sincronizacion en esta clase, la logica se puede encontrar en los nodos (Nodo, NodoFin, NodoInicio)
 */
public class Buzon {

	//-------------------------------------------------------------------
	//------------------------- ATRIBUTOS -------------------------------
	//-------------------------------------------------------------------

	private int capacidad;
	
	private int cantidad;
	
	private ArrayList<Mensaje> mensajes;

	//-------------------------------------------------------------------
	//----------------------- CONSTRUCTOR -------------------------------
	//-------------------------------------------------------------------

	public Buzon(int capacidad){
		this.capacidad=capacidad;
		mensajes= new ArrayList<Mensaje>();
		cantidad=0;
	}

	//-------------------------------------------------------------------
	//--------------------------- METODOS -------------------------------
	//-------------------------------------------------------------------

	public synchronized void recibir(Mensaje mensaje)
	{
		Debug.print("Agregué el mensaje" + mensaje.darTexto());
		mensajes.add(mensaje);
		cantidad++;
		Debug.print(String.format("Estado del buzon es %d/%d",cantidad,capacidad));
	}
	
	public synchronized Mensaje enviar(){
		Debug.print("Se envió un mensaje");
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
