package br.com.polaris.saferoutemessenger.controller;

import br.com.polaris.saferoutemessenger.configuration.SafeRouteTwilioProperties;
import br.com.polaris.saferoutemessenger.request.MessageRequest;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mensagens")
public class MessageController {

    @Autowired
    private SafeRouteTwilioProperties safeRouteTwilioProperties;

    @CrossOrigin
    @PostMapping("/enviar")
    public ResponseEntity enviarMensagem(@RequestBody MessageRequest request) {
        Twilio.init(safeRouteTwilioProperties.getAccount_sid(),safeRouteTwilioProperties.getAuth_token());

        for (String toNumber: request.getToNumbers()) {
            Message messageSms = Message.creator(
                    new com.twilio.type.PhoneNumber(toNumber),
                    new com.twilio.type.PhoneNumber(safeRouteTwilioProperties.getFrom_number_sms()),
                    String.format("Olá! Você é um dos contatos de confiança de %s " +
                                    "e ela pediu ajuda através da aplicação Safe Route! " +
                                    "Clique no link para ver a localização dela http://www.google.com/maps/place/%s,%s ",
                            request.getSenderName(), request.getLatitudeSender(), request.getLongitudeSender()))
                    .create();

            Message message = Message.creator(
                    new com.twilio.type.PhoneNumber("whatsapp:"+toNumber),
                    new com.twilio.type.PhoneNumber("whatsapp:"+safeRouteTwilioProperties.getFrom_number()),
                    String.format("Olá! Você é um dos contatos de confiança de %s " +
                                    "e ela pediu ajuda através da aplicação Safe Route! " +
                                    "Clique no link para ver a localização dela http://www.google.com/maps/place/%s,%s ",
                            request.getSenderName(), request.getLatitudeSender(), request.getLongitudeSender()))
                    .create();
        }

        return ResponseEntity.status(201).build();
    }

}
