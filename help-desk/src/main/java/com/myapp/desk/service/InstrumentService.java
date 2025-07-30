package com.myapp.desk.service;

import com.myapp.desk.domain.Agent;
import com.myapp.desk.domain.Instrument;

import java.util.List;
import java.util.Optional;

public interface InstrumentService {

    Ticket createInstrument(Instrument instrument);
    Ticket assignAgentToInstrument(Long instrumentId, Long agentId);
    Ticket updateInstrument(Long instrumentId, Instrument instrument);
    Ticket deleteInstrument(Long instrumentId);
    List<Instrument> getInstruments();
    Optional<Instrument> getInstrumentById(Long instrumentId);
}
