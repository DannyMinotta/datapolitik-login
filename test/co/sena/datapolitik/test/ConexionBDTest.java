package co.sena.datapolitik.test;

import co.sena.datapolitik.util.ConexionBD;
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.Test;
import static org.junit.Assert.*;

public class ConexionBDTest {

    @Test
    public void testObtenerConexionNoEsNula() throws SQLException {
        Connection conn = ConexionBD.getConnection();

        assertNotNull("La conexión a la base de datos no debe ser nula", conn);

        conn.close();
    }
}
