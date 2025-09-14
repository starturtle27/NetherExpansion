//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package net.netherX;

import net.minecraft.block.*;
import net.minecraft.block.material.*;
import java.util.*;
import cpw.mods.fml.relauncher.*;
import net.minecraft.world.*;
import net.minecraft.util.*;

public class BlockSoulGlass extends BlockBreakable
{
    private final boolean powered;
    
    public BlockSoulGlass(final int par1, final Material par2Material, final boolean par3) {
        super(par1, "glassSoul", par2Material, par3);
        this.powered = par3;
    }
    
    public int quantityDropped(final Random par1Random) {
        return 0;
    }
    
    @SideOnly(Side.CLIENT)
    public int getRenderBlockPass() {
        return 1;
    }
    
    public boolean isOpaqueCube() {
        return false;
    }
    
    public AxisAlignedBB getCollisionBoundingBoxFromPool(final World par1World, final int par2, final int par3, final int par4) {
        if (this.blockID == NetherX.soulglassTransparent.blockID) {
            return null;
        }
        return AxisAlignedBB.getAABBPool().getAABB(par2 + this.minX, par3 + this.minY, par4 + this.minZ, par2 + this.maxX, par3 + this.maxY, par4 + this.maxZ);
    }
    
    public boolean renderAsNormalBlock() {
        return false;
    }
    
    protected boolean canSilkHarvest() {
        return true;
    }
    
    public void onBlockAdded(final World par1World, final int par2, final int par3, final int par4) {
        if (!par1World.isRemote) {
            if (this.powered && !par1World.isBlockIndirectlyGettingPowered(par2, par3, par4)) {
                par1World.scheduleBlockUpdate(par2, par3, par4, this.blockID, 4);
            }
            else if (!this.powered && par1World.isBlockIndirectlyGettingPowered(par2, par3, par4)) {
                par1World.setBlock(par2, par3, par4, NetherX.soulglassTransparent.blockID);
            }
            else if (!this.powered && par1World.getBlockId(par2, par3 - 1, par4) == NetherX.soulglassTransparent.blockID) {
                par1World.setBlock(par2, par3, par4, NetherX.soulglassTransparent.blockID);
            }
        }
    }
    
    public void onNeighborBlockChange(final World par1World, final int par2, final int par3, final int par4, final int par5) {
        if (!par1World.isRemote) {
            if (this.powered && !par1World.isBlockIndirectlyGettingPowered(par2, par3, par4)) {
                par1World.scheduleBlockUpdate(par2, par3, par4, this.blockID, 4);
            }
            else if (!this.powered && par1World.isBlockIndirectlyGettingPowered(par2, par3, par4)) {
                par1World.setBlock(par2, par3, par4, NetherX.soulglassTransparent.blockID);
            }
            else if (!this.powered && par1World.getBlockId(par2, par3 - 1, par4) == NetherX.soulglassTransparent.blockID) {
                par1World.setBlock(par2, par3, par4, NetherX.soulglassTransparent.blockID);
            }
        }
    }
    
    public void updateTick(final World par1World, final int par2, final int par3, final int par4, final Random par5Random) {
        if (!par1World.isRemote && this.powered && !par1World.isBlockIndirectlyGettingPowered(par2, par3, par4) && par1World.getBlockId(par2, par3 - 1, par4) != NetherX.soulglassTransparent.blockID) {
            par1World.setBlock(par2, par3, par4, NetherX.soulglass.blockID);
        }
    }
    
    public int idDropped(final int par1, final Random par2Random, final int par3) {
        return NetherX.soulglass.blockID;
    }
    
    @SideOnly(Side.CLIENT)
    public int idPicked(final World par1World, final int par2, final int par3, final int par4) {
        return NetherX.soulglass.blockID;
    }
}
