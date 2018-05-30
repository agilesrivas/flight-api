package com.ApiVuelos.ApiVuelos.controller;

import com.ApiVuelos.ApiVuelos.service.CityService;
import com.ApiVuelos.ApiVuelos.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/city")
public class CityController {

    @Autowired
    private CityService cityService;
    @Autowired
    private StateService stateService;

    @PostMapping(value = "/add")
    public void add(String name_city,String iataCode,Long id_state){

        State state=this.stateService.getById(id_state);
        if(state!=null)
        {
            City city=new City(name_city,iataCode,state);
        }

    }

    @PutMapping(value = "/update")
    public void update(City value) {
        State st=this.stateService.getById(value.getState().getId());
        City city=this.cityService.getById(value.getId());
        if(st !=null && city !=null){
            //Seteo los datos
        }
    }

    @DeleteMapping(value = "/remove")
    public void remove(@RequestParam("id")Long id){
        this.cityService.removeObject(id);
    }
    @GetMapping(value = "/")
    @ResponseBody
    public List<City> getAll() {
        List<City>cities=this.cityService.getAll();
        return cities;
    }
}
