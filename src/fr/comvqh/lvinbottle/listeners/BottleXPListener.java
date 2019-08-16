package fr.comvqh.lvinbottle.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BottleXPListener implements Listener
{
    @EventHandler
    public void onSplash(final PlayerInteractEvent e) {
        if ((e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) && e.getPlayer().getItemInHand().getType() == Material.EXP_BOTTLE) {
            final Player p = e.getPlayer();
            if (p.getItemInHand().hasItemMeta()) {
                final ItemMeta im = p.getItemInHand().getItemMeta();
                if (im.getLore().get(0) != null && im.getLore().get(1) != null) {
                    final String s = im.getLore().get(0);
                    if (s.equals("§8• §aNiveaux §8•".replace('&', '§'))) {
                        e.setCancelled(true);
                        final int xp = Integer.parseInt(ChatColor.stripColor((String)im.getLore().get(1)));
                        if (p.getItemInHand().getAmount() > 1) {
                            p.getItemInHand().setAmount(p.getItemInHand().getAmount() - 1);
                        }
                        else {
                            p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
                        }
                        p.giveExpLevels(xp);
                        p.sendMessage(String.valueOf(String.valueOf("§8[§6BottleXP§8] ")) + ChatColor.YELLOW + xp + " niveau(x) ajout\u00e9(s) !");
                        p.updateInventory();
                    }
                }
            }
        }
    }
}