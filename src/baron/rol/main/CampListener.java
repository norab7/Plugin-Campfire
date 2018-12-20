package baron.rol.main;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class CampListener implements Listener {
public static ArrayList<Item> torches = new ArrayList<Item>();

	@EventHandler
	public void onDropItem(PlayerDropItemEvent event) {
		if (event.getItemDrop().getItemStack().getType().equals(Material.TORCH)) {
			System.out.println(event.getPlayer().getName() + " has dropped a torch!");
			
			// add dropped torch to the torch list
			torches.add(event.getItemDrop());
		}
	}

}
