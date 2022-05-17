package com.example.speakingclock;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.speakingclock.controller.SpeakingClockController;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpeakingClockController.class)
public class SpeakingClockControllerTest {

	@InjectMocks
	SpeakingClockController speakingClockController;
	
	@Test
	public void checkMidNight() {
		ResponseEntity<String> result = speakingClockController.getCurrentTimeInWords("00:00");
		assertEquals(result.getBody(),"It's Midnight");
		assertEquals(HttpStatus.OK, result.getStatusCode());
	}
	
	@Test
	public void checkMidday() {
		ResponseEntity<String> result = speakingClockController.getCurrentTimeInWords("12:00");
		assertEquals(result.getBody(),"It's Midday");
		assertEquals(HttpStatus.OK, result.getStatusCode());
	}
	
	@Test
	public void checkCustsomTime() {
		ResponseEntity<String> result = speakingClockController.getCurrentTimeInWords("10:40");
		assertEquals(result.getBody()," It's ten forty");
	}
	
	@Test
	public void checkInvalidCustsomTime() {
		ResponseEntity<String> result = speakingClockController.getCurrentTimeInWords("10:80");
		assertEquals(result.getBody(),"Invalid date time");
	}
	
	
}
