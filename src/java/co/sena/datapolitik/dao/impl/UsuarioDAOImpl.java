package co.sena.datapolitik.dao.impl;

import co.sena.datapolitik.dao.UsuarioDAO;
import co.sena.datapolitik.model.Usuario;
import co.sena.datapolitik.util.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOImpl implements UsuarioDAO {

    private static final String SQL_BUSCAR_POR_DOCUMENTO =
            "SELECT id_usuario, correo_electronico, contrasena_hash, nombres, apellidos, "
          + "tipo_documento, numero_documento, telefono "
          + "FROM usuario WHERE numero_documento = ?";

    private static final String SQL_INSERTAR =
            "INSERT INTO usuario (correo_electronico, contrasena_hash, nombres, apellidos, "
          + "tipo_documento, numero_documento, telefono) "
          + "VALUES (?, ?, ?, ?, ?, ?, ?)";

    private static final String SQL_LISTAR_TODOS =
            "SELECT id_usuario, correo_electronico, contrasena_hash, nombres, apellidos, "
          + "tipo_documento, numero_documento, telefono "
          + "FROM usuario";

    private static final String SQL_CONTAR_TODOS =
            "SELECT COUNT(*) AS total FROM usuario";

    @Override
    public Usuario buscarPorNumeroDocumento(String numeroDocumento) throws SQLException {
        Usuario usuario = null;

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_BUSCAR_POR_DOCUMENTO)) {

            ps.setString(1, numeroDocumento);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    usuario = new Usuario();
                    usuario.setIdUsuario(rs.getInt("id_usuario"));
                    usuario.setCorreoElectronico(rs.getString("correo_electronico"));
                    usuario.setContrasenaHash(rs.getString("contrasena_hash"));
                    usuario.setNombres(rs.getString("nombres"));
                    usuario.setApellidos(rs.getString("apellidos"));
                    usuario.setTipoDocumento(rs.getString("tipo_documento"));
                    usuario.setNumeroDocumento(rs.getString("numero_documento"));
                    usuario.setTelefono(rs.getString("telefono"));
                }
            }
        }

        return usuario;
    }

    @Override
    public boolean crear(Usuario usuario) throws SQLException {
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_INSERTAR)) {

            ps.setString(1, usuario.getCorreoElectronico());
            ps.setString(2, usuario.getContrasenaHash());
            ps.setString(3, usuario.getNombres());
            ps.setString(4, usuario.getApellidos());
            ps.setString(5, usuario.getTipoDocumento());
            ps.setString(6, usuario.getNumeroDocumento());
            ps.setString(7, usuario.getTelefono());

            int filas = ps.executeUpdate();
            return filas > 0;
        }
    }

    @Override
    public List<Usuario> listarTodos() throws SQLException {
        List<Usuario> lista = new ArrayList<>();

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_LISTAR_TODOS);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setCorreoElectronico(rs.getString("correo_electronico"));
                usuario.setContrasenaHash(rs.getString("contrasena_hash"));
                usuario.setNombres(rs.getString("nombres"));
                usuario.setApellidos(rs.getString("apellidos"));
                usuario.setTipoDocumento(rs.getString("tipo_documento"));
                usuario.setNumeroDocumento(rs.getString("numero_documento"));
                usuario.setTelefono(rs.getString("telefono"));

                lista.add(usuario);
            }
        }

        return lista;
    }

    @Override
    public int contarUsuarios() throws SQLException {
        int total = 0;

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_CONTAR_TODOS);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                total = rs.getInt("total");
            }
        }

        return total;
    }
}
