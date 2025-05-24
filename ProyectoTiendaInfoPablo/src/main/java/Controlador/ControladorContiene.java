package Controlador;

import Modelo.Contiene;
import Modelo.ContieneDAO;
import java.util.List;

public class ControladorContiene {
    public static List<Contiene> obtenerPorCompra(int idCompra) {
        return ContieneDAO.obtenerPorCompra(idCompra);
    }
}
