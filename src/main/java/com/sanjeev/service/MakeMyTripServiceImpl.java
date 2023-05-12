package com.sanjeev.service;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.sanjeev.request.Passenger;
import com.sanjeev.response.Ticket;

@Service
public class MakeMyTripServiceImpl implements MakeMyTripService {

	private String BOOK_TICKET_URL="http://localhost:1122/ticket";
	
	private String GET_TICKET_URL="http://localhost:1122/ticket/{ticketNum}";

	@Override
	public Ticket bookTicket(Passenger passenger) {
		
		// get the instance of webclient (impl class)
		WebClient webClient = WebClient.create();
		
		//send POST request with passenger date
		// and map response to Ticket obj
		
		Ticket ticket = webClient.post()
		                .uri(BOOK_TICKET_URL)
		                .bodyValue(passenger)
		                .retrieve()
		                .bodyToMono(Ticket.class)
		                .block();
		
		/*
		RestTemplate rt = new RestTemplate(); 
		 ResponseEntity<Ticket> respEntity = rt.postForEntity(BOOK_TICKET_URL, passenger, Ticket.class);
		 
		 Ticket ticket = respEntity.getBody();
		*/
		return ticket;
	}

	@Override
	public Ticket getTicketByNum(Integer ticketNumber) {
		
		// get the instance of webclient (impl class)
		WebClient webClient = WebClient.create();
		
		// send get request and map response to Ticket obj
	Ticket ticket = 	webClient.get()
		                         .uri(GET_TICKET_URL, ticketNumber)
		                         .retrieve()
		                         .bodyToMono(Ticket.class)
		                         .block(); // sync call
		
		/*
		RestTemplate rt = new RestTemplate();
		ResponseEntity<Ticket> respEntity = rt.getForEntity(GET_TICKET_URL, Ticket.class, ticketNumber);
		
		Ticket ticket = respEntity.getBody();
		*/
		return ticket;
	}

}
