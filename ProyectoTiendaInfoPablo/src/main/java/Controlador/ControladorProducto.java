package Controlador;

import Modelo.Producto;
import Modelo.ProductoDAO;

import java.util.List;

/**
 * Controlador que gestiona operaciones relacionadas con los productos.
 * Encapsula la lógica de acceso y manipulación de productos.
 */
public class ControladorProducto {

    /**
     * Devuelve todos los productos disponibles en la base de datos.
     * Se usa principalmente en las vistas para mostrar el catálogo.
     */
    public static List<Producto> obtenerTodos() {
        return ProductoDAO.getTodosLosProductos();
    }

    /**
     * Busca productos cuyo nombre contenga una palabra clave.
     * Usado en el buscador de la vista cliente.
     */
    public static List<Producto> buscarPorNombre(String texto) {
        return ProductoDAO.buscarPorNombre(texto);
    }
}
