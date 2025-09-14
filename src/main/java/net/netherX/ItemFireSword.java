//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package net.netherX;

import net.minecraft.item.*;
import net.minecraft.entity.*;

public class ItemFireSword extends ItemSword
{
    public ItemFireSword(final int par1, final EnumToolMaterial par2EnumToolMaterial) {
        super(par1, par2EnumToolMaterial);
    }
    
    public boolean hitEntity(final ItemStack par1ItemStack, final EntityLiving par2EntityLiving, final EntityLiving par3EntityLiving) {
        par2EntityLiving.setFire(5);
        par1ItemStack.damageItem(1, (EntityLivingBase)par3EntityLiving);
        return true;
    }
}
