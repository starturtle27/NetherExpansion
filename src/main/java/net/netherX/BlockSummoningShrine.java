//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package net.netherX;

import net.minecraft.block.*;
import net.minecraft.util.*;
import cpw.mods.fml.relauncher.*;
import net.minecraft.block.material.*;
import net.minecraft.client.renderer.texture.*;
import net.minecraft.world.*;
import java.util.*;
import net.minecraft.entity.*;

public class BlockSummoningShrine extends Block
{
    @SideOnly(Side.CLIENT)
    public Icon shrineIconTop;
    @SideOnly(Side.CLIENT)
    public Icon shrineIconFront;
    
    public BlockSummoningShrine(final int par1) {
        super(par1, Material.rock);
        this.setTickRandomly(true);
    }
    
    public Icon getIcon(final int par1, final int par2) {
        return (par1 == 1) ? this.shrineIconTop : ((par1 == 0) ? Block.netherBrick.getBlockTextureFromSide(par1) : ((par1 != 2 && par1 != 4) ? this.blockIcon : this.shrineIconFront));
    }
    
    @SideOnly(Side.CLIENT)
    public void registerIcons(final IconRegister iconRegister) {
        this.blockIcon = iconRegister.registerIcon(this.getTextureName() + "_side");
        this.shrineIconTop = iconRegister.registerIcon(this.getTextureName() + "_top_flashing");
        this.shrineIconFront = iconRegister.registerIcon(this.getTextureName() + "_front");
    }
    
    public void updateTick(final World par1World, final int par2, final int par3, final int par4, final Random par5Random) {
        this.summon(par1World, par2, par3, par4, (EntityLivingBase)par1World.getClosestPlayer((double)par2, (double)par3, (double)par4, 500.0));
    }
    
