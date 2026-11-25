package com.example.meetup.events;

public record RsvpReceived(Long meetupId, String name, String email) {

}
