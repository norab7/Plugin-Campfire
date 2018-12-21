package baron.rol.main;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Item;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

/**
 * Main class
 * 
 * @author norab7
 *
 */
public class PluginMain extends JavaPlugin {

	// Couldn't figure out how to non-static and object variables
	public static PluginMain thisplugin;
	public static BukkitTask taskTinderFinder;

	// camp fire data
	public static ArrayList<Item> tinderItems = new ArrayList<Item>();
	public static ArrayList<Campfire> campfires = new ArrayList<Campfire>();

	// configuration things
	public FileConfiguration config = this.getConfig();
	public static int duration;
	public static int quantity;
	public static Material tinder;
	public static Material eventual;
	public static Material fireblock;
	public static String onBlock;

	@Override
	public void onEnable() {

		// get configuration file
		this.saveDefaultConfig();
		config = this.getConfig();

		// extract values from file
		duration = config.getInt("duration");
		quantity = config.getInt("quantity");
		tinder = Material.matchMaterial(config.getString("tinder"));
		eventual = Material.matchMaterial(config.getString("eventual"));
		fireblock = Material.matchMaterial(config.getString("fireblock"));
		onBlock = config.getString("onblock");

		// output values to be used to console
		System.out.println("[Campfire]  Duration: " + duration + " seconds");
		System.out.println("[Campfire]    Tinder: " + tinder + "");
		System.out.println("[Campfire]  Quantity: " + quantity + "");
		System.out.println("[Campfire]  Eventual: " + eventual + "");
		System.out.println("[Campfire] Fireblock: " + fireblock + "");
		System.out.println("[Campfire] BlockType: " + onBlock + "");

		// Set PluginMain to variable and start listening for dropped tinderItems
		PluginMain.thisplugin = this;
		getServer().getPluginManager().registerEvents(new CampListener(), this);
	}

	@Override
	public void onDisable() {
		// Don't know what's needed here

	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		// Check if active or not using double //
		if (command.getName().equalsIgnoreCase("/campfire")) {
			sender.sendMessage("Plugin: Campfire Active");
			return true;
		}
		return false;
	}
}
