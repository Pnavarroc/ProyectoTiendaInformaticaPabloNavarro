package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * DAO (Data Access Object) para la entidad Cliente.
 * Se encarga de realizar operaciones CRUD sobre la tabla 'cliente' y su relación con 'persona'.
 */

public class ClienteDAO {

    /**
     * Realiza el inicio de sesión de un cliente comprobando ID y contraseña.
     */

    public static Cliente iniciarSesion(int id, String contraseña) {
        Cliente cliente = null;

        String sql = """
            SELECT p.nombre, p.email, p.telefono, p.direccion, c.contraseña
            FROM cliente c
            JOIN persona p ON c.id_persona = p.id_persona
            WHERE c.id_persona = ? AND c.contraseña = ?
        """;

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.setString(2, contraseña);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                cliente = new Cliente(
                        rs.getString("nombre"),
                        rs.getString("email"),
                        rs.getString("telefono"),
                        rs.getString("direccion"),
                        rs.getString("contraseña")
                );
                cliente.setId(id);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return cliente;
    }

    /**
     * Elimina un cliente de la base de datos.
     * También elimina su entrada en 'persona' gracias a la relación con clave foránea.
     */

    public static boolean eliminarCliente(int id) {
        String sqlCliente = "DELETE FROM cliente WHERE id_persona = ?";
        String sqlPersona = "DELETE FROM persona WHERE id_persona = ?";

        try (Connection conn = ConexionBD.conectar()) {
            conn.setAutoCommit(false); // Inicia transacción

            // 1. Elimina de cliente (compra.id_cliente se volverá NULL automáticamente)
            PreparedStatement psCliente = conn.prepareStatement(sqlCliente);
            psCliente.setInt(1, id);
            int filasCliente = psCliente.executeUpdate();

            if (filasCliente == 0) {
                conn.rollback();
                return false; // No se eliminó ningún cliente
            }

            // 2. Elimina de persona
            PreparedStatement psPersona = conn.prepareStatement(sqlPersona);
            psPersona.setInt(1, id);
            psPersona.executeUpdate();

            conn.commit();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Recupera un cliente a partir de su ID.
     * Útil para reconstruir un cliente desde compras pasadas.
     */

    public static Cliente obtenerPorId(int id) {
        Cliente cliente = null;

        String sql = """
            SELECT p.nombre, p.email, p.telefono, p.direccion, c.contraseña
            FROM cliente c
            JOIN persona p ON c.id_persona = p.id_persona
            WHERE c.id_persona = ?
        """;

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                cliente = new Cliente(
                        rs.getString("nombre"),
                        rs.getString("email"),
                        rs.getString("telefono"),
                        rs.getString("direccion"),
                        rs.getString("contraseña")
                );
                cliente.setId(id);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return cliente;
    }
}
