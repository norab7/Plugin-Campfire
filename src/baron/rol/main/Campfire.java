package baron.rol.main;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;

/**
 * Used to create camp fires using items, positions, and block types. If there
 * are a required amount of correct items in a stack it will alter the block
 * type on position to the fire block type. If the fire has ran it's course it
 * will change the below position block type to the eventual block type.
 * 
 * @author norab7
 *
 */
public class Campfire {

	// Some basic variables
	private long fireTime;
	private Location tinderPos;
	private Location onBlockPos;
	private ItemStack tinderStack;

	/**
	 * Create a camp fire object using the dropped 'ITEM' for information
	 * 
	 * @param item
	 */
	public Campfire(Item item) {

		// Get Locations
		this.tinderPos = item.getLocation();
		this.onBlockPos = new Location(tinderPos.getWorld(), 0, -1, 0).add(tinderPos);

		// Check if drop is on correct block
		if (!isOnRightBlock()) {
			return;
		}

		// Set stack and creation time
		this.fireTime = System.currentTimeMillis();
		this.tinderStack = item.getItemStack();

	}

	/**
	 * Check if the dropped item position is on top of the correct eventual block
	 * 
	 * @return boolean
	 */
	private boolean isOnRightBlock() {

		if (onBlockPos.getBlock().getType().name().contains(PluginMain.onBlock)) {
			return true;
		}
		return false;
	}

	/**
	 * Creates a fire block on the dropped item position
	 */
	public void createCampfire() {
		tinderPos.getBlock().setType(PluginMain.fireblock);
	}

	/**
	 * Checks if the quantity of dropped items in the stack matches the required for
	 * a camp fire to be created
	 * 
	 * @return
	 */
	public boolean canCampfire() {
		if (tinderStack.getAmount() >= PluginMain.quantity) {
			return true;
		}
		return false;
	}

	/**
	 * Using a runnable checks if the fire has been alive for the required duration
	 * to convert into a eventual block
	 * 
	 * @return
	 */
	public boolean setEventualBlock() {

		// Compares time of creation against the current time and converts to eventual
		if ((System.currentTimeMillis() - fireTime) >= (PluginMain.duration * 1000)) {
			if (tinderPos.getBlock().getType().equals(PluginMain.fireblock)
					&& onBlockPos.getBlock().getType().name().contains(PluginMain.onBlock)) {
				tinderPos.getBlock().setType(Material.AIR);
				onBlockPos.getBlock().setType(PluginMain.eventual);
			}
			return true;
		}
		return false;
	}

}
