package Modelo;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

public class CompraDAO {

    public static int guardarCompra(Compra compra) throws SQLException {
        Connection conn = ConexionBD.conectar();

        String sql = "INSERT INTO Compra (id_cliente, id_empleado, total) VALUES (?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, compra.getCliente().getId());
        ps.setInt(2, compra.getEmpleado().getId());
        ps.setDouble(3, compra.getTotal());
        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            int idGenerado = rs.getInt(1);
            compra.setId(idGenerado);
            return idGenerado;
        }
        throw new SQLException("No se pudo obtener el ID de la compra.");
    }

    public static void guardarProductosComprados(Compra compra) throws SQLException {
        Connection conn = ConexionBD.conectar();
        String sql = "INSERT INTO Contiene (id_compra, id_producto, fecha) VALUES (?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);

        LocalDate hoy = LocalDate.now();

        for (Map.Entry<Producto, Integer> entry : compra.getProductosComprados().entrySet()) {
            Producto producto = entry.getKey();
            int cantidad = entry.getValue();

            for (int i = 0; i < cantidad; i++) {
                ps.setInt(1, compra.getId());
                ps.setInt(2, producto.getId());
                ps.setDate(3, Date.valueOf(hoy));
                ps.addBatch();
            }
        }

        ps.executeBatch();
    }

    public static List<Compra> obtenerComprasPorCliente(int idCliente) {
        List<Compra> lista = new ArrayList<>();
        Connection conn = ConexionBD.conectar();

        try {
            String sql = "SELECT id_compra, id_empleado, total FROM Compra WHERE id_cliente = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idCliente);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int idCompra = rs.getInt("id_compra");
                int idEmpleado = rs.getInt("id_empleado");
                double total = rs.getDouble("total");

                Compra compra = new Compra(null, null, total, null);
                compra.setId(idCompra);
                lista.add(compra);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }


    public static Map<String, Double> obtenerClientesAtendidos(int idEmpleado) {
        Map<String, Double> resultado = new HashMap<>();
        Connection conn = ConexionBD.conectar();

        try {
            String sql = """
            SELECT p.nombre, SUM(c.total) as total_gastado
            FROM Compra c
            JOIN Persona p ON c.id_cliente = p.id_persona
            WHERE c.id_empleado = ?
            GROUP BY p.nombre
        """;

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idEmpleado);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                resultado.put(rs.getString("nombre"), rs.getDouble("total_gastado"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultado;
    }

    public static Map<String, Integer> obtenerProductosPorCompra(int idCompra) {
        Map<String, Integer> productos = new LinkedHashMap<>();
        Connection conn = ConexionBD.conectar();

        try {
            String sql = """
            SELECT pr.nombre, COUNT(*) as cantidad
            FROM Contiene c
            JOIN Producto pr ON c.id_producto = pr.id_producto
            WHERE c.id_compra = ?
            GROUP BY pr.nombre
        """;

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idCompra);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                productos.put(rs.getString("nombre"), rs.getInt("cantidad"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productos;
    }
}
