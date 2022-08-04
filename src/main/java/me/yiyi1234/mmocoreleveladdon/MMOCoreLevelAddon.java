package me.yiyi1234.mmocoreleveladdon;

import me.yiyi1234.mmocoreleveladdon.commands.MMOCoreLevelAddonCommand;
import me.yiyi1234.mmocoreleveladdon.events.PlayerLevelUp;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class MMOCoreLevelAddon extends JavaPlugin {
    private FileConfiguration config;
    private static MMOCoreLevelAddon instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        setInstance(this);
        config();
        getServer().getPluginManager().registerEvents(new PlayerLevelUp(), this);
        getCommand("MMOCoreLevelAddon").setExecutor(new MMOCoreLevelAddonCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void config() {
        File file = new File(this.getDataFolder().getAbsolutePath() + "/config.yml");
        if (!file.exists()) {
            Bukkit.getConsoleSender().sendMessage("§7<§6MMOCoreLevelAddon§7> §f正在生成config！");
            this.getDataFolder().mkdir();
            this.saveResource("config.yml", false);
        }
        config = YamlConfiguration.loadConfiguration(file);
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static MMOCoreLevelAddon getInstance() {
        return instance;
    }

    public void setInstance(MMOCoreLevelAddon instance) {
        MMOCoreLevelAddon.instance = instance;
    }

}
