//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package net.netherX;

import net.minecraft.block.material.*;
import net.minecraft.creativetab.*;
import net.minecraft.item.*;
import net.minecraft.entity.player.*;
import net.minecraft.world.*;
import net.minecraft.util.*;
import net.minecraft.block.*;

public class ItemObsidianDoor extends Item
{
    private Material doorMaterial;
    
    public ItemObsidianDoor(final int par1, final Material par2Material) {
        super(par1);
        this.doorMaterial = par2Material;
        this.maxStackSize = 1;
        this.setCreativeTab(CreativeTabs.tabRedstone);
    }
    
    public boolean onItemUse(final ItemStack par1ItemStack, final EntityPlayer par2EntityPlayer, final World par3World, final int par4, int par5, final int par6, final int par7, final float par8, final float par9, final float par10) {
        if (par7 != 1) {
            return false;
        }
        ++par5;
        final Block block = NetherX.doorObsidian;
        if (!par2EntityPlayer.canPlayerEdit(par4, par5, par6, par7, par1ItemStack) || !par2EntityPlayer.canPlayerEdit(par4, par5 + 1, par6, par7, par1ItemStack)) {
            return false;
        }
        if (!block.canPlaceBlockAt(par3World, par4, par5, par6)) {
            return false;
        }
        final int i1 = MathHelper.floor_double((par2EntityPlayer.rotationYaw + 180.0f) * 4.0f / 360.0f - 0.5) & 0x3;
        placeDoorBlock(par3World, par4, par5, par6, i1, block);
        --par1ItemStack.stackSize;
        return true;
    }
    
    public static void placeDoorBlock(final World par0World, final int par1, final int par2, final int par3, final int par4, final Block par5Block) {
        byte b0 = 0;
        byte b2 = 0;
        if (par4 == 0) {
            b2 = 1;
        }
        if (par4 == 1) {
            b0 = -1;
        }
        if (par4 == 2) {
            b2 = -1;
        }
        if (par4 == 3) {
            b0 = 1;
        }
        final int i1 = (par0World.isBlockNormalCube(par1 - b0, par2, par3 - b2) + par0World.isBlockNormalCube(par1 - b0, par2 + 1, par3 - b2)) ? 1 : 0;
        final int j1 = (par0World.isBlockNormalCube(par1 + b0, par2, par3 + b2) + par0World.isBlockNormalCube(par1 + b0, par2 + 1, par3 + b2)) ? 1 : 0;
        final boolean flag = par0World.getBlockId(par1 - b0, par2, par3 - b2) == par5Block.blockID || par0World.getBlockId(par1 - b0, par2 + 1, par3 - b2) == par5Block.blockID;
        final boolean flag2 = par0World.getBlockId(par1 + b0, par2, par3 + b2) == par5Block.blockID || par0World.getBlockId(par1 + b0, par2 + 1, par3 + b2) == par5Block.blockID;
        boolean flag3 = false;
        if (flag && !flag2) {
            flag3 = true;
        }
        else if (j1 > i1) {
            flag3 = true;
        }
        par0World.setBlock(par1, par2, par3, par5Block.blockID, par4, 2);
        par0World.setBlock(par1, par2 + 1, par3, par5Block.blockID, 0x8 | (flag3 ? 1 : 0), 2);
        par0World.notifyBlocksOfNeighborChange(par1, par2, par3, par5Block.blockID);
        par0World.notifyBlocksOfNeighborChange(par1, par2 + 1, par3, par5Block.blockID);
    }
}
