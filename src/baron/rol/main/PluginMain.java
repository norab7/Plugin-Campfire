package baron.rol.main;

import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Item;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class PluginMain extends JavaPlugin {
	private ArrayList<Campfire> campfires = new ArrayList<Campfire>();

	@Override
	public void onEnable() {

		// Set Listener
		getServer().getPluginManager().registerEvents(new CampListener(), this);

		// Constant check for Camp fires using Scheduler
		new BukkitRunnable() {
			@Override
			public void run() {

				// Loop torches that have been dropped and check if they stack on a log
				for (Item i : CampListener.torches) {
					if (i.isOnGround() && i.isValid()) {
						Campfire fire = new Campfire(i, 10);

						// if 4 torches, create camp fire
						if (fire.canCampfire()) {
							campfires.add(fire);
							fire.createCampfire();
						}
					}
				}

				for (Campfire cf : new ArrayList<Campfire>(campfires)) {
					if (cf.setMagma()) {
						campfires.remove(cf);
					}
				}

			}
		}.runTaskTimer(this, 0, 2);

		// System out if working
		System.out.println("[-Plugin-] Campfire Active!");
	}

	@Override
	public void onDisable() {

	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (command.getName().equalsIgnoreCase("/campfire")) {
			sender.sendMessage("Plugin: Campfire Working");
			return true;
		}
		return false;
	}

}
