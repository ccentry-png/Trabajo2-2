package cl.ipvg.trabajo2_2;

public class Producto {
    public String getIdProducto() {
        return IdProducto;
    }

    public void setIdProducto(String idProducto) {
        IdProducto = idProducto;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getPrecio() {
        return Precio;
    }

    public void setPrecio(String precio) {
        Precio = precio;
    }

    private String IdProducto;
    private String Nombre;
    private String Precio;

    public Producto(String nombre, String precio, String idProducto) {
        Nombre = nombre;
        Precio = precio;
        IdProducto = idProducto;
    }

    public Producto() {

    }


}
