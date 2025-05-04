package org.funtime.testMetric.Event;
import net.kyori.adventure.text.Component;

import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.funtime.testMetric.TestMetric;
import org.jetbrains.annotations.NotNull;




public class StartEventCommand implements CommandExecutor {
    JavaPlugin plugin = TestMetric.getInstance();

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        if (strings.length == 0) {
            if (!Event.isEventActive) {
                Event.isEventActive = true;
                for (Player player :Bukkit.getServer().getOnlinePlayers()) {
                    player.sendMessage(Component.text("Ивент успешно запущен!", NamedTextColor.GREEN));
                }
                Bukkit.getScheduler().runTaskLater(plugin, () -> Event.isEventActive = false, 600);
            } else {
                commandSender.sendMessage(Component.text("Ивент уже запущен!", NamedTextColor.RED));
            }
            return true;
        }
        return false;
    }
}
