//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package net.netherX;

import net.minecraft.item.*;
import net.minecraft.entity.*;
import net.minecraft.potion.*;
import net.minecraft.entity.player.*;
import java.util.*;
import cpw.mods.fml.relauncher.*;

public class ItemNecromiteSword extends ItemSword
{
    public ItemNecromiteSword(final int par1, final EnumToolMaterial par2EnumToolMaterial) {
        super(par1, par2EnumToolMaterial);
    }
    
    public boolean hitEntity(final ItemStack par1ItemStack, final EntityLivingBase par2EntityLivingBase, final EntityLivingBase par3EntityLivingBase) {
        if (this.itemID == NetherX.swordNecromiteCursed.itemID) {
            par2EntityLivingBase.addPotionEffect(new PotionEffect(Potion.wither.id, 100, 3));
        }
        else {
            par2EntityLivingBase.addPotionEffect(new PotionEffect(Potion.wither.id, 80, 2));
        }
        par1ItemStack.damageItem(1, par3EntityLivingBase);
        return true;
    }
    
    public void addInformation(final ItemStack stack, final EntityPlayer player, final List list, final boolean par4) {
        if (this.itemID == NetherX.swordNecromiteCursed.itemID) {
            list.add("Cursed");
        }
    }
    
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(final ItemStack par1ItemStack) {
        return this.itemID == NetherX.swordNecromiteCursed.itemID;
    }
}
