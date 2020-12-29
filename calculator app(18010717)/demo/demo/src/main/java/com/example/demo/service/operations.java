package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public class operations {
    public static double doubleOperations(double num1, double num2, String operator){
        double result=0;
        if(operator.equals("pulse")){
            result=num1+num2;
        }else if(operator.equals("subtract")){
            result=num1-num2;
        }else if(operator.equals("multi")){
            result=num1*num2;
        }else if(operator.equals("divide")) {
            result = num1 / num2;
        }else if(operator.equals("mode")){
            result=num1%num2;
        }
        return result;
    }
    public static double singleOperations(double num1,String operator){
        double result=0;
        if(operator.equals("root")){
            result=Math.sqrt(num1);
        }else if(operator.equals("toggle")){
            result=num1*-1;
        }else if(operator.equals("power")){
            result= Math.pow(num1,2);
        }else if(operator.equals("fraction")){
            result=1/num1;
        }else if(operator.equals("percent")){
        }
        return result;
    }
}
