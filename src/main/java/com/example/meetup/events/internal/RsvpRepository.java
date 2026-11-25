package com.example.meetup.events.internal;

import com.example.meetup.events.Rsvp;
import org.springframework.data.repository.ListCrudRepository;

public interface RsvpRepository extends ListCrudRepository<Rsvp, Long> {

}
