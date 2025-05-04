package org.funtime.testMetric;

import lombok.Getter;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.funtime.testMetric.Event.StartEventCommand;


public final class TestMetric extends JavaPlugin {
    @Getter
    private static TestMetric instance;

    @Override
    public void onEnable() {
        instance = this;
        Bukkit.getPluginCommand("startevent").setExecutor(new StartEventCommand());

        IsEventActiveMetric eventActiveMetric = new IsEventActiveMetric();
        eventActiveMetric.isEventActiveMetricCollector();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
