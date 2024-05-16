package DTOs;

public class Empleado {

    public String nombre;
    public String apellidos;
    public int departamento_id;

    public Empleado(String nombre, String apellidos, int idDepartamento) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.departamento_id = idDepartamento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getDepartamento_id() {
        return departamento_id;
    }

    public void setDepartamento_id(int departamento_id) {
        this.departamento_id = departamento_id;
    }
}
