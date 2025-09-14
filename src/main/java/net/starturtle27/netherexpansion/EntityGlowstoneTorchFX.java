//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package net.netherX;

import net.minecraft.client.particle.*;
import cpw.mods.fml.relauncher.*;
import net.minecraft.world.*;
import net.minecraft.client.renderer.*;

@SideOnly(Side.CLIENT)
public class EntityGlowstoneTorchFX extends EntityFX
{
    float reddustParticleScale;
    
    public EntityGlowstoneTorchFX(final World par1World, final double par2, final double par4, final double par6, final float par8, final float par9, final float par10) {
        this(par1World, par2, par4, par6, 1.0f, par8, par9, par10);
    }
    
    public EntityGlowstoneTorchFX(final World par1World, final double par2, final double par4, final double par6, final float par8, float par9, final float par10, final float par11) {
        super(par1World, par2, par4, par6, 0.0, 0.0, 0.0);
        this.motionX *= 0.1000000014901161;
        this.motionY *= 0.1000000014901161;
        this.motionZ *= 0.1000000014901161;
        if (par9 == 0.0f) {
            par9 = 1.0f;
        }
        final float f4 = (float)Math.random() * 0.4f + 0.6f;
        this.particleRed = ((float)(Math.random() * 0.2000000029802322) + 0.8f) * par9 * f4;
        this.particleGreen = ((float)(Math.random() * 0.2000000029802322) + 0.8f) * par9 * f4;
        this.particleBlue = 0.0f;
        this.particleScale *= 0.75f;
        this.particleScale *= par8;
        this.reddustParticleScale = this.particleScale;
        this.particleMaxAge = (int)(8.0 / (Math.random() * 0.8 + 0.2));
        this.particleMaxAge *= (int)par8;
        this.noClip = false;
    }
    
    public void renderParticle(final Tessellator par1Tessellator, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
        float f6 = (this.particleAge + par2) / this.particleMaxAge * 32.0f;
        if (f6 < 0.0f) {
            f6 = 0.0f;
        }
        if (f6 > 1.0f) {
            f6 = 1.0f;
        }
        this.particleScale = this.reddustParticleScale * f6;
        super.renderParticle(par1Tessellator, par2, par3, par4, par5, par6, par7);
    }
    
    public void onUpdate() {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        if (this.particleAge++ >= this.particleMaxAge) {
            this.setDead();
        }
        this.setParticleTextureIndex(7 - this.particleAge * 8 / this.particleMaxAge);
        this.moveEntity(this.motionX, this.motionY, this.motionZ);
        if (this.posY == this.prevPosY) {
            this.motionX *= 1.1;
            this.motionZ *= 1.1;
        }
        this.motionX *= 0.9599999785423279;
        this.motionY *= 0.9599999785423279;
        this.motionZ *= 0.9599999785423279;
        if (this.onGround) {
            this.motionX *= 0.699999988079071;
            this.motionZ *= 0.699999988079071;
        }
    }
}
