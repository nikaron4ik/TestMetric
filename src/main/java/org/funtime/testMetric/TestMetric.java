package org.funtime.testMetric;

import io.prometheus.client.Gauge;
import lombok.Getter;

import lombok.Setter;
import org.bukkit.plugin.java.JavaPlugin;
import org.funtime.testMetric.Event.MetricRegistrationCommand;
import org.funtime.testMetric.Event.StartEventCommand;


public final class TestMetric extends JavaPlugin {

    @Getter
    private static TestMetric instance;
    @Getter @Setter
    private Gauge gauge_metric;

    @Override
    public void onEnable() {
        instance = this;
        this.getCommand("startevent").setExecutor(new StartEventCommand());
        this.getCommand("metric").setExecutor(new MetricRegistrationCommand());
    }

    @Override
    public void onDisable() {

    }
}
