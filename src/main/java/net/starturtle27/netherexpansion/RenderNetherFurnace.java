//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package net.netherX;

import net.minecraft.client.renderer.*;
import net.minecraft.world.*;
import net.minecraft.block.*;

public class RenderNetherFurnace extends RenderBlocks
{
    public RenderNetherFurnace(final IBlockAccess par1iBlockAccess) {
        super(par1iBlockAccess);
    }
    
    public RenderNetherFurnace() {
    }
    
    public void renderBlockAsItem(final Block par1Block, final int par2, final float par3) {
        super.renderBlockAsItem(par1Block, par2, 3.0f);
    }
}
