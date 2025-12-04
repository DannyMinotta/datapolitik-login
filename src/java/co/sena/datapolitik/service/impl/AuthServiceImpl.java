package co.sena.datapolitik.service.impl;

import co.sena.datapolitik.dao.UsuarioDAO;
import co.sena.datapolitik.dao.impl.UsuarioDAOImpl;
import co.sena.datapolitik.model.Usuario;
import co.sena.datapolitik.service.AuthService;

public class AuthServiceImpl implements AuthService {

    private final UsuarioDAO usuarioDAO;

    public AuthServiceImpl() {
        this.usuarioDAO = new UsuarioDAOImpl();
    }

    @Override
    public Usuario login(String numeroDocumento, String password) throws Exception {
        Usuario usuario = usuarioDAO.buscarPorNumeroDocumento(numeroDocumento);

        if (usuario == null) {
            return null;
        }

        // Aquí comparamos la contraseña "en claro" con el campo contrasena_hash.
        // En un sistema real se debería almacenar un hash seguro.
        if (usuario.getContrasenaHash() != null
                && usuario.getContrasenaHash().equals(password)) {
            return usuario;
        }

        return null;
    }
}
