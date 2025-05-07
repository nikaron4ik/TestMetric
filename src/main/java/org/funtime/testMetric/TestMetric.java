package org.funtime.testMetric;

import lombok.Getter;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.funtime.testMetric.Event.Event;
import org.funtime.testMetric.Event.StartEventCommand;
import su.funtime.prometheusexporter.PrometheusApi;


public final class TestMetric extends JavaPlugin {
    @Getter
    private static TestMetric instance;

    @Override
    public void onEnable() {
        instance = this;
        Bukkit.getPluginCommand("startevent").setExecutor(new StartEventCommand());

        IsEventActiveMetric eventActiveMetric = new IsEventActiveMetric();
        eventActiveMetric.isEventActiveMetricCollector();

        //
        PrometheusApi.getRegisterMetrics().registerMetric(
                instance,
                "active_event_2",
                "check if event active",
                () -> Event.isEventActive ? 1.0 : 0.0,
                true
        );
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
