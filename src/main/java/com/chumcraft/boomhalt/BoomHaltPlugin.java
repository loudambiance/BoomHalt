/**
 * Copyright (C) 2020 Daniel Baucom
 * 
 * This program is free software: you can redistribute it and/or modify it 
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/license
 * 
 */

package com.chumcraft.boomhalt;

import com.chumcraft.boomhalt.utils.UpdateChecker;

import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

public class BoomHaltPlugin extends JavaPlugin {

    private static BoomHaltPlugin plugin;
    public boolean halt = false;

    public static BoomHaltPlugin getInstance(){
        return BoomHaltPlugin.plugin;
    }

    @Override
    public void onDisable() {
        // Don't log disabling, Spigot does that for you automatically!
    }

    @Override
    public void onEnable() {
        // Don't log enabling, Spigot does that for you automatically!
        BoomHaltPlugin.plugin = this;
        this.updateMetrics();
        this.checkUpdates();


        // Commands enabled with following method must have entries in plugin.yml
        this.getCommand("boomhalt").setExecutor(new BoomHaltCommand(this));
        getServer().getPluginManager().registerEvents(new BoomHaltListener(), this);
    }

    private void updateMetrics() {
        int bStatID = 7316;
        Metrics metrics = new Metrics(this, bStatID);
    }

    private void checkUpdates()
    {
        new UpdateChecker(this, 77477).getVersion(version -> { //points to wrong plugin number
            if (this.getDescription().getVersion().equalsIgnoreCase(version)) {
                //this.getLogger().info("There is not a new update available.");
            } else {
                this.getLogger().info("There is a new update for BoomHalt available, you should consider updating.");
            }
        });
    }
}
