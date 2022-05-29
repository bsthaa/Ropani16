package com.realestate.ropani16;

public class PropertyDesc {
    private int bImageResource;
    private String bPropertyName;
    private String bPropertyDesc;

    public PropertyDesc(int imageResource, String text, String text2){
        bImageResource = imageResource;
        bPropertyName = text;
        bPropertyDesc = text2;
    }

    public int getbImageResource() {
        return bImageResource;
    }

    public String getbPropertyName() {
        return bPropertyName;
    }

    public String getbPropertyDesc(){return bPropertyDesc;}
}
