package com.example.appmomspring.Services.IMPL;

import com.example.appmomspring.Exceptions.CreationFailedException;
import com.example.appmomspring.Models.Event;
import com.example.appmomspring.Repositories.EventRepository;
import com.example.appmomspring.Services.EventService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    private EventRepository eventRepository;
    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public void createEvent(Event event) {

        //Si me llega el evento con titulo y costo, seteo los atributos y los guardo

        if(event.getTitle() != null && event.getCostPerPerson() != null && event.getCostPerPerson() > 0){
            Event newEvent = new Event();
            newEvent.setTitle(event.getTitle());
            newEvent.setDescription(event.getDescription());
            newEvent.setCostPerPerson(event.getCostPerPerson());
            newEvent.setEventEndDate(event.getEventEndDate());
            newEvent.setStatus(true);

            eventRepository.save(event);
        }else{
            throw new CreationFailedException(Event.class);
        }



    }

    @Override
    public List<Event> findEvents() {
        return null;
    }

    @Override
    public Event findEventById(Integer eventId) {
        return null;
    }

    @Override
    public void updateEvent(Integer eventId, Event updatedEvent) {

    }

    @Override
    public void deleteEvent(Integer eventId) {

    }
}
