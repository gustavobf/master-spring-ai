package com.eazybytes.mcpserverremote.service;

import com.eazybytes.mcpserverremote.entity.*;
import com.eazybytes.mcpserverremote.model.*;
import com.eazybytes.mcpserverremote.repository.*;
import lombok.*;
import org.springframework.stereotype.*;

import java.time.*;
import java.util.*;

@Service
@RequiredArgsConstructor
public class HelpDeskTicketService {

    private final HelpDeskTicketRepository helpDeskTicketRepository;

    public HelpDeskTicket createTicket (TicketRequest ticketInput) {
        HelpDeskTicket ticket = HelpDeskTicket.builder().issue(ticketInput.issue()).username(ticketInput.username())
                .status("OPEN").createdAt(LocalDateTime.now()).eta(LocalDateTime.now().plusDays(7)).build();
        return helpDeskTicketRepository.save(ticket);
    }

    public List<HelpDeskTicket> getTicketsByUsername (String username) {
        return helpDeskTicketRepository.findByUsername(username);
    }

}