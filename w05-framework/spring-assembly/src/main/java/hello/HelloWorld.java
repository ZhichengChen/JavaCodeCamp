package hello;

import hello.bean.Greeter;
import hello.bean.Greeter2;
import org.joda.time.LocalTime;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class HelloWorld {

    public static void main(String[] args) {
        LocalTime currentTime = new LocalTime();
        System.out.println("The current local time is: " + currentTime);
        Greeter greeter = new Greeter();
        System.out.println(greeter.sayHello());

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-context.xml");
        Greeter greeter1 = applicationContext.getBean("greeter", Greeter.class);
        System.out.println(greeter1.sayHello());

        Greeter2 greeter2 = applicationContext.getBean("greeter2", Greeter2.class);
        System.out.println(greeter2.sayHello());
    }
}
