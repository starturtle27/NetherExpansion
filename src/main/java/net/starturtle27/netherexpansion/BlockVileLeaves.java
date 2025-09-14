//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package net.netherX;

import net.minecraftforge.common.*;
import net.minecraft.util.*;
import net.minecraft.block.material.*;
import net.minecraft.creativetab.*;
import net.minecraft.world.*;
import net.minecraft.block.*;
import cpw.mods.fml.relauncher.*;
import net.minecraft.item.*;
import net.minecraft.entity.player.*;
import net.minecraft.client.renderer.texture.*;
import java.util.*;

public class BlockVileLeaves extends BlockLeavesBase implements IShearable
{
    public static final String[][] field_94396_b;
    private int iconType;
    private Icon[][] iconArray;
    int[] adjacentTreeBlocks;
    
    protected BlockVileLeaves(final int par1, final Material par2Material, final boolean par3) {
        super(par1, par2Material, par3);
        this.iconArray = new Icon[2][];
        this.setTickRandomly(true);
        this.setCreativeTab(CreativeTabs.tabDecorations);
    }
    
    public void breakBlock(final World par1World, final int par2, final int par3, final int par4, final int par5, final int par6) {
        final byte b0 = 1;
        final int j1 = b0 + 1;
        if (par1World.checkChunksExist(par2 - j1, par3 - j1, par4 - j1, par2 + j1, par3 + j1, par4 + j1)) {
            for (int k1 = -b0; k1 <= b0; ++k1) {
                for (int l1 = -b0; l1 <= b0; ++l1) {
                    for (int i2 = -b0; i2 <= b0; ++i2) {
                        final int j2 = par1World.getBlockId(par2 + k1, par3 + l1, par4 + i2);
                        if (Block.blocksList[j2] != null) {
                            Block.blocksList[j2].beginLeavesDecay(par1World, par2 + k1, par3 + l1, par4 + i2);
                        }
                    }
                }
            }
        }
    }
    
