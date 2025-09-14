//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package net.netherX;

import net.minecraft.block.*;
import net.minecraft.block.material.*;
import net.minecraft.world.*;
import net.minecraft.util.*;
import java.util.*;
import net.minecraft.entity.player.*;

public class BlockMushroomVine extends Block
{
    public BlockMushroomVine(final int par1) {
        super(par1, Material.vine);
        this.setTickRandomly(true);
    }
    
    public void setBlockBoundsForItemRender() {
        this.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
    }
    
    public int getRenderType() {
        return 20;
    }
    
    public boolean isOpaqueCube() {
        return false;
    }
    
    public boolean renderAsNormalBlock() {
        return false;
    }
    
    public void setBlockBoundsBasedOnState(final IBlockAccess par1IBlockAccess, final int par2, final int par3, final int par4) {
        final float f = 0.0625f;
        final int l = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
        float f2 = 1.0f;
        float f3 = 1.0f;
        float f4 = 1.0f;
        float f5 = 0.0f;
        float f6 = 0.0f;
        float f7 = 0.0f;
        boolean flag = l > 0;
        if ((l & 0x2) != 0x0) {
            f5 = Math.max(f5, 0.0625f);
            f2 = 0.0f;
            f3 = 0.0f;
            f6 = 1.0f;
            f4 = 0.0f;
            f7 = 1.0f;
            flag = true;
        }
        if ((l & 0x8) != 0x0) {
            f2 = Math.min(f2, 0.9375f);
            f5 = 1.0f;
            f3 = 0.0f;
            f6 = 1.0f;
            f4 = 0.0f;
            f7 = 1.0f;
            flag = true;
        }
        if ((l & 0x4) != 0x0) {
            f7 = Math.max(f7, 0.0625f);
            f4 = 0.0f;
            f2 = 0.0f;
            f5 = 1.0f;
            f3 = 0.0f;
            f6 = 1.0f;
            flag = true;
        }
        if ((l & 0x1) != 0x0) {
            f4 = Math.min(f4, 0.9375f);
            f7 = 1.0f;
            f2 = 0.0f;
            f5 = 1.0f;
            f3 = 0.0f;
            f6 = 1.0f;
            flag = true;
        }
        if (!flag && this.canBePlacedOn(par1IBlockAccess.getBlockId(par2, par3 + 1, par4))) {
            f3 = Math.min(f3, 0.9375f);
            f6 = 1.0f;
            f2 = 0.0f;
            f5 = 1.0f;
            f4 = 0.0f;
            f7 = 1.0f;
        }
        this.setBlockBounds(f2, f3, f4, f5, f6, f7);
    }
    
    public AxisAlignedBB getCollisionBoundingBoxFromPool(final World par1World, final int par2, final int par3, final int par4) {
        return null;
    }
    
    public boolean canPlaceBlockOnSide(final World par1World, final int par2, final int par3, final int par4, final int par5) {
        switch (par5) {
            case 1: {
                return false;
            }
            case 2: {
                return this.canBePlacedOn(par1World.getBlockId(par2, par3, par4 + 1));
            }
            case 3: {
                return this.canBePlacedOn(par1World.getBlockId(par2, par3, par4 - 1));
            }
            case 4: {
                return this.canBePlacedOn(par1World.getBlockId(par2 + 1, par3, par4));
            }
            case 5: {
                return this.canBePlacedOn(par1World.getBlockId(par2 - 1, par3, par4));
            }
            default: {
                return false;
            }
        }
    }
    
    private boolean canBePlacedOn(final int par1) {
        if (par1 == 0) {
            return false;
        }
        final Block block = Block.blocksList[par1];
        return block.renderAsNormalBlock() && block.blockMaterial.blocksMovement();
    }
    
