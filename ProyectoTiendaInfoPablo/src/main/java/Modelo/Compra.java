package Modelo;

import java.util.Map;

public class Compra {

    private int id;
    private Cliente cliente;
    private Empleado empleado;
    private double total;
    private Map<Producto, Integer> productosComprados; // Producto → Cantidad

    public Compra(Cliente cliente, Empleado empleado, double total, Map<Producto, Integer> productosComprados) {
        this.cliente = cliente;
        this.empleado = empleado;
        this.total = total;
        this.productosComprados = productosComprados;
    }

    public int getId() {
        return id;
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

    public Map<Producto, Integer> getProductosComprados() {
        return productosComprados;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void mostrarResumen() {
        System.out.println("Compra ID: " + id);
        System.out.println("Cliente: " + cliente.getNombre());
        System.out.println("Empleado: " + (empleado != null ? empleado.getNombre() : "No asignado"));
        System.out.println("Total: " + total + "€");

        System.out.println("Productos comprados:");
        for (Map.Entry<Producto, Integer> entry : productosComprados.entrySet()) {
            Producto p = entry.getKey();
            int cantidad = entry.getValue();
            System.out.println("- " + p.getNombre() + " x" + cantidad + " (" + p.getPrecio() + "€/ud)");
        }
    }
}
