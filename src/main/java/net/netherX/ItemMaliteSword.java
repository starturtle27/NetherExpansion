//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package net.netherX;

import net.minecraft.item.*;
import net.minecraft.entity.*;
import net.minecraft.potion.*;
import net.minecraft.entity.player.*;
import java.util.*;
import cpw.mods.fml.relauncher.*;

public class ItemMaliteSword extends ItemSword
{
    public ItemMaliteSword(final int par1, final EnumToolMaterial par2EnumToolMaterial) {
        super(par1, par2EnumToolMaterial);
    }
    
    public boolean hitEntity(final ItemStack par1ItemStack, final EntityLivingBase par2EntityLivingBase, final EntityLivingBase par3EntityLivingBase) {
        if (this.itemID == NetherX.swordVenomiteCursed.itemID) {
            par2EntityLivingBase.addPotionEffect(new PotionEffect(Potion.poison.id, 240, 0));
        }
        else {
            par2EntityLivingBase.addPotionEffect(new PotionEffect(Potion.poison.id, 120, 0));
        }
        par1ItemStack.damageItem(1, par3EntityLivingBase);
        return true;
    }
    
    public void addInformation(final ItemStack stack, final EntityPlayer player, final List list, final boolean par4) {
        if (this.itemID == NetherX.swordVenomiteCursed.itemID) {
            list.add("Cursed");
        }
    }
    
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(final ItemStack par1ItemStack) {
        return this.itemID == NetherX.swordVenomiteCursed.itemID;
    }
}
