//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package net.netherX.worldgen;

import net.minecraft.world.gen.feature.*;
import net.minecraft.block.*;
import net.minecraft.world.*;
import java.util.*;
import net.minecraft.block.material.*;

public class WorldGenObsidian extends WorldGenerator
{
    private int obsidianBlockId;
    private int numberOfBlocks;
    
    public WorldGenObsidian(final int par1) {
        this.obsidianBlockId = Block.obsidian.blockID;
        this.numberOfBlocks = par1;
    }
    
    public boolean generate(final World par1World, final Random par2Random, final int par3, final int par4, final int par5) {
        if (par1World.getBlockMaterial(par3, par4, par5) != Material.lava) {
            return false;
        }
        final int l = par2Random.nextInt(this.numberOfBlocks - 2) + 2;
        final byte b0 = 1;
        for (int i1 = par3 - l; i1 <= par3 + l; ++i1) {
            for (int j1 = par5 - l; j1 <= par5 + l; ++j1) {
                final int k1 = i1 - par3;
                final int l2 = j1 - par5;
                if (k1 * k1 + l2 * l2 <= l * l) {
                    for (int i2 = par4 - b0; i2 <= par4 + b0; ++i2) {
                        final int j2 = par1World.getBlockId(i1, i2, j1);
                        if (j2 == Block.netherrack.blockID || j2 == Block.obsidian.blockID) {
                            par1World.setBlock(i1, i2, j1, this.obsidianBlockId, 0, 2);
                        }
                    }
                }
            }
        }
        return true;
    }
}
