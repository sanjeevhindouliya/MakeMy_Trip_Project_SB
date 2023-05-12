package com.sanjeev.service;

import com.sanjeev.request.Passenger;
import com.sanjeev.response.Ticket;

public interface MakeMyTripService {

	public Ticket bookTicket(Passenger passenger);
	
	public Ticket getTicketByNum(Integer ticketNumber);
}
