package com.example.meetup.events.internal;

import com.example.meetup.events.Meetup;
import com.example.meetup.events.Rsvp;
import com.example.meetup.events.RsvpReceived;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service 
@Transactional 
@RequiredArgsConstructor 
public class MeetupService {

    private static final Logger log = LoggerFactory.getLogger(MeetupService.class);
    private final MeetupRepository meetupRepository;
    private final RsvpRepository rsvpRepository;

    private final ApplicationEventPublisher eventPublisher;

    public Meetup create(String title, LocalDateTime date) {
        Meetup saved = meetupRepository.save(Meetup.of(title, date));
        log.info("Created meetup {}", saved);
        return saved;
    }

    public Rsvp rsvp(Long meetupId, String name, String email) {
        Rsvp saved = rsvpRepository.save(Rsvp.of(meetupId, name, email));
        log.info("RSVP {}", saved);

        eventPublisher.publishEvent(new RsvpReceived(meetupId, name, email));

        return saved;
    }
}
