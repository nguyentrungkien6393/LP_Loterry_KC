package com.kien.lp.myapplication.adapter.buyticket;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Ticket {
    private int id;
    private int number;
    public boolean selted;
    public int curent_select_number;
    public int curent_select_number_special;
    public int number_animation;

    private List<CellTicket> cell69;
    private List<CellTicket> cell26;
    private int[] choice69 = new int[]{-1, -1, -1, -1, -1};
    private int[] choice26 = new int[]{-1};

    public List<String> getmListString_Number() {
        return mListString_Number;
    }

    public void setmListString_Number(List<String> mListString_Number) {
        this.mListString_Number = mListString_Number;
    }

    public List<String> mListString_Number = new ArrayList<>();
    public List<String> mListString_Number_Special = new ArrayList<>();

    public List<String> getmListString_Number_Special() {
        return mListString_Number_Special;
    }

    public void setmListString_Number_Special(List<String> mListString_Number_Special) {
        this.mListString_Number_Special = mListString_Number_Special;
    }

    public int[] getChoice26() {
        return choice26;
    }

    public Ticket(int number, boolean selted) {
        this.number = number;
        this.selted = selted;
        cell69 = new ArrayList<>();
        cell26 = new ArrayList<>();
        for (int i = 0; i < 69; i++) {
            cell69.add(new CellTicket(i));
        }
        for (int i = 0; i < 26; i++) {
            cell26.add(new CellTicket(i));
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isSelted() {
        return selted;
    }

    public void setSelted(boolean selted) {
        this.selted = selted;
    }

    public List<CellTicket> getCell69() {
        return cell69;
    }

    public void setCell69(List<CellTicket> cell69) {
        this.cell69 = cell69;
    }

    public List<CellTicket> getCell26() {
        return cell26;
    }

    public void setCell26(List<CellTicket> cell26) {
        this.cell26 = cell26;
    }

    public int checkFull69(Context context) {
        for (int i = 0; i < choice69.length; i++) {
            if (choice69[i] == -1) {
                System.out.println("DATA return check69      " + i);
                return i;
            }
        }
        System.out.println("DATA return FUlll");
        return -1;
    }


    public int checkFull26(Context context) {
        for (int i = 0; i < choice26.length; i++) {
            if (choice26[i] == -1) {
                return i;

            }
        }
        return -1;
    }

    public int checkNumberIsSeleted69(int number) {
        for (int i = 0; i < choice69.length; i++) {
            if (choice69[i] == number) {
                choice69[i] = -1;
                return i;
            }
        }
        return -1;
    }

    public int checkNumberIsSeleted26(int number) {
        for (int i = 0; i < choice26.length; i++) {
            if (choice26[i] == number) {
                choice26[i] = -1;
                return i;
            }
        }
        return -1;
    }

    public void addNumber69(int id, int number) {
        choice69[id] = number;
    }
    public void addNumber26(int id, int number) {
        choice26[id] = number;
    }
}
