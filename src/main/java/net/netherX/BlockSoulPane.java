//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package net.netherX;

import net.minecraft.block.*;
import net.minecraft.block.material.*;
import net.minecraft.world.*;
import net.minecraft.util.*;
import java.util.*;
import cpw.mods.fml.relauncher.*;

public class BlockSoulPane extends BlockPane
{
    private final boolean powered;
    
    protected BlockSoulPane(final int par1, final String par2Str, final String par3Str, final Material par4Material, final boolean par5, final boolean par3) {
        super(par1, par2Str, par3Str, par4Material, par5);
        this.powered = par3;
    }
    
    public AxisAlignedBB getCollisionBoundingBoxFromPool(final World par1World, final int par2, final int par3, final int par4) {
        if (this.blockID == NetherX.soulglassPaneTransparent.blockID) {
            return null;
        }
        return super.getCollisionBoundingBoxFromPool(par1World, par2, par3, par4);
    }
    
    public void onBlockAdded(final World par1World, final int par2, final int par3, final int par4) {
        if (!par1World.isRemote) {
            if (this.powered && !par1World.isBlockIndirectlyGettingPowered(par2, par3, par4)) {
                par1World.scheduleBlockUpdate(par2, par3, par4, this.blockID, 4);
            }
            else if (!this.powered && par1World.isBlockIndirectlyGettingPowered(par2, par3, par4)) {
                par1World.setBlock(par2, par3, par4, NetherX.soulglassPaneTransparent.blockID);
            }
            else if (!this.powered && par1World.getBlockId(par2, par3 - 1, par4) == NetherX.soulglassPaneTransparent.blockID) {
                par1World.setBlock(par2, par3, par4, NetherX.soulglassPaneTransparent.blockID);
            }
        }
    }
    
    public void onNeighborBlockChange(final World par1World, final int par2, final int par3, final int par4, final int par5) {
        if (!par1World.isRemote) {
            if (this.powered && !par1World.isBlockIndirectlyGettingPowered(par2, par3, par4)) {
                par1World.scheduleBlockUpdate(par2, par3, par4, this.blockID, 4);
            }
            else if (!this.powered && par1World.isBlockIndirectlyGettingPowered(par2, par3, par4)) {
                par1World.setBlock(par2, par3, par4, NetherX.soulglassPaneTransparent.blockID);
            }
            else if (!this.powered && par1World.getBlockId(par2, par3 - 1, par4) == NetherX.soulglassPaneTransparent.blockID) {
                par1World.setBlock(par2, par3, par4, NetherX.soulglassPaneTransparent.blockID);
            }
        }
    }
    
    public void updateTick(final World par1World, final int par2, final int par3, final int par4, final Random par5Random) {
        if (!par1World.isRemote && this.powered && !par1World.isBlockIndirectlyGettingPowered(par2, par3, par4) && par1World.getBlockId(par2, par3 - 1, par4) != NetherX.soulglassPaneTransparent.blockID) {
            par1World.setBlock(par2, par3, par4, NetherX.soulglassPane.blockID);
        }
    }
    
    public int idDropped(final int par1, final Random par2Random, final int par3) {
        return NetherX.soulglassPane.blockID;
    }
    
    @SideOnly(Side.CLIENT)
    public int idPicked(final World par1World, final int par2, final int par3, final int par4) {
        return NetherX.soulglassPane.blockID;
    }
}
