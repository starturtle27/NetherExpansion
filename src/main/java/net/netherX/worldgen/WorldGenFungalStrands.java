//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package net.netherX.worldgen;

import net.minecraft.world.gen.feature.*;
import net.minecraft.world.*;
import java.util.*;
import net.minecraft.block.*;
import net.netherX.*;
import net.minecraft.util.*;

public class WorldGenFungalStrands extends WorldGenerator
{
    public boolean generate(final World par1World, final Random par2Random, int par3, int par4, int par5) {
        final int l = par3;
        final int i1 = par5;
        while (par4 < 128) {
            if (par1World.isAirBlock(par3, par4, par5)) {
                for (int j1 = 2; j1 <= 5; ++j1) {
                    if (Block.vine.canPlaceBlockOnSide(par1World, par3, par4, par5, j1)) {
                        par1World.setBlock(par3, par4, par5, NetherX.mushroomVineBrown.blockID, 1 << Direction.facingToDirection[Facing.oppositeSide[j1]], 2);
                        break;
                    }
                }
            }
            else {
                par3 = l + par2Random.nextInt(4) - par2Random.nextInt(4);
                par5 = i1 + par2Random.nextInt(4) - par2Random.nextInt(4);
            }
            ++par4;
        }
        return true;
    }
}
