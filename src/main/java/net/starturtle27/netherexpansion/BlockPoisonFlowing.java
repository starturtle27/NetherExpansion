//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package net.netherX;

import net.minecraft.block.*;
import net.minecraft.util.*;
import cpw.mods.fml.relauncher.*;
import net.minecraft.block.material.*;
import net.minecraft.world.*;
import net.minecraft.entity.player.*;
import net.minecraft.potion.*;
import net.minecraft.entity.*;
import net.minecraft.entity.monster.*;
import net.minecraft.client.renderer.texture.*;

public class BlockPoisonFlowing extends BlockFlowing
{
    @SideOnly(Side.CLIENT)
    private static Icon poison;
    
    protected BlockPoisonFlowing(final int par1, final Material par2Material) {
        super(par1, par2Material);
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
        BlockPoisonFlowing.poison = par1IconRegister.registerIcon(this.getTextureName());
    }
    
    public Icon getIcon(final int par1, final int par2) {
        return BlockPoisonFlowing.poison;
    }
}
