package com.example.appmomspring.Controllers;

import com.example.appmomspring.Dto.Request.EventRequestDTO;
import com.example.appmomspring.Dto.Response.EventResponseDTO;
import com.example.appmomspring.Models.Event;
import com.example.appmomspring.Services.IEventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/evento")
public class EventController {

    private IEventService IEventService;

    public EventController(IEventService IEventService) {
        this.IEventService = IEventService;
    }

    @PostMapping("/enviar")
    public ResponseEntity<EventRequestDTO> createEvent(@RequestBody EventRequestDTO eventRequestDTO) {
        if (eventRequestDTO.getTitle() != null || eventRequestDTO.getCostPerPerson() >= 0 || eventRequestDTO.getCostPerPerson() != null) {
            IEventService.createEvent(eventRequestDTO);
            return new ResponseEntity<>(eventRequestDTO, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/todos")
    public ResponseEntity<List<EventResponseDTO>> getAllEvents() {
        List<EventResponseDTO> eventList = IEventService.findEvents();
        if (eventList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(eventList, HttpStatus.OK);
        }
    }
}
