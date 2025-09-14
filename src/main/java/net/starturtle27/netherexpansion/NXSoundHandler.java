//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package net.netherX;

import net.minecraftforge.client.event.sound.*;
import net.minecraftforge.event.*;

public class NXSoundHandler
{
    @ForgeSubscribe
    public void onSound(final SoundLoadEvent event) {
        event.manager.addSound("netherx:brimspell.ogg");
        event.manager.addSound("netherx:brimspelldeath.ogg");
    }
}