    private boolean canVineStay(final World par1World, final int par2, final int par3, final int par4) {
        int i1;
        final int l = i1 = par1World.getBlockMetadata(par2, par3, par4);
        if (l > 0) {
            for (int j1 = 0; j1 <= 3; ++j1) {
                final int k1 = 1 << j1;
                if ((l & k1) != 0x0 && !this.canBePlacedOn(par1World.getBlockId(par2 + Direction.offsetX[j1], par3, par4 + Direction.offsetZ[j1])) && (par1World.getBlockId(par2, par3 + 1, par4) != this.blockID || (par1World.getBlockMetadata(par2, par3 + 1, par4) & k1) == 0x0)) {
                    i1 &= ~k1;
                }
            }
        }
        if (i1 == 0 && !this.canBePlacedOn(par1World.getBlockId(par2, par3 + 1, par4))) {
            return false;
        }
        if (i1 != l) {
            par1World.setBlockMetadata(par2, par3, par4, i1, 2);
        }
        return true;
    }
    
    public void onNeighborBlockChange(final World par1World, final int par2, final int par3, final int par4, final int par5) {
        if (!par1World.isRemote && !this.canVineStay(par1World, par2, par3, par4)) {
            this.dropBlockAsItem(par1World, par2, par3, par4, par1World.getBlockMetadata(par2, par3, par4), 0);
            par1World.setBlockToAir(par2, par3, par4);
        }
    }
    
