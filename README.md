# meetup
Study for Spring modulith and functional architecture

## Tóm tắt nội dung học được
- Cấu trúc dự án rõ ràng theo tính năng(functional architecture), các thư mục tính năng có các tiệp public có thể truy cập được và các thư mục không thể truy cập từ bên ngoài(internal):

```bash
├── MeetupApplication.java
├── events                                      // tính năng 1
│   ├── Meetup.java
│   ├── Rsvp.java
│   ├── RsvpReceived.java
│   └── internal                                // thư mục không thể được import từ bên ngoài
│       ├── MeetupController.java
│       ├── MeetupRepository.java
│       ├── MeetupService.java
│       └── RsvpRepository.java
└── notifications                               // tính năng 2
    └── internal
        ├── Confirmation.java
        ├── ConfirmationRepository.java
        └── NotificationListener.java
```

- Các module liên lạc với nhau qua ApplicationEventPublisher và ApplicationEventListenr, dữ liệu gửi qua 1 class DTO có thể truy cập được từ bên ngoài(bên ngoài internal folder).

```java
public Rsvp rsvp(Long meetupId, String name, String email) {
    Rsvp saved = rsvpRepository.save(Rsvp.of(meetupId, name, email));
    log.info("RSVP {}", saved);

    eventPublisher.publishEvent(new RsvpReceived(meetupId, name, email));

    return saved;
}
```
và 
```java
@ApplicationModuleListener
void on(RsvpReceived event) {
    if(fail) {
        throw new RuntimeException("Failing on purpose");
    }

    Confirmation saved = repository.save(Confirmation.of(event.meetupId(), event.email()));
    log.info("Saved confirmation {}", saved);
}
```

- có trường hợp và cài đặt cho lỗi liên quan đến miss events

```properties
spring.application.name=meetup

spring.docker.compose.skip.in-tests=false
spring.sql.init.mode=always

notifications.fail=false

spring.jpa.hibernate.ddl-auto=update
spring.jpa.open-in-view=false

spring.modulith.events.republish-outstanding-events-on-restart=true
```

- test modulith
```java
@SpringBootTest
class MeetupApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test   // test func
	void verifiesModuleStructure() {	
		ApplicationModules.of(MeetupApplication.class).verify();
	}
}
```