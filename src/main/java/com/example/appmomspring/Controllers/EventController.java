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

    private IEventService iEventService;

    public EventController(IEventService IEventService) {
        this.iEventService = IEventService;
    }

    @PostMapping("/enviar")
    public ResponseEntity<EventRequestDTO> createEvent(@RequestBody EventRequestDTO eventRequestDTO) {
        if (eventRequestDTO.getTitle() != null || eventRequestDTO.getCostPerPerson() >= 0 || eventRequestDTO.getCostPerPerson() != null) {
            iEventService.createEvent(eventRequestDTO);
            return new ResponseEntity<>(eventRequestDTO, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/todos")
    public ResponseEntity<List<EventResponseDTO>> getAllEvents() {
        List<EventResponseDTO> eventList = iEventService.findEvents();
        if (eventList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(eventList, HttpStatus.OK);
        }
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<EventResponseDTO> getEventById(@PathVariable Integer eventId){
        EventResponseDTO event = iEventService.findEventById(eventId);

        if(event != null) {
            return new ResponseEntity<>(event, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }


}
