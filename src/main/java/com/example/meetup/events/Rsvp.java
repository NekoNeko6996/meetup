package com.example.meetup.events;

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
public class Rsvp {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;
    
    private Long meetupId;
    private String name; 
    private String email;

    public Rsvp(Long meetupId, String name, String email) {
        this.meetupId = meetupId;
        this.name = name;
        this.email = email;
    }

    public static Rsvp of(Long meetupId, String name, String email) {
        return new Rsvp(meetupId, name, email);
    }
}
