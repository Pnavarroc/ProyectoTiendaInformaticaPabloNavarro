package Modelo;

import java.sql.*;
import java.time.LocalDate;
import java.util.Map;

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
}
