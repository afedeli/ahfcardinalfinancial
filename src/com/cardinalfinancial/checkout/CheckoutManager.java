package com.cardinalfinancial.checkout;

import com.cardinalfinancial.model.CustomException;
import com.cardinalfinancial.model.RentalAgreement;
import com.cardinalfinancial.model.Tool;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CheckoutManager {

    public static final BigDecimal DAILY_CHARGE_LADDER = new BigDecimal("1.99");
    public static final BigDecimal DAILY_CHARGE_CHAINSAW = new BigDecimal("1.49");
    public static final BigDecimal DAILY_CHARGE_JACKHAMMER = new BigDecimal("2.99");

    public static final int CHARGE_DAYS_ALL = 0;
    public static final int CHARGE_DAYS_WEEKENDS = 1;
    public static final int CHARGE_DAYS_HOLIDAYS = 2;
    public static final int CHARGE_DAYS_BOTH = 3;       //both weekends and holidays are exempt

    public CheckoutManager() {
    }

    public RentalAgreement checkOut(String toolCode, int numRentalDays, int discount, LocalDate checkOutDate) throws CustomException{
        /*
            This function accepts a toolCode, number of rental days, discount, and a check out date
            and generates a RentalAgreement if no exceptions are thrown
        */
        RentalAgreement agreement;      //the rental agreement to be generated
        Tool selectedTool;              //the tool populated based on toolCode
        DateChecker checker;            //used to check if a date is a weekend, independence day or labor day
        LocalDate temp;                 //used to hold the current date we're checking
        String msg;                     //contains the error message if we throw a CustomException
        int chargeDaysCt = 0;           //contains charge days count
        int exemptFlag = 0;             //holds exemptFlag based on toolCode
        BigDecimal tempAmt;             //temporary amount var

        if(numRentalDays <= 1) {
            msg = "The number of rental days supplied is less than 1. ";
            msg += "Please provide 1 or more days for number of days to rent and try again.";
            throw new CustomException(msg);
        }

        if((discount < 0) || (discount > 100)){
            msg = "The discount percent supplied is not between 0-100. ";
            msg += "Please provide a percent between 0-100 and try again.";
            throw new CustomException(msg);
        }

        //Setup the agreement based on toolCode
        selectedTool = new Tool(toolCode);
        agreement = new RentalAgreement();
        agreement.setToolCode(selectedTool.getToolCode());
        agreement.setBrand(selectedTool.getBrand());
        agreement.setToolType(selectedTool.getToolType());
        agreement.setRentalDays(numRentalDays);
        agreement.setCheckOut(checkOutDate);
        agreement.setDiscountPercent(discount);
        agreement.setDueDate(checkOutDate.plusDays(numRentalDays));
        switch (toolCode){
            case Tool.TOOL_CODE_LADW:
                agreement.setDailyRentalFee(DAILY_CHARGE_LADDER);
                exemptFlag = CHARGE_DAYS_ALL;
                break;
            case Tool.TOOL_CODE_CHNS:
                agreement.setDailyRentalFee(DAILY_CHARGE_CHAINSAW);
                exemptFlag = CHARGE_DAYS_WEEKENDS;
                break;
            case Tool.TOOL_CODE_JAKR:
                agreement.setDailyRentalFee(DAILY_CHARGE_JACKHAMMER);
                exemptFlag = CHARGE_DAYS_BOTH;
                break;
            case Tool.TOOL_CODE_JAKD:
                agreement.setDailyRentalFee(DAILY_CHARGE_JACKHAMMER);
                exemptFlag = CHARGE_DAYS_BOTH;
                break;
        }

        //Calculate charge days and pre-discount amount
        temp = checkOutDate;
        checker = new DateChecker();
        for(int i = 0; i < numRentalDays; i++){
            temp = temp.plusDays(1);
            switch(exemptFlag){
                case CHARGE_DAYS_ALL:
                    chargeDaysCt++;
                    break;
                case CHARGE_DAYS_WEEKENDS:
                    if(!checker.isWeekendDay(temp)) chargeDaysCt++;
                    break;
                case CHARGE_DAYS_HOLIDAYS:
                    if(checker.isWeekendDay(temp) && checker.isIndependenceDay(temp)){
                        if (temp.getDayOfWeek() == DayOfWeek.SATURDAY && i > 0) {
                            chargeDaysCt--;
                        } else if (temp.getDayOfWeek() == DayOfWeek.SUNDAY && i != numRentalDays - 1) {
                            chargeDaysCt--;
                        }
                    }else if(!checker.isLaborDay(temp) && !checker.isIndependenceDay(temp)){
                        chargeDaysCt++;
                    }
                    break;
                case CHARGE_DAYS_BOTH:
                    if(checker.isWeekendDay(temp) && checker.isIndependenceDay(temp)){
                        if (temp.getDayOfWeek() == DayOfWeek.SATURDAY && i > 0) {
                            chargeDaysCt--;
                        } else if (temp.getDayOfWeek() == DayOfWeek.SUNDAY && i != numRentalDays - 1) {
                            chargeDaysCt--;
                        }
                    }else if(!checker.isLaborDay(temp) && !checker.isIndependenceDay(temp) && !checker.isWeekendDay(temp)){
                        chargeDaysCt++;
                    }
                    break;
            }
        }
        tempAmt = agreement.getDailyRentalFee().multiply(BigDecimal.valueOf(chargeDaysCt));
        tempAmt = tempAmt.setScale(2,  RoundingMode.HALF_UP);
        agreement.setChargeDays(chargeDaysCt);
        agreement.setPrediscountCharge(tempAmt);

        //Calculate discount amount and final charge
        if(discount > 0 && discount < 100) {
            tempAmt = agreement.getPrediscountCharge();
            tempAmt = tempAmt.multiply(BigDecimal.valueOf(discount).movePointLeft(2));
            tempAmt = tempAmt.setScale(2, RoundingMode.HALF_UP);
        }else if(discount == 100){
            tempAmt = agreement.getPrediscountCharge();
        }else{
            tempAmt = new BigDecimal("0.00");
        }
        agreement.setDiscountAmount(tempAmt);
        agreement.setFinalCharge(agreement.getPrediscountCharge().subtract(agreement.getDiscountAmount()));

        return agreement;
    }

    public void outputAgreement(RentalAgreement agreement){
        /*
            This function accepts a RentalAgreement and prints selected fields
        */
        System.out.println("Tool Code: " + agreement.getToolCode());
        System.out.println("Checkout Date: " + agreement.getCheckOut().format(DateTimeFormatter.ofPattern("M/dd/yy")));
        System.out.println("Rental Days: " + agreement.getRentalDays());
        System.out.println("Discount: " + agreement.getDiscountPercent() + "%");

        System.out.println("");

        System.out.println("Due Date: " + agreement.getDueDate().format(DateTimeFormatter.ofPattern("M/dd/yy")));
        System.out.println("Daily Charge: " + NumberFormat.getCurrencyInstance().format(agreement.getDailyRentalFee()));
        System.out.println("Charge Days: " + agreement.getChargeDays());
        System.out.println("Pre-discount Charge: " + NumberFormat.getCurrencyInstance().format(agreement.getPrediscountCharge()));
        System.out.println("Discount: " + agreement.getDiscountPercent() + "%");
        System.out.println("Discount Amount: " + NumberFormat.getCurrencyInstance().format(agreement.getDiscountAmount()));
        System.out.println("Final Charge: " + NumberFormat.getCurrencyInstance().format(agreement.getFinalCharge()));
    }
}
