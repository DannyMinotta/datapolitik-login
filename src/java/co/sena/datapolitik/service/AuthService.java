package co.sena.datapolitik.service;

import co.sena.datapolitik.model.Usuario;

/**
 * Define las operaciones relacionadas con la autenticación de usuarios.
 */
public interface AuthService {

    /**
     * Intenta autenticar un usuario con el documento y contraseña indicados.
     *
     * @param documento Número de documento ingresado en el formulario.
     * @param password  Contraseña ingresada en el formulario.
     * @return Un objeto Usuario cuando las credenciales son válidas;
     *         null en caso contrario.
     */
    Usuario autenticar(String documento, String password);
}