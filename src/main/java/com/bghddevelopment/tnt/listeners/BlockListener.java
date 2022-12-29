package com.bghddevelopment.tnt.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.FallingBlock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.util.Vector;

public class BlockListener implements Listener {

    @EventHandler
    public void onEntityExplode(final EntityExplodeEvent event) {
        for (final Block block : event.blockList()) {
            if (block.getType() != Material.TNT) {
                final float x = -2.0f + (float)(Math.random() * 5.0);
                final float y = -3.0f + (float)(Math.random() * 7.0);
                final float z = -2.5f + (float)(Math.random() * 6.0);

                final FallingBlock fallingBlock = block.getWorld().spawnFallingBlock(block.getLocation(), block.getType(), block.getData());

                fallingBlock.setDropItem(false);
                fallingBlock.setVelocity(new Vector(x, y, z));

                block.setType(Material.AIR);
            }
        }
    }

}
