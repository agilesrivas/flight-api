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

        ResponseEntity status = new ResponseEntity(HttpStatus.NO_CONTENT);

        try{
            for (Price price : prices) {
                Cabin cabin = price.getCabin();

                if (cabin != null && !(cabin.validateNullEmptyIdentifier())) {
                    cabin = this.cabinService.getByAttributeType(price.getCabin().getName());
                    price.setCabin(cabin);

                    if(!price.validateNullEmpty()) {
                        this.priceService.newObject(price);
                        status = new ResponseEntity(HttpStatus.OK);

                    } else {
                        status = new ResponseEntity(HttpStatus.NO_CONTENT);
                    }
                } else {
                    status = new ResponseEntity(HttpStatus.NO_CONTENT);
                }
            }
        } catch(Exception e){
            status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return status;
    }

    @PutMapping(value = "/")
    public ResponseEntity update(Price value){

        ResponseEntity status = new ResponseEntity(HttpStatus.NO_CONTENT);

        try{
            if (!value.validateNullEmpty()) {
                Cabin cabin = null;
                cabin = this.cabinService.getByAttributeType(value.getCabin().getName());

                if(value != null) {
                    value.setCabin(cabin);
                    this.priceService.updateObject(value);
                    status = new ResponseEntity(HttpStatus.OK);

                } else {
                    status = new ResponseEntity(HttpStatus.NO_CONTENT);
                }
            } else {
                status = new ResponseEntity(HttpStatus.NO_CONTENT);
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
            }else{
                status = new ResponseEntity(HttpStatus.NO_CONTENT);
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

            }else{
                status = new ResponseEntity(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return status;
    }

    @GetMapping(value = "/")
    public ResponseEntity getByOnePricesOffCabin(@RequestParam("typeCabin")String typeCabin){

        ResponseEntity status = new ResponseEntity(HttpStatus.NO_CONTENT);
        List<Price> prices= new ArrayList<Price>();

        try{
            if(typeCabin != null && !(typeCabin.trim().equals(""))){
                prices = this.priceService.getByAttributeTypePricesOffCabin(typeCabin);

                if(!prices.isEmpty()){
                    status = new ResponseEntity<List<Price>>(prices,HttpStatus.OK);

                } else {
                    status = new ResponseEntity(HttpStatus.NO_CONTENT);
                }
            } else {
                status = new ResponseEntity(HttpStatus.NO_CONTENT);
            }
        } catch(Exception e){
            status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return status;
    }
}
