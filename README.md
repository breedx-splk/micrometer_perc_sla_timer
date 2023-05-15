# micrometer_perc_sla_timer
Explore how a time with percentiles and sla works today

# Status today

This test was performed using `splunk-otel-java` version 1.24.0 and `micrometer` 1.11.0. 
A single micrometer `Timer` is updated every 1 second with one of 10 random values. The `Timer` 
is configured for P50 and P95 percentiles and is configured to publish the percentile histogram.

The application emits `signalfx` formatted metric data from the ` to the collector.

<img width="161" alt="image" src="https://github.com/breedx-splk/micrometer_perc_sla_timer/assets/75337021/4db61680-5542-4e44-b128-3c630ffe978a">
