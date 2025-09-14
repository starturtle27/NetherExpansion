//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package net.netherX;

import net.minecraft.item.*;
import net.minecraft.entity.player.*;
import net.minecraft.world.*;
import net.minecraft.block.*;

public class ItemIchoriteShard extends Item
{
    public ItemIchoriteShard(final int par1) {
        super(par1);
    }
    
    public boolean onItemUse(final ItemStack par1ItemStack, final EntityPlayer par2EntityPlayer, final World par3World, final int par4, final int par5, final int par6, final int par7, final float par8, final float par9, final float par10) {
        if (!par2EntityPlayer.canPlayerEdit(par4, par5, par6, par7, par1ItemStack)) {
            return false;
        }
        final int i1 = par3World.getBlockId(par4, par5, par6);
        if (par7 != 0 && i1 == NetherX.poisoncrystalcauldron.blockID) {
            final Block block = NetherX.crystalcauldron;
            par3World.playSoundEffect((double)(par4 + 0.5f), (double)(par5 + 0.5f), (double)(par6 + 0.5f), "random.fizz", (block.stepSound.getVolume() + 1.0f) / 2.0f, block.stepSound.getPitch() * 0.8f);
            for (int j1 = 0; j1 < 30; ++j1) {
                final double d0 = Item.itemRand.nextGaussian() * 0.02;
                final double d2 = Item.itemRand.nextGaussian() * 0.02;
                final double d3 = Item.itemRand.nextGaussian() * 0.02;
                par3World.spawnParticle("cloud", (double)(par4 + Item.itemRand.nextFloat()), par5 + Item.itemRand.nextFloat() * block.getBlockBoundsMaxY(), (double)(par6 + Item.itemRand.nextFloat()), d0, d2, d3);
            }
            if (par3World.isRemote) {
                return true;
            }
            par3World.setBlock(par4, par5, par6, block.blockID);
            par3World.setBlockMetadata(par4, par5, par6, 3, 2);
            --par1ItemStack.stackSize;
            return true;
        }
        else {
            final int metadata = par3World.getBlockMetadata(par4, par5, par6);
            if (par7 != 0 && i1 == NetherX.glutrootBlock.blockID && metadata < 3) {
                final int i2 = par3World.getBlockId(par4, par5, par6);
                final Block block2 = (i2 > 0 && i2 < Block.blocksList.length) ? Block.blocksList[i2] : null;
                for (int j2 = 0; j2 < 15; ++j2) {
                    final double d4 = Item.itemRand.nextGaussian() * 0.02;
                    final double d5 = Item.itemRand.nextGaussian() * 0.02;
                    final double d6 = Item.itemRand.nextGaussian() * 0.02;
                    par3World.spawnParticle("happyVillager", (double)(par4 + Item.itemRand.nextFloat()), par5 + Item.itemRand.nextFloat() * block2.getBlockBoundsMaxY(), (double)(par6 + Item.itemRand.nextFloat()), d4, d5, d6);
                }
                if (par3World.isRemote) {
                    return true;
                }
                par3World.setBlockMetadata(par4, par5, par6, metadata + 1, 2);
                --par1ItemStack.stackSize;
                return true;
            }
            else {
                if (par7 == 0 || i1 != Block.netherStalk.blockID || metadata >= 3) {
                    return false;
                }
                final int i2 = par3World.getBlockId(par4, par5, par6);
                final Block block2 = (i2 > 0 && i2 < Block.blocksList.length) ? Block.blocksList[i2] : null;
                for (int j2 = 0; j2 < 15; ++j2) {
                    final double d4 = Item.itemRand.nextGaussian() * 0.02;
                    final double d5 = Item.itemRand.nextGaussian() * 0.02;
                    final double d6 = Item.itemRand.nextGaussian() * 0.02;
                    par3World.spawnParticle("happyVillager", (double)(par4 + Item.itemRand.nextFloat()), par5 + Item.itemRand.nextFloat() * block2.getBlockBoundsMaxY(), (double)(par6 + Item.itemRand.nextFloat()), d4, d5, d6);
                }
                if (par3World.isRemote) {
                    return true;
                }
                par3World.setBlockMetadata(par4, par5, par6, metadata + 1, 2);
                --par1ItemStack.stackSize;
                return true;
            }
        }
    }
}
