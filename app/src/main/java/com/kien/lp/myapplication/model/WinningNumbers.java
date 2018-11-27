package com.kien.lp.myapplication.model;

public class WinningNumbers {
    String id;
    String game_id;
    String draw_date;

    public WinningNumbers(String id, String game_id, String draw_date, String numbers, String special_number) {
        this.id = id;
        this.game_id = game_id;
        this.draw_date = draw_date;
        this.numbers = numbers;
        this.special_number = special_number;
    }

    String numbers;

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

    public String getDraw_date() {
        return draw_date;
    }

    public void setDraw_date(String draw_date) {
        this.draw_date = draw_date;
    }

    public String getNumbers() {
        return numbers;
    }

    public void setNumbers(String numbers) {
        this.numbers = numbers;
    }

    public String getSpecial_number() {
        return special_number;
    }

    public void setSpecial_number(String special_number) {
        this.special_number = special_number;
    }

    String special_number;
}
