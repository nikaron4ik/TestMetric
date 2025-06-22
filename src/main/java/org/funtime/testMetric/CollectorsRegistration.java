package org.funtime.testMetric;

import lombok.Getter;
import org.bukkit.plugin.Plugin;
import org.funtime.testMetric.Event.Event;

import su.funtime.prometheusexporter.api.MetricCollector;

public class CollectorsRegistration {

    final Plugin plugin = TestMetric.getInstance();

    @Getter
    private final MetricCollector eventMetricCollector = new MetricCollector(plugin, "event_active", "something", true) {
        @Override
        public double collect() {
            return Event.isEventActive ? 1 : 0;
        }
    };


}
