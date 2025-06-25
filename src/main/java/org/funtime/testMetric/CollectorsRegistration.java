package org.funtime.testMetric;

import lombok.Getter;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;

import org.funtime.testMetric.Event.Event;
import su.funtime.prometheusexporter.api.MetricCollector;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CollectorsRegistration {

    final Plugin plugin = TestMetric.getInstance();

    @Getter
    private final MetricCollector eventMetricCollector = new MetricCollector(plugin, "event_active", "something", true) {

        @Override
        public double collect() {
            return Event.isEventActive ? 1 : 0;
        }
    };

    @Getter
    private final MetricCollector metricWithLabels = new MetricCollector(plugin, "entitites_per_world", "entities in every world", false) {
        public List<String> getLabelNames() {
            return List.of("world");
        }

        @Override
        public Map<List<String>, Double> collectWithLabels() {
            Map<List<String>, Double> map = new HashMap<>();
            for (World world : plugin.getServer().getWorlds()) {
                    map.put(List.of(world.getName()), (double) world.getEntities().size());
                }
            return map;
        }
    };


}
