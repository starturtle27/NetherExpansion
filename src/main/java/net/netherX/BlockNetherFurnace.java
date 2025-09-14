//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package net.netherX;

import java.util.*;
import cpw.mods.fml.relauncher.*;
import net.minecraft.block.material.*;
import net.minecraft.world.*;
import net.minecraft.block.*;
import net.minecraft.client.renderer.texture.*;
import net.minecraft.entity.player.*;
import net.minecraft.tileentity.*;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.entity.item.*;
import net.minecraft.nbt.*;
import net.minecraft.entity.*;
import net.minecraft.inventory.*;

public class BlockNetherFurnace extends BlockContainer
{
    private final Random furnaceRand;
    private final boolean isActive;
    private static boolean keepFurnaceInventory;
    @SideOnly(Side.CLIENT)
    private Icon furnaceIconTop;
    @SideOnly(Side.CLIENT)
    private Icon furnaceIconFront;
    
    protected BlockNetherFurnace(final int par1, final boolean par2) {
        super(par1, Material.rock);
        this.furnaceRand = new Random();
        this.isActive = par2;
    }
    
    public int idDropped(final int par1, final Random par2Random, final int par3) {
        return NetherX.netherfurnaceIdle.blockID;
    }
    
    public void onBlockAdded(final World par1World, final int par2, final int par3, final int par4) {
        super.onBlockAdded(par1World, par2, par3, par4);
        this.setDefaultDirection(par1World, par2, par3, par4);
    }
    
    private void setDefaultDirection(final World w, final int par2, final int par3, final int par4) {
        if (w.isRemote) {
            return;
        }
        final int i = w.getBlockId(par2, par3, par4 - 1);
        final int j = w.getBlockId(par2, par3, par4 + 1);
        final int k = w.getBlockId(par2 - 1, par3, par4);
        final int l = w.getBlockId(par2 + 1, par3, par4);
        byte byte0 = 3;
        if (Block.opaqueCubeLookup[i] && !Block.opaqueCubeLookup[j]) {
            byte0 = 3;
        }
        if (Block.opaqueCubeLookup[j] && !Block.opaqueCubeLookup[i]) {
            byte0 = 2;
        }
        if (Block.opaqueCubeLookup[k] && !Block.opaqueCubeLookup[l]) {
            byte0 = 5;
        }
        if (Block.opaqueCubeLookup[l] && !Block.opaqueCubeLookup[k]) {
            byte0 = 4;
        }
        w.setBlockMetadata(par2, par3, par4, (int)byte0, 2);
    }
    
    @SideOnly(Side.CLIENT)
    public Icon getIcon(final int par1, final int par2) {
        if (par2 == 0 && par1 == 3) {
            return this.furnaceIconFront;
        }
        return (par1 != par2) ? this.blockIcon : ((par1 == 0) ? this.furnaceIconTop : ((par1 == 1) ? this.furnaceIconTop : this.furnaceIconFront));
    }
    
    @SideOnly(Side.CLIENT)
    public void registerIcons(final IconRegister par1IconRegister) {
        this.blockIcon = par1IconRegister.registerIcon("netherfurnace_side");
        this.furnaceIconFront = par1IconRegister.registerIcon(this.isActive ? "netherfurnace_front_on" : "netherfurnace_front_off");
        this.furnaceIconTop = par1IconRegister.registerIcon("netherfurnace_side");
    }
    
    public boolean onBlockActivated(final World par1World, final int par2, final int par3, final int par4, final EntityPlayer par5EntityPlayer, final int par6, final float par7, final float par8, final float par9) {
        if (!par5EntityPlayer.isSneaking()) {
            final TileEntityNetherFurnace tileentityfurnace = (TileEntityNetherFurnace)par1World.getBlockTileEntity(par2, par3, par4);
            if (tileentityfurnace != null) {
                par5EntityPlayer.openGui((Object)NetherX.instance, 0, par1World, par2, par3, par4);
            }
            return true;
        }
        return true;
    }
    
