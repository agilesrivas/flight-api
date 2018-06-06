package com.ApiVuelos.ApiVuelos.controller;

import com.ApiVuelos.ApiVuelos.service.CabinService;
import com.utn.tssi.tp5.Models.model.Cabin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cabin")
public class CabinController {

    @Autowired
    private CabinService cabinService;

    @PostMapping(value = "/", consumes = "application/json")
    public ResponseEntity add(@RequestBody List<Cabin> cabins) {

        ResponseEntity status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);

        try{
            for (Cabin cabin : cabins) {
                if (!cabin.validateNullEmpty()) {
                    this.cabinService.newObject(cabin);
                    status = new ResponseEntity(HttpStatus.OK);

                } else {
                    status = new ResponseEntity(HttpStatus.NO_CONTENT);
                    break;
                }
            }
        } catch(Exception e){
            status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return status;
    }

    @PutMapping(value = "/")
    public ResponseEntity update(Cabin value){

        ResponseEntity status = new ResponseEntity(HttpStatus.NO_CONTENT);

        try{
            if(value != null && !value.validateNullEmpty()) {
                Cabin cabinDB = this.cabinService.getById(value.getId());

                if(cabinDB != null) {
                    this.cabinService.newObject(value);
                    status = new ResponseEntity(HttpStatus.OK);
                }
            }
        } catch(Exception e) {
            status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return status;
    }

    @DeleteMapping(value = "/")
    public ResponseEntity remove(@RequestParam("id")Long id){

        ResponseEntity status = new ResponseEntity(HttpStatus.NO_CONTENT);

        try{
            if(id != null && id > 0){
                this.cabinService.removeObject(id);
                status = new ResponseEntity(HttpStatus.OK);
            }
        } catch(Exception e){
            status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return status;
    }

    @GetMapping
    public ResponseEntity<List<Cabin>> getAll() {

        ResponseEntity status = new ResponseEntity(HttpStatus.NO_CONTENT);

        try
        {
            List<Cabin> cabins =this.cabinService.getAll();
            if (!cabins.isEmpty()) {
                status = new ResponseEntity<List<Cabin>>(cabins, HttpStatus.OK);
            }
        } catch (Exception e) {
            status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return status;
    }

    @GetMapping(value = "/")
    public ResponseEntity getByOneCabin(@RequestParam("typeCabin")String typeCabin){

        ResponseEntity status = new ResponseEntity(HttpStatus.NO_CONTENT);

        try{
            if(typeCabin != null && !(typeCabin.trim().equals(""))){
                Cabin cabin = this.cabinService.getByAttributeType(typeCabin);

                if(cabin != null){
                    status = new ResponseEntity<Cabin>(cabin,HttpStatus.OK);
                }
            }
        } catch(Exception e){
            status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return status;
    }
}
