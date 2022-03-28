package be.pxl.superhero.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
public class GreetingController {

	private final List<String> messages = new ArrayList<>();
	private static final Random RANDOM = new Random();

	@PostConstruct
	public void init() {
		messages.add("Peek-a-boo!");
		messages.add("Howdy-doody!");
		messages.add("My name's Ralph, and I'm a bad guy.");
		messages.add("I come in peace!");
		messages.add("Put that cookie down!");

	}

	@GetMapping("/hello")
	public String doGreeting() {
		return messages.get(RANDOM.nextInt(messages.size()));
	}

}
