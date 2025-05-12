package org.funtime.testMetric;

import io.prometheus.client.Gauge;
import org.bukkit.plugin.java.JavaPlugin;
import org.funtime.testMetric.Event.Event;
import su.funtime.prometheusexporter.PrometheusApi;

public class IsEventActiveMetric {
    private static final Gauge IS_EVENT_ACTIVE = PrometheusApi.getRegisterMetrics().gaugeBuilder("active_event", "check if event active");
    JavaPlugin plugin = TestMetric.getInstance();

    void isEventActiveMetricCollector() {

        PrometheusApi.getRegisterMetrics().collectMetric(plugin, IS_EVENT_ACTIVE, () -> Event.isEventActive ? 1.0 : 0.0,true);
    }
    void stopEventGauge() {
        PrometheusApi.getRegisterMetrics().unregisterMetric(IS_EVENT_ACTIVE);
    }
}
