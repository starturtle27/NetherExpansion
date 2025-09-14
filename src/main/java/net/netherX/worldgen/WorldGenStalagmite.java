//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package net.netherX.worldgen;

import net.minecraft.world.gen.feature.*;
import net.minecraft.world.*;
import java.util.*;
import net.minecraft.block.*;
import net.netherX.biomes.*;
import net.minecraft.util.*;
import net.minecraft.block.material.*;

public class WorldGenStalagmite extends WorldGenerator
{
    private static final String __OBFID = "CL_00000417";
    
    public boolean generate(final World par1World, final Random par2Random, final int par3, int par4, final int par5) {
        while (par1World.isAirBlock(par3, par4, par5) && par4 > 2) {
            --par4;
        }
        if (par1World.getBlockId(par3, par4, par5) != Block.netherrack.blockID && par1World.getBlockId(par3, par4 - 1, par5) != Block.netherrack.blockID && par1World.getBlockId(par3, par4 - 2, par5) != Block.netherrack.blockID && par1World.getBiomeGenForCoords(par3, par5) != BiomeGenBaseNether.stalactite) {
            return false;
        }
        par4 += par2Random.nextInt(4);
        final int l = par2Random.nextInt(4) + 7;
        final int i1 = l / 4 + par2Random.nextInt(2);
        if (i1 > 1 && par2Random.nextInt(60) == 0) {
            par4 += 10 + par2Random.nextInt(30);
        }
        for (int j1 = 0; j1 < l; ++j1) {
            final float f = (1.0f - j1 / (float)l) * i1;
            for (int k1 = MathHelper.ceiling_float_int(f), l2 = -k1; l2 <= k1; ++l2) {
                final float f2 = MathHelper.abs_int(l2) - 0.25f;
                for (int i2 = -k1; i2 <= k1; ++i2) {
                    final float f3 = MathHelper.abs_int(i2) - 0.25f;
                    if (((l2 == 0 && i2 == 0) || f2 * f2 + f3 * f3 <= f * f) && ((l2 != -k1 && l2 != k1 && i2 != -k1 && i2 != k1) || par2Random.nextFloat() <= 0.75f)) {
                        Block block = Block.blocksList[par1World.getBlockId(par3 + l2, par4 + j1, par5 + i2)];
                        if (par1World.getBlockMaterial(par3 + l2, par4 + j1, par5 + i2) == Material.air || block == Block.netherrack) {
                            this.setBlock(par1World, par3 + l2, par4 + j1, par5 + i2, Block.netherrack.blockID);
                        }
                        if (j1 != 0 && k1 > 1) {
                            block = Block.blocksList[par1World.getBlockId(par3 + l2, par4 - j1, par5 + i2)];
                            if (par1World.getBlockMaterial(par3 + l2, par4 - j1, par5 + i2) == Material.air || block == Block.netherrack) {
                                this.setBlock(par1World, par3 + l2, par4 - j1, par5 + i2, Block.netherrack.blockID);
                            }
                        }
                    }
                }
            }
        }
        int j1 = i1 - 1;
        if (j1 < 0) {
            j1 = 0;
        }
        else if (j1 > 1) {
            j1 = 1;
        }
        for (int j2 = -j1; j2 <= j1; ++j2) {
            for (int k1 = -j1; k1 <= j1; ++k1) {
                int l2 = par4 - 1;
                int k2 = 50;
                if (Math.abs(j2) == 1 && Math.abs(k1) == 1) {
                    k2 = par2Random.nextInt(5);
                }
                while (l2 > 50) {
                    final Block block2 = Block.blocksList[par1World.getBlockId(par3 + j2, l2, par5 + k1)];
                    if (par1World.getBlockMaterial(par3 + j2, l2, par5 + k1) != Material.air && block2 != Block.netherrack) {
                        break;
                    }
                    this.setBlock(par1World, par3 + j2, l2, par5 + k1, Block.netherrack.blockID);
                    --l2;
                    if (--k2 > 0) {
                        continue;
                    }
                    l2 -= par2Random.nextInt(5) + 1;
                    k2 = par2Random.nextInt(5);
                }
            }
        }
        return true;
    }
}
