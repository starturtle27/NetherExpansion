//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package net.netherX;

import net.minecraft.block.*;
import net.minecraft.world.*;
import java.util.*;

public class BlockNetherTorch extends BlockTorch
{
    protected BlockNetherTorch(final int par1) {
        super(par1);
        this.setLightValue(1.0f);
    }
    
    public void randomDisplayTick(final World par1World, final int par2, final int par3, final int par4, final Random par5Random) {
        final int l = par1World.getBlockMetadata(par2, par3, par4);
        final double d0 = par2 + 0.5f + (par5Random.nextFloat() - 0.5f) * 0.2;
        final double d2 = par3 + 0.7f + (par5Random.nextFloat() - 0.5f) * 0.2;
        final double d3 = par4 + 0.5f + (par5Random.nextFloat() - 0.5f) * 0.2;
        final double d4 = 0.219999998807907;
        final double d5 = 0.2700000107288361;
        if (l == 1) {
            NXParticleEffects.spawnParticle("glowstonetorch", d0 - d5, d2 + d4, d3, 1.0, 0.0, 0.0);
        }
        else if (l == 2) {
            NXParticleEffects.spawnParticle("glowstonetorch", d0 + d5, d2 + d4, d3, 1.0, 0.0, 0.0);
        }
        else if (l == 3) {
            NXParticleEffects.spawnParticle("glowstonetorch", d0, d2 + d4, d3 - d5, 1.0, 0.0, 0.0);
        }
        else if (l == 4) {
            NXParticleEffects.spawnParticle("glowstonetorch", d0, d2 + d4, d3 + d5, 1.0, 0.0, 0.0);
        }
        else {
            NXParticleEffects.spawnParticle("glowstonetorch", d0, d2, d3, 0.0, 0.0, 0.0);
        }
    }
}
