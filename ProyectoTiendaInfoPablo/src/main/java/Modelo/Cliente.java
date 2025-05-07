package Modelo;

public class Cliente extends Persona {
    private String contraseña;
    private boolean activo;

    public Cliente(int id, String nombre, String email, String telefono, String direccion, String contraseña) {
        super(id, nombre, email, telefono, direccion);
        this.contraseña = contraseña;
        this.activo = true;
    }

    //Metodo para Registrarnos
    public void registrar() {
        System.out.println("Cliente registrado correctamente: " + getNombre());
        this.activo = true;
    }

    // Metodo para iniciar sesion
    public boolean iniciarSesion(String emailIngresado, String contraseñaIngresada) {
        if (!activo) {
            System.out.println("Cuenta desactivada. No se puede iniciar sesión.");
            return false;
        }

        if (this.getEmail().equals(emailIngresado) && this.contraseña.equals(contraseñaIngresada)) {
            System.out.println("Inicio de sesión exitoso.");
            return true;
        } else {
            System.out.println("Credenciales incorrectas.");
            return false;
        }
    }

    // Metodo para darse de baja
    public void darseDeBaja() {
        this.activo = false;
        System.out.println("El cliente " + getNombre() + " ha sido dado de baja.");
    }

    //MostrarInfo sobreescrito
    @Override
    public void mostrarInfo() {
        super.mostrarInfo();
        System.out.println("Estado de cuenta: " + (activo ? "Activa" : "Inactiva"));
    }
}

