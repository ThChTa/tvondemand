/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ProjectFX;

/**
 *
 * @author PX
 */
public class Model_Logs_Table {
    String fname,lname,change_date,katastasi,eidos,table_name;

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getChange_date() {
        return change_date;
    }

    public void setChange_date(String change_date) {
        this.change_date = change_date;
    }

    public String getKatastasi() {
        return katastasi;
    }

    public void setKatastasi(String katastasi) {
        this.katastasi = katastasi;
    }

    public String getEidos() {
        return eidos;
    }

    public void setEidos(String eidos) {
        this.eidos = eidos;
    }

    public String getTable_name() {
        return table_name;
    }

    public void setTable_name(String table_name) {
        this.table_name = table_name;
    }

    public Model_Logs_Table(String fname, String lname, String change_date, String katastasi, String eidos, String table_name) {
        this.fname = fname;
        this.lname = lname;
        this.change_date = change_date;
        this.katastasi = katastasi;
        this.eidos = eidos;
        this.table_name = table_name;
    }
    
}
