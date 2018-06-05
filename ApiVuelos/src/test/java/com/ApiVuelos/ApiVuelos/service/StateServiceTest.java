package com.ApiVuelos.ApiVuelos.service;

import com.ApiVuelos.ApiVuelos.repository.StateRepository;
import com.utn.tssi.tp5.Models.model.Country;
import com.utn.tssi.tp5.Models.model.State;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(SpringJUnit4ClassRunner.class)
public class StateServiceTest extends TestCase {
    @Mock
    private StateRepository stateRepository;

    @InjectMocks
    private StateService service;



    Country ct =new Country(1,"Argentina","ARG");
    State st=new State(1,"state","ARG",ct);



    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void getALlTest()throws Exception {
        List<State> stateList = new ArrayList<State>();
        stateList .add(this.st);
        stateList.add(this.st);
        stateList.add(this.st);
        when(this.stateRepository.findAll()).thenReturn(stateList);
        List<State>dao=this.service.getAll();
        assertEquals(3, stateList.size());
    }
    @Test
    public void addTest()throws Exception{
        when(this.stateRepository.save(this.st)).thenReturn(this.st);
        State state=this.service.newObject(this.st);
        assertEquals(1,state.getId());
        assertEquals("state",state.getName());
        assertEquals("ARG",state.getIataCode());
        assertEquals(ct,state.getCountry());

    }

    @Test
    public void removeTest()throws Exception{
        service.removeObject(this.st.getId());
        verify(this.stateRepository,times(1)).deleteById(this.st.getId());
    }
    @Test
    public void getByIdTest()throws Exception{

        when(this.stateRepository.findById(this.st.getId())).thenReturn(Optional.ofNullable(this.st));
        State stat=this.service.getById(this.st.getId());
        assertEquals(1,stat.getId());
        assertEquals("state",stat.getName());
        assertEquals("ARG",stat.getIataCode());
        assertEquals(ct,stat.getCountry());

    }
    @Test
    public void getByAttributeTypeTest()throws Exception{
        when(this.stateRepository.getAttribute(this.st.getIataCode())).thenReturn(this.st);
        State state=this.service.getByAttributeType(this.st.getIataCode());
        assertEquals(1,state.getId());
        assertEquals("state",state.getName());
        assertEquals("ARG",state.getIataCode());
        assertEquals(ct,state.getCountry());

    }

}
