package co.sena.datapolitik.dao;

import co.sena.datapolitik.model.Usuario;
import java.sql.SQLException;
import java.util.List;

public interface UsuarioDAO {

    Usuario buscarPorNumeroDocumento(String numeroDocumento) throws SQLException;

    boolean crear(Usuario usuario) throws SQLException;

    List<Usuario> listarTodos() throws SQLException;
    
    int contarUsuarios() throws SQLException;
}

