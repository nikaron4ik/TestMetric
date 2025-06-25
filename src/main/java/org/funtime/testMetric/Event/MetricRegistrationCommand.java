package org.funtime.testMetric.Event;


import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.funtime.testMetric.CollectorsRegistration;
import org.funtime.testMetric.TestMetric;
import org.jetbrains.annotations.NotNull;
import su.funtime.prometheusexporter.PrometheusApi;

public class MetricRegistrationCommand implements CommandExecutor {

    CollectorsRegistration collectorsRegistration = new CollectorsRegistration();

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {

        if (strings[0].equals("register")) {
            TestMetric.getInstance().setGauge_metric(PrometheusApi.getRegisterMetrics().registerMetric(collectorsRegistration.getEventMetricCollector()));
            commandSender.sendMessage(ChatColor.GREEN + "Метрика зарегистрирована.");
        } else if (strings[0].equals("unregister")) {
            PrometheusApi.getRegisterMetrics().unregisterMetric(collectorsRegistration.getEventMetricCollector());
            commandSender.sendMessage(ChatColor.RED + "Метрика разрегистрирована.");
        }
        return true;
    }
}
