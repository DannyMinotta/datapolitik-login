package co.sena.datapolitik.controller;

import co.sena.datapolitik.dao.UsuarioDAO;
import co.sena.datapolitik.dao.impl.UsuarioDAOImpl;
import co.sena.datapolitik.model.Usuario;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "UsuarioServlet", urlPatterns = {"/usuarios"})
public class UsuarioServlet extends HttpServlet {

    private UsuarioDAO usuarioDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        this.usuarioDAO = new UsuarioDAOImpl();
    }

    private boolean estaAutenticado(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return session != null && session.getAttribute("usuarioAutenticado") != null;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (!estaAutenticado(request)) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String accion = request.getParameter("accion");
        if (accion == null) {
            accion = "listar";
        }

        switch (accion) {
            case "nuevo":
                mostrarFormularioNuevo(request, response);
                break;
            case "listar":
            default:
                listarUsuarios(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (!estaAutenticado(request)) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String accion = request.getParameter("accion");
        if (accion == null) {
            accion = "guardar";
        }

        switch (accion) {
            case "guardar":
            default:
                guardarUsuario(request, response);
                break;
        }
    }

    private void listarUsuarios(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            List<Usuario> usuarios = usuarioDAO.listarTodos();
            request.setAttribute("usuarios", usuarios);
            request.getRequestDispatcher("views/usuarios.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "No fue posible obtener la lista de usuarios.");
            request.getRequestDispatcher("views/usuarios.jsp").forward(request, response);
        }
    }

    private void mostrarFormularioNuevo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("views/usuario-form.jsp").forward(request, response);
    }

    private void guardarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String nombres = request.getParameter("nombres");
        String apellidos = request.getParameter("apellidos");
        String tipoDocumento = request.getParameter("tipoDocumento");
        String numeroDocumento = request.getParameter("numeroDocumento");
        String correoElectronico = request.getParameter("correoElectronico");
        String telefono = request.getParameter("telefono");
        String password = request.getParameter("password");

        Usuario usuario = new Usuario();
        usuario.setNombres(nombres);
        usuario.setApellidos(apellidos);
        usuario.setTipoDocumento(tipoDocumento);
        usuario.setNumeroDocumento(numeroDocumento);
        usuario.setCorreoElectronico(correoElectronico);
        usuario.setTelefono(telefono);
        usuario.setContrasenaHash(password); // en un sistema real aquí iría un hash

        try {
            boolean creado = usuarioDAO.crear(usuario);

            if (creado) {
                response.sendRedirect(request.getContextPath() + "/usuarios?accion=listar");
            } else {
                request.setAttribute("error", "No fue posible registrar el usuario.");
                request.getRequestDispatcher("views/usuario-form.jsp").forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Ocurrió un error al registrar el usuario.");
            request.getRequestDispatcher("views/usuario-form.jsp").forward(request, response);
        }
    }
}
