package com.chumcraft.boomhalt;

import com.chumcraft.boomhalt.BoomHaltPlugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class BoomHaltCommand implements CommandExecutor {

    private BoomHaltPlugin plugin;

    public BoomHaltCommand(BoomHaltPlugin plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (sender.hasPermission("boomhalt")) {
                if(this.plugin.halt){
                    this.plugin.halt = false;
                    Bukkit.broadcastMessage(ChatColor.RED + "Explosions have been enabled!");
                }else{
                    this.plugin.halt = true;
                    Bukkit.broadcastMessage(ChatColor.RED + "Explosions have been halted!");
                }
            }else{
                sender.sendMessage("Permission denied.");
            }
        }else if(sender instanceof ConsoleCommandSender){
            if(this.plugin.halt){
                this.plugin.halt = false;
                Bukkit.broadcastMessage(ChatColor.RED + "Explosions have been enabled!");
            }else{
                this.plugin.halt = true;
                Bukkit.broadcastMessage(ChatColor.RED + "Explosions have been halted!");
            }
        }

        return false;
    }
}