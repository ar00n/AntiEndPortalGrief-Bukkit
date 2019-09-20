package com.ar0nMC;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockPlaceEvent;

public final class AntiEndPortalGrief extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onPlayerBucketEmpty(PlayerBucketEmptyEvent e){
        if(e.getBlock().getType() == Material.END_PORTAL){
            e.setCancelled(true);
            Player p = e.getPlayer();
            p.sendMessage(ChatColor.RED + "You aren't allowed to destroy End Portals.");
        } else if (e.getBlock().getType() == Material.END_GATEWAY) {
            e.setCancelled(true);
            Player p = e.getPlayer();
            p.sendMessage(ChatColor.RED + "You aren't allowed to destroy End Gateways.");
        }
    }

    @EventHandler
    public void onBlockPlaceEvent(BlockPlaceEvent e){
        if(e.getBlock().getType() == Material.DISPENSER) {
            if(e.getBlockAgainst().getType() == Material.END_PORTAL || e.getBlockAgainst().getType() == Material.END_GATEWAY) {
                e.setCancelled(true);
                Player p = e.getPlayer();
                p.sendMessage(ChatColor.RED + "You aren't allowed to destroy End Portals.");
            } else {
                int radius = 1;
                Block block = e.getBlock(); //placed block
                loop:
                for (int x = -(radius); x <= radius; x++) {
                    for (int y = -(radius); y <= radius; y++) {
                        for (int z = -(radius); z <= radius; z++) {
                            if (block.getRelative(x, y, z).getType() == Material.END_PORTAL || block.getRelative(x, y, z).getType() == Material.END_GATEWAY) {
                                e.setCancelled(true);
                                Player p = e.getPlayer();
                                p.sendMessage(ChatColor.RED + "You aren't allowed to destroy End Portals.");
                                break loop;
                            }
                        }
                    }
                }
            }
        }
    }
}