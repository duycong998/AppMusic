package com.example.appmusicc.Model;
import java.io.Serializable;

public class Menu implements  Serializable {
    private String mName;

    public String mPicture;

    public Menu(String mName, String mPicture) {
        this.mName = mName;
        this.mPicture = mPicture;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }




    public String getmPicture() {
        return mPicture;
    }

    public void setmPicture(String mPicture) {
        this.mPicture = mPicture;
    }
}
