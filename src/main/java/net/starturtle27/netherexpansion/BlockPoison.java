//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package net.netherX;

import net.minecraft.util.*;
import cpw.mods.fml.relauncher.*;
import net.minecraftforge.fluids.*;
import net.minecraft.block.material.*;
import net.minecraft.world.*;
import net.minecraft.entity.player.*;
import net.minecraft.potion.*;
import net.minecraft.entity.*;
import net.minecraft.entity.monster.*;
import net.minecraft.client.renderer.texture.*;

public class BlockPoison extends BlockFluidClassic
{
    @SideOnly(Side.CLIENT)
    static Icon poison;
    @SideOnly(Side.CLIENT)
    static Icon poisonMoving;
    
    public BlockPoison(final int id, final Fluid fluid, final Material material) {
        super(id, NetherX.poison, Material.water);
    }
    
    public boolean displaceIfPossible(final World world, final int x, final int y, final int z) {
        return !world.getBlockMaterial(x, y, z).isLiquid() && super.displaceIfPossible(world, x, y, z);
    }
    
    public boolean canDisplace(final IBlockAccess world, final int x, final int y, final int z) {
        return !world.getBlockMaterial(x, y, z).isLiquid() && super.canDisplace(world, x, y, z);
    }
    
    public void onEntityCollidedWithBlock(final World par1World, final int par2, final int par3, final int par4, final Entity par5Entity) {
        if (par5Entity instanceof EntityPlayer && !((EntityPlayer)par5Entity).isPotionActive(Potion.poison)) {
            ((EntityPlayer)par5Entity).addPotionEffect(new PotionEffect(Potion.poison.id, 100, 1));
        }
        if (par5Entity instanceof EntityLiving && !(par5Entity instanceof EntitySlime) && !((EntityLiving)par5Entity).isPotionActive(Potion.poison)) {
            ((EntityLiving)par5Entity).addPotionEffect(new PotionEffect(Potion.poison.id, 100, 1));
        }
    }
    
    public void registerIcons(final IconRegister par1IconRegister) {
        BlockPoison.poison = par1IconRegister.registerIcon(this.getTextureName() + "_still");
        BlockPoison.poisonMoving = par1IconRegister.registerIcon(this.getTextureName() + "_flow");
    }
    
    public Icon getIcon(final int side, final int meta) {
        return (side == 0 || side == 1) ? BlockPoison.poison : BlockPoison.poisonMoving;
    }
}
