//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package net.netherX;

import net.minecraft.creativetab.*;
import net.minecraft.item.*;
import net.minecraft.world.*;
import net.minecraft.entity.player.*;
import net.minecraftforge.event.entity.player.*;
import net.minecraftforge.common.*;
import net.minecraftforge.event.*;
import net.minecraft.block.*;
import net.minecraft.block.material.*;
import net.minecraft.util.*;

public class ItemNXBucket extends Item
{
    private int isFull;
    
    public ItemNXBucket(final int par1, final int par2) {
        super(par1);
        this.maxStackSize = 1;
        this.isFull = par2;
        this.setCreativeTab(CreativeTabs.tabMisc);
    }
    
    public ItemStack onItemRightClick(final ItemStack par1ItemStack, final World par2World, final EntityPlayer par3EntityPlayer) {
        final boolean flag = this.isFull == 0;
        final MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(par2World, par3EntityPlayer, flag);
        if (movingobjectposition == null) {
            return par1ItemStack;
        }
        final FillBucketEvent event = new FillBucketEvent(par3EntityPlayer, par1ItemStack, par2World, movingobjectposition);
        if (MinecraftForge.EVENT_BUS.post((Event)event)) {
            return par1ItemStack;
        }
        if (event.getResult() != Event.Result.ALLOW) {
            if (movingobjectposition.typeOfHit == EnumMovingObjectType.TILE) {
                int i = movingobjectposition.blockX;
                int j = movingobjectposition.blockY;
                int k = movingobjectposition.blockZ;
                if (!par2World.canMineBlock(par3EntityPlayer, i, j, k)) {
                    return par1ItemStack;
                }
                if (this.isFull == 0) {
                    if (!par3EntityPlayer.canPlayerEdit(i, j, k, movingobjectposition.sideHit, par1ItemStack)) {
                        return par1ItemStack;
                    }
                    if (par2World.getBlockId(i, j, k) == Block.waterMoving.blockID || par2World.getBlockId(i, j, k) == Block.waterStill.blockID) {
                        par2World.setBlockToAir(i, j, k);
                        if (par3EntityPlayer.capabilities.isCreativeMode) {
                            return par1ItemStack;
                        }
                        if (--par1ItemStack.stackSize <= 0) {
                            return new ItemStack(Item.bucketWater);
                        }
                        if (!par3EntityPlayer.inventory.addItemStackToInventory(new ItemStack(Item.bucketWater))) {
                            par3EntityPlayer.dropPlayerItem(new ItemStack(Item.bucketWater.itemID, 1, 0));
                        }
                        return par1ItemStack;
                    }
                    else if (par2World.getBlockMaterial(i, j, k) == Material.lava && par2World.getBlockMetadata(i, j, k) == 0) {
                        par2World.setBlockToAir(i, j, k);
                        if (par3EntityPlayer.capabilities.isCreativeMode) {
                            return par1ItemStack;
                        }
                        if (--par1ItemStack.stackSize <= 0) {
                            return new ItemStack(Item.bucketLava);
                        }
                        if (!par3EntityPlayer.inventory.addItemStackToInventory(new ItemStack(Item.bucketLava))) {
                            par3EntityPlayer.dropPlayerItem(new ItemStack(Item.bucketLava.itemID, 1, 0));
                        }
                        return par1ItemStack;
                    }
                }
                else {
                    if (this.isFull < 0) {
                        return new ItemStack(Item.bucketEmpty);
                    }
                    if (movingobjectposition.sideHit == 0) {
                        --j;
                    }
                    if (movingobjectposition.sideHit == 1) {
                        ++j;
                    }
                    if (movingobjectposition.sideHit == 2) {
                        --k;
                    }
                    if (movingobjectposition.sideHit == 3) {
                        ++k;
                    }
                    if (movingobjectposition.sideHit == 4) {
                        --i;
                    }
                    if (movingobjectposition.sideHit == 5) {
                        ++i;
                    }
                    if (!par3EntityPlayer.canPlayerEdit(i, j, k, movingobjectposition.sideHit, par1ItemStack)) {
                        return par1ItemStack;
                    }
                    if (this.tryPlaceContainedLiquid(par2World, i, j, k) && !par3EntityPlayer.capabilities.isCreativeMode) {
                        return new ItemStack(Item.bucketEmpty);
                    }
                }
            }
            return par1ItemStack;
        }
        if (par3EntityPlayer.capabilities.isCreativeMode) {
            return par1ItemStack;
        }
        if (--par1ItemStack.stackSize <= 0) {
            return event.result;
        }
        if (!par3EntityPlayer.inventory.addItemStackToInventory(event.result)) {
            par3EntityPlayer.dropPlayerItem(event.result);
        }
        return par1ItemStack;
    }
    
    public boolean tryPlaceContainedLiquid(final World par1World, final int par2, final int par3, final int par4) {
        if (this.isFull <= 0) {
            return false;
        }
        final Material material = par1World.getBlockMaterial(par2, par3, par4);
        final boolean flag = !material.isSolid();
        if (!par1World.isAirBlock(par2, par3, par4) && !flag) {
            return false;
        }
        if (par1World.provider.isHellWorld && this.isFull == Block.waterMoving.blockID) {
            par1World.playSoundEffect((double)(par2 + 0.5f), (double)(par3 + 0.5f), (double)(par4 + 0.5f), "random.fizz", 0.5f, 2.6f + (par1World.rand.nextFloat() - par1World.rand.nextFloat()) * 0.8f);
            for (int l = 0; l < 8; ++l) {
                par1World.spawnParticle("largesmoke", par2 + Math.random(), par3 + Math.random(), par4 + Math.random(), 0.0, 0.0, 0.0);
            }
        }
        else {
            if (!par1World.isRemote && flag && !material.isLiquid()) {
                par1World.destroyBlock(par2, par3, par4, true);
            }
            par1World.setBlock(par2, par3, par4, this.isFull, 0, 3);
        }
        return true;
    }
}
