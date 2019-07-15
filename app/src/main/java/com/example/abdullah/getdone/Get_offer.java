package com.example.abdullah.getdone;

public class Get_offer {
    private int gig_id;
    private String buyer_name;
    private String amount;



    public Get_offer(int gig_id,  String buyer_name, String amount) {
        this.gig_id = gig_id;
        this.buyer_name = buyer_name;
        this.amount = amount;

    }

    public int getId() {
        return gig_id;
    }

    public String getbuyer_name() {
        return buyer_name;
    }

    public String getamount() {
        return amount;
    }




}