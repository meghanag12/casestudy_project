package com.myapp.desk.service;

import com.myapp.desk.domain.Agent;
import com.myapp.desk.domain.Trade;

import java.util.List;
import java.util.Optional;

public interface TradeService {

    Trade createTrade(Trade trade);
    Trade assignAgentToTrade(Long tradeId, Long agentId);
    Trade updateTrade(Long tradeId, Trade trade);
    Trade deleteTrade(Long tradeId);
    List<Trade> getTrades();
    Optional<Trade> getTradeById(Long tradeId);
}