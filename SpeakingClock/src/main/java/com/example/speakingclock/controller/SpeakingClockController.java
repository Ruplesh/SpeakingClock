package com.example.speakingclock.controller;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpeakingClockController {

	@GetMapping("/getCurrentTimeInWords")
	public ResponseEntity<String> getCurrentTimeInWords() {

		String timeInwords = "";
		int hour = LocalDateTime.now().getHour();
		int minutes = LocalDateTime.now().getMinute();
		if (hour == 0 && minutes == 0) {
			timeInwords = "It's Midnight";
		} else if (hour == 12 && minutes == 0) {
			timeInwords = "It's Midday";
		} else {
			timeInwords = timeInwords + " " + getTimeInWords(hour, minutes);
		}

		return new ResponseEntity<String>(timeInwords, HttpStatus.OK);
	}

	@GetMapping("/getTimeInWords/{time}")
	public ResponseEntity<String> getCurrentTimeInWords(@PathVariable String time) {
		String[] timeparts = time.split(":");
		String timeInWords = "";
		int hour = Integer.parseInt(timeparts[0]);
		int minutes = Integer.parseInt(timeparts[1]);
		if (hour < 0 || hour > 23 || minutes < 0 || minutes > 59) {
			timeInWords = "Invalid date time";
		} else {
			if (hour == 0 && minutes == 0) {
				timeInWords = "It's Midnight";
			} else if (hour == 12 && minutes == 0) {
				timeInWords = "It's Midday";
			} else {
				timeInWords = timeInWords + " " + getTimeInWords(hour, minutes);
			}
		}

		return new ResponseEntity<String>(timeInWords, HttpStatus.OK);
	}

	private String getTimeInWords(int hour, int minutes) {
		String timeInWords = "";
		String[] wordsDictionary = new String[] { "zero", "one", "two", "three", "four", "five", "six", "seven",
				"eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen",
				"eighteen", "nineteen", "twenty", "twenty one", "twenty two", "twenty three", "thirty", "forty", "fifty" };
		if (hour >= 0 && hour < 24) {
			timeInWords = "It's " + wordsDictionary[hour];
		}
		if (minutes > 0 && minutes <= 20) {
			timeInWords = timeInWords + " " + wordsDictionary[minutes];
		} else if (minutes > 23 && minutes <= 60) {
			int index = 23;
			int decimalPart = minutes / 10;
			int unitPart = minutes % 10;
			timeInWords = timeInWords + " " + wordsDictionary[index + decimalPart - 2];
			if(unitPart>0) {
				timeInWords = timeInWords + " " + wordsDictionary[unitPart];
			}
		}
		return timeInWords;

	}
}
