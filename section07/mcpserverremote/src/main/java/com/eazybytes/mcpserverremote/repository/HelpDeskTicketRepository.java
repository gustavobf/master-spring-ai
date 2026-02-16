package com.eazybytes.mcpserverremote.repository;

import com.eazybytes.mcpserverremote.entity.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface HelpDeskTicketRepository extends JpaRepository<HelpDeskTicket, Long> {

    List<HelpDeskTicket> findByUsername (String username);

}