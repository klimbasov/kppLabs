package com.example.demo.model;




public class Client {

    private Integer id;
    //@Column(name = "name", nullable = false)
    private String name;
    private String email;
    private String phone;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isValid(){
        if(this.id == null || this.name == null || this.email == null || this.phone == null){
            return false;
        }
        if(this.id <= 0 || this.name.isEmpty() || this.name.length() > 20 || this.email.isEmpty() || this.email.length() > 20 || this.phone.isEmpty() || this.phone.length() > 20)
            return false;
        return true;
    }
}