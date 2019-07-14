package com.example.abdullah.getdone;

public class get_offer {
    private int gig_id;
    private String buyer_name;

    private String amount;




    public get_offer(int gig_id, String buyer_name, String amount) {
        this.gig_id = gig_id;
        this.buyer_name = buyer_name;
        this.amount = amount;

    }

    public int getId() {
        return gig_id;
    }

    public String getName() {
        return buyer_name;
    }

    public String getAmount() {
        return amount;
    }


}
