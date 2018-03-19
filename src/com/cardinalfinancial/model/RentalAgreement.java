package com.cardinalfinancial.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class RentalAgreement {
    private String toolCode;                //Tool Code for rental agreement, specified at checkout
    private String brand;                   //Tool Type for rental agreement, retrieved via toolCode
    private int toolType;                   //brand for rental agreement, retrieved via toolCode
    private int rentalDays;                 //number of days for rental agreement, specified at checkout
    private LocalDate checkOut;                  //day this tool was checked out, specified at checkout
    private LocalDate dueDate;                   //day this tool is to be returned, calculated via checkout date and rental days

    private BigDecimal dailyRentalFee;      //amount charged per day, specified by toolType
    private int chargeDays;                 /*count of chargeable days, from AFTER checkout through and including due date.
                                              this excludes "no charge" days as specified by toolType*/
    private BigDecimal prediscountCharge;   //amount calculated by charge days * daily charge, result rounded up to cents
    private int discountPercent;            //percent discount for the rental, specified at checkout
    private BigDecimal discountAmount;      //calculated amount via discount% and prediscount charge. result rounded up to cents
    private BigDecimal finalCharge;         //final amount calculated via prediscount charge - discount amount

    //GETTERS
    public String getToolCode() {
        return toolCode;
    }

    public String getBrand() {
        return brand;
    }

    public int getToolType() {
        return toolType;
    }

    public int getRentalDays() {
        return rentalDays;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public BigDecimal getDailyRentalFee() {
        return dailyRentalFee;
    }

    public int getChargeDays() {
        return chargeDays;
    }

    public BigDecimal getPrediscountCharge() {
        return prediscountCharge;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public BigDecimal getFinalCharge() {
        return finalCharge;
    }

    //SETTERS
    public void setToolCode(String toolCode) {
        this.toolCode = toolCode;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setToolType(int toolType) {
        this.toolType = toolType;
    }

    public void setRentalDays(int rentalDays) {
        this.rentalDays = rentalDays;
    }

    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setDailyRentalFee(BigDecimal dailyRentalFee) {
        this.dailyRentalFee = dailyRentalFee;
    }

    public void setChargeDays(int chargeDays) {
        this.chargeDays = chargeDays;
    }

    public void setPrediscountCharge(BigDecimal prediscountCharge) {
        this.prediscountCharge = prediscountCharge;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public void setFinalCharge(BigDecimal finalCharge) {
        this.finalCharge = finalCharge;
    }
}
