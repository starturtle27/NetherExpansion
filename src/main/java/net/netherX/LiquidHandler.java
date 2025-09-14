//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package net.netherX;

import net.minecraftforge.client.event.*;
import net.minecraftforge.event.*;
import cpw.mods.fml.relauncher.*;

public class LiquidHandler
{
    @ForgeSubscribe
    @SideOnly(Side.CLIENT)
    public void textureHook(final TextureStitchEvent.Post event) {
        if (event.map.textureType == 0) {
            NetherX.poison.setIcons(NetherX.poisonLiquid.getBlockTextureFromSide(1));
        }
    }
}
