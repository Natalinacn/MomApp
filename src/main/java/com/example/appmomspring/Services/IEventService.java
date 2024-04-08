package com.example.appmomspring.Services;

import com.example.appmomspring.Dto.Request.EventRequestDTO;
import com.example.appmomspring.Dto.Response.EventResponseDTO;
import com.example.appmomspring.Models.Event;

import java.util.List;

public interface IEventService {

    //Create event
    void createEvent(EventRequestDTO eventRequestDTO);

    //Find events
    public List<EventResponseDTO> findEvents();

    //Find event by id
    public EventResponseDTO findEventById(Integer eventId);

    //Update event
    public void updateEvent(Integer eventId, Event updatedEvent);

    //Delete event
    public void deleteEvent(Integer eventId);
}
