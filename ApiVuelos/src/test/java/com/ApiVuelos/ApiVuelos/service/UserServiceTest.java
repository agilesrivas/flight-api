package com.ApiVuelos.ApiVuelos.service;

import com.ApiVuelos.ApiVuelos.repository.AirportRepository;
import com.ApiVuelos.ApiVuelos.repository.UserRepository;
import com.utn.tssi.tp5.Models.model.*;
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

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest extends TestCase {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
     private UserService service;



    User us =new User(1,"Alejandro","123");


    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void getALlTest() throws Exception{
        List<User> listUser= new ArrayList<User>();
        listUser.add(this.us);
        listUser.add(this.us);
        listUser.add(this.us);
        when(this.userRepository.findAll()).thenReturn(listUser);
        List<User>dao=this.service.getAll();
        assertEquals(3,dao.size());
    }
    @Test
    public void addTest()throws Exception{
        when(this.userRepository.save(this.us)).thenReturn(this.us);
        User user=this.service.newObject(this.us);
        assertEquals(1,user.getId());
        assertEquals("Alejandro",user.getName());
        assertEquals("123",user.getPassword());

    }
    @Test
    public void removeTest()throws Exception{
        service.removeObject(this.us.getId());
        verify(this.userRepository,times(1)).deleteById(this.us.getId());
    }
    @Test
    public void getByIdTest()throws Exception{
        when(this.userRepository.findById(this.us.getId())).thenReturn(java.util.Optional.ofNullable(this.us));
        User user=this.service.getById(this.us.getId());
        assertEquals(1,user.getId());
        assertEquals("Alejandro",user.getName());
        assertEquals("123",user.getPassword());
    }
    @Test
    public void getByAttributeTypeTest()throws Exception{
      when(this.service.getByAttributeType("a")).thenReturn(null);
        User user=this.service.getByAttributeType(this.us.getName());
        assertNull(user);
    }
    @Test
    public void getByAttributeTypeUserTest() throws Exception{
        when(this.userRepository.getAttribute("Alejandro","123")).thenReturn(this.us);
        User user=this.service.getByAttributeTypeUser("Alejandro","123");
        assertEquals(1,user.getId());
        assertEquals("Alejandro",user.getName());
        assertEquals("123",user.getPassword());
    }
}
