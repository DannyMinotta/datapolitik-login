package co.sena.datapolitik.controller;

import co.sena.datapolitik.model.Usuario;
import co.sena.datapolitik.service.AuthService;
import co.sena.datapolitik.service.impl.AuthServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet encargado de procesar las peticiones de inicio de sesión (login).
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    private AuthService authService;

    /**
     * Se inicializa el servicio de autenticación cuando se carga el servlet.
     */
    @Override
    public void init() throws ServletException {
        super.init();
        this.authService = new AuthServiceImpl();
    }

    /**
     * Atiende las peticiones GET.
     * Se utiliza para mostrar la página de login.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Reenvía la petición a la vista login.jsp
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    /**
     * Atiende las peticiones POST del formulario de login.
     * Realiza las validaciones básicas y utiliza el servicio de autenticación.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String documento = request.getParameter("documento");
        String password = request.getParameter("password");

        // Guardamos el documento ingresado para no perderlo si hay error
        request.setAttribute("documentoIngresado", documento);

        // 1. Validaciones básicas de campos vacíos
        if ((documento == null || documento.isBlank()) &&
            (password == null || password.isBlank())) {

            request.setAttribute("mensajeError",
                    "El número de documento y la contraseña son obligatorios.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        if (documento == null || documento.isBlank()) {
            request.setAttribute("mensajeError", "El número de documento es obligatorio.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        if (password == null || password.isBlank()) {
            request.setAttribute("mensajeError", "La contraseña es obligatoria.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        // 2. Validación de formato: el documento debe contener solo dígitos
        if (!documento.matches("\\d+")) {
            request.setAttribute("mensajeError",
                    "El número de documento solo debe contener dígitos.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        // 3. Autenticación mediante el servicio
        Usuario usuarioAutenticado = authService.autenticar(documento, password);

        if (usuarioAutenticado == null) {
            // Cuando las credenciales no son válidas
            request.setAttribute("mensajeError", "Documento o contraseña incorrectos.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            // Cuando las credenciales son válidas, se crea la sesión
            HttpSession session = request.getSession();
            session.setAttribute("usuarioAutenticado", usuarioAutenticado);

            // Se redirige al dashboard
            response.sendRedirect(request.getContextPath() + "/dashboard.jsp");
        }
    }
}
