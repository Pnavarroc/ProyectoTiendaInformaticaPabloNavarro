package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    public static List<Producto> getTodosLosProductos() {
        List<Producto> lista = new ArrayList<>();
        Connection conn = ConexionBD.conectar();

        try {
            String sql = "SELECT * FROM Producto";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Producto p = new Producto(
                        rs.getInt("id_producto"),
                        rs.getString("nombre"),
                        rs.getString("marca"),
                        rs.getDouble("precio")
                );
                lista.add(p);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener productos.");
            e.printStackTrace();
        }

        return lista;
    }
}
