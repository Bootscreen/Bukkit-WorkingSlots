package me.bootscreen.workingslots;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
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

	public FileConfiguration config = null;

	@Override
	public void onDisable() {
		log.info("[" + plugdisc.getName() + "] Version " + plugdisc.getVersion() + " disabled.");		
	}

	@Override
	public void onEnable() {

		config = this.getConfig();
		loadConfig();

		plugdisc = this.getDescription();

		Plugin plugin = this.getServer().getPluginManager().getPlugin("Spout");

		if(plugin != null)
		{
			OptionalSpout os = new OptionalSpout(this);
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
								succeed = loadPreset(player, presetnum);
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
									succeed = savePreset(player, presetnum);
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
	

	public boolean savePreset(Player player, int number)
	{
		String wert = "";
		try 
		{
			ItemStack item;
			for(int i = 0; i < 9; i++)
			{
				item = player.getInventory().getItem(i);

				if(i != 0)
				{
					wert += ",";
				}

				wert += item.getTypeId();

				if(item.getType().getMaxDurability() == 0 && item.getDurability() > 0)
				{
					wert += "-"+item.getDurability();
				}									
			}

			if(number >= 0 && number <= 9)
			{
				config.set("Preset_" + number, wert);
				saveConfig();
				return true;						
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		return false;
	}
	
	public boolean loadPreset(Player player, int presetnum)
	{
		boolean succeed = false;

		try
		{
			if(presetnum >= 0 && presetnum <= 9)
			{		
				String preset = config.getString("Preset_" + presetnum,"0");

				String[] temp;
				String delimiter = ",";
				temp = preset.split(delimiter);

				for(int i = 0; i < temp.length && i < 9 ; i++)
				{
					String[] temp2;
					String delimiter2 = "-";
					temp2 = temp[i].split(delimiter2);
					int item = Integer.parseInt(temp2[0]);
					if(temp2.length == 2)
					{
						short dmgvalue = Short.parseShort(temp2[1]);
						player.getInventory().setItem(i, new ItemStack(item, 1, dmgvalue));
					}
					else
					{
						player.getInventory().setItem(i, new ItemStack(item, 1));
					}
				}

			}
			succeed = true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			succeed = false;
		}

		return succeed;
	}
	
	public void loadConfig()
	{
		config.addDefault("Preset_0", 0);
		config.addDefault("Preset_1", 0);
		config.addDefault("Preset_2", 0);
		config.addDefault("Preset_3", 0);
		config.addDefault("Preset_4", 0);
		config.addDefault("Preset_5", 0);
		config.addDefault("Preset_6", 0);
		config.addDefault("Preset_7", 0);
		config.addDefault("Preset_8", 0);
		config.addDefault("Preset_9", 0);
		config.options().copyDefaults(true);
		saveConfig();
	}
}