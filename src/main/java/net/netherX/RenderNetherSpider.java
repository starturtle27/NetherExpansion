//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package net.netherX;

import net.minecraft.client.renderer.entity.*;
import cpw.mods.fml.relauncher.*;
import net.minecraft.util.*;
import org.lwjgl.opengl.*;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.*;

@SideOnly(Side.CLIENT)
public class RenderNetherSpider extends RenderSpider
{
    private static final ResourceLocation netherSpiderTextures;
    
    public RenderNetherSpider() {
        this.shadowSize *= 1.3f;
    }
    
    protected void scaleSpider(final EntityNetherSpider par1EntityNetherSpider, final float par2) {
        GL11.glScalef(1.3f, 1.2f, 1.3f);
    }
    
    protected ResourceLocation getNetherSpiderTextures(final EntityNetherSpider par1EntityNetherSpider) {
        return RenderNetherSpider.netherSpiderTextures;
    }
    
    protected ResourceLocation getSpiderTextures(final EntitySpider par1EntitySpider) {
        return this.getNetherSpiderTextures((EntityNetherSpider)par1EntitySpider);
    }
    
    protected void preRenderCallback(final EntityLivingBase par1EntityLivingBase, final float par2) {
        this.scaleSpider((EntityNetherSpider)par1EntityLivingBase, par2);
    }
    
    protected ResourceLocation getEntityTexture(final Entity par1Entity) {
        return this.getNetherSpiderTextures((EntityNetherSpider)par1Entity);
    }
    
    static {
        netherSpiderTextures = new ResourceLocation("textures/entity/nethermobs/netherspider.png");
    }
}
