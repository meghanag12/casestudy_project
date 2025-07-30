package com.myapp.desk.respository;

import com.myapp.desk.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface InstrumentRepository  extends JpaRepository<Instrument, Long> {
    // select * from tickets where closedDate =
    // Spring Boot Convention
    //List<Ticket> findBy(String userId);
    // Convention Over Configuration
    List<Instrument> findByClosedDate(LocalDateTime closedDate);
}