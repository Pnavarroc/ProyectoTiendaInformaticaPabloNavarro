package Modelo;

public abstract class Persona {
    private int id=0;
    private String nombre;
    private String email;
    private String telefono;
    private String direccion;

    public Persona() {
    }

    public Persona(String nombre, String email, String telefono, String direccion) {

        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.direccion = direccion;
    }


    public int getId() {
        return id;
    }


    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public abstract void guardarEnBD();

    public void mostrarInfo() {
        System.out.println("ID: " + id);
        System.out.println("Nombre: " + nombre);
        System.out.println("Email: " + email);
        System.out.println("Teléfono: " + telefono);
        System.out.println("Dirección: " + direccion);
    }

}