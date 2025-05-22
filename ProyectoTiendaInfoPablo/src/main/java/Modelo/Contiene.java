package Modelo;

public class Contiene {
    private int idCompra;
    private Producto producto;
    private int cantidad;

    public Contiene(int idCompra, Producto producto, int cantidad) {
        this.idCompra = idCompra;
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
