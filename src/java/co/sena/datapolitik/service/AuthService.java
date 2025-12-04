package co.sena.datapolitik.service;

import co.sena.datapolitik.model.Usuario;

public interface AuthService {

    /**
     * Autentica al usuario a partir de su número de documento y contraseña.
     * @return el Usuario autenticado o null si las credenciales no son válidas.
     */
    Usuario login(String numeroDocumento, String password) throws Exception;
}