    public static void updateFurnaceBlockState(final boolean par0, final World par1World, final int par2, final int par3, final int par4) {
        final int l = par1World.getBlockMetadata(par2, par3, par4);
        final TileEntity tileentity = par1World.getBlockTileEntity(par2, par3, par4);
        BlockNetherFurnace.keepFurnaceInventory = true;
        if (par0) {
            par1World.setBlock(par2, par3, par4, NetherX.netherfurnaceBurning.blockID);
        }
        else {
            par1World.setBlock(par2, par3, par4, NetherX.netherfurnaceIdle.blockID);
        }
        BlockNetherFurnace.keepFurnaceInventory = false;
        par1World.setBlockMetadata(par2, par3, par4, l, 2);
        if (tileentity != null) {
            tileentity.validate();
            par1World.setBlockTileEntity(par2, par3, par4, tileentity);
        }
    }
    
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(final World par1World, final int par2, final int par3, final int par4, final Random par5Random) {
        if (this.isActive) {
            final int l = par1World.getBlockMetadata(par2, par3, par4);
            final float f = par2 + 0.5f;
            final float f2 = par3 + 0.0f + par5Random.nextFloat() * 6.0f / 16.0f;
            final float f3 = par4 + 0.5f;
            final float f4 = 0.52f;
            final float f5 = par5Random.nextFloat() * 0.6f - 0.3f;
            if (l == 4) {
                par1World.spawnParticle("smoke", (double)(f - f4), (double)f2, (double)(f3 + f5), 0.0, 0.0, 0.0);
                par1World.spawnParticle("flame", (double)(f - f4), (double)f2, (double)(f3 + f5), 0.0, 0.0, 0.0);
            }
            else if (l == 5) {
                par1World.spawnParticle("smoke", (double)(f + f4), (double)f2, (double)(f3 + f5), 0.0, 0.0, 0.0);
                par1World.spawnParticle("flame", (double)(f + f4), (double)f2, (double)(f3 + f5), 0.0, 0.0, 0.0);
            }
            else if (l == 2) {
                par1World.spawnParticle("smoke", (double)(f + f5), (double)f2, (double)(f3 - f4), 0.0, 0.0, 0.0);
                par1World.spawnParticle("flame", (double)(f + f5), (double)f2, (double)(f3 - f4), 0.0, 0.0, 0.0);
            }
            else if (l == 3) {
                par1World.spawnParticle("smoke", (double)(f + f5), (double)f2, (double)(f3 + f4), 0.0, 0.0, 0.0);
                par1World.spawnParticle("flame", (double)(f + f5), (double)f2, (double)(f3 + f4), 0.0, 0.0, 0.0);
            }
        }
    }
    
    public TileEntity createNewTileEntity(final World par1World) {
        return new TileEntityNetherFurnace();
    }
    
    public void onBlockPlacedBy(final World par1World, final int par2, final int par3, final int par4, final EntityLivingBase par5EntityLivingBase, final ItemStack par6ItemStack) {
        final int l = MathHelper.floor_double(par5EntityLivingBase.rotationYaw * 4.0f / 360.0f + 0.5) & 0x3;
        if (l == 0) {
            par1World.setBlockMetadata(par2, par3, par4, 2, 2);
        }
        if (l == 1) {
            par1World.setBlockMetadata(par2, par3, par4, 5, 2);
        }
        if (l == 2) {
            par1World.setBlockMetadata(par2, par3, par4, 3, 2);
        }
        if (l == 3) {
            par1World.setBlockMetadata(par2, par3, par4, 4, 2);
        }
        if (par6ItemStack.hasDisplayName()) {
            ((TileEntityNetherFurnace)par1World.getBlockTileEntity(par2, par3, par4)).setGuiDisplayName(par6ItemStack.getDisplayName());
        }
    }
    
    public void breakBlock(final World par1World, final int par2, final int par3, final int par4, final int par5, final int par6) {
        if (!BlockNetherFurnace.keepFurnaceInventory) {
            final TileEntityNetherFurnace tileentityfurnace = (TileEntityNetherFurnace)par1World.getBlockTileEntity(par2, par3, par4);
            if (tileentityfurnace != null) {
                for (int j1 = 0; j1 < tileentityfurnace.getSizeInventory(); ++j1) {
                    final ItemStack itemstack = tileentityfurnace.getStackInSlot(j1);
                    if (itemstack != null) {
                        final float f = this.furnaceRand.nextFloat() * 0.8f + 0.1f;
                        final float f2 = this.furnaceRand.nextFloat() * 0.8f + 0.1f;
                        final float f3 = this.furnaceRand.nextFloat() * 0.8f + 0.1f;
                        while (itemstack.stackSize > 0) {
                            int k1 = this.furnaceRand.nextInt(21) + 10;
                            if (k1 > itemstack.stackSize) {
                                k1 = itemstack.stackSize;
                            }
                            final ItemStack itemStack = itemstack;
                            itemStack.stackSize -= k1;
                            final EntityItem entityitem = new EntityItem(par1World, (double)(par2 + f), (double)(par3 + f2), (double)(par4 + f3), new ItemStack(itemstack.itemID, k1, itemstack.getItemDamage()));
                            if (itemstack.hasTagCompound()) {
                                entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
                            }
                            final float f4 = 0.05f;
                            entityitem.motionX = (float)this.furnaceRand.nextGaussian() * f4;
                            entityitem.motionY = (float)this.furnaceRand.nextGaussian() * f4 + 0.2f;
                            entityitem.motionZ = (float)this.furnaceRand.nextGaussian() * f4;
                            par1World.spawnEntityInWorld((Entity)entityitem);
                        }
                    }
                }
                par1World.func_96440_m(par2, par3, par4, par5);
            }
        }
        super.breakBlock(par1World, par2, par3, par4, par5, par6);
    }
    
    public boolean hasComparatorInputOverride() {
        return true;
    }
    
    public int getComparatorInputOverride(final World par1World, final int par2, final int par3, final int par4, final int par5) {
        return Container.calcRedstoneFromInventory((IInventory)par1World.getBlockTileEntity(par2, par3, par4));
    }
    
    @SideOnly(Side.CLIENT)
    public int idPicked(final World par1World, final int par2, final int par3, final int par4) {
        return NetherX.netherfurnaceIdle.blockID;
    }
}
