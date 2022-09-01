/**
 * Representa un mensaje tipo String en el mundo.
 *
 * Habilita metodos para modificar el mensaje al concatenarle una nueva cadena al final de este.
 */
public class Mensaje {

	//-------------------------------------------------------------------
	//------------------------- ATRIBUTOS -------------------------------
	//-------------------------------------------------------------------
	private String texto;


	//-------------------------------------------------------------------
	//----------------------- CONSTRUCTOR -------------------------------
	//-------------------------------------------------------------------

	public Mensaje(String texto)
	{
		this.texto=texto;
	}

	//-------------------------------------------------------------------
	//--------------------------- METODOS -------------------------------
	//-------------------------------------------------------------------

	public void agregar(String adicion)
	{
		this.texto+=adicion;
	}
	
	public String darTexto()
	{
		return texto;
	}

}