    public void summon(final World w, final int par2, final int par3, final int par4, final EntityLivingBase par6EntityLivingBase) {
        final float f = 7.0f;
        w.setBlock(par2, par3, par4, 0);
        w.setBlock(par2, par3 + 1, par4, 0);
        w.setBlock(par2, par3 + 2, par4, 0);
        w.setBlock(par2 + 1, par3, par4, 0);
        w.setBlock(par2 + 1, par3 + 1, par4, 0);
        w.setBlock(par2 + 1, par3 + 2, par4, 0);
        w.setBlock(par2 + 2, par3, par4, 0);
        w.setBlock(par2 + 2, par3 + 1, par4, 0);
        w.setBlock(par2 + 2, par3 + 2, par4, 0);
        w.setBlock(par2, par3, par4 + 1, 0);
        w.setBlock(par2, par3 + 1, par4 + 1, 0);
        w.setBlock(par2, par3 + 2, par4 + 1, 0);
        w.setBlock(par2, par3, par4 + 2, 0);
        w.setBlock(par2, par3 + 1, par4 + 2, 0);
        w.setBlock(par2, par3 + 2, par4 + 2, 0);
        w.setBlock(par2 + 1, par3, par4 + 1, 0);
        w.setBlock(par2 + 1, par3 + 1, par4 + 1, 0);
        w.setBlock(par2 + 1, par3 + 2, par4 + 1, 0);
        w.setBlock(par2 + 2, par3, par4 + 1, 0);
        w.setBlock(par2 + 2, par3 + 1, par4 + 1, 0);
        w.setBlock(par2 + 2, par3 + 2, par4 + 1, 0);
        w.setBlock(par2 + 1, par3, par4 + 2, 0);
        w.setBlock(par2 + 1, par3 + 1, par4 + 2, 0);
        w.setBlock(par2 + 1, par3 + 2, par4 + 2, 0);
        w.setBlock(par2 + 2, par3, par4 + 2, 0);
        w.setBlock(par2 + 2, par3 + 1, par4 + 2, 0);
        w.setBlock(par2 + 2, par3 + 2, par4 + 2, 0);
        w.setBlock(par2 - 1, par3, par4, 0);
        w.setBlock(par2 - 1, par3 + 1, par4, 0);
        w.setBlock(par2 - 1, par3 + 2, par4, 0);
        w.setBlock(par2 - 2, par3, par4, 0);
        w.setBlock(par2 - 2, par3 + 1, par4, 0);
        w.setBlock(par2 - 2, par3 + 2, par4, 0);
        w.setBlock(par2 - 1, par3, par4 + 1, 0);
        w.setBlock(par2 - 1, par3 + 1, par4 + 1, 0);
        w.setBlock(par2 - 1, par3 + 2, par4 + 1, 0);
        w.setBlock(par2 - 2, par3, par4 + 1, 0);
        w.setBlock(par2 - 2, par3 + 1, par4 + 1, 0);
        w.setBlock(par2 - 2, par3 + 2, par4 + 1, 0);
        w.setBlock(par2 - 1, par3, par4 + 2, 0);
        w.setBlock(par2 - 1, par3 + 1, par4 + 2, 0);
        w.setBlock(par2 - 1, par3 + 2, par4 + 2, 0);
        w.setBlock(par2 - 2, par3, par4 + 2, 0);
        w.setBlock(par2 - 2, par3 + 1, par4 + 2, 0);
        w.setBlock(par2 - 2, par3 + 2, par4 + 2, 0);
        w.setBlock(par2, par3, par4 - 1, 0);
        w.setBlock(par2, par3 + 1, par4 - 1, 0);
        w.setBlock(par2, par3 + 2, par4 - 1, 0);
        w.setBlock(par2, par3, par4 - 2, 0);
        w.setBlock(par2, par3 + 1, par4 - 2, 0);
        w.setBlock(par2, par3 + 2, par4 - 2, 0);
        w.setBlock(par2 + 1, par3, par4 - 1, 0);
        w.setBlock(par2 + 1, par3 + 1, par4 - 1, 0);
        w.setBlock(par2 + 1, par3 + 2, par4 - 1, 0);
        w.setBlock(par2 + 2, par3, par4 - 1, 0);
        w.setBlock(par2 + 2, par3 + 1, par4 - 1, 0);
        w.setBlock(par2 + 2, par3 + 2, par4 - 1, 0);
        w.setBlock(par2 + 1, par3, par4 - 2, 0);
        w.setBlock(par2 + 1, par3 + 1, par4 - 2, 0);
        w.setBlock(par2 + 1, par3 + 2, par4 - 2, 0);
        w.setBlock(par2 + 2, par3, par4 - 2, 0);
        w.setBlock(par2 + 2, par3 + 1, par4 - 2, 0);
        w.setBlock(par2 + 2, par3 + 2, par4 - 2, 0);
        w.setBlock(par2 - 1, par3, par4 - 1, 0);
        w.setBlock(par2 - 1, par3 + 1, par4 - 1, 0);
        w.setBlock(par2 - 1, par3 + 2, par4 - 1, 0);
        w.setBlock(par2 - 2, par3, par4 - 1, 0);
        w.setBlock(par2 - 2, par3 + 1, par4 - 1, 0);
        w.setBlock(par2 - 2, par3 + 2, par4 - 1, 0);
        w.setBlock(par2 - 1, par3, par4 - 2, 0);
        w.setBlock(par2 - 1, par3 + 1, par4 - 2, 0);
        w.setBlock(par2 - 1, par3 + 2, par4 - 2, 0);
        w.setBlock(par2 - 2, par3, par4 - 2, 0);
        w.setBlock(par2 - 2, par3 + 1, par4 - 2, 0);
        w.setBlock(par2 - 2, par3 + 2, par4 - 2, 0);
        w.createExplosion((Entity)par6EntityLivingBase, (double)par2, (double)par3, (double)par4, f, true);
        if (!w.isRemote) {
            final EntityGhastQueen ghastqueen = new EntityGhastQueen(w);
            ghastqueen.setLocationAndAngles(par2 + 0.5, (double)par3, par4 + 0.5, 0.0f, 0.0f);
            w.spawnEntityInWorld((Entity)ghastqueen);
        }
    }
    
    public int idDropped(final int par1, final Random par2Random, final int par3) {
        return 0;
    }
    
    public int idPicked(final World par1World, final int par2, final int par3, final int par4) {
        return NetherX.netherShrine.blockID;
    }
}
