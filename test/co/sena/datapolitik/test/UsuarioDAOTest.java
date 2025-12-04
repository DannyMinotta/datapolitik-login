package co.sena.datapolitik.test;

import co.sena.datapolitik.dao.UsuarioDAO;
import co.sena.datapolitik.dao.impl.UsuarioDAOImpl;
import java.sql.SQLException;
import org.junit.Test;
import static org.junit.Assert.*;

public class UsuarioDAOTest {

    @Test
    public void testContarUsuariosDevuelveAlMenosUno() throws SQLException {
        UsuarioDAO dao = new UsuarioDAOImpl();

        int total = dao.contarUsuarios();

        assertTrue("Debe existir al menos un usuario en la tabla 'usuario'", total >= 1);
    }
}
