package Modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * DAO (Data Access Object) para la entidad Compra.
 * Gestiona operaciones de inserción y recuperación de compras, así como su relación con productos.
 */

public class CompraDAO {

    /**
     * Guarda una nueva compra en la base de datos y asigna el ID generado a la compra.
     */

    public static void guardarCompra(Compra compra) throws SQLException {
        Connection conn = ConexionBD.conectar();

        String sqlCompra = "INSERT INTO compra (id_cliente, id_empleado, total) VALUES (?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sqlCompra, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, compra.getCliente().getId());
        ps.setInt(2, compra.getEmpleado().getId());
        ps.setDouble(3, compra.getTotal());
        ps.executeUpdate();

        // Recuperar ID de la compra
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            compra.setId(rs.getInt(1));
        }

        ps.close();
        conn.close();


    }


    /**
     * Guarda en la base de datos los productos comprados durante una compra,
     * es decir, rellena la tabla intermedia `contiene` que representa la relación N:M
     * entre `compra` y `producto`.
     *
     * Cada fila insertada indica qué producto se compró, cuántas unidades y en qué compra.
     * La fecha se inserta automáticamente con NOW().
     */
    public static void guardarProductosComprados(Compra compra) throws SQLException {
        Connection conn = ConexionBD.conectar();

        String sqlDetalle = "INSERT INTO contiene (id_compra, id_producto, cantidad) VALUES (?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sqlDetalle);

        // Recorremos todos los productos del carrito
        for (Map.Entry<Producto, Integer> entry : compra.getProductos().entrySet()) {
            Producto producto = entry.getKey();      // El producto comprado
            int cantidad = entry.getValue();         // Cuántas unidades se compraron

            ps.setInt(1, compra.getId());            // ID de la compra realizada
            ps.setInt(2, producto.getId());          // ID del producto comprado
            ps.setInt(3, cantidad);                  // Cantidad de ese producto

            ps.addBatch(); // Añade esta inserción al lote
        }

        ps.executeBatch();
        ps.close();
        conn.close();
    }



    /**
     * Devuelve todas las compras realizadas por un cliente.
     * Útil para mostrar historial de compras.
     */

    public static List<Compra> obtenerComprasPorCliente(int idCliente) {
        List<Compra> compras = new ArrayList<>();

        String sql = """
        SELECT c.id_compra, c.total, c.id_empleado, p.nombre AS nombre_empleado
        FROM compra c
        JOIN empleado e ON c.id_empleado = e.id_persona
        JOIN persona p ON e.id_persona = p.id_persona
        WHERE c.id_cliente = ?
        ORDER BY c.id_compra DESC
        """;

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idCliente);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int idCompra = rs.getInt("id_compra");
                double total = rs.getDouble("total");

                // Empleado simplificado (solo con nombre e ID)
                Empleado empleado = new Empleado(rs.getString("nombre_empleado"), "", "", "", "");
                empleado.setId(rs.getInt("id_empleado"));

                Compra compra = new Compra(null, empleado, total, null);
                compra.setId(idCompra);
                compras.add(compra);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return compras;
    }


    /**
     * Devuelve todas las compras gestionadas por un empleado.
     * Se usa en los informes de actividad del empleado.
     *
     */
    public static List<Compra> obtenerComprasPorEmpleado(int idEmpleado) {
        List<Compra> lista = new ArrayList<>();

        String sql = """
            SELECT c.id_compra, c.total, c.id_cliente
            FROM compra c
            WHERE c.id_empleado = ?
            ORDER BY c.id_compra DESC
        """;

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idEmpleado);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int idCompra = rs.getInt("id_compra");
                double total = rs.getDouble("total");
                int idCliente = rs.getInt("id_cliente");
                Cliente cliente = null;

                // 💡 Solo buscar cliente si no es NULL
                if (!rs.wasNull()) {
                    cliente = ClienteDAO.obtenerPorId(idCliente);
                }

                // Obtener también el empleado completo
                Empleado empleado = EmpleadoDAO.obtenerPorId(idEmpleado);

                Compra compra = new Compra(cliente, empleado, total);
                compra.setId(idCompra);
                lista.add(compra);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }


}
