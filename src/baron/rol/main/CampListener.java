package baron.rol.main;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

/**
 * Listens for any player that drops the correct item in the world and adds that
 * item to a list to be checked if it can spawn a camp fire
 * 
 * @author norab7
 *
 */
public class CampListener implements Listener {

	@EventHandler
	public void onDropItem(PlayerDropItemEvent event) {

		// Checks if the dropped item matches the required
		if (event.getItemDrop().getItemStack().getType().equals(PluginMain.tinder)) {

			// Adds item to list and starts a runnable task to monitor for creating fires
			PluginMain.taskTinderFinder = new TinderFinder().runTaskTimer(PluginMain.thisplugin, 0, 2);
			PluginMain.tinderItems.add(event.getItemDrop());
		}
	}
}
