package com.myapp.desk.domain;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name="tickets")
public class Ticket implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private LocalDateTime createdDate;
    private LocalDateTime closedDate;
    private String resolutionSummary;
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name="assignedAgentId")
    private Agent assignedAgent;

    public Ticket() {
    }

    public Ticket(String description, LocalDateTime createdDate, LocalDateTime closedDate, String resolutionSummary) {
        this.description = description;
        this.createdDate = createdDate;
        this.closedDate = closedDate;
        this.resolutionSummary = resolutionSummary;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getClosedDate() {
        return closedDate;
    }

    public void setClosedDate(LocalDateTime closedDate) {
        this.closedDate = closedDate;
    }

    public String getResolutionSummary() {
        return resolutionSummary;
    }

    public void setResolutionSummary(String resolutionSummary) {
        this.resolutionSummary = resolutionSummary;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Agent getAssignedAgent() {
        return assignedAgent;
    }

    public void setAssignedAgent(Agent assignedAgent) {
        this.assignedAgent = assignedAgent;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(id, ticket.id) && Objects.equals(description, ticket.description) && Objects.equals(createdDate, ticket.createdDate) && Objects.equals(closedDate, ticket.closedDate) && Objects.equals(resolutionSummary, ticket.resolutionSummary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, createdDate, closedDate, resolutionSummary);
    }
}
