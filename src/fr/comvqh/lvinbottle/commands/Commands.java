package fr.comvqh.lvinbottle.commands;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Commands implements CommandExecutor {
	
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player)sender;
            
            if (cmd.getName().equalsIgnoreCase("bottlexp") || cmd.getName().equalsIgnoreCase("bouteillexp")) {
              int amount;
              
              if (p.getLevel() <= 0) {
                p.sendMessage(String.valueOf(String.valueOf("[BottleXP]")) + ChatColor.YELLOW + "Vous n'avez aucun niveau !");
                return true;
              } 
              
              if (args.length > 0) { 
                try { amount = Integer.parseInt(args[0]);
                  if (amount <= p.getLevel()) { 
                    if (amount <= 0){ 
                    	p.sendMessage(String.valueOf(String.valueOf("[BottleXP]")) + ChatColor.YELLOW + amount + " n'est pas assez grand !");
                      return true; 
                      }  
                    } 
                  else {
                	  p.sendMessage(String.valueOf(String.valueOf("§8[§6BottleXP§8] ")) + ChatColor.YELLOW + "Vous n'avez pas assez de niveaux !"); return true; }
                   }
                catch (NumberFormatException e) { 
                	p.sendMessage(String.valueOf(String.valueOf("§8[§6BottleXP§8] ")) + ChatColor.YELLOW + "\"" + args[0] + "\" n'est pas un nombre !"); return true; }
                 }
              else { 
            	  amount = p.getLevel(); 
              }
              
              ItemStack is = new ItemStack(Material.EXP_BOTTLE);
              ItemMeta im = is.getItemMeta();
              ArrayList<String> lore = new ArrayList<String>();
              lore.add("§8• §aNiveaux §8•");
              lore.add(String.valueOf(ChatColor.RED) + amount);
              im.setLore(lore);
              im.setDisplayName("§8• §6BottleXP §8•");
              is.setItemMeta(im);
              p.setLevel(p.getLevel() - amount);
              p.getInventory().addItem(new ItemStack[] { is });
              p.sendMessage(String.valueOf(String.valueOf("§8[§6BottleXP§8] ")) + "Votre XP vient d'être mis en bouteille.");
            } 
          } 
          return true;
        }
}
