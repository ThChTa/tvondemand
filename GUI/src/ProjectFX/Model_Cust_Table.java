/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ProjectFX;

/**
 *
 * @author vpddk
 */
public class Model_Cust_Table { 
    String customer_id,fname,lname,email,address,district,pcode,phone,city,country,cr_date,fpack,spack,flpack,active;

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getPcode() {
        return pcode;
    }

    public void setPcode(String pcode) {
        this.pcode = pcode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCr_date() {
        return cr_date;
    }

    public void setCr_date(String cr_date) {
        this.cr_date = cr_date;
    }

    public String getFpack() {
        return fpack;
    }

    public void setFpack(String fpack) {
        this.fpack = fpack;
    }

    public String getSpack() {
        return spack;
    }

    public void setSpack(String spack) {
        this.spack = spack;
    }

    public String getFlpack() {
        return flpack;
    }

    public void setFlpack(String flpack) {
        this.flpack = flpack;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public Model_Cust_Table(String customer_id, String fname, String lname, String email, String address, String district, String pcode, String phone, String city, String country, String cr_date, String fpack, String spack, String flpack, String active) {
        this.customer_id = customer_id;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.address = address;
        this.district = district;
        this.pcode = pcode;
        this.phone = phone;
        this.city = city;
        this.country = country;
        this.cr_date = cr_date;
        this.fpack = fpack;
        this.spack = spack;
        this.flpack = flpack;
        this.active = active;
    }

}