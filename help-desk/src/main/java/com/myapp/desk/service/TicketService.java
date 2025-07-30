package com.myapp.desk.service;

import com.myapp.desk.domain.Agent;
import com.myapp.desk.domain.Ticket;

import java.util.List;

public interface TicketService {

    Ticket createTicket(Ticket ticket);
    Ticket assignAgentToTicket(Long ticketId, Long agentId);
    Ticket resolveTicket(Long ticketId);
    Ticket closeTicket(Long ticketId);
    Ticket updateTicket(Long ticketId, Ticket ticket);
    List<Ticket> getTickets();
}
