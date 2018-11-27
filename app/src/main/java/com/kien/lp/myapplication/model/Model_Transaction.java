package com.kien.lp.myapplication.model;

public class Model_Transaction {
    public Model_Transaction(String id, String game_id, String user_id, String numbers, String status, String created_at, String updated_at, String special_numbers, String price, String txhash, String img) {
        this.id = id;
        this.game_id = game_id;
        this.user_id = user_id;
        this.numbers = numbers;
        this.status = status;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.special_numbers = special_numbers;
        this.price = price;
        this.txhash = txhash;
        this.img = img;
    }

    String id;
    String game_id;
    String user_id;
    String numbers;
    String status;
    String created_at;
    String updated_at;
    String special_numbers;
    String price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGame_id() {
        return game_id;
    }

    public void setGame_id(String game_id) {
        this.game_id = game_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getNumbers() {
        return numbers;
    }

    public void setNumbers(String numbers) {
        this.numbers = numbers;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getSpecial_numbers() {
        return special_numbers;
    }

    public void setSpecial_numbers(String special_numbers) {
        this.special_numbers = special_numbers;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTxhash() {
        return txhash;
    }

    public void setTxhash(String txhash) {
        this.txhash = txhash;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    String txhash;
    String img;
}
