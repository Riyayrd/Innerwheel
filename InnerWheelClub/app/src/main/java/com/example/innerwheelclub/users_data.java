package com.example.innerwheelclub;


public class users_data {


        String Name=null,r_id=null,doj=null,club_name=null,email=null,phno=null,res_addr=null,off_addr=null,dob=null,pass=null;
       /* users_data(String Name,String r_id,String doj,String club_name,String email,String phno,String res_addr,String off_addr,String dob,String pass){
            this.Name=Name;
            this.r_id=r_id;
            this.doj=doj;
            this.club_name=club_name;
            this.email=email;
            this.phno=phno;
            this.res_addr=res_addr;
            this.off_addr=off_addr;
            this.dob=dob;
            this.pass=pass;
        }*/

    public users_data(String Name, String r_id, String doj, String club_name, String email, String phno, String res_addr, String off_addr, String dob, String pass) {
        this.Name = Name;
        this.r_id = r_id;
        this.doj = doj;
        this.club_name = club_name;
        this.email = email;
        this.phno = phno;
        this.res_addr = res_addr;
        this.off_addr = off_addr;
        this.dob = dob;
        this.pass = pass;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getR_id() {
        return r_id;
    }

    public void setR_id(String r_id) {
        this.r_id = r_id;
    }

    public String getDoj() {
        return doj;
    }

    public void setDoj(String doj) {
        this.doj = doj;
    }

    public String getClub_name() {
        return club_name;
    }

    public void setClub_name(String club_name) {
        this.club_name = club_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhno() {
        return phno;
    }

    public void setPhno(String phno) {
        this.phno = phno;
    }

    public String getRes_addr() {
        return res_addr;
    }

    public void setRes_addr(String res_addr) {
        this.res_addr = res_addr;
    }

    public String getOff_addr() {
        return off_addr;
    }

    public void setOff_addr(String off_addr) {
        this.off_addr = off_addr;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
