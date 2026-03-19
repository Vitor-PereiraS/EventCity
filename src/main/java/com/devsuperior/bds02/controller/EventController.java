package com.devsuperior.bds02.controller;

import com.devsuperior.bds02.dto.EventDTO;
import com.devsuperior.bds02.entities.Event;
import com.devsuperior.bds02.service.EventService;
import com.devsuperior.bds02.service.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/events")
public class EventController {

    @Autowired
    private EventService service;

    @GetMapping
    public ResponseEntity<List<EventDTO>> findAll() {
        List<EventDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<EventDTO> insert(@RequestBody EventDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<EventDTO> update(@PathVariable Long id,@RequestBody EventDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }
}
