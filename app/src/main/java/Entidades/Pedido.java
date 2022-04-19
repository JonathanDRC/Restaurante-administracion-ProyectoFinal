package Entidades;

import android.os.Build;

import androidx.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.O)
public class Pedido {

    private Integer cod;
    private String  platos_pedidos;
    private String hora_pedido;
    private String Fecha_pedido;
    private Integer Precio_Total;

    public Pedido(Integer cod, String platos_pedidos,String Fecha_pedido, String hora_pedido) {
        this.cod = cod;
        this.platos_pedidos = platos_pedidos;
        this.Fecha_pedido  = Fecha_pedido;
        this.hora_pedido = hora_pedido;
    }

    public Pedido (){

    }

    public Integer getCod() {
        return cod;
    }

    public String getPlatos_pedidos() {
        return platos_pedidos;
    }

    public String getFecha_pedido() { return Fecha_pedido; }

    public String getHora_pedido() {
        return hora_pedido;
    }

    public Integer getPrecio_Total() { return Precio_Total; }

    public void setPrecio_Total(Integer precio_Total) {
        Precio_Total = precio_Total;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    public void setFecha_pedido(String fecha_pedido) { Fecha_pedido = fecha_pedido; }

    public void setPlatos_pedidos(String platos_pedidos) {
        this.platos_pedidos = platos_pedidos;
    }

    public void setHora_pedido(String hora_pedido) {
        this.hora_pedido = hora_pedido;
    }
}
