//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package net.netherX.proxies;

import java.util.*;
import cpw.mods.fml.common.*;
import net.minecraft.entity.player.*;
import net.netherX.*;

public class NXPlayerTickHandler implements ITickHandler
{
    private final EnumSet<TickType> ticksToGet;
    static int limit;
    
    public NXPlayerTickHandler(final EnumSet<TickType> ticksToGet) {
        this.ticksToGet = ticksToGet;
    }
    
    public void tickStart(final EnumSet<TickType> type, final Object[] tickData) {
        playerTick((EntityPlayer)tickData[0]);
    }
    
    public void tickEnd(final EnumSet<TickType> type, final Object[] tickData) {
    }
    
    public EnumSet<TickType> ticks() {
        return this.ticksToGet;
    }
    
    public String getLabel() {
        return "NXPlayerTick";
    }
    
    public static void playerTick(final EntityPlayer player) {
        if (NXCapeKeyBinding.isKeyPressed) {
            if (NXPlayerTickHandler.limit > 0) {
                player.addChatMessage("Toggled capes.");
                if (ClientProxyNX.capeEnabled) {
                    ClientProxyNX.capeEnabled = false;
                }
                else {
                    ClientProxyNX.capeEnabled = true;
                }
                --NXPlayerTickHandler.limit;
            }
        }
        else {
            NXPlayerTickHandler.limit = 1;
        }
    }
    
    static {
        NXPlayerTickHandler.limit = 1;
    }
}
