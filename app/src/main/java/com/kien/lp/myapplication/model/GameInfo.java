package com.kien.lp.myapplication.model;

public class GameInfo {
String id;
String name;
String image;
String ticket_price;
String numbers;
String min_number;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTicket_price() {
        return ticket_price;
    }

    public void setTicket_price(String ticket_price) {
        this.ticket_price = ticket_price;
    }

    public String getNumbers() {
        return numbers;
    }

    public void setNumbers(String numbers) {
        this.numbers = numbers;
    }

    public String getMin_number() {
        return min_number;
    }

    public void setMin_number(String min_number) {
        this.min_number = min_number;
    }

    public String getMax_number() {
        return max_number;
    }

    public void setMax_number(String max_number) {
        this.max_number = max_number;
    }

    public String getHas_special_number() {
        return has_special_number;
    }

    public void setHas_special_number(String has_special_number) {
        this.has_special_number = has_special_number;
    }

    public String getMin_special() {
        return min_special;
    }

    public void setMin_special(String min_special) {
        this.min_special = min_special;
    }

    public String getMax_special() {
        return max_special;
    }

    public void setMax_special(String max_special) {
        this.max_special = max_special;
    }

    public String getDraw_time() {
        return draw_time;
    }

    public void setDraw_time(String draw_time) {
        this.draw_time = draw_time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSpecial_numbers() {
        return special_numbers;
    }

    public void setSpecial_numbers(String special_numbers) {
        this.special_numbers = special_numbers;
    }

    String max_number;
String has_special_number;
String min_special;
String max_special;
String draw_time;
String description;
String special_numbers;

    public GameInfo(String id, String name, String image, String ticket_price, String numbers, String max_number, String has_special_number, String max_special, String draw_time, String description, String special_numbers) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.ticket_price = ticket_price;
        this.numbers = numbers;
        this.max_number = max_number;
        this.has_special_number = has_special_number;
        this.max_special = max_special;
        this.draw_time = draw_time;
        this.description = description;
        this.special_numbers = special_numbers;
    }

    public GameInfo(String id, String name, String image, String ticket_price, String numbers, String min_number, String max_number, String has_special_number, String min_special, String max_special, String draw_time, String description, String special_numbers) {

        this.id = id;
        this.name = name;
        this.image = image;
        this.ticket_price = ticket_price;
        this.numbers = numbers;
        this.min_number = min_number;
        this.max_number = max_number;
        this.has_special_number = has_special_number;
        this.min_special = min_special;
        this.max_special = max_special;
        this.draw_time = draw_time;
        this.description = description;
        this.special_numbers = special_numbers;
    }
}
