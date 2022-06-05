package io.kimmking;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
@ComponentScan("io.kimmking.spring")
@EnableAutoConfiguration
public class DemoAutoConfiguration implements EnvironmentAware {
    @Override
    public void setEnvironment(Environment environment) {

    }
}
