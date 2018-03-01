package com.vicmob.fly.newview;

/**
 * Created by smartOrange_3 on 2018/3/1.
 */

public class Fruit  {
    private String name;
    private int imageid;

    public Fruit(String name, int imageid) {
        this.name = name ;
        this.imageid = imageid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageid() {
        return imageid;
    }

    public void setImageid(int imageid) {
        this.imageid = imageid;
    }
}
