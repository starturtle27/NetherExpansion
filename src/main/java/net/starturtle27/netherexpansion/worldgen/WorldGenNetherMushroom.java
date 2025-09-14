//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package net.netherX.worldgen;

import net.minecraft.world.gen.feature.*;
import net.minecraft.world.*;
import java.util.*;
import net.minecraft.block.*;

public class WorldGenNetherMushroom extends WorldGenerator
{
    private int mushroomType;
    
    public WorldGenNetherMushroom(final int par1) {
        super(true);
        this.mushroomType = -1;
        this.mushroomType = par1;
    }
    
    public WorldGenNetherMushroom() {
        super(false);
        this.mushroomType = -1;
    }
    
    public boolean generate(final World par1World, final Random par2Random, final int par3, final int par4, final int par5) {
        int l = par2Random.nextInt(2);
        if (this.mushroomType >= 0) {
            l = this.mushroomType;
        }
        final int i1 = par2Random.nextInt(3) + 4;
        boolean flag = true;
        if (par4 < 1 || par4 + i1 + 1 >= 256) {
            return false;
        }
        for (int j1 = par4; j1 <= par4 + 1 + i1; ++j1) {
            byte b0 = 3;
            if (j1 <= par4 + 3) {
                b0 = 0;
            }
            for (int k1 = par3 - b0; k1 <= par3 + b0 && flag; ++k1) {
                for (int l2 = par5 - b0; l2 <= par5 + b0 && flag; ++l2) {
                    if (j1 >= 0 && j1 < 256) {
                        final int i2 = par1World.getBlockId(k1, j1, l2);
                        final Block block = Block.blocksList[i2];
                        if (block != null && !block.isAirBlock(par1World, k1, j1, l2) && !block.isLeaves(par1World, k1, j1, l2)) {
                            flag = false;
                        }
                    }
                    else {
                        flag = false;
                    }
                }
            }
        }
        if (!flag) {
            return false;
        }
        int j1 = par1World.getBlockId(par3, par4 - 1, par5);
        if (j1 != Block.slowSand.blockID && j1 != Block.netherrack.blockID && j1 != Block.mycelium.blockID) {
            return false;
        }
        int j2 = par4 + i1;
        if (l == 1) {
            j2 = par4 + i1 - 3;
        }
        for (int k1 = j2; k1 <= par4 + i1; ++k1) {
            int l2 = 1;
            if (k1 < par4 + i1) {
                ++l2;
            }
            if (l == 0) {
                l2 = 3;
            }
            for (int i2 = par3 - l2; i2 <= par3 + l2; ++i2) {
                for (int k2 = par5 - l2; k2 <= par5 + l2; ++k2) {
                    int l3 = 5;
                    if (i2 == par3 - l2) {
                        --l3;
                    }
                    if (i2 == par3 + l2) {
                        ++l3;
                    }
                    if (k2 == par5 - l2) {
                        l3 -= 3;
                    }
                    if (k2 == par5 + l2) {
                        l3 += 3;
                    }
                    if (l == 0 || k1 < par4 + i1) {
                        if (i2 == par3 - l2 || i2 == par3 + l2) {
                            if (k2 == par5 - l2) {
                                continue;
                            }
                            if (k2 == par5 + l2) {
                                continue;
                            }
                        }
                        if (i2 == par3 - (l2 - 1) && k2 == par5 - l2) {
                            l3 = 1;
                        }
                        if (i2 == par3 - l2 && k2 == par5 - (l2 - 1)) {
                            l3 = 1;
                        }
                        if (i2 == par3 + (l2 - 1) && k2 == par5 - l2) {
                            l3 = 3;
                        }
                        if (i2 == par3 + l2 && k2 == par5 - (l2 - 1)) {
                            l3 = 3;
                        }
                        if (i2 == par3 - (l2 - 1) && k2 == par5 + l2) {
                            l3 = 7;
                        }
                        if (i2 == par3 - l2 && k2 == par5 + (l2 - 1)) {
                            l3 = 7;
                        }
                        if (i2 == par3 + (l2 - 1) && k2 == par5 + l2) {
                            l3 = 9;
                        }
                        if (i2 == par3 + l2 && k2 == par5 + (l2 - 1)) {
                            l3 = 9;
                        }
                    }
                    if (l3 == 5 && k1 < par4 + i1) {
                        l3 = 0;
                    }
                    final Block block2 = Block.blocksList[par1World.getBlockId(i2, k1, k2)];
                    if ((l3 != 0 || par4 >= par4 + i1 - 1) && (block2 == null || block2.canBeReplacedByLeaves(par1World, i2, k1, k2))) {
                        this.setBlockAndMetadata(par1World, i2, k1, k2, Block.mushroomCapBrown.blockID + l, l3);
                    }
                }
            }
        }
        for (int k1 = 0; k1 < i1; ++k1) {
            final int l2 = par1World.getBlockId(par3, par4 + k1, par5);
            final Block block3 = Block.blocksList[l2];
            if (block3 == null || block3.canBeReplacedByLeaves(par1World, par3, par4 + k1, par5)) {
                this.setBlockAndMetadata(par1World, par3, par4 + k1, par5, Block.mushroomCapBrown.blockID + l, 10);
            }
        }
        return true;
    }
}
