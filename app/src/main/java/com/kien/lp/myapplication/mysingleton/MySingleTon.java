package com.kien.lp.myapplication.mysingleton;

import com.kien.lp.myapplication.adapter.buyticket.Ticket;
import com.kien.lp.myapplication.model.Item_View_Number;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MySingleTon {
    private static MySingleTon INSTANCE = null;
    private int RENDERCOUNT = 2;
    public int choose_number = 0;
    public Map<String, String> map;
    public HashMap<Integer, Ticket> mHashMap;
    public List<Item_View_Number> mList_View_Number;
    public HashMap<Integer, Ticket> getmHashMap() {
        return mHashMap;
    }
    public List<Ticket>mList_Ticket;
    public List<Item_View_Number> getmList_View_Number() {
        return mList_View_Number;
    }

    public void setmList_View_Number(List<Item_View_Number> mList_View_Number) {
        this.mList_View_Number = mList_View_Number;
    }

    public void setmHashMap(HashMap<Integer, Ticket> mHashMap) {
        this.mHashMap = mHashMap;
    }


    public int getChoose_number() {
        return choose_number;
    }

    public void setChoose_number(int choose_number) {
        this.choose_number = choose_number;
    }

    public String url_images_portrait = "";
    public String url_images_passport = "";
    public String Email_Account = "";
    public String Password_Account = "";
    public String Phone_Account = "";
    public String Full_Name_Personal = "";
    public String Date_of_Birth_Personal = "";
    public String Gender_Personal = "";
    public String Country_Personal = "";
    public String Wallet_Finish = "";
    public String Adress_Finish = "";
    public String Image_Portrait = "";
    public String Image_Pasport = "";
    public String Image_Portrait_Choose = "";
    public String Image_Passport_Choose = "";
    public String LTR_Balance = "";
    public double Price_usd;

    public double getPrice_usd() {
        return Price_usd;
    }

    public void setPrice_usd(double price_usd) {
        Price_usd = price_usd;
    }

    public String getLTR_Balance() {
        return LTR_Balance;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public void setLTR_Balance(String LTR_Balance) {
        this.LTR_Balance = LTR_Balance;
    }

    public String getETH_Balance() {
        return ETH_Balance;
    }

    public void setETH_Balance(String ETH_Balance) {
        this.ETH_Balance = ETH_Balance;
    }

    public String ETH_Balance = "";

    public String getUrl_images_portrait() {
        return url_images_portrait;
    }

    public void setUrl_images_portrait(String url_images_portrait) {
        this.url_images_portrait = url_images_portrait;
    }

    public String getUrl_images_passport() {
        return url_images_passport;
    }

    public void setUrl_images_passport(String url_images_passport) {
        this.url_images_passport = url_images_passport;
    }

    public String getEmail_Account() {
        return Email_Account;
    }

    public void setEmail_Account(String email_Account) {
        Email_Account = email_Account;
    }

    public String getPassword_Account() {
        return Password_Account;
    }

    public void setPassword_Account(String password_Account) {
        Password_Account = password_Account;
    }

    public String getPhone_Account() {
        return Phone_Account;
    }

    public void setPhone_Account(String phone_Account) {
        Phone_Account = phone_Account;
    }

    public String getFull_Name_Personal() {
        return Full_Name_Personal;
    }

    public void setFull_Name_Personal(String full_Name_Personal) {
        Full_Name_Personal = full_Name_Personal;
    }

    public String getDate_of_Birth_Personal() {
        return Date_of_Birth_Personal;
    }

    public void setDate_of_Birth_Personal(String date_of_Birth_Personal) {
        Date_of_Birth_Personal = date_of_Birth_Personal;
    }

    public String getGender_Personal() {
        return Gender_Personal;
    }

    public void setGender_Personal(String gender_Personal) {
        Gender_Personal = gender_Personal;
    }

    public String getCountry_Personal() {
        return Country_Personal;
    }

    public void setCountry_Personal(String country_Personal) {
        Country_Personal = country_Personal;
    }

    public String getWallet_Finish() {
        return Wallet_Finish;
    }

    public void setWallet_Finish(String wallet_Finish) {
        Wallet_Finish = wallet_Finish;
    }

    public String getAdress_Finish() {
        return Adress_Finish;
    }

    public void setAdress_Finish(String adress_Finish) {
        Adress_Finish = adress_Finish;
    }

    public String getImage_Portrait() {
        return Image_Portrait;
    }

    public void setImage_Portrait(String image_Portrait) {
        Image_Portrait = image_Portrait;
    }

    public String getImage_Pasport() {
        return Image_Pasport;
    }

    public void setImage_Pasport(String image_Pasport) {
        Image_Pasport = image_Pasport;
    }


    public String getImage_Portrait_Choose() {
        return Image_Portrait_Choose;
    }

    public void setImage_Portrait_Choose(String image_Portrait_Choose) {
        Image_Portrait_Choose = image_Portrait_Choose;
    }

    public String getImage_Passport_Choose() {
        return Image_Passport_Choose;
    }

    public void setImage_Passport_Choose(String image_Passport_Choose) {
        Image_Passport_Choose = image_Passport_Choose;
    }

    public List<Ticket> getmList_Ticket() {
        return mList_Ticket;
    }

    public void setmList_Ticket(List<Ticket> mList_Ticket) {
        this.mList_Ticket = mList_Ticket;
    }

    public MySingleTon() {
        this.map = new HashMap<>();
        this.mHashMap = new HashMap<>();
        this.mList_View_Number = new ArrayList<>();
        this.mList_Ticket = new ArrayList<>();
    }

    public static MySingleTon getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MySingleTon();
        }
        return INSTANCE;
    }
}
