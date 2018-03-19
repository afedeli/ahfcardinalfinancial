import com.cardinalfinancial.checkout.CheckoutManager;
import com.cardinalfinancial.model.CustomException;
import com.cardinalfinancial.model.RentalAgreement;
import com.cardinalfinancial.model.Tool;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

public class UnitTest1 {

    CheckoutManager manager = new CheckoutManager();

    @Test
    public void testCaseOne(){
        //Input values
        String toolCode = Tool.TOOL_CODE_JAKR;
        LocalDate checkOut = LocalDate.of(2015,9,3);
        int rentalDays = 5;
        int discount = 101;
        //Output values
        LocalDate dueDate = LocalDate.of(2015,9,8);
        BigDecimal dailyCharge = manager.DAILY_CHARGE_LADDER;
        int chargeDays = 5;
        BigDecimal preDiscountCharge = new BigDecimal("9.95");
        BigDecimal discountAmount = new BigDecimal("1.00");
        BigDecimal finalAmount = new BigDecimal("8.95");

        RentalAgreement agreement = new RentalAgreement();
        try{
            agreement = manager.checkOut(toolCode, rentalDays, discount, checkOut);
            System.out.println("***TestCase1***");
            manager.outputAgreement(agreement);
            System.out.println("");
            assertEquals("Due Date", agreement.getDueDate(), dueDate);
            assertEquals("Daily Charge", agreement.getDailyRentalFee(), dailyCharge);
            assertEquals("PreDiscount Charge", agreement.getPrediscountCharge(), preDiscountCharge);
            assertEquals("Percent Discount", agreement.getDiscountPercent(), discount);
            assertEquals("Discount Amount", agreement.getDiscountAmount(), discountAmount);
            assertEquals("Final Charge", agreement.getFinalCharge(), finalAmount);
        }catch(CustomException e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testCaseTwo(){
        //Input values
        String toolCode = Tool.TOOL_CODE_LADW;
        LocalDate checkOut = LocalDate.of(2015,9,3);
        int rentalDays = 5;
        int discount = 10;
        //Output values
        LocalDate dueDate = LocalDate.of(2015,9,8);
        BigDecimal dailyCharge = manager.DAILY_CHARGE_LADDER;
        int chargeDays = 5;
        BigDecimal preDiscountCharge = new BigDecimal("9.95");
        BigDecimal discountAmount = new BigDecimal("1.00");
        BigDecimal finalAmount = new BigDecimal("8.95");

        RentalAgreement agreement = new RentalAgreement();
        try{
            agreement = manager.checkOut(toolCode, rentalDays, discount, checkOut);
            System.out.println("***TestCase2***");
            manager.outputAgreement(agreement);
            System.out.println("");
            assertEquals("Due Date", agreement.getDueDate(), dueDate);
            assertEquals("Daily Charge", agreement.getDailyRentalFee(), dailyCharge);
            assertEquals("PreDiscount Charge", agreement.getPrediscountCharge(), preDiscountCharge);
            assertEquals("Percent Discount", agreement.getDiscountPercent(), discount);
            assertEquals("Discount Amount", agreement.getDiscountAmount(), discountAmount);
            assertEquals("Final Charge", agreement.getFinalCharge(), finalAmount);
        }catch(CustomException e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testCaseThree(){
        //Input values
        String toolCode = Tool.TOOL_CODE_CHNS;
        LocalDate checkOut = LocalDate.of(2015,7,2);
        int rentalDays = 5;
        int discount = 25;
        //Output values
        LocalDate dueDate = LocalDate.of(2015,7,7);
        BigDecimal dailyCharge = manager.DAILY_CHARGE_CHAINSAW;
        int chargeDays = 3;
        BigDecimal preDiscountCharge = new BigDecimal("4.47");
        BigDecimal discountAmount = new BigDecimal("1.12");
        BigDecimal finalAmount = new BigDecimal("3.35");

        RentalAgreement agreement = new RentalAgreement();
        try{
            agreement = manager.checkOut(toolCode, rentalDays, discount, checkOut);
            System.out.println("***TestCase3***");
            manager.outputAgreement(agreement);
            System.out.println("");
            assertEquals("Due Date", agreement.getDueDate(), dueDate);
            assertEquals("Daily Charge", agreement.getDailyRentalFee(), dailyCharge);
            assertEquals("PreDiscount Charge", agreement.getPrediscountCharge(), preDiscountCharge);
            assertEquals("Percent Discount", agreement.getDiscountPercent(), discount);
            assertEquals("Discount Amount", agreement.getDiscountAmount(), discountAmount);
            assertEquals("Final Charge", agreement.getFinalCharge(), finalAmount);
        }catch(CustomException e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testCaseFour(){
        //Input values
        String toolCode = Tool.TOOL_CODE_JAKD;
        LocalDate checkOut = LocalDate.of(2015,9,3);
        int rentalDays = 6;
        int discount = 0;
        //Output values
        LocalDate dueDate = LocalDate.of(2015,9,9);
        BigDecimal dailyCharge = manager.DAILY_CHARGE_JACKHAMMER;
        int chargeDays = 3;
        BigDecimal preDiscountCharge = new BigDecimal("8.97");
        BigDecimal discountAmount = new BigDecimal("0.00");
        BigDecimal finalAmount = new BigDecimal("8.97");

        RentalAgreement agreement = new RentalAgreement();
        try{
            agreement = manager.checkOut(toolCode, rentalDays, discount, checkOut);
            System.out.println("***TestCase4***");
            manager.outputAgreement(agreement);
            System.out.println("");
            assertEquals("Due Date", agreement.getDueDate(), dueDate);
            assertEquals("Daily Charge", agreement.getDailyRentalFee(), dailyCharge);
            assertEquals("PreDiscount Charge", agreement.getPrediscountCharge(), preDiscountCharge);
            assertEquals("Percent Discount", agreement.getDiscountPercent(), discount);
            assertEquals("Discount Amount", agreement.getDiscountAmount(), discountAmount);
            assertEquals("Final Charge", agreement.getFinalCharge(), finalAmount);
        }catch(CustomException e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testCaseFive(){
        //Input values
        String toolCode = Tool.TOOL_CODE_JAKR;
        LocalDate checkOut = LocalDate.of(2015,7,2);
        int rentalDays = 9;
        int discount = 0;
        //Output values
        LocalDate dueDate = LocalDate.of(2015,7,11);
        BigDecimal dailyCharge = manager.DAILY_CHARGE_JACKHAMMER;
        int chargeDays = 5;
        BigDecimal preDiscountCharge = new BigDecimal("14.95");
        BigDecimal discountAmount = new BigDecimal("0.00");
        BigDecimal finalAmount = new BigDecimal("14.95");

        RentalAgreement agreement = new RentalAgreement();
        try{
            agreement = manager.checkOut(toolCode, rentalDays, discount, checkOut);
            System.out.println("***TestCase5***");
            manager.outputAgreement(agreement);
            System.out.println("");
            assertEquals("Due Date", agreement.getDueDate(), dueDate);
            assertEquals("Daily Charge", agreement.getDailyRentalFee(), dailyCharge);
            assertEquals("PreDiscount Charge", agreement.getPrediscountCharge(), preDiscountCharge);
            assertEquals("Percent Discount", agreement.getDiscountPercent(), discount);
            assertEquals("Discount Amount", agreement.getDiscountAmount(), discountAmount);
            assertEquals("Final Charge", agreement.getFinalCharge(), finalAmount);
        }catch(CustomException e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testCaseSix(){
        //Input values
        String toolCode = Tool.TOOL_CODE_JAKR;
        LocalDate checkOut = LocalDate.of(2020,7,2);
        int rentalDays = 4;
        int discount = 50;
        //Output values
        LocalDate dueDate = LocalDate.of(2020,7,6);
        BigDecimal dailyCharge = manager.DAILY_CHARGE_JACKHAMMER;
        int chargeDays = 1;
        BigDecimal preDiscountCharge = new BigDecimal("2.99");
        BigDecimal discountAmount = new BigDecimal("1.50");
        BigDecimal finalAmount = new BigDecimal("1.49");

        RentalAgreement agreement = new RentalAgreement();
        try{
            agreement = manager.checkOut(toolCode, rentalDays, discount, checkOut);
            System.out.println("***TestCase6***");
            manager.outputAgreement(agreement);
            System.out.println("");
            assertEquals("Due Date", agreement.getDueDate(), dueDate);
            assertEquals("Daily Charge", agreement.getDailyRentalFee(), dailyCharge);
            assertEquals("PreDiscount Charge", agreement.getPrediscountCharge(), preDiscountCharge);
            assertEquals("Percent Discount", agreement.getDiscountPercent(), discount);
            assertEquals("Discount Amount", agreement.getDiscountAmount(), discountAmount);
            assertEquals("Final Charge", agreement.getFinalCharge(), finalAmount);
        }catch(CustomException e){
            System.out.println(e.getMessage());
        }
    }
}
