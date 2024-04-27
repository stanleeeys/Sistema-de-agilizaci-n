
package Model;


public class Cotizacion {
   private int cantidad;
    private String areaInversion;
    private String unidadMedida;
    private String descripcion;
    private double precioUnitario;
    private double precioTotal;

    public Cotizacion() {
    }

    public Cotizacion(int cantidad, String areaInversion, String unidadMedida, String descripcion, double precioUnitario, double precioTotal) {
        this.cantidad = cantidad;
        this.areaInversion = areaInversion;
        this.unidadMedida = unidadMedida;
        this.descripcion = descripcion;
        this.precioUnitario = precioUnitario;
        this.precioTotal = precioTotal;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getArea() {
        return areaInversion;
    }

    public void setArea(String area) {
        this.areaInversion = area;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }
    
}

