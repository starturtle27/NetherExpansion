//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package net.netherX.entity.render;

import net.minecraft.client.renderer.entity.*;
import cpw.mods.fml.relauncher.*;
import net.minecraft.util.*;
import net.netherX.*;
import org.lwjgl.opengl.*;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.*;

@SideOnly(Side.CLIENT)
public class RenderInfernoSpider extends RenderSpider
{
    private static final ResourceLocation infernoSpiderTextures;
    
    public RenderInfernoSpider() {
        this.shadowSize *= 0.5f;
    }
    
    protected void scaleSpider(final EntityInfernoSpider par1EntityInfernoSpider, final float par2) {
        GL11.glScalef(0.5f, 0.5f, 0.5f);
    }
    
    protected ResourceLocation getInfernoSpiderTextures(final EntityInfernoSpider par1EntityInfernoSpider) {
        return RenderInfernoSpider.infernoSpiderTextures;
    }
    
    protected ResourceLocation getSpiderTextures(final EntitySpider par1EntitySpider) {
        return this.getInfernoSpiderTextures((EntityInfernoSpider)par1EntitySpider);
    }
    
    protected void preRenderCallback(final EntityLivingBase par1EntityLivingBase, final float par2) {
        this.scaleSpider((EntityInfernoSpider)par1EntityLivingBase, par2);
    }
    
    protected ResourceLocation getEntityTexture(final Entity par1Entity) {
        return this.getInfernoSpiderTextures((EntityInfernoSpider)par1Entity);
    }
    
    static {
        infernoSpiderTextures = new ResourceLocation("textures/entity/nethermobs/infernospider.png");
    }
}
