package org.qa.light.session16;

import io.cucumber.plugin.EventListener;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.Status;
import io.cucumber.plugin.event.TestStepFinished;

import java.time.LocalDateTime;

public class CucumberHooks implements EventListener {

    @Override
    public void setEventPublisher(EventPublisher eventPublisher) {
        eventPublisher.registerHandlerFor(TestStepFinished.class,
                testStepFinished -> {
                    if (!testStepFinished.getResult().getStatus().is(Status.PASSED)){

                    }
                    System.out.println(LocalDateTime.now());
                    System.out.println(">>>>>>>>>>>TestStepStarted<<<<<<<<<<<<<<<<<");
                });
    }
}
