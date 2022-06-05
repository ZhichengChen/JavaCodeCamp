package hello.bean;

import org.springframework.stereotype.Component;

@Component
public class Greeter2 {
	public String sayHello() {
		return "Hello world!";
	}
}
