package Controlador;

import Modelo.Contiene;
import Modelo.ConexionBD;
import Modelo.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ControladorContiene {

    public static List<Contiene> obtenerPorCompra(int idCompra) {
        List<Contiene> lista = new ArrayList<>();

        String sql = """
            SELECT p.id_producto, p.nombre, p.marca, p.precio, c.cantidad
            FROM contiene c
            JOIN producto p ON c.id_producto = p.id_producto
            WHERE c.id_compra = ?
        """;

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idCompra);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Producto producto = new Producto(
                        rs.getInt("id_producto"),
                        rs.getString("nombre"),
                        rs.getString("marca"),
                        rs.getDouble("precio")
                );

                Contiene contiene = new Contiene(idCompra, producto, rs.getInt("cantidad"));
                lista.add(contiene);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}
