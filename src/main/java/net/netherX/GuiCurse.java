//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package net.netherX;

import net.minecraft.client.gui.inventory.*;
import cpw.mods.fml.relauncher.*;
import net.minecraft.util.*;
import net.minecraft.entity.player.*;
import net.minecraft.inventory.*;
import net.minecraft.client.resources.*;
import org.lwjgl.opengl.*;

@SideOnly(Side.CLIENT)
public class GuiCurse extends GuiContainer
{
    private static final ResourceLocation furnaceGuiTextures;
    private static final ResourceLocation bookOverlay;
    private TileEntityCurse furnaceInventory;
    
    public GuiCurse(final InventoryPlayer par1InventoryPlayer, final TileEntityCurse par2TileEntityCurse) {
        super((Container)new ContainerCurse(par1InventoryPlayer, par2TileEntityCurse));
        this.furnaceInventory = par2TileEntityCurse;
    }
    
    protected void drawGuiContainerForegroundLayer(final int par1, final int par2) {
        final String s = this.furnaceInventory.isInvNameLocalized() ? this.furnaceInventory.getInvName() : I18n.getString(this.furnaceInventory.getInvName());
        this.fontRenderer.drawString(s, this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 6, 4210752);
        this.fontRenderer.drawString(I18n.getString("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
    }
    
    protected void drawGuiContainerBackgroundLayer(final float par1, final int par2, final int par3) {
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.mc.getTextureManager().bindTexture(GuiCurse.furnaceGuiTextures);
        final int k = (this.width - this.xSize) / 2;
        final int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
        if (this.furnaceInventory.isBurning()) {
            final int i1 = this.furnaceInventory.getBurnTimeRemainingScaled(12);
            this.drawTexturedModalRect(k + 56, l + 36 + 12 - i1, 176, 12 - i1, 14, i1 + 2);
        }
        final int i1 = this.furnaceInventory.getCookProgressScaled(24);
        this.drawTexturedModalRect(k + 79, l + 34, 176, 14, i1 + 1, 16);
    }
    
    static {
        furnaceGuiTextures = new ResourceLocation("textures/gui/container/curse.png");
        bookOverlay = new ResourceLocation("textures/items/book_cursed_outline.png");
    }
}
