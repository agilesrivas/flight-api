import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import persistence.Connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectTest extends TestCase {

        Connect conexion;

    @Before
    public void setUp()
    {
        this.conexion=new Connect();
    }



}
