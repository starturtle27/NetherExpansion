//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package net.netherX;

import net.minecraft.world.*;
import net.minecraft.entity.*;
import net.minecraft.util.*;
import net.minecraft.block.*;
import java.util.*;
import net.minecraftforge.common.*;
import net.minecraft.entity.player.*;
import net.minecraft.stats.*;
import net.minecraft.item.*;

public class BlockNetherThorn extends BlockDeadBush
{
    protected BlockNetherThorn(final int par1) {
        super(par1);
        final float f = 0.4f;
        this.setBlockBounds(0.5f - f, 0.0f, 0.5f - f, 0.5f + f, 0.8f, 0.5f + f);
    }
    
    public void onEntityCollidedWithBlock(final World par1World, final int par2, final int par3, final int par4, final Entity par5Entity) {
        par5Entity.attackEntityFrom(DamageSource.cactus, 1.0f);
    }
    
    protected boolean canThisPlantGrowOnThisBlockID(final int par1) {
        return par1 == Block.netherrack.blockID || par1 == Block.slowSand.blockID;
    }
    
    public int idDropped(final int par1, final Random par2Random, final int par3) {
        return 0;
    }
    
    public boolean canBlockStay(final World par1World, final int par2, final int par3, final int par4) {
        final Block block = Block.blocksList[par1World.getBlockId(par2, par3 - 1, par4)];
        return block == Block.netherrack || (block == Block.slowSand && block.canSustainPlant(par1World, par2, par3 - 1, par4, ForgeDirection.UP, (IPlantable)this));
    }
    
    public void harvestBlock(final World par1World, final EntityPlayer par2EntityPlayer, final int par3, final int par4, final int par5, final int par6) {
        if (!par1World.isRemote && par2EntityPlayer.getCurrentEquippedItem() != null && par2EntityPlayer.getCurrentEquippedItem().itemID == Item.shears.itemID) {
            par2EntityPlayer.addStat(StatList.mineBlockStatArray[this.blockID], 1);
            this.dropBlockAsItem_do(par1World, par3, par4, par5, new ItemStack((Block)this, 1, par6));
        }
        else {
            super.harvestBlock(par1World, par2EntityPlayer, par3, par4, par5, par6);
        }
    }
}
