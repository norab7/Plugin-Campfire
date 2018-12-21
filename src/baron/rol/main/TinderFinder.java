package baron.rol.main;

import java.util.ArrayList;

import org.bukkit.entity.Item;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Used to check through lists if there is a position that requires a fire or a
 * eventual (magma_block) If there are no instances where this is required the
 * runnable will be cancelled and removed
 * 
 * @author norab7
 *
 */
public class TinderFinder extends BukkitRunnable {

	@Override
	public void run() {
		// Checks if there are no more items or fires to check and stops the runnable
		if (PluginMain.tinderItems.size() == 0 && PluginMain.campfires.size() == 0) {
			cancel();
			PluginMain.taskTinderFinder = null;
		}

		// Iterates a list of items to check if they can become a fire
		for (Item i : new ArrayList<Item>(PluginMain.tinderItems)) {

			// If the dropped item is on ground and valid start a camp fire and remove item
			if (i.isOnGround() && i.isValid()) {
				Campfire fire = new Campfire(i);
				PluginMain.tinderItems.remove(i);

				// If a fire can be created create a fire add to list of fires
				if (fire.canCampfire()) {
					PluginMain.campfires.add(fire);
					fire.createCampfire();
				}
			}
		}

		// Check if the fire has run it's course and change to eventual
		for (Campfire cf : new ArrayList<Campfire>(PluginMain.campfires)) {
			if (cf.setEventualBlock()) {
				PluginMain.campfires.remove(cf);
			}
		}
	}
}
