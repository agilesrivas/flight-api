package service;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import repository.GenericsRepository;

import java.util.List;
import java.util.Optional;

public class AiportsTest extends TestCase {

    @Mock
    private GenericsRepository aiports;

    @Before
    public void setup(){
        //BUSCAR EXPLICACION!!
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAll()
    {
        
    }


}
