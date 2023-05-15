package com.splunk.example;

import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.Timer;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MicrometerTimerApp {

    public static void main(String[] args) throws Exception {
        System.out.println("App starting");

        Timer timer = Timer.builder("my.timer")
                .publishPercentiles(0.5, 0.95) // median and 95th percentile (1)
                .publishPercentileHistogram() // (2)
                .serviceLevelObjectives(Duration.ofMillis(100)) // (3)
                .minimumExpectedValue(Duration.ofMillis(1)) // (4)
                .maximumExpectedValue(Duration.ofSeconds(10))
                .register(Metrics.globalRegistry);

        while(true){
            Duration duration = doOperation();
            System.out.println("tick: " + duration);
            timer.record(duration);
            TimeUnit.SECONDS.sleep(1);
        }
    }

    private static Duration doOperation() {
        Random rand = new Random();
        List<Integer> values = List.of(10, 50, 75, 100, 1000, 1500, 2000, 5000, 7500, 10000);
        return Duration.ofMillis(values.get(rand.nextInt(values.size())));
    }
}
