package ViewModel;

public class ClienteVM {

    public int idCliente;

    public String encargadoCompra;

    public String nombreInstitucion;

    public String municipio;
    
    public String codigo;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getEncargadoCompra() {
        return encargadoCompra;
    }

    public void setEncargadoCompra(String encargadoCompra) {
        this.encargadoCompra = encargadoCompra;
    }

    public String getNombreInstitucion() {
        return nombreInstitucion;
    }

    public void setNombreInstitucion(String nombreInstitucion) {
        this.nombreInstitucion = nombreInstitucion;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

}
