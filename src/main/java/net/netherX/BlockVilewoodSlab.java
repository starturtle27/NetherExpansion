//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package net.netherX;

import net.minecraft.block.*;
import net.minecraft.block.material.*;
import java.util.*;
import net.minecraft.world.*;

public class BlockVilewoodSlab extends BlockHalfSlab
{
    public BlockVilewoodSlab(final int par1, final boolean par2, final Material par3Material) {
        super(par1, par2, par3Material);
        BlockVilewoodSlab.useNeighborBrightness[this.blockID] = true;
    }
    
    public String getFullSlabName(final int i) {
        return super.getUnlocalizedName();
    }
    
    public int idDropped(final int par1, final Random par2Random, final int par3) {
        return NetherX.vilewoodSingleSlab.blockID;
    }
    
    public int idPicked(final World par1World, final int par2, final int par3, final int par4) {
        return NetherX.vilewoodSingleSlab.blockID;
    }
}
