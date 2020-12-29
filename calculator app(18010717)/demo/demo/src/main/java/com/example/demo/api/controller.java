package com.example.demo.api;

import com.example.demo.service.operations;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class controller {
    @GetMapping("/{num1}/{num2}/{operator}")
    public double  doubleOperation(@PathVariable("num1") double num1 ,@PathVariable("num2") double num2,@PathVariable("operator") String operator){
        double result= operations.doubleOperations(num1,num2,operator);
        return result;
    }
    @GetMapping("/{num1}/{operator}")
    public double  singleOperation(@PathVariable("num1") double num1 ,@PathVariable("operator") String operator){
        double result= operations.singleOperations(num1,operator);
        return result;
    }

}
