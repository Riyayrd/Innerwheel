package com.example.innerwheelclub;

import android.util.Log;

public class item_member {
    String Name, Phone;
    public item_member(String n, String p) {
        this.Name=n;
        this.Phone=p;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
        Log.e("msg",Name);
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }
}
