package me.yiyi1234.mmocoreleveladdon.commands;

import me.yiyi1234.mmocoreleveladdon.MMOCoreLevelAddon;
import net.Indyuce.mmocore.api.MMOCoreAPI;
import net.Indyuce.mmocore.api.player.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Set;

public class MMOCoreLevelAddonCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length < 1) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7MMOCoreLevelAddon 自動加成獎勵派發"));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6製作者: 依依#1350 &7| &bVersion &e1.0"));
        }
        // MMOCoreLevelAddon check dx11

        if (args.length == 2) {
            if (args[0].equalsIgnoreCase("check")) {

                Set<String> commandAmount = MMOCoreLevelAddon.getInstance().getConfig().getConfigurationSection("commandcheck").getKeys(false);
                for (String s : commandAmount) {
                    if (s.contains(args[1])) {
                        Player player = (Player) sender;
                        MMOCoreAPI mmoCoreAPI = new MMOCoreAPI(MMOCoreLevelAddon.getInstance());
                        PlayerData playerData = mmoCoreAPI.getPlayerData(player);

                        if (!player.hasPermission(MMOCoreLevelAddon.getInstance().getConfig().getString("commandcheck." + s + ".permission"))) {
                            if (playerData.getLevel() >= MMOCoreLevelAddon.getInstance().getConfig().getInt("commandcheck." + s + ".limitlevel")) {
                                for (String cmdList : MMOCoreLevelAddon.getInstance().getConfig().getStringList("commandcheck." + s + ".commands")) {
                                    Bukkit.getScheduler().runTask(MMOCoreLevelAddon.getInstance(), () -> {
                                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmdList.replace("%player%", sender.getName()));
                                    });
                                }
                            }
                        }

                    }

                }
            }
        }



        return false;
    }
}
