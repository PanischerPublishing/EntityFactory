package de.Panischer.listener;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Dispenser;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDispenseEvent;
import de.Panischer.main.Main;
public class InfiniteDispensers implements Listener
{
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onBlockDispense(BlockDispenseEvent event)
	{
		if(event.getBlock().getType().equals(Material.DISPENSER))
		{
			Dispenser dispenser = (Dispenser) event.getBlock().getState();
			Bukkit.getServer().getScheduler().scheduleAsyncDelayedTask(Main.plugin, new Runnable()
			{
				@Override
				public void run()
				{
					dispenser.getInventory().addItem(event.getItem());
				}
			});
		}
	}
}
