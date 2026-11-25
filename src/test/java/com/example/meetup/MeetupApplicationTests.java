package com.example.meetup;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.modulith.core.ApplicationModules;

@SpringBootTest
class MeetupApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test 
	void verifiesModuleStructure() {	
		ApplicationModules.of(MeetupApplication.class).verify();
	}
}
