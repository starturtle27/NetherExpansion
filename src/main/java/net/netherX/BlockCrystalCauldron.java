//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package net.netherX;

import net.minecraft.block.*;
import net.minecraft.util.*;
import cpw.mods.fml.relauncher.*;
import net.netherX.proxies.*;
import net.minecraft.client.renderer.texture.*;
import java.util.*;
import net.minecraft.world.*;
import net.minecraft.entity.item.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import cpw.mods.fml.client.registry.*;

public class BlockCrystalCauldron extends BlockCauldron
{
    @SideOnly(Side.CLIENT)
    private static Icon field_94378_a;
    @SideOnly(Side.CLIENT)
    private static Icon cauldronBottomIcon;
    @SideOnly(Side.CLIENT)
    private static Icon cauldronTopIcon;
    public static ClientProxyNX proxy;
    public static int renderID;
    int renderType;
    
    public BlockCrystalCauldron(final int par1) {
        super(par1);
        this.renderType = BlockCrystalCauldron.renderID;
    }
    
    public Icon getIcon(final int par1, final int par2) {
        return (par1 == 0) ? BlockCrystalCauldron.cauldronBottomIcon : ((par1 == 1) ? BlockCrystalCauldron.cauldronTopIcon : this.blockIcon);
    }
    
    public void registerIcons(final IconRegister par1IconRegister) {
        BlockCrystalCauldron.field_94378_a = par1IconRegister.registerIcon(this.getTextureName() + "_" + "inner");
        BlockCrystalCauldron.cauldronTopIcon = par1IconRegister.registerIcon(this.getTextureName() + "_top");
        BlockCrystalCauldron.cauldronBottomIcon = par1IconRegister.registerIcon(this.getTextureName() + "_" + "bottom");
        this.blockIcon = par1IconRegister.registerIcon(this.getTextureName() + "_side");
    }
    
    public int getRenderType() {
        return this.renderType;
    }
    
    public int idDropped(final int par1, final Random par2Random, final int par3) {
        return NetherX.itemcrystalcauldron.itemID;
    }
    
    @SideOnly(Side.CLIENT)
    public static Icon getCrystalCauldronIcon(final String par0Str) {
        return par0Str.equals("bottom") ? BlockCrystalCauldron.cauldronBottomIcon : (par0Str.equals("inner") ? BlockCrystalCauldron.field_94378_a : null);
    }
    
    public boolean onBlockActivated(final World par1World, final int par2, final int par3, final int par4, final EntityPlayer par5EntityPlayer, final int par6, final float par7, final float par8, final float par9) {
        if (par1World.isRemote) {
            return true;
        }
        final ItemStack itemstack = par5EntityPlayer.inventory.getCurrentItem();
        if (itemstack == null) {
            return true;
        }
        final int i1 = par1World.getBlockMetadata(par2, par3, par4);
        final int j1 = func_111045_h_(i1);
        if (itemstack.itemID == Item.bucketWater.itemID) {
            if (j1 < 3) {
                if (!par5EntityPlayer.capabilities.isCreativeMode) {
                    par5EntityPlayer.inventory.setInventorySlotContents(par5EntityPlayer.inventory.currentItem, new ItemStack(Item.bucketEmpty));
                }
                par1World.setBlockMetadata(par2, par3, par4, 3, 2);
                par1World.func_96440_m(par2, par3, par4, this.blockID);
            }
            return true;
        }
        if (itemstack.itemID == Item.glassBottle.itemID) {
            if (j1 > 0) {
                final ItemStack itemstack2 = new ItemStack((Item)Item.potion, 1, 0);
                if (!par5EntityPlayer.inventory.addItemStackToInventory(itemstack2)) {
                    par1World.spawnEntityInWorld((Entity)new EntityItem(par1World, par2 + 0.5, par3 + 1.5, par4 + 0.5, itemstack2));
                }
                else if (par5EntityPlayer instanceof EntityPlayerMP) {
                    ((EntityPlayerMP)par5EntityPlayer).sendContainerToPlayer(par5EntityPlayer.inventoryContainer);
                }
                final ItemStack itemStack = itemstack;
                --itemStack.stackSize;
                if (itemstack.stackSize <= 0) {
                    par5EntityPlayer.inventory.setInventorySlotContents(par5EntityPlayer.inventory.currentItem, (ItemStack)null);
                }
                par1World.setBlockMetadata(par2, par3, par4, j1 - 1, 2);
                par1World.func_96440_m(par2, par3, par4, this.blockID);
            }
        }
        else {
            if (j1 > 0 && itemstack.getItem() instanceof ItemArmor && ((ItemArmor)itemstack.getItem()).getArmorMaterial() == EnumArmorMaterial.CLOTH) {
                final ItemArmor itemarmor = (ItemArmor)itemstack.getItem();
                itemarmor.removeColor(itemstack);
                par1World.setBlockMetadata(par2, par3, par4, j1 - 1, 2);
                par1World.func_96440_m(par2, par3, par4, this.blockID);
                return true;
            }
            if (itemstack.itemID == NetherX.poisonbucketPoison.itemID) {
                if (j1 < 3) {
                    if (!par5EntityPlayer.capabilities.isCreativeMode) {
                        par5EntityPlayer.inventory.setInventorySlotContents(par5EntityPlayer.inventory.currentItem, new ItemStack(NetherX.poisonbucketEmpty));
                    }
                    par1World.setBlock(par2, par3, par4, NetherX.poisoncrystalcauldron.blockID);
                    par1World.setBlockMetadata(par2, par3, par4, 3, 2);
                }
                return true;
            }
        }
        return true;
    }
    
    public int idPicked(final World par1World, final int par2, final int par3, final int par4) {
        return NetherX.itemcrystalcauldron.itemID;
    }
    
    static {
        BlockCrystalCauldron.renderID = RenderingRegistry.getNextAvailableRenderId();
    }
}
