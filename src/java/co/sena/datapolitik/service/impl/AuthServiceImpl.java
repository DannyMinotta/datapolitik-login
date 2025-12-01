package co.sena.datapolitik.service.impl;

import co.sena.datapolitik.model.Usuario;
import co.sena.datapolitik.service.AuthService;

/**
 * Implementación sencilla del servicio de autenticación.
 * En esta evidencia se utiliza un usuario "quemado" en el código
 * únicamente con fines académicos.
 */
public class AuthServiceImpl implements AuthService {

    @Override
    public Usuario autenticar(String documento, String password) {
        // NOTA: En un escenario real, aquí se consultaría una base de datos.
        // Para efectos de la evidencia, se valida contra un usuario estático.

        String documentoValido = "123456789";
        String passwordValida = "admin123";

        if (documentoValido.equals(documento) && passwordValida.equals(password)) {
            // Si las credenciales coinciden, se construye y retorna el usuario autenticado
            return new Usuario(documentoValido, "Usuario Administrador", "ADMIN");
        }

        // Cuando no coincide, se retorna null para indicar fallo en la autenticación
        return null;
    }
}