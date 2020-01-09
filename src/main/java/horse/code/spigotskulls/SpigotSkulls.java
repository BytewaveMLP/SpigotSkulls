package horse.code.spigotskulls;

import org.bukkit.plugin.java.JavaPlugin;

public class SpigotSkulls extends JavaPlugin {
	@Override
	public void onEnable() {
		this.getCommand("skull").setExecutor(new SkullCommand());
		getLogger().info("Hello, Minecraft!");
	}

	@Override
	public void onDisable() {
		getLogger().info("Elvis has left the building!");
	}
}
