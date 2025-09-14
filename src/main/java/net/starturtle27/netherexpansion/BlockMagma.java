//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package net.netherX;

import net.minecraft.block.material.*;
import net.minecraft.creativetab.*;
import cpw.mods.fml.relauncher.*;
import net.minecraft.entity.player.*;
import net.minecraft.stats.*;
import net.minecraft.enchantment.*;
import net.minecraft.entity.*;
import net.minecraft.block.*;
import net.minecraft.item.*;
import java.util.*;
import net.minecraft.world.*;

public class BlockMagma extends BlockBreakable
{
    public BlockMagma(final int par1) {
        super(par1, "magma", Material.clay, false);
        this.setCreativeTab(CreativeTabs.tabBlock);
    }
    
    @SideOnly(Side.CLIENT)
    public int getRenderBlockPass() {
        return 1;
    }
    
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(final IBlockAccess par1IBlockAccess, final int par2, final int par3, final int par4, final int par5) {
        return super.shouldSideBeRendered(par1IBlockAccess, par2, par3, par4, 1 - par5);
    }
    
    public void harvestBlock(final World par1World, final EntityPlayer par2EntityPlayer, final int par3, final int par4, final int par5, final int par6) {
        par2EntityPlayer.addStat(StatList.mineBlockStatArray[this.blockID], 1);
        par2EntityPlayer.addExhaustion(0.025f);
        if (this.canSilkHarvest() && EnchantmentHelper.getSilkTouchModifier((EntityLivingBase)par2EntityPlayer)) {
            final ItemStack itemstack = this.createStackedBlock(par6);
            if (itemstack != null) {
                this.dropBlockAsItem_do(par1World, par3, par4, par5, itemstack);
            }
        }
        else {
            final int i1 = EnchantmentHelper.getFortuneModifier((EntityLivingBase)par2EntityPlayer);
            this.dropBlockAsItem(par1World, par3, par4, par5, par6, i1);
            final Material material = par1World.getBlockMaterial(par3, par4 - 1, par5);
            if (material.blocksMovement() || material.isLiquid()) {
                par1World.setBlock(par3, par4, par5, Block.lavaMoving.blockID);
            }
        }
    }
    
    public int quantityDropped(final Random par1Random) {
        return 0;
    }
    
    public int getMobilityFlag() {
        return 0;
    }
    
    public void onBlockDestroyedByExplosion(final World par1World, final int par2, final int par3, final int par4, final Explosion par5Explosion) {
        this.dropBlockAsItem_do(par1World, par2, par3, par4, new ItemStack(NetherX.magmaCrystal));
    }
}
