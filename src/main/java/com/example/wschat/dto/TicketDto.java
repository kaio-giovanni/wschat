package com.example.wschat.dto;

public class TicketDto {

    private String ticket;

    public TicketDto() {
    }

    public TicketDto(String ticket) {
        this.ticket = ticket;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }
}
