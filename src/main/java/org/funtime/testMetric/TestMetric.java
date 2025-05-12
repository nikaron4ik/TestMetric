package org.funtime.testMetric;

import io.prometheus.client.Gauge;
import lombok.Getter;

import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.funtime.testMetric.Event.Event;
import org.funtime.testMetric.Event.StartEventCommand;
import su.funtime.prometheusexporter.PrometheusApi;


public final class TestMetric extends JavaPlugin {
    @Getter
    private static TestMetric instance;
    Gauge is_event_active_2;
    IsEventActiveMetric eventActiveMetric;

    @Override
    public void onEnable() {
        instance = this;
        eventActiveMetric = new IsEventActiveMetric();

        Bukkit.getPluginCommand("startevent").setExecutor(new StartEventCommand());

        eventActiveMetric.isEventActiveMetricCollector();

        is_event_active_2 = PrometheusApi.getRegisterMetrics().registerMetric(
                instance,
                "active_event_2",
                "check if event active",
                () -> Event.isEventActive ? 1.0 : 0.0,
                true
        );
    }

    @Override
    public void onDisable() {
        // Пример для регистраци через методы gaugeBuilder() и collectMetric()
        eventActiveMetric.stopEventGauge();

        // Пример для регистрации метрики через registerMetric()
        PrometheusApi.getRegisterMetrics().unregisterMetric(is_event_active_2);
    }
}
