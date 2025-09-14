//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package net.netherX;

import net.minecraft.block.*;
import java.util.*;

public class BlockIchorOre extends BlockOre
{
    public BlockIchorOre(final int par1) {
        super(par1);
    }
    
    public int idDropped(final int par1, final Random par2Random, final int par3) {
        return NetherX.ichoriteShard.itemID;
    }
}
