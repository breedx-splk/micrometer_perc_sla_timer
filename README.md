# micrometer_perc_sla_timer
Explore how a time with percentiles and sla works today

# Status today

This test was performed using `splunk-otel-java` version 1.24.0 and `micrometer` 1.11.0. 
A single micrometer `Timer` is updated every 1 second with one of 10 random values. The `Timer` 
is configured for P50 and P95 percentiles and is configured to publish the percentile histogram.

The application emits `signalfx` formatted metric data from the `SignalFxMeterRegistry` to the collector.
The collector is configured to forward metrics to Splunk O11y ingest in OTLP/HTTP. It is possibly that a 
slightly different configuration could yield slightly different results.

The data in the graphs below was spot-checked by hand agianst verbose collector logs.

From the collector logs, we see the following metrics related to the timer:

* `my.timer.percentile` - gauge
* `my.timer.histogram` - gauge
* `my.timer.max` - gauge
* `my.timer.avg` - gauge
* `my.timer.totalTime` - delta sum, monotonic
* `my.timer.count` - delta sum, monotonic

In the Splunk O11y app, these show up in Metric Finder and can be added to the dashboard like this:

<img width="1085" alt="image" src="https://github.com/breedx-splk/micrometer_perc_sla_timer/assets/75337021/305c4452-e984-4fda-80ee-17fcf9861c35">


## count:

<img width="844" alt="image" src="https://github.com/breedx-splk/micrometer_perc_sla_timer/assets/75337021/1f65ba6f-ffa9-42b0-8744-d7df4982a57d">


## totalTime:

<img width="834" alt="image" src="https://github.com/breedx-splk/micrometer_perc_sla_timer/assets/75337021/2b99b58a-2b3d-419e-9e87-2b1fea44fbcd">

## max:

<img width="836" alt="image" src="https://github.com/breedx-splk/micrometer_perc_sla_timer/assets/75337021/33a7633d-3c92-404b-b3b5-f30639b6a19c">

## percentile:

<img width="843" alt="image" src="https://github.com/breedx-splk/micrometer_perc_sla_timer/assets/75337021/fb6beb3a-7412-443e-aeb1-1fd6374d5b3a">

## avg:

<img width="836" alt="image" src="https://github.com/breedx-splk/micrometer_perc_sla_timer/assets/75337021/73c2e883-be45-4230-82e3-88a024f48dd1">

## histogram:

* Note: type is gauge, not actually histogram
* Note: the two timeseries correspond to the min/max SLOs configured into the timer.

<img width="1092" alt="image" src="https://github.com/breedx-splk/micrometer_perc_sla_timer/assets/75337021/c5adabc3-8d35-4078-b1c1-ce11e8a400c4">
