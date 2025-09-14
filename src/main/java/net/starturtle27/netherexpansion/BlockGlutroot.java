//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package net.netherX;

import net.minecraft.util.*;
import cpw.mods.fml.relauncher.*;
import net.minecraft.creativetab.*;
import net.minecraft.block.*;
import net.minecraft.world.*;
import net.minecraftforge.common.*;
import net.minecraft.client.renderer.texture.*;
import java.util.*;
import net.minecraft.item.*;

public class BlockGlutroot extends BlockFlower
{
    @SideOnly(Side.CLIENT)
    private Icon[] iconArray;
    
    protected BlockGlutroot(final int par1) {
        super(par1);
        this.setTickRandomly(true);
        final float f = 0.5f;
        this.setBlockBounds(0.5f - f, 0.0f, 0.5f - f, 0.5f + f, 0.25f, 0.5f + f);
        this.setCreativeTab((CreativeTabs)null);
    }
    
    protected boolean canThisPlantGrowOnThisBlockID(final int par1) {
        return par1 == Block.slowSand.blockID;
    }
    
    public boolean canBlockStay(final World par1World, final int par2, final int par3, final int par4) {
        final Block block = Block.blocksList[par1World.getBlockId(par2, par3 - 1, par4)];
        return block != null && block.canSustainPlant(par1World, par2, par3 - 1, par4, ForgeDirection.UP, (IPlantable)this);
    }
    
    public void updateTick(final World par1World, final int par2, final int par3, final int par4, final Random par5Random) {
        int l = par1World.getBlockMetadata(par2, par3, par4);
        if (l < 3 && par5Random.nextInt(10) == 0) {
            ++l;
            par1World.setBlockMetadata(par2, par3, par4, l, 2);
        }
        super.updateTick(par1World, par2, par3, par4, par5Random);
    }
    
    @SideOnly(Side.CLIENT)
    public Icon getIcon(final int par1, final int par2) {
        return (par2 >= 3) ? this.iconArray[2] : ((par2 > 0) ? this.iconArray[1] : this.iconArray[0]);
    }
    
    public int getRenderType() {
        return 6;
    }
    
    public void dropBlockAsItemWithChance(final World par1World, final int par2, final int par3, final int par4, final int par5, final float par6, final int par7) {
        super.dropBlockAsItemWithChance(par1World, par2, par3, par4, par5, par6, par7);
    }
    
    public int idDropped(final int par1, final Random par2Random, final int par3) {
        return 0;
    }
    
    public int quantityDropped(final Random par1Random) {
        return 0;
    }
    
    @SideOnly(Side.CLIENT)
    public int idPicked(final World par1World, final int par2, final int par3, final int par4) {
        return NetherX.glutrootSeeds.itemID;
    }
    
    @SideOnly(Side.CLIENT)
    public void registerIcons(final IconRegister par1IconRegister) {
        this.iconArray = new Icon[3];
        for (int i = 0; i < this.iconArray.length; ++i) {
            this.iconArray[i] = par1IconRegister.registerIcon(this.getTextureName() + "_stage_" + i);
        }
    }
    
    public ArrayList<ItemStack> getBlockDropped(final World world, final int x, final int y, final int z, final int metadata, final int fortune) {
        final ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        int count = 1;
        int glutrootCount = 0;
        if (metadata >= 3) {
            count = 1 + world.rand.nextInt(3) + ((fortune > 0) ? world.rand.nextInt(fortune + 1) : 0);
            glutrootCount = 1 + world.rand.nextInt(2) + ((fortune > 0) ? world.rand.nextInt(fortune + 1) : 0);
        }
        for (int i = 0; i < count; ++i) {
            ret.add(new ItemStack(NetherX.glutrootSeeds));
        }
        for (int i = 0; i < glutrootCount; ++i) {
            ret.add(new ItemStack(NetherX.glutroot));
        }
        return ret;
    }
}
