//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package net.netherX;

import net.minecraft.block.*;
import net.minecraft.block.material.*;
import net.minecraft.world.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import java.util.*;

public class BlockTrappedNetherrack extends Block
{
    public BlockTrappedNetherrack(final int par1, final Material par2Material) {
        super(par1, par2Material);
    }
    
    public void onEntityWalking(final World par1World, final int par2, final int par3, final int par4, final Entity par5Entity) {
        if (par5Entity instanceof EntityPlayer) {
            final Random rand = new Random();
            final Block block = Block.sand;
            for (int j1 = 0; j1 < 30; ++j1) {
                final double d0 = rand.nextGaussian() * 0.02;
                final double d2 = rand.nextGaussian() * 0.02;
                final double d3 = rand.nextGaussian() * 0.02;
                par1World.playSoundEffect((double)(par2 + 0.5f), (double)(par3 + 0.5f), (double)(par4 + 0.5f), block.stepSound.getStepSound(), (block.stepSound.getVolume() + 1.0f) / 2.0f, block.stepSound.getPitch() * 0.8f);
                par1World.spawnParticle("cloud", (double)(par2 + rand.nextFloat()), par3 + rand.nextFloat() * block.getBlockBoundsMaxY(), (double)(par4 + rand.nextFloat()), d0, d2, d3);
            }
            par1World.setBlockToAir(par2, par3, par4);
        }
    }
}
