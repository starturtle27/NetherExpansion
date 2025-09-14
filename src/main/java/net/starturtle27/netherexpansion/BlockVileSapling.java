//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package net.netherX;

import net.minecraft.util.*;
import cpw.mods.fml.relauncher.*;
import net.minecraft.creativetab.*;
import net.minecraft.world.*;
import java.util.*;
import net.minecraftforge.event.terraingen.*;
import net.minecraft.world.gen.feature.*;
import net.minecraft.client.renderer.texture.*;
import net.minecraft.block.*;

public class BlockVileSapling extends BlockSapling
{
    public static final String[] WOOD_TYPES;
    @SideOnly(Side.CLIENT)
    private Icon[] saplingIcon;
    
    protected BlockVileSapling(final int par1) {
        super(par1);
        final float f = 0.4f;
        this.setBlockBounds(0.5f - f, 0.0f, 0.5f - f, 0.5f + f, f * 2.0f, 0.5f + f);
        this.setCreativeTab(CreativeTabs.tabDecorations);
    }
    
    public void updateTick(final World par1World, final int par2, final int par3, final int par4, final Random par5Random) {
        if (!par1World.isRemote) {
            super.updateTick(par1World, par2, par3, par4, par5Random);
            if (par1World.getBlockLightValue(par2, par3 + 1, par4) >= 9 && par5Random.nextInt(7) == 0) {
                this.markOrGrowMarked(par1World, par2, par3, par4, par5Random);
            }
        }
    }
    
    @SideOnly(Side.CLIENT)
    public Icon getIcon(final int par1, int par2) {
        par2 &= 0x3;
        return this.saplingIcon[par2];
    }
    
    public void markOrGrowMarked(final World par1World, final int par2, final int par3, final int par4, final Random par5Random) {
        final int l = par1World.getBlockMetadata(par2, par3, par4);
        if ((l & 0x8) == 0x0) {
            par1World.setBlockMetadata(par2, par3, par4, l | 0x8, 4);
        }
        else {
            this.growTree(par1World, par2, par3, par4, par5Random);
        }
    }
    
    public void growTree(final World par1World, final int par2, final int par3, final int par4, final Random par5Random) {
        if (!TerrainGen.saplingGrowTree(par1World, par5Random, par2, par3, par4)) {
            return;
        }
        final int l = par1World.getBlockMetadata(par2, par3, par4) & 0x3;
        Object object = null;
        int i1 = 0;
        int j1 = 0;
        boolean flag = false;
        if (l == 1) {
            object = new WorldGenTaiga2(true);
        }
        else if (l == 2) {
            object = new WorldGenForest(true);
        }
        else if (l == 3) {
            for (i1 = 0; i1 >= -1; --i1) {
                for (j1 = 0; j1 >= -1; --j1) {
                    if (this.isSameSapling(par1World, par2 + i1, par3, par4 + j1, 3) && this.isSameSapling(par1World, par2 + i1 + 1, par3, par4 + j1, 3) && this.isSameSapling(par1World, par2 + i1, par3, par4 + j1 + 1, 3) && this.isSameSapling(par1World, par2 + i1 + 1, par3, par4 + j1 + 1, 3)) {
                        object = new WorldGenHugeTrees(true, 10 + par5Random.nextInt(20), 3, 3);
                        flag = true;
                        break;
                    }
                }
                if (object != null) {
                    break;
                }
            }
            if (object == null) {
                j1 = 0;
                i1 = 0;
                object = new WorldGenTrees(true, 4 + par5Random.nextInt(7), 3, 3, false);
            }
        }
        else {
            object = new WorldGenTrees(true);
            if (par5Random.nextInt(10) == 0) {
                object = new WorldGenBigTree(true);
            }
        }
        if (flag) {
            par1World.setBlock(par2 + i1, par3, par4 + j1, 0, 0, 4);
            par1World.setBlock(par2 + i1 + 1, par3, par4 + j1, 0, 0, 4);
            par1World.setBlock(par2 + i1, par3, par4 + j1 + 1, 0, 0, 4);
            par1World.setBlock(par2 + i1 + 1, par3, par4 + j1 + 1, 0, 0, 4);
        }
        else {
            par1World.setBlock(par2, par3, par4, 0, 0, 4);
        }
        if (!((WorldGenerator)object).generate(par1World, par5Random, par2 + i1, par3, par4 + j1)) {
            if (flag) {
                par1World.setBlock(par2 + i1, par3, par4 + j1, this.blockID, l, 4);
                par1World.setBlock(par2 + i1 + 1, par3, par4 + j1, this.blockID, l, 4);
                par1World.setBlock(par2 + i1, par3, par4 + j1 + 1, this.blockID, l, 4);
                par1World.setBlock(par2 + i1 + 1, par3, par4 + j1 + 1, this.blockID, l, 4);
            }
            else {
                par1World.setBlock(par2, par3, par4, this.blockID, l, 4);
            }
        }
    }
    
    public boolean isSameSapling(final World par1World, final int par2, final int par3, final int par4, final int par5) {
        return par1World.getBlockId(par2, par3, par4) == this.blockID && (par1World.getBlockMetadata(par2, par3, par4) & 0x3) == par5;
    }
    
    public int damageDropped(final int par1) {
        return par1 & 0x3;
    }
    
    @SideOnly(Side.CLIENT)
    public void registerIcons(final IconRegister par1IconRegister) {
        this.saplingIcon = new Icon[BlockVileSapling.WOOD_TYPES.length];
        for (int i = 0; i < this.saplingIcon.length; ++i) {
            this.saplingIcon[i] = par1IconRegister.registerIcon(this.getTextureName() + "_" + BlockVileSapling.WOOD_TYPES[i]);
        }
    }
    
    protected boolean canThisPlantGrowOnThisBlockID(final int par1) {
        return par1 == Block.slowSand.blockID || par1 == Block.netherrack.blockID;
    }
    
    static {
        WOOD_TYPES = new String[] { "vile" };
    }
}
