package me.bootscreen.workingslots;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

/**
* WorkingSlots for CraftBukkit/Spout
*
* @author Bootscreen
* @version 0.5
* 
*/

public class WorkingSlots extends JavaPlugin{

	private FileManager fileManager = new FileManager();
	
	@Override
	public void onDisable() {
		System.out.println("WorkingSlots disabled.");
		
	}

	@Override
	public void onEnable() {
		
		fileManager.createConfig();
		
		Plugin plugin = this.getServer().getPluginManager().getPlugin("Spout");
		
		if(plugin != null)
		{
			OptionalSpout os = new OptionalSpout();
			os.registerspoutkeys(this);
			System.out.println("WorkingSlots with SpoutKeys enabled.");
		}
		else
		{
			System.out.println("WorkingSlots enabled.");
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
				if(args.length == 1)
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
							e.printStackTrace();
							succeed = false;
						}
					}
					else
					{
						player.sendMessage(ChatColor.RED + "[WorkingSlots] |"+args[0]+"|");
						succeed = false;
					}
				}
				else
				{
					succeed = false;
				}
			}
		}
		
		return succeed;
		
	}

}
