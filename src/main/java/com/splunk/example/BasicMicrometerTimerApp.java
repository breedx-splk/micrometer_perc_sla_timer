package com.splunk.example;

import io.micrometer.core.instrument.Clock;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import io.micrometer.signalfx.SignalFxConfig;
import io.micrometer.signalfx.SignalFxMeterRegistry;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class BasicMicrometerTimerApp {

    public static void main(String[] args) throws Exception {
        System.out.println("App starting");

        SignalFxConfig signalFxConfig = new SignalFxConfig() {
            public String accessToken() {
                return "ignored"; // we send to collector configured with the token
            }

            @Override
            public String uri() {
                return "http://localhost:9943";
            }

            @Override
            public boolean publishDeltaHistogram() {
                return true;
            }

            @Override
            public Duration step() {
                return Duration.ofSeconds(60);
            }

            @Override
            public String get(String k) {
                return null; // accept the rest of the defaults
            }
        };

        MeterRegistry registry = new SignalFxMeterRegistry(signalFxConfig, Clock.SYSTEM);

        Timer timer = Timer.builder("my.timer")
                .publishPercentiles(0.5, 0.95) // median and 95th percentile (1)
                .publishPercentileHistogram() // (2)
                .serviceLevelObjectives(Duration.ofMillis(999), Duration.ofMillis(1750)) // (3)
                .minimumExpectedValue(Duration.ofMillis(10)) // (4)
                .maximumExpectedValue(Duration.ofSeconds(10000))
                .register(registry);

        long i = 0;
        while(true){
            Duration duration = Duration.ofSeconds(1);
            System.out.println(i++ + "tick: " + duration);
            timer.record(duration);
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
