package com.example.meetup.events.internal;

import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.time.LocalDateTime;

import com.example.meetup.events.Meetup;
import com.example.meetup.events.Rsvp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController 
@RequestMapping("/meetups")
@RequiredArgsConstructor 
public class MeetupController {
    
    private final MeetupService meetupService;

    public record CreateMeetupRequest(String title, LocalDateTime date) {
    }

    public record RsvpRequest(String name, String email) {
    }

    @PostMapping
    public ResponseEntity<Meetup> create(@RequestBody CreateMeetupRequest request) {
        Meetup created = meetupService.create(request.title(), request.date());
        return ResponseEntity.created(URI.create("/meetups/" + created.getId())).body(created);
    }
    
    @PostMapping("/{id}/rsvp")
    public ResponseEntity<Rsvp> rsvp(@PathVariable Long id, @RequestBody RsvpRequest request) {
        Rsvp saved = meetupService.rsvp(id, request.name(), request.email());
        return ResponseEntity.ok(saved);
    }
    
}
