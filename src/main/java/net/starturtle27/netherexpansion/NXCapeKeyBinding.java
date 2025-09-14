//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package net.netherX;

import cpw.mods.fml.client.registry.*;
import net.minecraft.client.*;
import java.util.*;
import net.minecraft.client.settings.*;
import cpw.mods.fml.common.*;

public class NXCapeKeyBinding extends KeyBindingRegistry.KeyHandler
{
    public static boolean isKeyPressed;
    private static Minecraft mc;
    private EnumSet tickTypes;
    
    public NXCapeKeyBinding(final KeyBinding[] keyBindings, final boolean[] repeatings) {
        super(keyBindings, repeatings);
        this.tickTypes = EnumSet.of(TickType.CLIENT);
    }
    
    public String getLabel() {
        return "NXCapeToggler";
    }
    
    public void keyDown(final EnumSet<TickType> types, final KeyBinding kb, final boolean tickEnd, final boolean isRepeat) {
        if (NXCapeKeyBinding.mc.currentScreen == null) {
            NXCapeKeyBinding.isKeyPressed = true;
        }
    }
    
    public void keyUp(final EnumSet<TickType> types, final KeyBinding kb, final boolean tickEnd) {
        NXCapeKeyBinding.isKeyPressed = false;
    }
    
    public EnumSet<TickType> ticks() {
        return (EnumSet<TickType>)this.tickTypes;
    }
    
    static {
        NXCapeKeyBinding.isKeyPressed = false;
        NXCapeKeyBinding.mc = Minecraft.getMinecraft();
    }
}
