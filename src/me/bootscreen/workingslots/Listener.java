package me.bootscreen.workingslots;

import org.getspout.spoutapi.event.input.KeyBindingEvent;
import org.getspout.spoutapi.keyboard.BindingExecutionDelegate;
import org.getspout.spoutapi.keyboard.Keyboard;


/**
* WorkingSlots for CraftBukkit/Spout
*
* handels the keybinding functions of spoutcraft
* 
* @author Bootscreen
*
*/

public class Listener implements BindingExecutionDelegate
{
	private FileManager fileManager = new FileManager();

    
	@Override
	public void keyPressed(KeyBindingEvent arg0)
	{
        if (!arg0.getPlayer().isSpoutCraftEnabled()) return;
		if(arg0.getBinding().getDefaultKey() == Keyboard.KEY_NUMPAD0)
		{
			fileManager.loadPreset(arg0.getPlayer(),0);
		}
		if(arg0.getBinding().getDefaultKey() == Keyboard.KEY_NUMPAD1)
		{
			fileManager.loadPreset(arg0.getPlayer(),1);
		}
		if(arg0.getBinding().getDefaultKey() == Keyboard.KEY_NUMPAD2)
		{
			fileManager.loadPreset(arg0.getPlayer(),2);
		}
		if(arg0.getBinding().getDefaultKey() == Keyboard.KEY_NUMPAD3)
		{
			fileManager.loadPreset(arg0.getPlayer(),3);
		}
		if(arg0.getBinding().getDefaultKey() == Keyboard.KEY_NUMPAD4)
		{
			fileManager.loadPreset(arg0.getPlayer(),4);
		}
		if(arg0.getBinding().getDefaultKey() == Keyboard.KEY_NUMPAD5)
		{
			fileManager.loadPreset(arg0.getPlayer(),5);
		}
		if(arg0.getBinding().getDefaultKey() == Keyboard.KEY_NUMPAD6)
		{
			fileManager.loadPreset(arg0.getPlayer(),6);
		}
		if(arg0.getBinding().getDefaultKey() == Keyboard.KEY_NUMPAD7)
		{
			fileManager.loadPreset(arg0.getPlayer(),7);
		}
		if(arg0.getBinding().getDefaultKey() == Keyboard.KEY_NUMPAD8)
		{
			fileManager.loadPreset(arg0.getPlayer(),8);
		}
		if(arg0.getBinding().getDefaultKey() == Keyboard.KEY_NUMPAD9)
		{
			fileManager.loadPreset(arg0.getPlayer(),9);
		}
	}

	@Override
	public void keyReleased(KeyBindingEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}