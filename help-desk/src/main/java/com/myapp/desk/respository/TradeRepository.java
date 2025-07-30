package com.myapp.desk.respository;

import com.myapp.desk.domain.Trade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TradeRepository  extends JpaRepository<Trade, Long> {
    // select * from tickets where closedDate =
    // Spring Boot Convention
    //List<Ticket> findBy(String userId);
    // Convention Over Configuration
    List<Trade> findByClosedDate(LocalDateTime closedDate);
}