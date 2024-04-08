package com.example.appmomspring.Services.IMPL;

import com.example.appmomspring.Dto.Request.EventRequestDTO;
import com.example.appmomspring.Dto.Response.EventResponseDTO;
import com.example.appmomspring.Exceptions.CreationFailedException;
import com.example.appmomspring.Exceptions.NotFoundException;
import com.example.appmomspring.Models.Event;
import com.example.appmomspring.Repositories.EventRepository;
import com.example.appmomspring.Services.IEventService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements IEventService {

    private EventRepository eventRepository;
    private ModelMapper modelMapper;

    public EventServiceImpl(EventRepository eventRepository, ModelMapper modelMapper) {
        this.eventRepository = eventRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createEvent(EventRequestDTO eventRequestDTO) {

        Event event = modelMapper.map(eventRequestDTO, Event.class); // Convertir EventRequestDTO a Event

        if (event.getTitle() != null && event.getCostPerPerson() != null && event.getCostPerPerson() > 0) {
            event.setStatus(true);
            event.setCreatedAt(LocalDate.now());
            eventRepository.save(event);
        } else {
            throw new CreationFailedException(Event.class);
        }
    }

    @Override
    public List<EventResponseDTO> findEvents() {
        List<Event> eventList = eventRepository.findAll();
        if (eventList.isEmpty()) {
            throw new NotFoundException("No existen eventos cargados");
        } else {
            return eventList.stream()
                    .map(event -> modelMapper.map(event, EventResponseDTO.class))//Mapeo cada evento de la lista a un responseDTO
                    .collect(Collectors.toList()); //Guardo cada elemento en una lista nueva
        }
    }

    @Override
    public EventResponseDTO findEventById(Integer eventId) {
        if (eventId != null && eventId > 0) {
            Event foundEvent = eventRepository.findById(eventId).orElseThrow(() -> new NotFoundException(eventId)); //Obtiene el valor encapsulado o lanza la excepción

            // Mapear el objeto Event a un EventResponseDTO usando ModelMapper
            EventResponseDTO eventResponseDTO = modelMapper.map(foundEvent, EventResponseDTO.class);
            return eventResponseDTO;
        } else {
            throw new IllegalArgumentException("El ID del evento proporcionado no es válido: " + eventId);
        }
    }

    @Override
    public void updateEvent(Integer eventId, Event updatedEvent) {

        if (eventId != null && eventId > 0) {
            Event foundEvent = eventRepository.findById(eventId).orElseThrow(() -> new NotFoundException(eventId));
            foundEvent.setTitle(updatedEvent.getTitle());
            foundEvent.setDescription(updatedEvent.getDescription());
            foundEvent.setCostPerPerson(updatedEvent.getCostPerPerson());
            foundEvent.setEventEndDate(updatedEvent.getEventEndDate());
            foundEvent.setUpdatedAt(LocalDate.now());
            eventRepository.save(foundEvent);
        } else {
            throw new IllegalArgumentException("El ID del evento proporcionado no es válido: " + eventId);
        }
    }

    //Borrado lógico
    @Override
    public void deleteEvent(Integer eventId) {
        if (eventId != null && eventId > 0) {
            Event foundEvent = eventRepository.findById(eventId).orElseThrow(() -> new NotFoundException(eventId));
            foundEvent.setStatus(false);
            foundEvent.setDeletedAt(LocalDate.now());
            eventRepository.save(foundEvent);
        } else {
            throw new IllegalArgumentException("El ID del evento proporcionado no es válido: " + eventId);
        }
    }
}
