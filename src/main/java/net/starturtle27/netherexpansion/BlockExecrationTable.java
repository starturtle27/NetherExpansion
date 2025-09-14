//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package net.netherX;

import net.minecraft.util.*;
import cpw.mods.fml.relauncher.*;
import net.minecraft.block.material.*;
import net.minecraft.world.*;
import java.util.*;
import net.minecraft.block.*;
import net.minecraft.tileentity.*;
import net.minecraft.entity.player.*;
import net.minecraft.client.renderer.texture.*;

public class BlockExecrationTable extends BlockContainer
{
    @SideOnly(Side.CLIENT)
    private Icon field_94461_a;
    @SideOnly(Side.CLIENT)
    private Icon field_94460_b;
    
    protected BlockExecrationTable(final int par1) {
        super(par1, Material.rock);
        this.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 0.75f, 1.0f);
        this.setLightOpacity(0);
    }
    
    public boolean renderAsNormalBlock() {
        return false;
    }
    
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(final World par1World, final int par2, final int par3, final int par4, final Random par5Random) {
        super.randomDisplayTick(par1World, par2, par3, par4, par5Random);
        for (int l = par2 - 2; l <= par2 + 2; ++l) {
            for (int i1 = par4 - 2; i1 <= par4 + 2; ++i1) {
                if (l > par2 - 2 && l < par2 + 2 && i1 == par4 - 1) {
                    i1 = par4 + 2;
                }
                if (par5Random.nextInt(16) == 0) {
                    for (int j1 = par3; j1 <= par3 + 1; ++j1) {
                        if (par1World.getBlockId(l, j1, i1) == Block.bookShelf.blockID) {
                            if (!par1World.isAirBlock((l - par2) / 2 + par2, j1, (i1 - par4) / 2 + par4)) {
                                break;
                            }
                            par1World.spawnParticle("enchantmenttable", par2 + 0.5, par3 + 2.0, par4 + 0.5, l - par2 + par5Random.nextFloat() - 0.5, (double)(j1 - par3 - par5Random.nextFloat() - 1.0f), i1 - par4 + par5Random.nextFloat() - 0.5);
                        }
                    }
                }
            }
        }
    }
    
    public boolean isOpaqueCube() {
        return false;
    }
    
    @SideOnly(Side.CLIENT)
    public Icon getIcon(final int par1, final int par2) {
        return (par1 == 1) ? this.field_94461_a : ((par1 == 0) ? this.field_94460_b : this.blockIcon);
    }
    
    public TileEntity createNewTileEntity(final World par1World) {
        return new TileEntityCurse();
    }
    
    public boolean onBlockActivated(final World par1World, final int par2, final int par3, final int par4, final EntityPlayer par5EntityPlayer, final int par6, final float par7, final float par8, final float par9) {
        if (!par5EntityPlayer.isSneaking()) {
            final TileEntityCurse tileentitycurse = (TileEntityCurse)par1World.getBlockTileEntity(par2, par3, par4);
            if (tileentitycurse != null) {
                par5EntityPlayer.openGui((Object)NetherX.instance, 1, par1World, par2, par3, par4);
            }
            return true;
        }
        return true;
    }
    
    @SideOnly(Side.CLIENT)
    public void registerIcons(final IconRegister par1IconRegister) {
        this.blockIcon = par1IconRegister.registerIcon(this.getTextureName() + "_" + "side");
        this.field_94461_a = par1IconRegister.registerIcon(this.getTextureName() + "_" + "top");
        this.field_94460_b = par1IconRegister.registerIcon(this.getTextureName() + "_" + "bottom");
    }
}
