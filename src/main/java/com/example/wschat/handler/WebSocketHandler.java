package com.example.wschat.handler;

import com.example.wschat.log.Log;
import com.example.wschat.services.TicketService;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class WebSocketHandler extends TextWebSocketHandler {

    private final Log logger = new Log(WebSocketHandler.class);

    private final TicketService ticketService;

    private final Map<String, WebSocketSession> sessions;

    public WebSocketHandler(TicketService ticketService) {
        this.ticketService = ticketService;
        this.sessions = new ConcurrentHashMap<>();
    }


    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        logger.info("Session ID: " + session.getId());
        Optional<String> ticket = ticketOf(session);
        if (ticket.isEmpty() || ticket.get().isBlank()) {
            logger.warning("session " + session.getId() + " without ticket");
            closeSession(session, CloseStatus.POLICY_VIOLATION);
            return;
        }

        Optional<String> userId = ticketService.getUserIdByTicket(ticket.get());
        if (userId.isEmpty()) {
            logger.warning("session " + session.getId() + "with invalid ticket");
            closeSession(session, CloseStatus.POLICY_VIOLATION);
            return;
        }

        sessions.put(userId.get(), session);

        logger.info("session " + session.getId() + " was bind to user " + userId.get());
    }

    private Optional<String> ticketOf(WebSocketSession session) {
        return Optional
                .ofNullable(session.getUri())
                .map(UriComponentsBuilder::fromUri)
                .map(UriComponentsBuilder::build)
                .map(UriComponents::getQueryParams)
                .map(it -> it.get("ticket"))
                .flatMap(it -> it.stream().findFirst())
                .map(String::trim);
    }

    private void closeSession(WebSocketSession session, CloseStatus status) {
        try {
            session.close(status);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        logger.info("Text message: " + message.getPayload());
        super.handleTextMessage(session, message);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        logger.info("Connection closed");
        super.afterConnectionClosed(session, status);
    }
}