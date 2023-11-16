/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ProjectFX;

/**
 *
 * @author PX
 */
public class Model_Employee_Table {
    String employee_id,fname,lname,email,gender,date_of_hire,salary,rating,mobile,address,district,pcode,phone,city,country;    

    public Model_Employee_Table(String employee_id, String fname, String lname, String email, String gender, String date_of_hire, String salary, String rating, String address, String district, String pcode, String phone, String city, String country) {
        this.employee_id = employee_id;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.gender = gender;
        this.date_of_hire = date_of_hire;
        this.salary = salary;
        this.rating = rating;
        this.address = address;
        this.district = district;
        this.pcode = pcode;
        this.phone = phone;
        this.city = city;
        this.country = country;
    }

    public String getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
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

    public String getDate_of_hire() {
        return date_of_hire;
    }

    public void setDate_of_hire(String date_of_hire) {
        this.date_of_hire = date_of_hire;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
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

   
}
