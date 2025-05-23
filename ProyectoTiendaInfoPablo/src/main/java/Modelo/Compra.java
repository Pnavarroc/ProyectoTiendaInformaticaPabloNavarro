package Modelo;

import java.util.Map;

public class Compra {
    private int id;
    private Cliente cliente;
    private Empleado empleado;
    private double total;
    private Map<Producto, Integer> productos; // producto → cantidad

    public Compra(Cliente cliente, Empleado empleado, double total, Map<Producto, Integer> productos) {
        this.cliente = cliente;
        this.empleado = empleado;
        this.total = total;
        this.productos = productos;
    }
    public Compra(Cliente cliente, Empleado empleado, double total) {
        this.cliente = cliente;
        this.empleado = empleado;
        this.total = total;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public double getTotal() {
        return total;
    }

    public Map<Producto, Integer> getProductos() {
        return productos;
    }
}
