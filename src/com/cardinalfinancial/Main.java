package com.cardinalfinancial;

import com.cardinalfinancial.checkout.CheckoutManager;
import com.cardinalfinancial.model.CustomException;
import com.cardinalfinancial.model.RentalAgreement;
import com.cardinalfinancial.model.Tool;
import java.time.LocalDate;


public class Main {
    public static void main(String args[]){
        RentalAgreement agreement;
        CheckoutManager manager = new CheckoutManager();
        try{
            agreement = manager.checkOut(Tool.TOOL_CODE_LADW, 5, 10, LocalDate.of(2015, 9, 3));
            manager.outputAgreement(agreement);
        }catch(CustomException e){
            System.out.println(e.getMessage());
        }

    }
}
