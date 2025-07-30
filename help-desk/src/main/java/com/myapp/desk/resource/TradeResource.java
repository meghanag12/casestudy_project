package com.myapp.desk.resource;

import com.myapp.desk.domain.Trade; 
import com.myapp.desk.service.TradeService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("trade")
public class TradeResource {

    private TradeService tradeService;

    public TradeResource(TradeService tradeService) {
        this.tradeService = tradeService;
    }

    @PostMapping
    @RolesAllowed({"ADMIN", "AGENT"})
    public ResponseEntity<Trade> createTrade(@RequestBody Trade trade) {
        trade = tradeService.createTrade(trade);
        return new ResponseEntity<>(trade, HttpStatus.OK);
    }

    @GetMapping
    @RolesAllowed("ADMIN")
    public ResponseEntity<List<Trade>> getAllTrades() {
        return ResponseEntity.ok(tradeService.getTrades());
    }
    @PutMapping("/{id}/agent/{agentId}")
    @RolesAllowed("ADMIN")
    public ResponseEntity<Trade> assignAgent(@PathVariable("id") Long tradeId,
                                          @PathVariable("agentId")    Long agentId) {
       Ticket updated = tradeService.assignAgentToTicket(tradeId, agentId);
       return ResponseEntity.ok(updated);
    }
    @GetMapping("/{id}")
    public Trade getTradeById(@PathVariable Long id) {
        return tradeService.getTradeById(id).get();
    }

    @DeleteMapping("/{id}")
    @RolesAllowed("ADMIN")
    public ResponseEntity<Void> deleteTrade(@PathVariable("id") Long tradeId){
        instrumentService.deleteTradeById(tradeId);
    }
 
}
