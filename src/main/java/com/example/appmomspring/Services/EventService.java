package com.example.appmomspring.Services;

import com.example.appmomspring.Models.Event;

import java.util.List;

public interface EventService {

    //Create event
    public void createEvent(Event event);
    //Find events
    public List<Event> findEvents();

    //Find event by id
    public Event findEventById(Integer eventId);

    //Update event
    public void updateEvent(Integer eventId, Event updatedEvent);

    //Delete event
    public void deleteEvent(Integer eventId);
}
