package co.sena.datapolitik.controller;

import co.sena.datapolitik.model.Usuario;
import co.sena.datapolitik.service.AuthService;
import co.sena.datapolitik.service.impl.AuthServiceImpl;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    private AuthService authService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.authService = new AuthServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Mostrar el formulario de login
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String numeroDocumento = request.getParameter("numeroDocumento");
        String password = request.getParameter("password");

        try {
            Usuario usuario = authService.login(numeroDocumento, password);

            if (usuario != null) {
                HttpSession session = request.getSession(true);
                session.setAttribute("usuarioAutenticado", usuario);

                // Redirigir al dashboard
                response.sendRedirect(request.getContextPath() + "/dashboard.jsp");
            } else {
                request.setAttribute("error", "Número de documento o contraseña incorrectos.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Ocurrió un error al procesar el inicio de sesión.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
