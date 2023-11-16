/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ProjectFX;

/**
 *
 * @author PX
 */
public class Model_Admins_Table {
    String admins_id,fname,lname,email,gender,salary,address,district,pcode,phone,city,country;

    public String getAdmins_id() {
        return admins_id;
    }

    public void setAdmins_id(String admins_id) {
        this.admins_id = admins_id;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
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

    public Model_Admins_Table(String admins_id, String fname, String lname, String email, String gender, String salary, String address, String district, String pcode, String phone, String city, String country) {
        this.admins_id = admins_id;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.gender = gender;
        this.salary = salary;
        this.address = address;
        this.district = district;
        this.pcode = pcode;
        this.phone = phone;
        this.city = city;
        this.country = country;
    }

    
}
