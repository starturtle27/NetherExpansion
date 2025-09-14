//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package net.netherX;

import net.minecraft.client.renderer.tileentity.*;
import cpw.mods.fml.relauncher.*;
import net.minecraft.client.model.*;
import org.lwjgl.opengl.*;
import net.minecraft.util.*;
import net.minecraft.entity.*;
import net.minecraft.tileentity.*;

@SideOnly(Side.CLIENT)
public class TileEntityCurseRenderer extends TileEntitySpecialRenderer
{
    private static final ResourceLocation enchantingTableBookTextures;
    private ModelBook enchantmentBook;
    
    public TileEntityCurseRenderer() {
        this.enchantmentBook = new ModelBook();
    }
    
    public void renderTileEntityEnchantmentTableAt(final TileEntityCurse par1TileEntityEnchantmentTable, final double par2, final double par4, final double par6, final float par8) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)par2 + 0.5f, (float)par4 + 0.75f, (float)par6 + 0.5f);
        final float f1 = par1TileEntityEnchantmentTable.tickCount + par8;
        GL11.glTranslatef(0.0f, 0.1f + MathHelper.sin(f1 * 0.1f) * 0.01f, 0.0f);
        float f2;
        for (f2 = par1TileEntityEnchantmentTable.bookRotation2 - par1TileEntityEnchantmentTable.bookRotationPrev; f2 >= 3.141593f; f2 -= 6.283186f) {}
        while (f2 < -3.141593f) {
            f2 += 6.283186f;
        }
        final float f3 = par1TileEntityEnchantmentTable.bookRotationPrev + f2 * par8;
        GL11.glRotatef(-f3 * 180.0f / 3.141593f, 0.0f, 1.0f, 0.0f);
        GL11.glRotatef(80.0f, 0.0f, 0.0f, 1.0f);
        this.bindTexture(TileEntityCurseRenderer.enchantingTableBookTextures);
        float f4 = par1TileEntityEnchantmentTable.pageFlipPrev + (par1TileEntityEnchantmentTable.pageFlip - par1TileEntityEnchantmentTable.pageFlipPrev) * par8 + 0.25f;
        float f5 = par1TileEntityEnchantmentTable.pageFlipPrev + (par1TileEntityEnchantmentTable.pageFlip - par1TileEntityEnchantmentTable.pageFlipPrev) * par8 + 0.75f;
        f4 = (f4 - MathHelper.truncateDoubleToInt((double)f4)) * 1.6f - 0.3f;
        f5 = (f5 - MathHelper.truncateDoubleToInt((double)f5)) * 1.6f - 0.3f;
        if (f4 < 0.0f) {
            f4 = 0.0f;
        }
        if (f5 < 0.0f) {
            f5 = 0.0f;
        }
        if (f4 > 1.0f) {
            f4 = 1.0f;
        }
        if (f5 > 1.0f) {
            f5 = 1.0f;
        }
        final float f6 = par1TileEntityEnchantmentTable.bookSpreadPrev + (par1TileEntityEnchantmentTable.bookSpread - par1TileEntityEnchantmentTable.bookSpreadPrev) * par8;
        GL11.glEnable(2884);
        this.enchantmentBook.render((Entity)null, f1, f4, f5, f6, 0.0f, 0.0625f);
        GL11.glPopMatrix();
    }
    
    public void renderTileEntityAt(final TileEntity par1TileEntity, final double par2, final double par4, final double par6, final float par8) {
        this.renderTileEntityEnchantmentTableAt((TileEntityCurse)par1TileEntity, par2, par4, par6, par8);
    }
    
    static {
        enchantingTableBookTextures = new ResourceLocation("textures/entity/curse_table_book.png");
    }
}
