//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package net.netherX;

import java.util.*;
import net.minecraft.block.*;
import net.minecraftforge.common.*;
import net.minecraft.world.*;
import net.minecraft.entity.*;
import net.minecraft.entity.item.*;

public class BlockBrimFire extends BlockFire
{
    protected BlockBrimFire(final int par1) {
        super(par1);
        this.disableStats();
    }
    
    public void updateTick(final World par1World, final int par2, final int par3, final int par4, final Random par5Random) {
        if (par1World.getGameRules().getGameRuleBooleanValue("doFireTick")) {
            final Block base = Block.blocksList[par1World.getBlockId(par2, par3 - 1, par4)];
            final boolean flag = base != null && base.isFireSource(par1World, par2, par3 - 1, par4, par1World.getBlockMetadata(par2, par3 - 1, par4), ForgeDirection.UP);
            if (!this.canPlaceBlockAt(par1World, par2, par3, par4)) {
                par1World.setBlockToAir(par2, par3, par4);
            }
            if (!flag && par1World.isRaining() && (par1World.canLightningStrikeAt(par2, par3, par4) || par1World.canLightningStrikeAt(par2 - 1, par3, par4) || par1World.canLightningStrikeAt(par2 + 1, par3, par4) || par1World.canLightningStrikeAt(par2, par3, par4 - 1) || par1World.canLightningStrikeAt(par2, par3, par4 + 1))) {
                par1World.setBlockToAir(par2, par3, par4);
            }
            else {
                final int l = par1World.getBlockMetadata(par2, par3, par4);
                if (l < 15) {
                    par1World.setBlockMetadata(par2, par3, par4, l + par5Random.nextInt(3) / 2, 4);
                }
                par1World.scheduleBlockUpdate(par2, par3, par4, this.blockID, this.tickRate(par1World) + par5Random.nextInt(10));
                if (!flag && !this.canNeighborBurn(par1World, par2, par3, par4)) {
                    if (!par1World.doesBlockHaveSolidTopSurface(par2, par3 - 1, par4) || l > 3) {
                        par1World.setBlockToAir(par2, par3, par4);
                    }
                }
                else if (!flag && !this.canBlockCatchFire((IBlockAccess)par1World, par2, par3 - 1, par4, ForgeDirection.UP) && l == 15 && par5Random.nextInt(4) == 0) {
                    par1World.setBlockToAir(par2, par3, par4);
                }
                else {
                    final boolean flag2 = par1World.isBlockHighHumidity(par2, par3, par4);
                    byte b0 = 0;
                    if (flag2) {
                        b0 = -50;
                    }
                    this.tryToCatchBlockOnFire(par1World, par2 + 1, par3, par4, 300 + b0, par5Random, l, ForgeDirection.WEST);
                    this.tryToCatchBlockOnFire(par1World, par2 - 1, par3, par4, 300 + b0, par5Random, l, ForgeDirection.EAST);
                    this.tryToCatchBlockOnFire(par1World, par2, par3 - 1, par4, 250 + b0, par5Random, l, ForgeDirection.UP);
                    this.tryToCatchBlockOnFire(par1World, par2, par3 + 1, par4, 250 + b0, par5Random, l, ForgeDirection.DOWN);
                    this.tryToCatchBlockOnFire(par1World, par2, par3, par4 - 1, 300 + b0, par5Random, l, ForgeDirection.SOUTH);
                    this.tryToCatchBlockOnFire(par1World, par2, par3, par4 + 1, 300 + b0, par5Random, l, ForgeDirection.NORTH);
                    for (int i1 = par2 - 1; i1 <= par2 + 1; ++i1) {
                        for (int j1 = par4 - 1; j1 <= par4 + 1; ++j1) {
                            for (int k1 = par3 - 1; k1 <= par3 + 4; ++k1) {
                                if (i1 != par2 || k1 != par3 || j1 != par4) {
                                    int l2 = 100;
                                    if (k1 > par3 + 1) {
                                        l2 += (k1 - (par3 + 1)) * 100;
                                    }
                                    final int i2 = this.getChanceOfNeighborsEncouragingFire(par1World, i1, k1, j1);
                                    if (i2 > 0) {
                                        int j2 = (i2 + 40 + par1World.difficultySetting * 7) / (l + 30);
                                        if (flag2) {
                                            j2 /= 2;
                                        }
                                        if (j2 > 0 && par5Random.nextInt(l2) <= j2 && (!par1World.isRaining() || !par1World.canLightningStrikeAt(i1, k1, j1)) && !par1World.canLightningStrikeAt(i1 - 1, k1, par4) && !par1World.canLightningStrikeAt(i1 + 1, k1, j1) && !par1World.canLightningStrikeAt(i1, k1, j1 - 1) && !par1World.canLightningStrikeAt(i1, k1, j1 + 1)) {
                                            int k2 = l + par5Random.nextInt(5) / 4;
                                            if (k2 > 15) {
                                                k2 = 15;
                                            }
                                            par1World.setBlock(i1, k1, j1, this.blockID, k2, 3);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        par1World.setBlockToAir(par2, par3, par4);
    }
    
    public void onEntityCollidedWithBlock(final World par1World, final int par2, final int par3, final int par4, final Entity par5Entity) {
        if (!(par5Entity instanceof EntityItem)) {
            par5Entity.setFire(10);
        }
    }
}
