package com.example.wschat.utils;

import com.example.wschat.log.Log;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Timer;
import java.util.TimerTask;

@Component
@Profile("test-app")
public class TimeLoop {

    @Autowired
    private StringGenerator stringGenerator;

    private final Log logger = new Log(TimeLoop.class);

    @PostConstruct
    void init() {
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                String simple = stringGenerator.randomString();
                String cached = stringGenerator.randomCachedString();

                logger.info(String.format("Simple String: %s Cached String: %s", simple, cached));
            }
        }, 2000L, 2000L);
    }
}
