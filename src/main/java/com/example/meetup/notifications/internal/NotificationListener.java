package com.example.meetup.notifications.internal;

import com.example.meetup.events.RsvpReceived;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Component;

@Component 
@RequiredArgsConstructor 
public class NotificationListener {

    private static final Logger log = LoggerFactory.getLogger(NotificationListener.class);
    private final ConfirmationRepository repository;

    @Value("${notifications.fail}")
    boolean fail;

    @ApplicationModuleListener
    void on(RsvpReceived event) {
        if(fail) {
            throw new RuntimeException("Failing on purpose");
        }

        Confirmation saved = repository.save(Confirmation.of(event.meetupId(), event.email()));
        log.info("Saved confirmation {}", saved);
    }
}
