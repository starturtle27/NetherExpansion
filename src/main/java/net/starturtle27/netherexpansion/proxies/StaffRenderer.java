//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package net.netherX.proxies;

import net.minecraftforge.client.*;
import net.minecraft.client.*;
import net.minecraft.item.*;
import org.lwjgl.opengl.*;
import net.netherX.*;
import net.minecraft.client.renderer.*;
import net.minecraft.util.*;

public class StaffRenderer implements IItemRenderer
{
    private Minecraft mc;
    
    public boolean handleRenderType(final ItemStack item, final IItemRenderer.ItemRenderType type) {
        return type == IItemRenderer.ItemRenderType.EQUIPPED;
    }
    
    public boolean shouldUseRenderHelper(final IItemRenderer.ItemRenderType type, final ItemStack item, final IItemRenderer.ItemRendererHelper helper) {
        return false;
    }
    
    public void renderItem(final IItemRenderer.ItemRenderType type, final ItemStack item, final Object[] data) {
        final Tessellator t = Tessellator.instance;
        GL11.glPushMatrix();
        GL11.glTranslatef(-0.5f, -0.5f, 0.0f);
        GL11.glScalef(2.0f, 2.0f, 1.0f);
        final Icon icon = ItemInfernoStaff.iconStaffHeld;
        ItemRenderer.renderItemIn2D(t, icon.getMaxU(), icon.getMinV(), icon.getMinU(), icon.getMaxV(), icon.getIconWidth(), icon.getIconHeight(), 0.0625f);
        GL11.glPopMatrix();
    }
}
