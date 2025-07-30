package com.myapp.desk.service;

import com.myapp.desk.domain.Agent;
import com.myapp.desk.domain.Status;
import com.myapp.desk.domain.Trade;
import com.myapp.desk.respository.AgentRepository;
import com.myapp.desk.respository.TradeRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TradeServiceImpl implements TradeService {

    private TradeRepository tradeRepository;
    private AgentRepository agentRepository;
    public TradeServiceImpl(TradeRepository tradeRepository, AgentRepository agentRepository) {
        this.tradeRepository = tradeRepository;
        this.agentRepository = agentRepository;

    }

    @Override
    public Trade createTicket(Trade trade) {

        return tradeRepository.save(trade);
    }

    @Override
    public Trade assignAgentToTrade(Long tradeId, Long agentId) {
        Trade existingTrade = tradeRepository.findById(tradeId)
                .orElseThrow(() -> new RuntimeException("Trade not found"));
        Agent agent = agentRepository.findById(agentId)
                .orElseThrow(() -> new RuntimeException("Agent not found"));;
        existingTrade.setStatus(Status.IN_PROGRESS);
        existingTrade.setAssignedAgent(agent);
                return TradeRepository.save(existingTrade);
    }

    

    @Override
    public Trade updateTrade(Long tradeId, Trade trade) {
        return null;
    }

    @Override 
    @CacheEvict(value = "trades",key = "#tradeId")
    public void deleteTrade(Long tradeId){
        tradeRepository.deleteById(); 
    }

    @Override
    public List<Trade> getInstrments() {
        return ticketRepository.findAll();
    }

    @Override
    @Cacheable(value = "trades",key = "#tradeId")
    public Optional<Trade> getTradeById(Long tradeId) {
        return tradeRepository.findById(tradeId);
    }
}
