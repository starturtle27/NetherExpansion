//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package net.netherX;

import net.minecraft.item.*;
import net.minecraft.world.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.projectile.*;
import net.minecraft.entity.*;
import net.minecraft.util.*;
import java.util.*;
import cpw.mods.fml.relauncher.*;
import net.minecraft.client.renderer.texture.*;

public class ItemInfernoStaff extends Item
{
    public static Icon iconStaffHeld;
    public int reload;
    
    public ItemInfernoStaff(final int par1) {
        super(par1);
        this.reload = 0;
    }
    
    public ItemStack onItemRightClick(final ItemStack stack, final World world, final EntityPlayer player) {
        if (!world.isRemote & this.reload == 0) {
            world.playSoundEffect(player.posX + 0.5, player.posY + 0.5, player.posZ + 0.5, "random.fizz", 1.0f, Item.itemRand.nextFloat() * 0.4f + 0.8f);
            final Vec3 lookPos = player.getLookVec();
            final EntityLargeFireball fireball = new EntityLargeFireball(world, (EntityLivingBase)player, 1.0, 1.0, 1.0);
            fireball.setPosition(player.posX + lookPos.xCoord * 1.1, player.posY + lookPos.yCoord * 1.1 + 0.4, player.posZ + lookPos.zCoord * 1.1);
            fireball.accelerationX = lookPos.xCoord * 0.18;
            fireball.accelerationY = lookPos.yCoord * 0.18;
            fireball.accelerationZ = lookPos.zCoord * 0.18;
            world.spawnEntityInWorld((Entity)fireball);
            if (this.itemID == NetherX.infernoStaff.itemID) {
                this.reload = 15;
            }
            else {
                this.reload = 5;
            }
            stack.damageItem(1, (EntityLivingBase)player);
        }
        return stack;
    }
    
    public void onUpdate(final ItemStack par1ItemStack, final World par2World, final Entity par3Entity, final int par4, final boolean par5) {
        if (this.reload > 0) {
            --this.reload;
        }
    }
    
    public boolean isFull3D() {
        return true;
    }
    
    public void addInformation(final ItemStack stack, final EntityPlayer player, final List list, final boolean par4) {
        if (this.itemID == NetherX.infernoStaffCursed.itemID) {
            list.add("Cursed");
        }
    }
    
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(final ItemStack par1ItemStack) {
        return this.itemID == NetherX.infernoStaffCursed.itemID;
    }
    
    public void registerIcons(final IconRegister par1IconRegister) {
        ItemInfernoStaff.iconStaffHeld = par1IconRegister.registerIcon("inferno_staff_held");
        this.itemIcon = par1IconRegister.registerIcon("inferno_staff");
    }
}
