package me.fizz.serverselector;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Selector extends JavaPlugin implements Listener {
	
	public void onEnable(){
		getServer().getPluginManager().registerEvents(this, this);
	}
	
	public void teleportInWorld(Player player, int x, int y, int z) {
		player.teleport(new Location(player.getWorld(), x, y, z));
	}
	
	private void openGUI(Player player){
		Inventory inv = Bukkit.createInventory(null, 9, ChatColor.AQUA + "Server Selector");
		
		
	    ItemStack Jenava = new ItemStack(Material.SAND);
 	    ItemMeta JenavaMeta = Jenava.getItemMeta();
 	    ItemStack Malino = new ItemStack(Material.STONE);
	    ItemMeta MalinoMeta = Malino.getItemMeta();
	    ItemStack Entropia = new ItemStack(Material.GRASS);
 	    ItemMeta EntropiaMeta = Entropia.getItemMeta();
 	    ItemStack Atlas = new ItemStack(Material.LEAVES);
	    ItemMeta AtlasMeta = Atlas.getItemMeta();
 	    
	    JenavaMeta.setDisplayName(ChatColor.YELLOW + "Jenava");
	    Jenava.setItemMeta(JenavaMeta);
	    
	    MalinoMeta.setDisplayName(ChatColor.GRAY + "Malino");
	    Malino.setItemMeta(MalinoMeta);
	    
	    AtlasMeta.setDisplayName(ChatColor.BLUE + "Atlas");
	    Atlas.setItemMeta(AtlasMeta);
	    
	    EntropiaMeta.setDisplayName(ChatColor.BLUE + "Entropia");
	    Entropia.setItemMeta(EntropiaMeta);
	    
	    inv.setItem(1, Jenava);
	    inv.setItem(3, Malino);
	    inv.setItem(5, Atlas);
	    inv.setItem(7, Entropia);
	    
	    player.openInventory(inv);
	    
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event){
		if(!ChatColor.stripColor(event.getInventory().getName())
			.equalsIgnoreCase("Server Selector"))
			return;
		Player player = (Player) event.getWhoClicked();
		event.setCancelled(true);
		
		if(event.getCurrentItem()==null || event.getCurrentItem().getType()==Material.AIR||!event.getCurrentItem().hasItemMeta()){
			player.closeInventory();
			return;
		}
		
		switch(event.getCurrentItem().getType()){
		case SAND:
			teleportInWorld(player, 10, 10, 100);
			player.closeInventory();
			player.sendMessage(String.format("%sTeleported to %sJenava%s!",
					ChatColor.GREEN, ChatColor.YELLOW, ChatColor.GREEN));
			break;
		case GRASS:
			teleportInWorld(player, 10, 10, 100);
			player.closeInventory();
			player.sendMessage(String.format("%sTeleported to %sMalino%s!",
					ChatColor.GREEN, ChatColor.GRAY, ChatColor.GREEN));
			break;
		case STONE:
			teleportInWorld(player, 10, 10, 100);
			player.closeInventory();
			player.sendMessage(String.format("%sTeleported to %sAtlas%s!",
					ChatColor.GREEN, ChatColor.GREEN, ChatColor.GREEN));
		case LEAVES:
			teleportInWorld(player, 10, 10, 100);
			player.closeInventory();
			player.sendMessage(String.format("%sTeleported to %sEntropia%s!",
					ChatColor.GREEN, ChatColor.DARK_GREEN, ChatColor.GREEN));
			break;
		default:
			player.closeInventory();
			break;
		}
	}
	
	
		
	

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event){
		event.getPlayer().getInventory()
		    .addItem(new ItemStack(Material.COMPASS));
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event){
		Action a = event.getAction();
		ItemStack is = event.getItem();
		
		if (a == Action.PHYSICAL || is == null || is.getType() == Material.AIR)
			return;
		
		if(is.getType() == Material.COMPASS)
			openGUI(event.getPlayer());
	}
	
}
