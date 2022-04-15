package Utilidades;

public class Utilidades {

    public static  final String TABLA_Pedidos = "pedidos";

    public static  final String Campo_cod = "cod";

    public static  final String Campo_platos_pedidos = "platos_pedidos";

    public static  final String Campo_hora = "hora_pedido";

    public static  final String Campo_total = "precio_total";

  public static  final String CREATE_TABLA_PEDIDOS = "CREATE Table "
          +TABLA_Pedidos+"("+Campo_cod+" integer primary key AUTOINCREMENT," +
          ""+Campo_platos_pedidos+" text," +
          ""+Campo_hora+" text," +
          ""+Campo_total+" INTEGER)";

}
