package com.example.meetup.notifications.internal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity 
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Confirmation {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long meetupId;
    private String email;

    public Confirmation(Long meetupId, String email) {
        this.meetupId = meetupId;
        this.email = email;
    }

    public static Confirmation of(Long meetupId, String email) {
        return new Confirmation(meetupId, email);
    }
}
