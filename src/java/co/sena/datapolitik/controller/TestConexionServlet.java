package co.sena.datapolitik.controller;

import co.sena.datapolitik.util.ConexionBD;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "TestConexionServlet", urlPatterns = {"/test-conexion"})
public class TestConexionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/plain;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            try (Connection conn = ConexionBD.getConnection()) {
                if (conn != null && !conn.isClosed()) {
                    out.println("Conexión EXITOSA a MySQL (datapolitik_db)");
                } else {
                    out.println("Conexión NO válida (conn es null o está cerrada).");
                }
            } catch (SQLException e) {
                out.println("Error al intentar conectar con la base de datos:");
                out.println(e.getMessage());
            }
        }
    }
}
