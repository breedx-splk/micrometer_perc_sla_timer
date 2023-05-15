# micrometer_perc_sla_timer
Explore how a time with percentiles and sla works today

# Status today

This test was performed using `splunk-otel-java` version 1.24.0 and `micrometer` 1.11.0. 
A single micrometer `Timer` is updated every 1 second with one of 10 random values. The `Timer` 
is configured for P50 and P95 percentiles and is configured to publish the percentile histogram.

The application emits `signalfx` formatted metric data from the `SignalFxMeterRegistry` to the collector.
The collector is configured to forward metrics to Splunk O11y ingest in OTLP/HTTP.

From the collector logs, we see the following metrics related to the timer:

* `my.timer.percentile` - gauge
* `my.timer.max` - gauge
* `my.timer.avg` - gauge
* `my.timer.totalTime` - delta sum, monotonic
* `my.timer.count` - delta sum, monotonic

In the Splunk O11y app, these show up in Metric Finder like this:

<img width="161" alt="image" src="https://github.com/breedx-splk/micrometer_perc_sla_timer/assets/75337021/4db61680-5542-4e44-b128-3c630ffe978a">

## totalTime:

<img width="851" alt="image" src="https://github.com/breedx-splk/micrometer_perc_sla_timer/assets/75337021/6c55c450-2016-43ca-b51d-2344d8e20d19">
