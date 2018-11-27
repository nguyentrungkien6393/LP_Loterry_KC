package com.kien.lp.myapplication.object;

public class post_Ticket_Json {
    String game_id;
    String user_id;
    String numbers;
    String special_numbers;
    String price;
    String status;

    public post_Ticket_Json(String game_id, String user_id, String numbers, String special_numbers, String price, String status, String txhash) {
        this.game_id = game_id;
        this.user_id = user_id;
        this.numbers = numbers;
        this.special_numbers = special_numbers;
        this.price = price;
        this.status = status;
        this.txhash = txhash;
    }

    String txhash;
}
