package com.example.meetup.events.internal;

import org.springframework.data.repository.ListCrudRepository;
import com.example.meetup.events.Meetup;

public interface MeetupRepository extends ListCrudRepository<Meetup, Long> {

}