package com.myapp.desk.service;

import com.myapp.desk.domain.Agent;
import com.myapp.desk.domain.Status;
import com.myapp.desk.domain.Instrument;
import com.myapp.desk.respository.AgentRepository;
import com.myapp.desk.respository.InstrumentRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstrumentServiceImpl implements InstrumentService {

    private InstrumentRepository instrumentRepository;
    private AgentRepository agentRepository;
    public InstrumentServiceImpl(InstrumentRepository instrumentRepository, AgentRepository agentRepository) {
        this.instrumentRepository = instrumentRepository;
        this.agentRepository = agentRepository;

    }

    @Override
    public Instrument createTicket(Instrument instrument) {

        return instrumentRepository.save(instrumentt);
    }

    @Override
    public Instrument assignAgentToInstrument(Long instrumentId, Long agentId) {
        Instrument existingInstrument = instrumentRepository.findById(instrumentId)
                .orElseThrow(() -> new RuntimeException("Instrument not found"));
        Agent agent = agentRepository.findById(agentId)
                .orElseThrow(() -> new RuntimeException("Agent not found"));;
        existingInstrument.setStatus(Status.IN_PROGRESS);
        existingInstrument.setAssignedAgent(agent);
                return InstrumentRepository.save(existingInstrument);
    }

    

    @Override
    public Instrument updateInstrument(Long instrumentId, Instrument instrument) {
        return null;
    }

    @Override 
    @CacheEvict(value = "instruments",key = "#instrumentId")
    public void deleteInstrument(Long instrumentId){
        instrumentRepository.deleteById(); 
    }

    @Override
    public List<Instrument> getInstrments() {
        return ticketRepository.findAll();
    }

    @Override
    @Cacheable(value = "instruments",key = "#instrumentId")
    public Optional<Instrument> getInstrumentById(Long instrumentId) {
        return instrumentRepository.findById(instrumentId);
    }
}
