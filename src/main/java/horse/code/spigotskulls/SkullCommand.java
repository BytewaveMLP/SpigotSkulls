package horse.code.spigotskulls;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class SkullCommand implements CommandExecutor {
	@Override
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Only living players can get skulls!");
			return true;
		}

		Player player = (Player) sender;
		OfflinePlayer target = args.length > 0 ? Bukkit.getOfflinePlayer(args[0]) : player;

		ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
		skull.setAmount(1);

		SkullMeta meta = (SkullMeta) skull.getItemMeta();
		meta.setDisplayName(target.getName() + "'s Head");

		meta.setOwningPlayer(target);
		skull.setItemMeta(meta);

		player.getInventory().addItem(skull);
		player.sendMessage(ChatColor.GOLD + "Here's your skull!");

		return true;
	}
}
