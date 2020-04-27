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

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ExplosionPrimeEvent;

public class BoomHaltListener implements Listener {
     private BoomHaltPlugin plugin;

     public BoomHaltListener() {
          this.plugin = BoomHaltPlugin.getInstance();
     }

     @EventHandler
     public void onExplosionPrime(ExplosionPrimeEvent event) {
          if (this.plugin.halt) {
               event.setCancelled(true);
               event.getEntity().remove();
          } else {
               int radius = Math.round(event.getRadius());
               Location location = event.getEntity().getLocation();
               int tntcount = 0;
               for (int x = location.getBlockX() - radius; x <= location.getBlockX() + radius; x++) {
                    for (int y = location.getBlockY() - radius; y <= location.getBlockY() + radius; y++) {
                         for (int z = location.getBlockZ() - radius; z <= location.getBlockZ() + radius; z++) {
                              Material material = location.getWorld().getBlockAt(x, y, z).getType();
                              if (material == Material.TNT) {
                                   tntcount += 1;
                                   if (tntcount >= 9) {
                                        break;
                                   }
                              }
                         }
                         if (tntcount >= 9) {
                              break;
                         }
                    }
                    if (tntcount >= 9) {
                         break;
                    }
               }
               if (tntcount >= 9) {
                    event.setCancelled(true);
                    this.plugin.getLogger().info("Stopping blast, TNT COUNT: "+ tntcount);
                    event.getEntity().remove();
               }
          }

     }
}