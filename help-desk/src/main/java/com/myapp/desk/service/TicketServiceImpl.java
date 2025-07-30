package com.myapp.desk.service;

import com.myapp.desk.domain.Agent;
import com.myapp.desk.domain.Status;
import com.myapp.desk.domain.Ticket;
import com.myapp.desk.respository.AgentRepository;
import com.myapp.desk.respository.TicketRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService {

    private TicketRepository ticketRepository;
    private AgentRepository agentRepository;
    public TicketServiceImpl(TicketRepository ticketRepository, AgentRepository agentRepository) {
        this.ticketRepository = ticketRepository;
        this.agentRepository = agentRepository;

    }

    @Override
    public Ticket createTicket(Ticket ticket) {

        return ticketRepository.save(ticket);
    }

    @Override
    public Ticket assignAgentToTicket(Long ticketId, Long agentId) {
        Ticket existingTicket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));
        Agent agent = agentRepository.findById(agentId)
                .orElseThrow(() -> new RuntimeException("Agent not found"));;
        existingTicket.setStatus(Status.IN_PROGRESS);
        existingTicket.setAssignedAgent(agent);
                return ticketRepository.save(existingTicket);
    }

    @Override
    public Ticket resolveTicket(Long ticketId) {
        return null;
    }

    @Override
    @CacheEvict(value = "tickets",key = "#ticketId")
    public Ticket closeTicket(Long ticketId) {
        return null;
    }

    @Override
    public Ticket updateTicket(Long ticketId, Ticket ticket) {
        return null;
    }

    @Override
    public List<Ticket> getTickets() {
        return ticketRepository.findAll();
    }

    @Override
    @Cacheable(value = "tickets",key = "#ticketId")
    public Optional<Ticket> getTicketById(Long ticketId) {
        return ticketRepository.findById(ticketId);
    }
}
