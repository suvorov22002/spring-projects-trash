package com.tutorialspoint.demo.interceptor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/interceptor/api/v1.0")
public class ProductServiceController {

    private static Map<String, Product> productRepo = new HashMap<>();
    static {
        Product honey = new Product("1", "Honey");

        productRepo.put(honey.id(), honey);
        Product almond = new Product("2", "Almond");
        productRepo.put(almond.id(), almond);
    }
    @RequestMapping(value = "/products")
    public ResponseEntity<Object> getProduct() {
        return new ResponseEntity<>(productRepo.values(), HttpStatus.OK);
    }

    @PostMapping(value = "/products")
    public ResponseEntity<Object> createProduct(@RequestBody Product product) {

       try{
           productRepo.put(product.id(), product);
       }
       catch (Exception ex) {
           ex.printStackTrace();
       }

        return new ResponseEntity<>(productRepo.values(), HttpStatus.OK);
    }

}
