//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package net.netherX;

import net.minecraft.client.renderer.entity.*;
import net.minecraft.util.*;
import net.minecraft.client.model.*;
import org.lwjgl.opengl.*;
import net.minecraft.entity.*;

public class RenderGhastQueen extends RenderLiving
{
    private static final ResourceLocation ghastqueenTextures;
    private static final ResourceLocation ghastqueenShootingTextures;
    
    public RenderGhastQueen() {
        super((ModelBase)new ModelGhast(), 0.5f);
    }
    
    protected ResourceLocation func_110867_a(final EntityGhastQueen par1EntityGhastQueen) {
        return par1EntityGhastQueen.func_110182_bF() ? RenderGhastQueen.ghastqueenShootingTextures : RenderGhastQueen.ghastqueenTextures;
    }
    
    protected void preRenderGhast(final EntityGhastQueen par1EntityGhastQueen, final float par2) {
        float f1 = (par1EntityGhastQueen.prevAttackCounter + (par1EntityGhastQueen.attackCounter - par1EntityGhastQueen.prevAttackCounter) * par2) / 20.0f;
        if (f1 < 0.0f) {
            f1 = 0.0f;
        }
        f1 = 1.0f / (f1 * f1 * f1 * f1 * f1 * 2.0f + 1.0f);
        final float f2 = (8.0f + f1) / 2.0f;
        final float f3 = (8.0f + 1.0f / f1) / 2.0f;
        GL11.glScalef(f3, f2, f3);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
    }
    
    protected void preRenderCallback(final EntityLivingBase par1EntityLivingBase, final float par2) {
        this.preRenderGhast((EntityGhastQueen)par1EntityLivingBase, par2);
    }
    
    protected ResourceLocation getEntityTexture(final Entity par1Entity) {
        return this.func_110867_a((EntityGhastQueen)par1Entity);
    }
    
    static {
        ghastqueenTextures = new ResourceLocation("textures/entity/nethermobs/ghastqueen.png");
        ghastqueenShootingTextures = new ResourceLocation("textures/entity/nethermobs/ghastqueen_shooting.png");
    }
}
