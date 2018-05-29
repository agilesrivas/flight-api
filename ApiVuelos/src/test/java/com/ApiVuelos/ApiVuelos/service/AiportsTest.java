package service;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import repository.GenericsRepository;
import repository.MethodsRepository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AiportsTest extends TestCase {

    @Mock
    private GenericsRepository aiports;

    @InjectMocks
    private MethodsRepository met;

    @Before
    public void setup(){
        //BUSCAR EXPLICACION!!
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAll()
    {
        /*List<Airport>listTest=new ArrayList<Airport>();
        listTest.add(new Airport(1,"CC","name",2,-2222,2222));
        listTest.add(new Airport(2,"CC","name",2,-2222,2222));
        listTest.add(new Airport(2,"CC","name",2,-2222,2222));
        when(this.aiports.findAll()).thenReturn(listTest);
        List<Airport>result=met.getAll();
        assertEquals("3",result.size());*/
    }
    @Test
    public void testGetId(){
       /* Airport air = new Airport(1,"Todo", "name1", 1,-2222,2222);
        when(this.aiports.findById(1L)).thenReturn(air);
        Airport result = this.met.getById((long) 1);
        assertEquals(1, result.getId());
        assertEquals("Todo", result.getIata());
        assertEquals("name1", result.getName());
        assertEquals(1, result.getCity());
        assertEquals(-2222, result.getLongitud());
        assertEquals(2222, result.getLatitud());
        */
       }
     @Test
     public void testnewObject(){
        /* Airport air = new Airport(1,"Todo", "name1", 1,-2222,2222);
         when(this.aiports.save(air)).thenReturn(air);
         Airport result = this.met.newObject(air);
         assertEquals(1, result.getId());
         assertEquals("Todo", result.getIata());
         assertEquals("name1", result.getName());
         assertEquals(1, result.getCity());
         assertEquals(-2222, result.getLongitud());
         assertEquals(2222, result.getLatitud());
         */
         /* Acomodar Metodos de clases (problemas al importar jar) jeje*/

     }
    @Test
    public void testUpdateObject(){

       /* Airport air = new Airport(1,"Todo", "name1", 1,-2222,2222);
        EntityManager conOb=mock(EntityManager.class);
        when(conOb.getTransaction().begin()).thenReturn(conOb);
        when(conOb.find(Airport,air.getId())).thenReturn(air)
        /* Acomodar Metodos de clases (problemas al importar jar) jeje*/

    }
    @Test
    public void testRemoveObject(){
        /*
        Airport air = new Airport(1,"Todo", "name1", 1,-2222,2222);
        this.met.removeObject(air);
        verify(this.met, times(1)).delete(air);
        */

    }


}
