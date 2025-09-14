//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package net.netherX;

import net.minecraft.creativetab.*;
import net.minecraft.item.*;
import net.minecraft.entity.player.*;
import net.minecraft.world.*;
import net.minecraft.block.*;
import net.minecraftforge.common.*;

public class ItemGlutrootSeeds extends Item implements IPlantable
{
    private int blockType;
    private int soilBlockID;
    
    public ItemGlutrootSeeds(final int par1, final int par2, final int par3) {
        super(par1);
        this.blockType = par2;
        this.soilBlockID = par3;
        this.setCreativeTab(CreativeTabs.tabMaterials);
    }
    
    public boolean onItemUse(final ItemStack par1ItemStack, final EntityPlayer par2EntityPlayer, final World par3World, final int par4, final int par5, final int par6, final int par7, final float par8, final float par9, final float par10) {
        if (par7 != 1) {
            return false;
        }
        if (!par2EntityPlayer.canPlayerEdit(par4, par5, par6, par7, par1ItemStack) || !par2EntityPlayer.canPlayerEdit(par4, par5 + 1, par6, par7, par1ItemStack)) {
            return false;
        }
        final int i1 = par3World.getBlockId(par4, par5, par6);
        final Block soil = Block.blocksList[i1];
        if (soil != null && soil.canSustainPlant(par3World, par4, par5, par6, ForgeDirection.UP, (IPlantable)this) && par3World.isAirBlock(par4, par5 + 1, par6)) {
            par3World.setBlock(par4, par5 + 1, par6, this.blockType);
            --par1ItemStack.stackSize;
            return true;
        }
        return false;
    }
    
    public EnumPlantType getPlantType(final World world, final int x, final int y, final int z) {
        return (this.blockType == NetherX.glutrootBlock.blockID) ? EnumPlantType.Nether : EnumPlantType.Crop;
    }
    
    public int getPlantID(final World world, final int x, final int y, final int z) {
        return this.blockType;
    }
    
    public int getPlantMetadata(final World world, final int x, final int y, final int z) {
        return 0;
    }
}
