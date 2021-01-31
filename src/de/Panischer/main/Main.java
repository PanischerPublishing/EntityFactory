package de.Panischer.main;
import org.bukkit.plugin.java.JavaPlugin;
import de.Panischer.listener.InfiniteDispensers;
import de.Panischer.timer.EntityTimer;
public class Main extends JavaPlugin
{
	public static Main plugin;
	public void onEnable()
	{
		plugin = this;
		new EntityTimer(this).runTaskTimerAsynchronously(this, 20, 20);
		getServer().getPluginManager().registerEvents(new InfiniteDispensers(), this);
	}
}
