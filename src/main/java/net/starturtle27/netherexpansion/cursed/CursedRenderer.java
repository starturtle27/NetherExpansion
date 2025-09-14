package net.netherX.cursed;

import net.minecraftforge.client.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.item.*;
import net.minecraft.entity.*;
import net.minecraft.util.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.*;
import net.minecraft.client.renderer.*;

public class CursedRenderer implements IItemRenderer
{
    private static final ResourceLocation itemGlint;
    private static RenderItem renderItem;
    
    public boolean handleRenderType(final ItemStack item, final IItemRenderer.ItemRenderType type) {
        return false;
    }
    
    public boolean shouldUseRenderHelper(final IItemRenderer.ItemRenderType type, final ItemStack item, final IItemRenderer.ItemRendererHelper helper) {
        return false;
    }
    
    public void renderItem(final IItemRenderer.ItemRenderType type, final ItemStack item, final Object[] data) {
        if (type == IItemRenderer.ItemRenderType.EQUIPPED) {
            final EntityLivingBase p = (EntityLivingBase)data[1];
            this.render(p, item, 0);
        }
        if (type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON) {
            final EntityLivingBase p = (EntityLivingBase)data[1];
            this.renderFirstPerson(p, item, 0);
        }
    }
    
    protected void renderFirstPerson(final EntityLivingBase player, final ItemStack itemstack, final int par3) {
        this.render(player, itemstack, par3);
    }
    
    protected void render(final EntityLivingBase player, final ItemStack itemstack, final int par3) {
        doRenderItem(player, itemstack, par3);
    }
    
    public static void doRenderItem(final EntityLivingBase entity, final ItemStack itemstack, final int color) {
        doRenderItem(entity, itemstack, color, false);
    }
    
    public static void doRenderItem(final EntityLivingBase entity, final ItemStack itemstack, final int color, final boolean effect) {
        final Icon icon = entity.getItemIcon(itemstack, color);
        doRenderItem(icon, itemstack, color, effect);
    }
    
    public static void doRenderItem(final Icon icon, final ItemStack itemstack, final int effectColor, final boolean effect) {
        if (icon == null) {
            GL11.glPopMatrix();
            return;
        }
        final Minecraft mc = Minecraft.getMinecraft();
        final ResourceLocation resourcelocation = mc.renderEngine.getResourceLocation(itemstack.getItemSpriteNumber());
        mc.renderEngine.bindTexture(resourcelocation);
        GL11.glPushMatrix();
        final Tessellator tessellator = Tessellator.instance;
        final float f = icon.getMinU();
        final float f2 = icon.getMaxU();
        final float f3 = icon.getMinV();
        final float f4 = icon.getMaxV();
        final float f5 = 0.0f;
        final float f6 = 0.3f;
        ItemRenderer.renderItemIn2D(tessellator, f2, f3, f, f4, icon.getIconWidth(), icon.getIconHeight(), 0.0625f);
        if (itemstack != null && (itemstack.hasEffect() || effect)) {
            float red;
            float green;
            float blue;
            if (effect) {
                red = (float)(((effectColor & 0xFF0000) >> 16) / 255.0);
                green = (float)(((effectColor & 0xFF00) >> 8) / 255.0);
                blue = (float)((effectColor & 0xFF) / 255.0);
            }
            else {
                final float f7 = 0.76f;
                red = 1.3f * f7;
                blue = 0.25f * f7;
                green = 0.25f * f7;
            }
            GL11.glDepthFunc(514);
            GL11.glDisable(2896);
            mc.renderEngine.bindTexture(CursedRenderer.itemGlint);
            GL11.glEnable(3042);
            GL11.glBlendFunc(768, 1);
            GL11.glColor4f(red, green, blue, 0.5f);
            GL11.glMatrixMode(5890);
            GL11.glPushMatrix();
            final float f8 = 0.125f;
            GL11.glScalef(f8, f8, f8);
            float f9 = Minecraft.getSystemTime() % 3000L / 3000.0f * 8.0f;
            GL11.glTranslatef(f9, 0.0f, 0.0f);
            GL11.glRotatef(-50.0f, 0.0f, 0.0f, 1.0f);
            ItemRenderer.renderItemIn2D(tessellator, 0.0f, 0.0f, 1.0f, 1.0f, 256, 256, 0.0625f);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glScalef(f8, f8, f8);
            f9 = Minecraft.getSystemTime() % 4873L / 4873.0f * 8.0f;
            GL11.glTranslatef(-f9, 0.0f, 0.0f);
            GL11.glRotatef(10.0f, 0.0f, 0.0f, 1.0f);
            ItemRenderer.renderItemIn2D(tessellator, 0.0f, 0.0f, 1.0f, 1.0f, 256, 256, 0.0625f);
            GL11.glPopMatrix();
            GL11.glMatrixMode(5888);
            GL11.glDisable(3042);
            GL11.glEnable(2896);
            GL11.glDepthFunc(515);
        }
        GL11.glDisable(32826);
        GL11.glPopMatrix();
    }
    
    static {
        itemGlint = new ResourceLocation("textures/misc/enchanted_item_glint.png");
        CursedRenderer.renderItem = new RenderItem();
    }
}
