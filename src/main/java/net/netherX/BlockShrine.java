//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package net.netherX;

import net.minecraft.block.*;
import net.minecraft.util.*;
import cpw.mods.fml.relauncher.*;
import net.minecraft.block.material.*;
import net.minecraft.client.renderer.texture.*;
import net.minecraft.world.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.*;

public class BlockShrine extends Block
{
    @SideOnly(Side.CLIENT)
    public Icon shrineIconTop;
    @SideOnly(Side.CLIENT)
    public Icon shrineIconFront;
    
    public BlockShrine(final int par1) {
        super(par1, Material.rock);
    }
    
    public Icon getIcon(final int par1, final int par2) {
        return (par1 == 1) ? this.shrineIconTop : ((par1 == 0) ? Block.netherBrick.getBlockTextureFromSide(par1) : ((par1 != 2 && par1 != 4) ? this.blockIcon : this.shrineIconFront));
    }
    
    @SideOnly(Side.CLIENT)
    public void registerIcons(final IconRegister iconRegister) {
        this.blockIcon = iconRegister.registerIcon(this.getTextureName() + "_side");
        this.shrineIconTop = iconRegister.registerIcon(this.getTextureName() + "_top");
        this.shrineIconFront = iconRegister.registerIcon(this.getTextureName() + "_front");
    }
    
    public boolean onBlockActivated(final World par1World, final int par2, final int par3, final int par4, final EntityPlayer par5EntityPlayer, final int par6, final float par7, final float par8, final float par9) {
        par1World.setBlock(par2, par3, par4, NetherX.netherShrineSummoning.blockID);
        par1World.playSoundAtEntity((Entity)par5EntityPlayer, "mob.wither.spawn", 1.0f, 1.0f);
        return true;
    }
    
    public void onNeighborBlockChange(final World par1World, final int par2, final int par3, final int par4, final int par5) {
        if (!par1World.isRemote) {
            if (!par1World.isBlockIndirectlyGettingPowered(par2, par3, par4)) {
                par1World.scheduleBlockUpdate(par2, par3, par4, this.blockID, 4);
            }
            else if (par1World.isBlockIndirectlyGettingPowered(par2, par3, par4)) {
                par1World.setBlock(par2, par3, par4, NetherX.netherShrineSummoning.blockID);
                par1World.playSoundAtEntity((Entity)par1World.getClosestPlayer((double)par2, (double)par3, (double)par4, (double)par5), "mob.wither.spawn", 1.0f, 1.0f);
            }
        }
    }
    
    public void summon(final World par1World, final int par2, final int par3, final int par4, final EntityLivingBase par6EntityLivingBase) {
        final float f = 4.0f;
        par1World.createExplosion((Entity)par6EntityLivingBase, (double)par2, (double)par3, (double)par4, f, true);
    }
}
