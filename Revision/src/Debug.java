public class Debug {

    /*
    Modo que indica si debug mode esta habilitado.
    Si es verdad se imprimen los prints en el proyecto.
     */
    public static boolean DEBUG_MODE = true;


    /**
     * Prints debug messages if debug mode is on
     * @param mensaje debug message
     */
    public static void print(String mensaje){
        if(DEBUG_MODE){
            System.out.println(mensaje);
        }

    }
}