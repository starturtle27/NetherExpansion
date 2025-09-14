//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package net.netherX;

import net.minecraft.client.*;
import net.minecraft.world.*;
import net.minecraft.client.renderer.texture.*;
import net.minecraft.client.particle.*;

public class NXParticleEffects
{
    private static Minecraft mc;
    private static World theWorld;
    private static TextureManager renderEngine;
    
    public static EntityFX spawnParticle(final String particleName, final double par2, final double par4, final double par6, final double par8, final double par10, final double par12) {
        if (NXParticleEffects.mc == null || NXParticleEffects.mc.renderViewEntity == null || NXParticleEffects.mc.effectRenderer == null) {
            return null;
        }
        int var14 = NXParticleEffects.mc.gameSettings.particleSetting;
        if (var14 == 1 && NXParticleEffects.theWorld.rand.nextInt(3) == 0) {
            var14 = 2;
        }
        final double var15 = NXParticleEffects.mc.renderViewEntity.posX - par2;
        final double var16 = NXParticleEffects.mc.renderViewEntity.posY - par4;
        final double var17 = NXParticleEffects.mc.renderViewEntity.posZ - par6;
        EntityFX var18 = null;
        final double var19 = 16.0;
        if (var15 * var15 + var16 * var16 + var17 * var17 > var19 * var19) {
            return null;
        }
        if (var14 > 1) {
            return null;
        }
        if (particleName.equals("glowstonetorch")) {
            var18 = (EntityFX)new EntityGlowstoneTorchFX(NXParticleEffects.theWorld, par2, par4, par6, (float)par8, (float)par10, (float)par12);
        }
        NXParticleEffects.mc.effectRenderer.addEffect(var18);
        return var18;
    }
    
    static {
        NXParticleEffects.mc = Minecraft.getMinecraft();
        NXParticleEffects.theWorld = (World)NXParticleEffects.mc.theWorld;
        NXParticleEffects.renderEngine = NXParticleEffects.mc.renderEngine;
    }
}
