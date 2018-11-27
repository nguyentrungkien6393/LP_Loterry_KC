package com.kien.lp.myapplication.adapter.buyticket;

public class CellTicket {
    int number;
    boolean seleted;
    String type;
    String positon_ticket;

    public CellTicket(int number, String type, String positon_ticket) {
        this.number = number;
        this.type = type;
        this.positon_ticket = positon_ticket;
    }

    public CellTicket(int number, boolean seleted, String type, String positon_ticket) {
        this.number = number;
        this.seleted = seleted;
        this.type = type;
        this.positon_ticket = positon_ticket;
    }

    public CellTicket(int number) {
        this.number = number;

        this.seleted = false;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isSeleted() {
        return seleted;
    }

    public void setSeleted(boolean seleted) {
        this.seleted = seleted;
    }
}
