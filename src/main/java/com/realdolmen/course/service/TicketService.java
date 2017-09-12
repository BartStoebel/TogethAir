package com.realdolmen.course.service;

import com.realdolmen.course.domain.Ticket;
import com.realdolmen.course.repository.TicketRepository;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.List;

/**
 * Service that handles logic and repo requests for tickets
 * @author JBCBF07
 */

@Stateless @LocalBean
public class TicketService {

    @EJB
    private TicketRepository ticketRepository;


    public void saveTickets(List<Ticket> tickets){
        ticketRepository.save(tickets);
    }


}
