//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package net.netherX.proxies;

import net.minecraft.client.renderer.entity.*;
import cpw.mods.fml.relauncher.*;
import net.minecraft.client.model.*;
import net.netherX.*;
import org.lwjgl.opengl.*;
import net.minecraft.util.*;
import net.minecraft.entity.*;

@SideOnly(Side.CLIENT)
public class RenderObsidianBoat extends Render
{
    private static final ResourceLocation boatTextures;
    protected ModelBase modelBoat;
    
    public RenderObsidianBoat() {
        this.shadowSize = 0.5f;
        this.modelBoat = (ModelBase)new ModelBoat();
    }
    
    public void renderBoat(final EntityObsidianBoat par1EntityObsidianBoat, final double par2, final double par4, final double par6, final float par8, final float par9) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)par2, (float)par4, (float)par6);
        GL11.glRotatef(180.0f - par8, 0.0f, 1.0f, 0.0f);
        final float f2 = par1EntityObsidianBoat.getTimeSinceHit() - par9;
        float f3 = par1EntityObsidianBoat.getDamageTaken() - par9;
        if (f3 < 0.0f) {
            f3 = 0.0f;
        }
        if (f2 > 0.0f) {
            GL11.glRotatef(MathHelper.sin(f2) * f2 * f3 / 10.0f * par1EntityObsidianBoat.getForwardDirection(), 1.0f, 0.0f, 0.0f);
        }
        final float f4 = 0.75f;
        GL11.glScalef(f4, f4, f4);
        GL11.glScalef(1.0f / f4, 1.0f / f4, 1.0f / f4);
        this.bindEntityTexture((Entity)par1EntityObsidianBoat);
        GL11.glScalef(-1.0f, -1.0f, 1.0f);
        this.modelBoat.render((Entity)par1EntityObsidianBoat, 0.0f, 0.0f, -0.1f, 0.0f, 0.0f, 0.0625f);
        GL11.glPopMatrix();
    }
    
    protected ResourceLocation getBoatTextures(final EntityObsidianBoat par1EntityObsidianBoat) {
        return RenderObsidianBoat.boatTextures;
    }
    
    protected ResourceLocation getEntityTexture(final Entity par1Entity) {
        return this.getBoatTextures((EntityObsidianBoat)par1Entity);
    }
    
    public void doRender(final Entity par1Entity, final double par2, final double par4, final double par6, final float par8, final float par9) {
        this.renderBoat((EntityObsidianBoat)par1Entity, par2, par4, par6, par8, par9);
    }
    
    static {
        boatTextures = new ResourceLocation("textures/entity/obsidian_boat.png");
    }
}
