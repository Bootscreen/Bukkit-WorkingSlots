package me.bootscreen.workingslots;

import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.keyboard.Keyboard;

/**
* WorkingSlots for CraftBukkit/Spout
* 
* handels the SpoutKeyRegistration
* 
* @author Bootscreen
* 
*/

public class OptionalSpout 
{	
	public WorkingSlots plugin;
	public OptionalSpout(WorkingSlots instance) {
		plugin = instance;
	}
		
	private final KeyListener listener = new KeyListener(this);

	public void registerspoutkeys(Plugin plugin2)
	{
		SpoutManager.getKeyBindingManager().registerBinding("Preset0", Keyboard.KEY_NUMPAD0, "Load Preset 0", listener, plugin2);
		SpoutManager.getKeyBindingManager().registerBinding("Preset1", Keyboard.KEY_NUMPAD1, "Load Preset 1", listener, plugin2);
		SpoutManager.getKeyBindingManager().registerBinding("Preset2", Keyboard.KEY_NUMPAD2, "Load Preset 2", listener, plugin2);
		SpoutManager.getKeyBindingManager().registerBinding("Preset3", Keyboard.KEY_NUMPAD3, "Load Preset 3", listener, plugin2);
		SpoutManager.getKeyBindingManager().registerBinding("Preset4", Keyboard.KEY_NUMPAD4, "Load Preset 4", listener, plugin2);
		SpoutManager.getKeyBindingManager().registerBinding("Preset5", Keyboard.KEY_NUMPAD5, "Load Preset 5", listener, plugin2);
		SpoutManager.getKeyBindingManager().registerBinding("Preset6", Keyboard.KEY_NUMPAD6, "Load Preset 6", listener, plugin2);
		SpoutManager.getKeyBindingManager().registerBinding("Preset7", Keyboard.KEY_NUMPAD7, "Load Preset 7", listener, plugin2);
		SpoutManager.getKeyBindingManager().registerBinding("Preset8", Keyboard.KEY_NUMPAD8, "Load Preset 8", listener, plugin2);
		SpoutManager.getKeyBindingManager().registerBinding("Preset9", Keyboard.KEY_NUMPAD9, "Load Preset 9", listener, plugin2);
	}
}
