package co.sena.datapolitik.model;

/**
 * Representa a un usuario de la aplicación DataPolitik.
 * Para esta evidencia solo se usarán algunos atributos básicos.
 */
public class Usuario {

    private String documento;
    private String nombreCompleto;
    private String rol;

    public Usuario() {
        // Constructor vacío requerido por JavaBeans
    }

    /**
     * Constructor principal para inicializar un usuario.
     *
     * @param documento      Número de documento del usuario
     * @param nombreCompleto Nombre completo del usuario
     * @param rol            Rol del usuario dentro de la aplicación (ej. ADMIN, ANALISTA)
     */
    public Usuario(String documento, String nombreCompleto, String rol) {
        this.documento = documento;
        this.nombreCompleto = nombreCompleto;
        this.rol = rol;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "documento='" + documento + '\'' +
                ", nombreCompleto='" + nombreCompleto + '\'' +
                ", rol='" + rol + '\'' +
                '}';
    }
}