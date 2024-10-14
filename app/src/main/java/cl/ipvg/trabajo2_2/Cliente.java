package cl.ipvg.trabajo2_2;

public class Cliente {
    private String Rut;
    private String Nombre;
    private String NumeroTelefono;
    private  String IdCliente;

    public Cliente()
    {
        this.IdCliente ="";
        this.Rut ="";
        this.Nombre ="";
        this.NumeroTelefono ="";
    }

    public Cliente( String id,String rut, String nombre, String numeroTelefono )
    {
        this.IdCliente = id;
        this.Rut =rut;
        this.Nombre =nombre;
        this.NumeroTelefono = numeroTelefono;
    }

    public String getRut() {
        return Rut;
    }

    public void setRut(String rut) {
        this.Rut = rut;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        this.Nombre = nombre;
    }

    public String getNumeroTelefono() {
        return NumeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.NumeroTelefono = numeroTelefono;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "Rut='" + Rut + '\'' +
                ", Nombre='" + Nombre + '\'' +
                ", Numero='" + NumeroTelefono + '\'' +
                '}';
    }

    public String getIdCliente() {
        return IdCliente;
    }

    public void setIdCliente(String idCliente) {
        IdCliente = idCliente;
    }
}



