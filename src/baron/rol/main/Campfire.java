package baron.rol.main;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;

public class Campfire {
	private long fireTime;
	private Location torchPos;
	private Location onBlock;
	private ItemStack torchStack;

	private long duration;
	private boolean canCampfire = false;

	public Campfire(Item d, int t) {

		// get the location and check for log under
		this.torchPos = d.getLocation();
		this.onBlock = new Location(torchPos.getWorld(), 0, -1, 0).add(torchPos);
		if (!isOnLog()) {
			return;
		}

		// set base values
		this.fireTime = System.currentTimeMillis();
		this.torchStack = d.getItemStack();
		this.duration = t * 1000;

		// if there is 4 torches create a camp fire in place of the log block
		if (torchStack.getAmount() >= 4) {
			System.out.println("HAS 4 TORCHES");
			canCampfire = true;
		}

	}

	// Check that the block under the dropped position is a log
	private boolean isOnLog() {

		String onBlockName = onBlock.getBlock().getType().name();

		if (onBlockName.contains("LOG")) {
			System.out.println("CampfireBlock: " + onBlockName);
			return true;
		}
		return false;
	}

	// Spawn a fire in place of the log block
	public void createCampfire() {
		torchPos.getBlock().setType(Material.FIRE);
	}

	public boolean canCampfire() {
		return this.canCampfire;
	}

	public boolean setMagma() {

		if ((System.currentTimeMillis() - fireTime) >= duration) {
			if (torchPos.getBlock().getType().equals(Material.FIRE)
					&& onBlock.getBlock().getType().name().contains("LOG")) {
				torchPos.getBlock().setType(Material.AIR);
				onBlock.getBlock().setType(Material.MAGMA_BLOCK);
			}
			return true;
		}
		return false;
	}

}
