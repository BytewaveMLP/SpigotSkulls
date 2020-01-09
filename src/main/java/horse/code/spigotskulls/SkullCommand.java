package horse.code.spigotskulls;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.io.IOException;
import java.util.UUID;

public class SkullCommand implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Only living players can get skulls!");
			return true;
		}

		Player player = (Player) sender;
		String targetName = player.getDisplayName();
		if (args.length > 0) {
			targetName = args[0];
		}

		ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
		skull.setAmount(1);

		SkullMeta meta = (SkullMeta) skull.getItemMeta();
		meta.setDisplayName(targetName + "'s Head");

		UUID uuid;
		try {
			uuid = Util.getPlayerUUIDFromName(targetName);
		} catch (IOException e) {
			sender.sendMessage(ChatColor.RED + "Failed to fetch player data.");
			return true;
		} catch (RuntimeException e) {
			sender.sendMessage(ChatColor.RED + "Couldn't get a skull for player " + targetName + ".");
			return true;
		}

		meta.setOwningPlayer(Bukkit.getOfflinePlayer(uuid));
		skull.setItemMeta(meta);

		player.getInventory().addItem(skull);
		player.sendMessage(ChatColor.GOLD + "Here's your skull!");

		return true;
	}
}
