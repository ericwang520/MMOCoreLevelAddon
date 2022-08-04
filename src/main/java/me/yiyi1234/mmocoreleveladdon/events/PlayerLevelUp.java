package me.yiyi1234.mmocoreleveladdon.events;

import me.yiyi1234.mmocoreleveladdon.MMOCoreLevelAddon;
import net.Indyuce.mmocore.api.event.PlayerLevelUpEvent;
import net.Indyuce.mmocore.experience.Profession;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Objects;
import java.util.Set;

public class PlayerLevelUp implements Listener {
    @EventHandler
    public void onLevelUp(PlayerLevelUpEvent event) {
        if (event.getProfession() == null) {
            int newLevel = event.getNewLevel();
            Set<String> commandAmount = MMOCoreLevelAddon.getInstance().getConfig().getConfigurationSection("levelupcommands").getKeys(false);
            for (String s : commandAmount) {
                if (newLevel == Integer.valueOf(s)) {
                    for (String cmd : MMOCoreLevelAddon.getInstance().getConfig().getStringList("levelupcommands." + s)) {
                        Bukkit.getScheduler().runTask(MMOCoreLevelAddon.getInstance(), () -> {
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd.replace("%player%", event.getPlayer().getName()));
                        });
                    }
                }
            }
        }
    }
}
