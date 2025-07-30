package com.myapp.desk.resource;

import com.myapp.desk.domain.Ticket;


import com.myapp.desk.service.TicketService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tickets")
public class TicketResource {

    private TicketService ticketService;

    public TicketResource(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    @RolesAllowed({"ADMIN", "AGENT"})
    public ResponseEntity<Ticket> createTicket(@RequestBody Ticket ticket) {
        ticket = ticketService.createTicket(ticket);
        return new ResponseEntity<>(ticket, HttpStatus.OK);
    }

    @GetMapping
    @RolesAllowed("ADMIN")
    public ResponseEntity<List<Ticket>> getAllTickets() {
        return ResponseEntity.ok(ticketService.getTickets());
    }
    @PutMapping("/{id}/agent/{agentId}")
    @RolesAllowed("ADMIN")
    public ResponseEntity<Ticket> assignAgent(@PathVariable("id") Long ticketId,
                                          @PathVariable("agentId")    Long agentId) {
       Ticket updated = ticketService.assignAgentToTicket(ticketId, agentId);
       return ResponseEntity.ok(updated);
    }
    @GetMapping("/{id}")
    public Ticket getTicketById(@PathVariable Long id) {
        return ticketService.getTicketById(id).get();
    }
}
