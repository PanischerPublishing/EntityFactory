package de.Panischer.timer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
public class BukkitTimer extends BukkitRunnable
{
	public final JavaPlugin plugin;
	public BukkitTimer(JavaPlugin plugin)
	{
		this.plugin = plugin;
	}
	public void run()
	{
		Bukkit.getWorlds().forEach(w ->
		{
			w.getEntities().forEach(i ->
			{
			if(i.getLocation().subtract(0, 1, 0).getBlock().getType().equals(Material.MAGENTA_GLAZED_TERRACOTTA))
			{
				//ItemAutoPickup
				if(i instanceof Item && i.getLocation().add(0, 2, 0).getBlock().getType().equals(Material.HOPPER))
				{
					if(i.getLocation().subtract(0, 3, 0).getBlock().getType().equals(Material.CHEST))
					{
						Item item = (Item) i;
						ItemStack itemstack = item.getItemStack();
						Chest chest = (Chest) i.getLocation().subtract(0, 3, 0).getBlock().getState();
						boolean empty = false;
						for (ItemStack is : chest.getInventory())
						{
						    if (is == null)
						    {
						        empty = true;
						        break;
						    }
						}
						if(empty)
						{
							chest.getInventory().addItem(itemstack);
							i.remove();
						}
					}
				}
				if(i.getLocation().add(0, 2, 0).getBlock().getType().equals(Material.OBSIDIAN) || i.getLocation().add(0, 3, 0).getBlock().getType().equals(Material.OBSIDIAN))
				{
					if(i instanceof LivingEntity &!(i instanceof Player))
					{
					LivingEntity le = (LivingEntity) i;
					le.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 1200, 255));
					}
				}
				//EntityAutoFire
				if(i.getLocation().add(0, 2, 0).getBlock().getType().equals(Material.MAGMA_BLOCK) || i.getLocation().add(0, 3, 0).getBlock().getType().equals(Material.MAGMA_BLOCK))
				{
					if(i instanceof LivingEntity &!(i instanceof Player))
					{
					LivingEntity le = (LivingEntity) i;
					le.setFireTicks(1200);
					}
				}
				//EntityAutoKill
				if(i.getLocation().add(0, 2, 0).getBlock().getType().equals(Material.STONECUTTER) || i.getLocation().add(0, 3, 0).getBlock().getType().equals(Material.STONECUTTER))
				{
					if(i instanceof LivingEntity &!(i instanceof Player))
					{
					LivingEntity le = (LivingEntity) i;
					le.setHealth(0);
					}
				}
				//EntityTransportation
				String blockFace = i.getLocation().subtract(0, 1, 0).getBlock().getBlockData().getAsString();
				double speed = 0.5;
				if(blockFace.contains("north"))
				{
					if(i.getLocation().add(0, 1, 1).getBlock().getType().equals(Material.MAGENTA_GLAZED_TERRACOTTA) && i.getLocation().add(0, 1, 1).getBlock().getBlockData().getAsString().contains("west"))
					{
					i.teleport(i.getLocation().add(0, 5, 0));
					}else
					{
					i.teleport(i.getLocation().add(0, 0, speed));
					}
				}
				if(blockFace.contains("south"))
				{
					if(i.getLocation().add(0, 1, 0).subtract(0, 0, 1).getBlock().getType().equals(Material.MAGENTA_GLAZED_TERRACOTTA) && i.getLocation().add(0, 1, 0).subtract(0, 0, 1).getBlock().getBlockData().getAsString().contains("east"))
					{
					i.teleport(i.getLocation().add(0, 5, 0));
					}else
					{
					i.teleport(i.getLocation().subtract(0, 0, speed));
					}
				}
				if(blockFace.contains("east"))
				{
					if(i.getLocation().add(0, 1, 0).subtract(1, 0, 0).getBlock().getType().equals(Material.MAGENTA_GLAZED_TERRACOTTA) && i.getLocation().add(0, 1, 0).subtract(1, 0, 0).getBlock().getBlockData().getAsString().contains("north"))
					{
					i.teleport(i.getLocation().add(0, 5, 0));
					}else
					{
					i.teleport(i.getLocation().subtract(speed, 0, 0));
					}
				}
				if(blockFace.contains("west"))
				{
					if(i.getLocation().add(1, 1, 0).getBlock().getType().equals(Material.MAGENTA_GLAZED_TERRACOTTA) && i.getLocation().add(1, 1, 0).getBlock().getBlockData().getAsString().contains("south"))
					{
					i.teleport(i.getLocation().add(0, 5, 0));
					}else
					{
					i.teleport(i.getLocation().add(speed, 0, 0));
					}
				}
			}
			});
		});
	}
}
