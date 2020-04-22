package com.example.audio;

public class FeePojo {

    String fee;
    String remark;
    String time;


    public FeePojo(){

    }

    public FeePojo(String fee,String remark,String time){

        this.fee = fee;
        this.time=time;
        this.remark = remark;

    }

    public String getFee(){return fee; }

    public  void setFee(String mobile){this.fee=fee;}

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time=time;
    }

    public String getRemark() {
        return remark;
    }


    public void setRemark(String remark) {
        this.remark=remark;
    }


}
