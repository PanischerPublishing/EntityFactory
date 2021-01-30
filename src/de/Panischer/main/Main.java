package de.Panischer.main;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import de.Panischer.timer.EntityTimer;
public class Main extends JavaPlugin
{
	public static Main plugin;
	@SuppressWarnings("unused")
	public void onEnable()
	{
		plugin = this;
		BukkitTask repeatingTask = new EntityTimer(this).runTaskTimer(this, 20, 20);
	}
}
