package Controlador;

import Modelo.Producto;
import Modelo.ProductoDAO;

import java.util.List;

public class ControladorProducto {

    public static List<Producto> obtenerTodos() {
        return ProductoDAO.getTodosLosProductos();
    }

    public static List<Producto> buscarPorNombre(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            return ProductoDAO.getTodosLosProductos();
        }
        return ProductoDAO.buscarPorNombre(texto);
    }


}
