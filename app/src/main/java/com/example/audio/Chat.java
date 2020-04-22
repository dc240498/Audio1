package com.example.audio;

public class Chat {

    String name;
    String batch;
    String mobile;
    String father;

    public Chat(){

    }

    public Chat(String name,String batch,String father,String mobile){

        this.name = name;
        this.mobile=mobile;
        this.batch = batch;
        this.father = father;
    }

    public String getMobile(){return mobile; }
    public  void setMobile(String mobile){this.mobile=mobile;}

    public String getFather() {
        return father;
    }

    public void setFather(String father) {
        this.father=father;
    }

    public String getName() {
        return name;
    }

    public String getBatch() {
        return batch;
    }

    public void setName(String name) {
        this.name=name;
    }

    public void setBatch(String batch) {
        this.batch=batch;
    }
}
