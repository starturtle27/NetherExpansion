//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package net.netherX;

import cpw.mods.fml.client.registry.*;
import net.minecraft.world.*;
import net.minecraft.client.renderer.*;
import net.minecraft.block.*;
import net.minecraft.util.*;

public class RenderCrystalCauldron implements ISimpleBlockRenderingHandler
{
    public void renderInventoryBlock(final Block block, final int metadata, final int modelID, final RenderBlocks renderer) {
    }
    
    public boolean renderWorldBlock(final IBlockAccess blockAccess, final int par2, final int par3, final int par4, final Block par1BlockCrystalCauldron, final int modelId, final RenderBlocks renderer) {
        renderer.renderStandardBlock(par1BlockCrystalCauldron, par2, par3, par4);
        final Tessellator tessellator = Tessellator.instance;
        tessellator.setBrightness(par1BlockCrystalCauldron.getMixedBrightnessForBlock(blockAccess, par2, par3, par4));
        final float f = 1.0f;
        final int l = par1BlockCrystalCauldron.colorMultiplier(blockAccess, par2, par3, par4);
        float f2 = (l >> 16 & 0xFF) / 255.0f;
        float f3 = (l >> 8 & 0xFF) / 255.0f;
        float f4 = (l & 0xFF) / 255.0f;
        if (EntityRenderer.anaglyphEnable) {
            final float f5 = (f2 * 30.0f + f3 * 59.0f + f4 * 11.0f) / 100.0f;
            final float f6 = (f2 * 30.0f + f3 * 70.0f) / 100.0f;
            final float f7 = (f2 * 30.0f + f4 * 70.0f) / 100.0f;
            f2 = f5;
            f3 = f6;
            f4 = f7;
        }
        tessellator.setColorOpaque_F(f * f2, f * f3, f * f4);
        final Icon icon = par1BlockCrystalCauldron.getBlockTextureFromSide(2);
        final float f6 = 0.125f;
        renderer.renderFaceXPos(par1BlockCrystalCauldron, (double)(par2 - 1.0f + f6), (double)par3, (double)par4, icon);
        renderer.renderFaceXNeg(par1BlockCrystalCauldron, (double)(par2 + 1.0f - f6), (double)par3, (double)par4, icon);
        renderer.renderFaceZPos(par1BlockCrystalCauldron, (double)par2, (double)par3, (double)(par4 - 1.0f + f6), icon);
        renderer.renderFaceZNeg(par1BlockCrystalCauldron, (double)par2, (double)par3, (double)(par4 + 1.0f - f6), icon);
        final Icon icon2 = BlockCrystalCauldron.getCrystalCauldronIcon("inner");
        renderer.renderFaceYPos(par1BlockCrystalCauldron, (double)par2, (double)(par3 - 1.0f + 0.25f), (double)par4, icon2);
        renderer.renderFaceYNeg(par1BlockCrystalCauldron, (double)par2, (double)(par3 + 1.0f - 0.75f), (double)par4, icon2);
        int i1 = blockAccess.getBlockMetadata(par2, par3, par4);
        if (i1 > 0) {
            final Icon icon3 = BlockFluid.getFluidIcon("water_still");
            final Icon icon2_poison = BlockPoison.poison;
            if (i1 > 3) {
                i1 = 3;
            }
            if (par1BlockCrystalCauldron.blockID == NetherX.poisoncrystalcauldron.blockID) {
                renderer.renderFaceYPos(par1BlockCrystalCauldron, (double)par2, (double)(par3 - 1.0f + (6.0f + i1 * 3.0f) / 16.0f), (double)par4, icon2_poison);
            }
            else {
                renderer.renderFaceYPos(par1BlockCrystalCauldron, (double)par2, (double)(par3 - 1.0f + (6.0f + i1 * 3.0f) / 16.0f), (double)par4, icon3);
            }
        }
        return true;
    }
    
    public boolean shouldRender3DInInventory() {
        return false;
    }
    
    public int getRenderId() {
        return 0;
    }
}
