package com.ApiVuelos.ApiVuelos.controller;

import com.ApiVuelos.ApiVuelos.service.CabinService;
import com.ApiVuelos.ApiVuelos.service.PriceService;
import com.utn.tssi.tp5.Models.model.Cabin;
import com.utn.tssi.tp5.Models.model.Price;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/price")
public class PriceController {

    @Autowired
    private PriceService priceService;

    @Autowired
    private CabinService cabinService;

    @PostMapping(value = "/", consumes = "application/json")
    public ResponseEntity add(@RequestBody List<Price> prices) {

        ResponseEntity status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);

        try{
            for (Price price : prices) {
                Cabin cabin = price.getCabin();

                if (cabin != null && !(cabin.validateNullEmptyIdentifier())) {
                    cabin = this.cabinService.getByAttributeType(cabin.getName());
                    price.setCabin(cabin);

                    if(!price.validateNullEmpty()) {
                        this.priceService.newObject(price);
                        status = new ResponseEntity(HttpStatus.OK);

                    } else {
                        status = new ResponseEntity(HttpStatus.NO_CONTENT);
                        break;
                    }
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

    @PutMapping(value = "/", consumes = "application/json")
    public ResponseEntity update(@RequestBody Price value){

        ResponseEntity status = new ResponseEntity(HttpStatus.NO_CONTENT);

        try{
            if (value != null) {
                Price priceDB = this.priceService.getById(value.getId());

                if(priceDB != null) {
                    Cabin cabin = this.cabinService.getByAttributeType(value.getCabin().getName());
                    value.setCabin(cabin);

                    if (!value.validateNullEmpty()) {
                        this.priceService.newObject(value);
                        status = new ResponseEntity(HttpStatus.OK);
                    }
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
                this.priceService.removeObject(id);
                status = new ResponseEntity(HttpStatus.OK);
            }
        } catch(Exception e){
            status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return status;
    }

    @GetMapping
    public ResponseEntity<List<Price>> getAll() {

        ResponseEntity status = new ResponseEntity(HttpStatus.NO_CONTENT);

        try
        {
            List<Price> prices = this.priceService.getAll();
            if (!prices.isEmpty()) {
                status = new ResponseEntity<List<Price>>(prices, HttpStatus.OK);
            }
        } catch (Exception e) {
            status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return status;
    }

    @GetMapping(value = "/")
    public ResponseEntity getPriceOfCabinAndDate(@RequestParam("typeCabin")String typeCabin, @RequestParam("date")String date){

        ResponseEntity status = new ResponseEntity(HttpStatus.NO_CONTENT);

        try{
            if(typeCabin != null && !(typeCabin.trim().equals(""))){
                Price price = this.priceService.getPriceOfCabinAndDate(typeCabin, date);

                if(price != null){
                    status = new ResponseEntity<Price>(price, HttpStatus.OK);
                }
            }
        } catch(Exception e){
            status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return status;
    }
}