    public void updateTick(final World par1World, final int par2, final int par3, final int par4, final Random par5Random) {
        if (!par1World.isRemote) {
            final int l = par1World.getBlockMetadata(par2, par3, par4);
            if ((l & 0x8) != 0x0 && (l & 0x4) == 0x0) {
                final byte b0 = 4;
                final int i1 = b0 + 1;
                final byte b2 = 32;
                final int j1 = b2 * b2;
                final int k1 = b2 / 2;
                if (this.adjacentTreeBlocks == null) {
                    this.adjacentTreeBlocks = new int[b2 * b2 * b2];
                }
                if (par1World.checkChunksExist(par2 - i1, par3 - i1, par4 - i1, par2 + i1, par3 + i1, par4 + i1)) {
                    for (int l2 = -b0; l2 <= b0; ++l2) {
                        for (int i2 = -b0; i2 <= b0; ++i2) {
                            for (int j2 = -b0; j2 <= b0; ++j2) {
                                final int k2 = par1World.getBlockId(par2 + l2, par3 + i2, par4 + j2);
                                final Block block = Block.blocksList[k2];
                                if (block != null && block.canSustainLeaves(par1World, par2 + l2, par3 + i2, par4 + j2)) {
                                    this.adjacentTreeBlocks[(l2 + k1) * j1 + (i2 + k1) * b2 + j2 + k1] = 0;
                                }
                                else if (block != null && block.isLeaves(par1World, par2 + l2, par3 + i2, par4 + j2)) {
                                    this.adjacentTreeBlocks[(l2 + k1) * j1 + (i2 + k1) * b2 + j2 + k1] = -2;
                                }
                                else {
                                    this.adjacentTreeBlocks[(l2 + k1) * j1 + (i2 + k1) * b2 + j2 + k1] = -1;
                                }
                            }
                        }
                    }
                    for (int l2 = 1; l2 <= 4; ++l2) {
                        for (int i2 = -b0; i2 <= b0; ++i2) {
                            for (int j2 = -b0; j2 <= b0; ++j2) {
                                for (int k2 = -b0; k2 <= b0; ++k2) {
                                    if (this.adjacentTreeBlocks[(i2 + k1) * j1 + (j2 + k1) * b2 + k2 + k1] == l2 - 1) {
                                        if (this.adjacentTreeBlocks[(i2 + k1 - 1) * j1 + (j2 + k1) * b2 + k2 + k1] == -2) {
                                            this.adjacentTreeBlocks[(i2 + k1 - 1) * j1 + (j2 + k1) * b2 + k2 + k1] = l2;
                                        }
                                        if (this.adjacentTreeBlocks[(i2 + k1 + 1) * j1 + (j2 + k1) * b2 + k2 + k1] == -2) {
                                            this.adjacentTreeBlocks[(i2 + k1 + 1) * j1 + (j2 + k1) * b2 + k2 + k1] = l2;
                                        }
                                        if (this.adjacentTreeBlocks[(i2 + k1) * j1 + (j2 + k1 - 1) * b2 + k2 + k1] == -2) {
                                            this.adjacentTreeBlocks[(i2 + k1) * j1 + (j2 + k1 - 1) * b2 + k2 + k1] = l2;
                                        }
                                        if (this.adjacentTreeBlocks[(i2 + k1) * j1 + (j2 + k1 + 1) * b2 + k2 + k1] == -2) {
                                            this.adjacentTreeBlocks[(i2 + k1) * j1 + (j2 + k1 + 1) * b2 + k2 + k1] = l2;
                                        }
                                        if (this.adjacentTreeBlocks[(i2 + k1) * j1 + (j2 + k1) * b2 + (k2 + k1 - 1)] == -2) {
                                            this.adjacentTreeBlocks[(i2 + k1) * j1 + (j2 + k1) * b2 + (k2 + k1 - 1)] = l2;
                                        }
                                        if (this.adjacentTreeBlocks[(i2 + k1) * j1 + (j2 + k1) * b2 + k2 + k1 + 1] == -2) {
                                            this.adjacentTreeBlocks[(i2 + k1) * j1 + (j2 + k1) * b2 + k2 + k1 + 1] = l2;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                int l2 = this.adjacentTreeBlocks[k1 * j1 + k1 * b2 + k1];
                if (l2 >= 0) {
                    par1World.setBlockMetadata(par2, par3, par4, l & 0xFFFFFFF7, 4);
                }
                else {
                    this.removeLeaves(par1World, par2, par3, par4);
                }
            }
        }
    }
    
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(final World par1World, final int par2, final int par3, final int par4, final Random par5Random) {
        if (par1World.canLightningStrikeAt(par2, par3 + 1, par4) && !par1World.doesBlockHaveSolidTopSurface(par2, par3 - 1, par4) && par5Random.nextInt(15) == 1) {
            final double d0 = par2 + par5Random.nextFloat();
            final double d2 = par3 - 0.05;
            final double d3 = par4 + par5Random.nextFloat();
            par1World.spawnParticle("dripWater", d0, d2, d3, 0.0, 0.0, 0.0);
        }
    }
    
    private void removeLeaves(final World par1World, final int par2, final int par3, final int par4) {
        this.dropBlockAsItem(par1World, par2, par3, par4, par1World.getBlockMetadata(par2, par3, par4), 0);
        par1World.setBlockToAir(par2, par3, par4);
    }
    
    public int quantityDropped(final Random par1Random) {
        return (par1Random.nextInt(20) == 0) ? 1 : 0;
    }
    
    public int idDropped(final int par1, final Random par2Random, final int par3) {
        return Block.sapling.blockID;
    }
    
    public void dropBlockAsItemWithChance(final World par1World, final int par2, final int par3, final int par4, final int par5, final float par6, final int par7) {
        if (!par1World.isRemote) {
            int j1 = 20;
            if ((par5 & 0x3) == 0x3) {
                j1 = 40;
            }
            if (par7 > 0) {
                j1 -= 2 << par7;
                if (j1 < 10) {
                    j1 = 10;
                }
            }
            if (par1World.rand.nextInt(j1) == 0) {
                final int k1 = this.idDropped(par5, par1World.rand, par7);
                this.dropBlockAsItem_do(par1World, par2, par3, par4, new ItemStack(k1, 1, this.damageDropped(par5)));
            }
            j1 = 200;
            if (par7 > 0) {
                j1 -= 10 << par7;
                if (j1 < 40) {
                    j1 = 40;
                }
            }
            if ((par5 & 0x3) == 0x0 && par1World.rand.nextInt(j1) == 0) {
                this.dropBlockAsItem_do(par1World, par2, par3, par4, new ItemStack(Item.appleRed, 1, 0));
            }
        }
    }
    
    public void harvestBlock(final World par1World, final EntityPlayer par2EntityPlayer, final int par3, final int par4, final int par5, final int par6) {
        super.harvestBlock(par1World, par2EntityPlayer, par3, par4, par5, par6);
    }
    
    public int damageDropped(final int par1) {
        return par1 & 0x3;
    }
    
    public boolean isOpaqueCube() {
        return !this.graphicsLevel;
    }
    
    @SideOnly(Side.CLIENT)
    public Icon getIcon(final int par1, final int par2) {
        return ((par2 & 0x3) == 0x2) ? this.iconArray[this.iconType][2] : (((par2 & 0x3) == 0x3) ? this.iconArray[this.iconType][3] : (((par2 & 0x3) == 0x1) ? this.iconArray[this.iconType][1] : this.iconArray[this.iconType][0]));
    }
    
    @SideOnly(Side.CLIENT)
    public void setGraphicsLevel(final boolean par1) {
        this.graphicsLevel = par1;
        this.iconType = (par1 ? 0 : 1);
    }
    
    public void registerIcons(final IconRegister par1IconRegister) {
        for (int i = 0; i < BlockVileLeaves.field_94396_b.length; ++i) {
            this.iconArray[i] = new Icon[BlockVileLeaves.field_94396_b[i].length];
            for (int j = 0; j < BlockVileLeaves.field_94396_b[i].length; ++j) {
                this.iconArray[i][j] = par1IconRegister.registerIcon(BlockVileLeaves.field_94396_b[i][j]);
            }
        }
    }
    
    public boolean isShearable(final ItemStack item, final World world, final int x, final int y, final int z) {
        return true;
    }
    
    public ArrayList<ItemStack> onSheared(final ItemStack item, final World world, final int x, final int y, final int z, final int fortune) {
        final ArrayList ret = new ArrayList();
        ret.add(new ItemStack((Block)this, 1, world.getBlockMetadata(x, y, z) & 0x3));
        return (ArrayList<ItemStack>)ret;
    }
    
    public void beginLeavesDecay(final World world, final int x, final int y, final int z) {
        world.setBlockMetadata(x, y, z, world.getBlockMetadata(x, y, z) | 0x8, 4);
    }
    
    public boolean isLeaves(final World world, final int x, final int y, final int z) {
        return true;
    }
    
    static {
        field_94396_b = new String[][] { { "leaves_vile" }, { "leaves_vile" } };
    }
}
