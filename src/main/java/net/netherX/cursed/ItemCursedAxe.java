//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package net.netherX.cursed;

import net.minecraft.creativetab.*;
import net.minecraft.item.*;
import net.minecraft.entity.player.*;
import java.util.*;
import cpw.mods.fml.relauncher.*;
import net.minecraft.entity.*;
import net.netherX.*;
import net.minecraft.potion.*;

public class ItemCursedAxe extends ItemAxe
{
    public ItemCursedAxe(final int par1, final EnumToolMaterial par2EnumToolMaterial) {
        super(par1, par2EnumToolMaterial);
        this.setCreativeTab((CreativeTabs)null);
    }
    
    public void addInformation(final ItemStack stack, final EntityPlayer player, final List list, final boolean par4) {
        list.add("Cursed");
    }
    
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(final ItemStack par1ItemStack) {
        return true;
    }
    
    public boolean hitEntity(final ItemStack par1ItemStack, final EntityLivingBase par2EntityLivingBase, final EntityLivingBase par3EntityLivingBase) {
        if (this.itemID == NetherX.axeVenomiteCursed.itemID) {
            par2EntityLivingBase.addPotionEffect(new PotionEffect(Potion.poison.id, 80, 0));
        }
        if (this.itemID == NetherX.axeNecromiteCursed.itemID) {
            par2EntityLivingBase.addPotionEffect(new PotionEffect(Potion.wither.id, 60, 0));
        }
        par1ItemStack.damageItem(1, par3EntityLivingBase);
        return true;
    }
}
