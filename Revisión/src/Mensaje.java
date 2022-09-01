
public class Mensaje {
	
	private String texto;
	
	public Mensaje(String texto)
	{
		this.texto=texto;
	}
	
	public void agregar(String adicion)
	{
		this.texto+=adicion;
	}
	
	public String darTexto()
	{
		return texto;
	}

}