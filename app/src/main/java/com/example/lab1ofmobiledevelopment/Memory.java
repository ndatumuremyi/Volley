package com.example.lab1ofmobiledevelopment;

public class Memory {
    private String memory;
    private Province province;
    private int id;

    public String getMemory() {
        return memory;
    }
    public Memory(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Memory(int id, String memory, String province){
        this.id = id;
        this.memory = memory;
        this.province = new Province(province);
    }

    public Memory( String memory, String province){
        this.memory = memory;
        this.province = new Province(province);
    }
    public void setMemory(String memory) {
        this.memory = memory;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }
}
