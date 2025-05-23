package Modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CompraDAO {

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

    public static void guardarProductosComprados(Compra compra) throws SQLException {
        Connection conn = ConexionBD.conectar();

        String sqlDetalle = "INSERT INTO contiene (id_compra, id_producto, cantidad) VALUES (?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sqlDetalle);

        for (Map.Entry<Producto, Integer> entry : compra.getProductos().entrySet()) {
            Producto p = entry.getKey();
            int cantidad = entry.getValue();

            ps.setInt(1, compra.getId());
            ps.setInt(2, p.getId());
            ps.setInt(3, cantidad);
            ps.addBatch(); // Recomendado para eficiencia
        }

        ps.executeBatch();
        ps.close();
        conn.close();
    }

    public static Map<String, Double> obtenerClientesAtendidos(int idEmpleado) {
        Map<String, Double> resultados = new LinkedHashMap<>();

        String sql = """
        SELECT p.nombre, SUM(c.total) AS total_gastado
        FROM compra c
        JOIN cliente cli ON c.id_cliente = cli.id_persona
        JOIN persona p ON cli.id_persona = p.id_persona
        WHERE c.id_empleado = ?
        GROUP BY p.nombre
        ORDER BY total_gastado DESC
        """;

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idEmpleado);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String nombreCliente = rs.getString("nombre");
                double totalGastado = rs.getDouble("total_gastado");
                resultados.put(nombreCliente, totalGastado);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultados;
    }

    public static Map<String, Integer> obtenerProductosPorCompra(int idCompra) {
        Map<String, Integer> productos = new LinkedHashMap<>();

        String sql = """
        SELECT p.nombre, c.cantidad
        FROM contiene c
        JOIN producto p ON c.id_producto = p.id_producto
        WHERE c.id_compra = ?
        """;

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idCompra);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String nombreProducto = rs.getString("nombre");
                int cantidad = rs.getInt("cantidad");
                productos.put(nombreProducto, cantidad);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return productos;
    }

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
