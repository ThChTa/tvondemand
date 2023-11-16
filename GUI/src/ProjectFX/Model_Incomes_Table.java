/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ProjectFX;

/**
 *
 * @author PX
 */
public class Model_Incomes_Table {
    String mon_year,amount;

    public String getMon_year() {
        return mon_year;
    }

    public void setMon_year(String mon_year) {
        this.mon_year = mon_year;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Model_Incomes_Table(String mon_year, String amount) {
        this.mon_year = mon_year;
        this.amount = amount;
    }
}
