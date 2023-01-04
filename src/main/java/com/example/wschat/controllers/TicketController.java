package com.example.wschat.controllers;

import com.example.wschat.dto.TicketDto;
import com.example.wschat.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("v1/ticket")
@CrossOrigin
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping("/")
    public ResponseEntity<TicketDto> buildTicket(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization) {
        String token = Optional.ofNullable(authorization)
                .map(it -> it.replace("Bearer ", ""))
                .orElse("");
        String ticket = ticketService.buildAndSaveTicket(token);
        return new ResponseEntity<>(new TicketDto(ticket), HttpStatus.OK);
    }
}
