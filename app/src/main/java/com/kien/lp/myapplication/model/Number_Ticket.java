package com.kien.lp.myapplication.model;

public class Number_Ticket {
    String ticket;

    public Number_Ticket(String ticket, boolean status_ticket) {
        this.ticket = ticket;
        this.status_ticket = status_ticket;
    }

    public String getTicket() {

        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public boolean isStatus_ticket() {
        return status_ticket;
    }

    public void setStatus_ticket(boolean status_ticket) {
        this.status_ticket = status_ticket;
    }

    boolean status_ticket;


}