    public void updateTick(final World par1World, final int par2, final int par3, final int par4, final Random par5Random) {
        if (!par1World.isRemote && par1World.rand.nextInt(4) == 0) {
            final byte b0 = 4;
            int l = 5;
            boolean flag = false;
        Label_0121:
            for (int i1 = par2 - b0; i1 <= par2 + b0; ++i1) {
                for (int j1 = par4 - b0; j1 <= par4 + b0; ++j1) {
                    for (int k1 = par3 - 1; k1 <= par3 + 1; ++k1) {
                        if (par1World.getBlockId(i1, k1, j1) == this.blockID && --l <= 0) {
                            flag = true;
                            break Label_0121;
                        }
                    }
                }
            }
            int i1 = par1World.getBlockMetadata(par2, par3, par4);
            int j1 = par1World.rand.nextInt(6);
            int k1 = Direction.facingToDirection[j1];
            if (j1 == 1 && par3 < 255 && par1World.isAirBlock(par2, par3 + 1, par4)) {
                if (flag) {
                    return;
                }
                int l2 = par1World.rand.nextInt(16) & i1;
                if (l2 > 0) {
                    for (int i2 = 0; i2 <= 3; ++i2) {
                        if (!this.canBePlacedOn(par1World.getBlockId(par2 + Direction.offsetX[i2], par3 + 1, par4 + Direction.offsetZ[i2]))) {
                            l2 &= ~(1 << i2);
                        }
                    }
                    if (l2 > 0) {
                        par1World.setBlock(par2, par3 + 1, par4, this.blockID, l2, 2);
                    }
                }
            }
            else if (j1 >= 2 && j1 <= 5 && (i1 & 1 << k1) == 0x0) {
                if (flag) {
                    return;
                }
                final int l2 = par1World.getBlockId(par2 + Direction.offsetX[k1], par3, par4 + Direction.offsetZ[k1]);
                if (l2 != 0 && Block.blocksList[l2] != null) {
                    if (Block.blocksList[l2].blockMaterial.isOpaque() && Block.blocksList[l2].renderAsNormalBlock()) {
                        par1World.setBlockMetadata(par2, par3, par4, i1 | 1 << k1, 2);
                    }
                }
                else {
                    final int i2 = k1 + 1 & 0x3;
                    final int j2 = k1 + 3 & 0x3;
                    if ((i1 & 1 << i2) != 0x0 && this.canBePlacedOn(par1World.getBlockId(par2 + Direction.offsetX[k1] + Direction.offsetX[i2], par3, par4 + Direction.offsetZ[k1] + Direction.offsetZ[i2]))) {
                        par1World.setBlock(par2 + Direction.offsetX[k1], par3, par4 + Direction.offsetZ[k1], this.blockID, 1 << i2, 2);
                    }
                    else if ((i1 & 1 << j2) != 0x0 && this.canBePlacedOn(par1World.getBlockId(par2 + Direction.offsetX[k1] + Direction.offsetX[j2], par3, par4 + Direction.offsetZ[k1] + Direction.offsetZ[j2]))) {
                        par1World.setBlock(par2 + Direction.offsetX[k1], par3, par4 + Direction.offsetZ[k1], this.blockID, 1 << j2, 2);
                    }
                    else if ((i1 & 1 << i2) != 0x0 && par1World.isAirBlock(par2 + Direction.offsetX[k1] + Direction.offsetX[i2], par3, par4 + Direction.offsetZ[k1] + Direction.offsetZ[i2]) && this.canBePlacedOn(par1World.getBlockId(par2 + Direction.offsetX[i2], par3, par4 + Direction.offsetZ[i2]))) {
                        par1World.setBlock(par2 + Direction.offsetX[k1] + Direction.offsetX[i2], par3, par4 + Direction.offsetZ[k1] + Direction.offsetZ[i2], this.blockID, 1 << (k1 + 2 & 0x3), 2);
                    }
                    else if ((i1 & 1 << j2) != 0x0 && par1World.isAirBlock(par2 + Direction.offsetX[k1] + Direction.offsetX[j2], par3, par4 + Direction.offsetZ[k1] + Direction.offsetZ[j2]) && this.canBePlacedOn(par1World.getBlockId(par2 + Direction.offsetX[j2], par3, par4 + Direction.offsetZ[j2]))) {
                        par1World.setBlock(par2 + Direction.offsetX[k1] + Direction.offsetX[j2], par3, par4 + Direction.offsetZ[k1] + Direction.offsetZ[j2], this.blockID, 1 << (k1 + 2 & 0x3), 2);
                    }
                    else if (this.canBePlacedOn(par1World.getBlockId(par2 + Direction.offsetX[k1], par3 + 1, par4 + Direction.offsetZ[k1]))) {
                        par1World.setBlock(par2 + Direction.offsetX[k1], par3, par4 + Direction.offsetZ[k1], this.blockID, 0, 2);
                    }
                }
            }
            else if (par3 > 1) {
                final int l2 = par1World.getBlockId(par2, par3 - 1, par4);
                if (l2 == 0) {
                    final int i2 = par1World.rand.nextInt(16) & i1;
                    if (i2 > 0) {
                        par1World.setBlock(par2, par3 - 1, par4, this.blockID, i2, 2);
                    }
                }
                else if (l2 == this.blockID) {
                    final int i2 = par1World.rand.nextInt(16) & i1;
                    final int j2 = par1World.getBlockMetadata(par2, par3 - 1, par4);
                    if (j2 != (j2 | i2)) {
                        par1World.setBlockMetadata(par2, par3 - 1, par4, j2 | i2, 2);
                    }
                }
            }
        }
    }
    
    public int onBlockPlaced(final World par1World, final int par2, final int par3, final int par4, final int par5, final float par6, final float par7, final float par8, final int par9) {
        byte b0 = 0;
        switch (par5) {
            case 2: {
                b0 = 1;
                break;
            }
            case 3: {
                b0 = 4;
                break;
            }
            case 4: {
                b0 = 8;
                break;
            }
            case 5: {
                b0 = 2;
                break;
            }
        }
        return (b0 != 0) ? b0 : par9;
    }
    
    public int idDropped(final int par1, final Random par2Random, final int par3) {
        return 0;
    }
    
    public int quantityDropped(final Random par1Random) {
        return 0;
    }
    
    public void harvestBlock(final World par1World, final EntityPlayer par2EntityPlayer, final int par3, final int par4, final int par5, final int par6) {
        super.harvestBlock(par1World, par2EntityPlayer, par3, par4, par5, par6);
    }
}
