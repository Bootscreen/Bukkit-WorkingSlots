package me.bootscreen.workingslots;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

/**
* WorkingSlots for CraftBukkit/Spout
*
* @author Bootscreen
* 
*/

public class WorkingSlots extends JavaPlugin{
	public final Logger log = Logger.getLogger("Minecraft");
	
	PluginDescriptionFile plugdisc;
	
	private FileManager fileManager = new FileManager();
		
	@Override
	public void onDisable() {
		log.info("[" + plugdisc.getName() + "] Version " + plugdisc.getVersion() + " disabled.");		
	}

	@Override
	public void onEnable() {
		
		fileManager.createConfig();
		
		plugdisc = this.getDescription();
		
		Plugin plugin = this.getServer().getPluginManager().getPlugin("Spout");
		
		if(plugin != null)
		{
			OptionalSpout os = new OptionalSpout();
			os.registerspoutkeys(this);
			log.info("[" + plugdisc.getName() + "] Version " + plugdisc.getVersion() + " with SpoutKeys enabled.");
		}
		else
		{
			log.info("[" + plugdisc.getName() + "] Version " + plugdisc.getVersion() + " enabled.");
		}
	}
	

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		boolean succeed = false;
		int presetnum;
		
		if(sender instanceof Player)
		{
			Player player = (Player) sender;
			
			if(cmd.getName().equalsIgnoreCase("ws"))
			{
				if(args.length == 0)
				{
					player.sendMessage("[" + plugdisc.getName() + "] Version " + plugdisc.getVersion() + ".");
					succeed = false;
				}
				else if(args.length == 1)
				{
					try
					{
						presetnum = Integer.parseInt(args[0]);
						
						if(presetnum >= 0 && presetnum < 10)
						{
							if(player.hasPermission("workingslots."+presetnum) || player.hasPermission("workingslots.*")) 
							{
								succeed = fileManager.loadPreset(player, presetnum);
							}
							else
							{
								player.sendMessage(ChatColor.RED + "[WorkingSlots] Du hast leider keine Rechte das Prest zu laden.");
							}
						}
						else
						{
							player.sendMessage(ChatColor.RED + "[WorkingSlots] Es gibt nur die Presets 0-9.");
						}
					}
					catch(NumberFormatException e)
					{
						player.sendMessage(ChatColor.RED + "[WorkingSlots] Gib bitte eine Zahl zwischen 0-9 als Preset an.");
					}
					catch(Exception e)
					{
						log.info(plugdisc.getName() + " " + plugdisc.getVersion() + " Error in the try/catch block of the Preset loading command.");
						e.printStackTrace();
						succeed = false;
					}
				}
				else if(args.length == 2)
				{
					if(args[0].equalsIgnoreCase("save"))
					{
						try
						{
							presetnum = Integer.parseInt(args[1]);

							if(presetnum >= 0 && presetnum < 10)
							{
								if(player.hasPermission("workingslots.save") || player.hasPermission("workingslots.*")) 
								{
									succeed = fileManager.savePreset(player, presetnum);
								}
								else
								{
									player.sendMessage(ChatColor.RED + "[WorkingSlots] Du hast leider keine Rechte das Prest zu laden.");
									succeed = false;
								}
							}
							else
							{
								player.sendMessage(ChatColor.RED + "[WorkingSlots] Es gibt nur die Presets 0-9.");
								succeed = false;
							}
						}
						catch(NumberFormatException e)
						{
							player.sendMessage(ChatColor.RED + "[WorkingSlots] Gib bitte eine Zahl zwischen 0-9 als Preset an.");
							succeed = false;
						}
						catch(Exception e)
						{
							log.info(plugdisc.getName() + " " + plugdisc.getVersion() + " Error in the try/catch block of the Preset saving command.");
							e.printStackTrace();
							succeed = false;
						}
					}
					else
					{
						succeed = false;
					}
				}
				else
				{
					succeed = false;
				}
			}
		}
		else
		{
			log.info("[" + plugdisc.getName() + "] the /ws commands can only be used by a Player.");
			succeed = true;
		}
		
		return succeed;
	}

}
