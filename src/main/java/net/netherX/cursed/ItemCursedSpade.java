//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package net.netherX.cursed;

import net.minecraft.creativetab.*;
import net.minecraft.item.*;
import net.minecraft.entity.*;
import net.netherX.*;
import net.minecraft.potion.*;
import net.minecraft.entity.player.*;
import java.util.*;
import cpw.mods.fml.relauncher.*;

public class ItemCursedSpade extends ItemSpade
{
    public ItemCursedSpade(final int par1, final EnumToolMaterial par2EnumToolMaterial) {
        super(par1, par2EnumToolMaterial);
        this.setCreativeTab((CreativeTabs)null);
    }
    
    public boolean hitEntity(final ItemStack par1ItemStack, final EntityLivingBase par2EntityLivingBase, final EntityLivingBase par3EntityLivingBase) {
        if (this.itemID == NetherX.shovelVenomiteCursed.itemID) {
            par2EntityLivingBase.addPotionEffect(new PotionEffect(Potion.poison.id, 60, 0));
        }
        if (this.itemID == NetherX.shovelNecromiteCursed.itemID) {
            par2EntityLivingBase.addPotionEffect(new PotionEffect(Potion.wither.id, 50, 0));
        }
        par1ItemStack.damageItem(1, par3EntityLivingBase);
        return true;
    }
    
    public void addInformation(final ItemStack stack, final EntityPlayer player, final List list, final boolean par4) {
        list.add("Cursed");
    }
    
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(final ItemStack par1ItemStack) {
        return true;
    }
}
