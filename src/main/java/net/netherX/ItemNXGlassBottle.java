//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package net.netherX;

import net.minecraft.creativetab.*;
import cpw.mods.fml.relauncher.*;
import net.minecraft.item.*;
import net.minecraft.world.*;
import net.minecraft.entity.player.*;
import net.minecraft.block.*;
import net.minecraft.util.*;
import net.minecraft.client.renderer.texture.*;

public class ItemNXGlassBottle extends Item
{
    public ItemNXGlassBottle(final int par1) {
        super(par1);
        this.setCreativeTab(CreativeTabs.tabBrewing);
    }
    
    @SideOnly(Side.CLIENT)
    public Icon getIconFromDamage(final int par1) {
        return Item.potion.getIconFromDamage(0);
    }
    
    public ItemStack onItemRightClick(final ItemStack par1ItemStack, final World par2World, final EntityPlayer par3EntityPlayer) {
        final MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(par2World, par3EntityPlayer, true);
        if (movingobjectposition == null) {
            return par1ItemStack;
        }
        if (movingobjectposition.typeOfHit == EnumMovingObjectType.TILE) {
            final int i = movingobjectposition.blockX;
            final int j = movingobjectposition.blockY;
            final int k = movingobjectposition.blockZ;
            if (!par2World.canMineBlock(par3EntityPlayer, i, j, k)) {
                return par1ItemStack;
            }
            if (!par3EntityPlayer.canPlayerEdit(i, j, k, movingobjectposition.sideHit, par1ItemStack)) {
                return par1ItemStack;
            }
            if (par2World.getBlockId(i, j, k) == Block.waterMoving.blockID || par2World.getBlockId(i, j, k) == Block.waterStill.blockID) {
                --par1ItemStack.stackSize;
                if (par1ItemStack.stackSize <= 0) {
                    return new ItemStack((Item)Item.potion);
                }
                if (!par3EntityPlayer.inventory.addItemStackToInventory(new ItemStack((Item)Item.potion))) {
                    par3EntityPlayer.dropPlayerItem(new ItemStack(Item.potion.itemID, 1, 0));
                }
            }
        }
        return par1ItemStack;
    }
    
    @SideOnly(Side.CLIENT)
    public void registerIcons(final IconRegister par1IconRegister) {
    }
}
