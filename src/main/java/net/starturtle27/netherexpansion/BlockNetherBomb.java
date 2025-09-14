//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package net.netherX;

import net.minecraft.block.*;
import net.minecraft.util.*;
import cpw.mods.fml.relauncher.*;
import net.minecraft.block.material.*;
import net.minecraft.creativetab.*;
import java.util.*;
import net.minecraft.world.*;
import net.minecraft.entity.item.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.entity.projectile.*;
import net.minecraft.client.renderer.texture.*;

public class BlockNetherBomb extends Block
{
    @SideOnly(Side.CLIENT)
    private Icon field_94393_a;
    @SideOnly(Side.CLIENT)
    private Icon field_94392_b;
    
    public BlockNetherBomb(final int par1) {
        super(par1, Material.tnt);
        this.setCreativeTab(CreativeTabs.tabRedstone);
    }
    
    @SideOnly(Side.CLIENT)
    public Icon getIcon(final int par1, final int par2) {
        return (par1 == 1) ? this.field_94393_a : ((par1 == 0) ? this.field_94392_b : this.blockIcon);
    }
    
    public void onBlockAdded(final World par1World, final int par2, final int par3, final int par4) {
        super.onBlockAdded(par1World, par2, par3, par4);
        if (par1World.isBlockIndirectlyGettingPowered(par2, par3, par4)) {
            this.onBlockDestroyedByPlayer(par1World, par2, par3, par4, 1);
            par1World.setBlockToAir(par2, par3, par4);
        }
    }
    
    public void onNeighborBlockChange(final World par1World, final int par2, final int par3, final int par4, final int par5) {
        if (par1World.isBlockIndirectlyGettingPowered(par2, par3, par4)) {
            this.onBlockDestroyedByPlayer(par1World, par2, par3, par4, 1);
            par1World.setBlockToAir(par2, par3, par4);
        }
    }
    
    public int quantityDropped(final Random par1Random) {
        return 1;
    }
    
    public void onBlockDestroyedByExplosion(final World par1World, final int par2, final int par3, final int par4, final Explosion par5Explosion) {
        if (!par1World.isRemote) {
            final EntityTNTPrimed entitytntprimed = new EntityTNTPrimed(par1World, (double)(par2 + 0.5f), (double)(par3 + 0.5f), (double)(par4 + 0.5f), par5Explosion.getExplosivePlacedBy());
            entitytntprimed.fuse = 0;
            par1World.spawnEntityInWorld((Entity)entitytntprimed);
        }
    }
    
    public void onBlockDestroyedByPlayer(final World par1World, final int par2, final int par3, final int par4, final int par5) {
        this.primeTnt(par1World, par2, par3, par4, par5, null);
    }
    
    public void primeTnt(final World par1World, final int par2, final int par3, final int par4, final int par5, final EntityLivingBase par6EntityLivingBase) {
        if (!par1World.isRemote && (par5 & 0x1) == 0x1) {
            final EntityTNTPrimed entitytntprimed = new EntityTNTPrimed(par1World, (double)(par2 + 0.5f), (double)(par3 + 0.5f), (double)(par4 + 0.5f), par6EntityLivingBase);
            entitytntprimed.fuse = 0;
            par1World.spawnEntityInWorld((Entity)entitytntprimed);
            par1World.playSoundAtEntity((Entity)entitytntprimed, "random.fuse", 1.0f, 1.0f);
        }
    }
    
    public boolean onBlockActivated(final World par1World, final int par2, final int par3, final int par4, final EntityPlayer par5EntityPlayer, final int par6, final float par7, final float par8, final float par9) {
        if (par5EntityPlayer.getCurrentEquippedItem() != null && par5EntityPlayer.getCurrentEquippedItem().itemID == Item.flintAndSteel.itemID) {
            this.primeTnt(par1World, par2, par3, par4, 1, (EntityLivingBase)par5EntityPlayer);
            par1World.setBlockToAir(par2, par3, par4);
            par5EntityPlayer.getCurrentEquippedItem().damageItem(1, (EntityLivingBase)par5EntityPlayer);
            return true;
        }
        return super.onBlockActivated(par1World, par2, par3, par4, par5EntityPlayer, par6, par7, par8, par9);
    }
    
    public void onEntityCollidedWithBlock(final World par1World, final int par2, final int par3, final int par4, final Entity par5Entity) {
        if (par5Entity instanceof EntityArrow && !par1World.isRemote) {
            final EntityArrow entityarrow = (EntityArrow)par5Entity;
            if (entityarrow.isBurning()) {
                this.primeTnt(par1World, par2, par3, par4, 1, (entityarrow.shootingEntity instanceof EntityLivingBase) ? entityarrow.shootingEntity : null);
                par1World.setBlockToAir(par2, par3, par4);
            }
        }
    }
    
    public boolean canDropFromExplosion(final Explosion par1Explosion) {
        return false;
    }
    
    @SideOnly(Side.CLIENT)
    public void registerIcons(final IconRegister par1IconRegister) {
        this.blockIcon = par1IconRegister.registerIcon(this.getTextureName() + "_side");
        this.field_94393_a = par1IconRegister.registerIcon(this.getTextureName() + "_top");
        this.field_94392_b = par1IconRegister.registerIcon(this.getTextureName() + "_bottom");
    }
}
