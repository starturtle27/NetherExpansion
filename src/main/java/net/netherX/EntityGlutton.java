//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package net.netherX;

import net.minecraft.entity.monster.*;
import net.minecraft.world.*;
import net.minecraft.entity.ai.*;
import net.minecraft.item.*;
import java.util.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import net.minecraft.potion.*;

public class EntityGlutton extends EntityMob
{
    int lastEatenFood;
    
    public EntityGlutton(final World par1World) {
        super(par1World);
        this.tasks.addTask(1, (EntityAIBase)new EntityAILeapAtTarget((EntityLiving)this, 0.4f));
        this.tasks.addTask(2, (EntityAIBase)new EntityAIAttackOnCollide((EntityCreature)this, 0.6, true));
        this.isImmuneToFire = true;
    }
    
    public EnumCreatureAttribute getCreatureAttribute() {
        return EnumCreatureAttribute.UNDEFINED;
    }
    
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(10.0);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setAttribute(32.0);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setAttribute(0.0);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.699);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(2.0);
    }
    
    protected int getDropItemId() {
        return Item.bone.itemID;
    }
    
    protected void dropRareDrop(final int par1) {
        final Calendar calendar = Calendar.getInstance();
        if (calendar.get(2) + 1 != 12 || calendar.get(5) < 24 || calendar.get(5) > 26) {
            this.dropItem(NetherX.glutrootSeeds.itemID, 1);
        }
    }
    
    protected void dropFewItems(final boolean par1, final int par2) {
        super.dropFewItems(par1, par2);
        final Calendar calendar = Calendar.getInstance();
        if (calendar.get(2) + 1 == 12 && calendar.get(5) >= 24 && calendar.get(5) <= 26) {
            this.dropItem(NetherX.glutrootChocolateSeeds.itemID, this.rand.nextInt(5));
        }
        else {
            for (int j = this.rand.nextInt(2) + this.rand.nextInt(1 + par2), k = 0; k < j; ++k) {
                this.dropItem(Item.bone.itemID, 1);
            }
            for (int j = this.rand.nextInt(3) + this.rand.nextInt(1 + par2), k = 0; k < j; ++k) {
                this.dropItem(Item.leather.itemID, 1);
            }
            final int j = this.rand.nextInt(3) + this.rand.nextInt(1 + par2);
            for (int k = 0; k < 1; ++k) {
                this.dropItem(this.lastEatenFood, 1);
            }
        }
    }
    
    protected String getLivingSound() {
        return "random.burp";
    }
    
    protected String getHurtSound() {
        return "random.burp";
    }
    
    protected String getDeathSound() {
        return "mob.horse.zombie.death";
    }
    
    public int getAttackStrength(final Entity par1Entity) {
        return 4;
    }
    
    public boolean attackEntityAsMob(final Entity par1Entity) {
        if (par1Entity instanceof EntityPlayer) {
            final EntityPlayer par1EntityPlayer = (EntityPlayer)par1Entity;
            if (par1EntityPlayer.inventory.hasItem(Item.sugar.itemID)) {
                par1EntityPlayer.inventory.consumeInventoryItem(Item.sugar.itemID);
                par1EntityPlayer.attackEntityFrom(NetherX.bite, 1.0f);
                this.playSound("random.eat", 1.0f, 1.0f);
                this.lastEatenFood = Item.sugar.itemID;
            }
            else if (par1EntityPlayer.inventory.hasItem(Item.appleRed.itemID)) {
                par1EntityPlayer.inventory.consumeInventoryItem(Item.appleRed.itemID);
                par1EntityPlayer.attackEntityFrom(NetherX.bite, 1.0f);
                this.playSound("random.eat", 1.0f, 1.0f);
                this.lastEatenFood = Item.appleRed.itemID;
            }
            else if (par1EntityPlayer.inventory.hasItem(Item.beefCooked.itemID)) {
                par1EntityPlayer.inventory.consumeInventoryItem(Item.beefCooked.itemID);
                par1EntityPlayer.attackEntityFrom(NetherX.bite, 1.0f);
                this.playSound("random.eat", 1.0f, 1.0f);
                this.lastEatenFood = Item.beefCooked.itemID;
            }
            else if (par1EntityPlayer.inventory.hasItem(Item.porkCooked.itemID)) {
                par1EntityPlayer.inventory.consumeInventoryItem(Item.porkCooked.itemID);
                par1EntityPlayer.attackEntityFrom(NetherX.bite, 1.0f);
                this.playSound("random.eat", 1.0f, 1.0f);
                this.lastEatenFood = Item.porkCooked.itemID;
            }
            else if (par1EntityPlayer.inventory.hasItem(Item.chickenCooked.itemID)) {
                par1EntityPlayer.inventory.consumeInventoryItem(Item.chickenCooked.itemID);
                par1EntityPlayer.attackEntityFrom(NetherX.bite, 1.0f);
                this.playSound("random.eat", 1.0f, 1.0f);
                this.lastEatenFood = Item.chickenCooked.itemID;
            }
            else if (par1EntityPlayer.inventory.hasItem(Item.fishCooked.itemID)) {
                par1EntityPlayer.inventory.consumeInventoryItem(Item.fishCooked.itemID);
                par1EntityPlayer.attackEntityFrom(NetherX.bite, 1.0f);
                this.playSound("random.eat", 1.0f, 1.0f);
                this.lastEatenFood = Item.fishCooked.itemID;
            }
            else if (par1EntityPlayer.inventory.hasItem(Item.beefRaw.itemID)) {
                par1EntityPlayer.inventory.consumeInventoryItem(Item.beefRaw.itemID);
                par1EntityPlayer.attackEntityFrom(NetherX.bite, 1.0f);
                this.playSound("random.eat", 1.0f, 1.0f);
                this.lastEatenFood = Item.beefRaw.itemID;
            }
            else if (par1EntityPlayer.inventory.hasItem(Item.porkRaw.itemID)) {
                par1EntityPlayer.inventory.consumeInventoryItem(Item.porkRaw.itemID);
                par1EntityPlayer.attackEntityFrom(NetherX.bite, 1.0f);
                this.playSound("random.eat", 1.0f, 1.0f);
                this.lastEatenFood = Item.porkRaw.itemID;
            }
            else if (par1EntityPlayer.inventory.hasItem(Item.chickenRaw.itemID)) {
                par1EntityPlayer.inventory.consumeInventoryItem(Item.chickenRaw.itemID);
                par1EntityPlayer.attackEntityFrom(NetherX.bite, 1.0f);
                this.playSound("random.eat", 1.0f, 1.0f);
                this.lastEatenFood = Item.chickenRaw.itemID;
            }
            else {
                if (par1EntityPlayer.inventory.hasItem(Item.fishRaw.itemID)) {
                    par1EntityPlayer.inventory.consumeInventoryItem(Item.fishRaw.itemID);
                    par1EntityPlayer.attackEntityFrom(NetherX.bite, 1.0f);
                    this.playSound("random.eat", 1.0f, 1.0f);
                    this.lastEatenFood = Item.fishRaw.itemID;
                }
                if (par1EntityPlayer.inventory.hasItem(Item.cake.itemID)) {
                    par1EntityPlayer.inventory.consumeInventoryItem(Item.cake.itemID);
                    par1EntityPlayer.attackEntityFrom(NetherX.bite, 1.0f);
                    this.playSound("random.eat", 1.0f, 1.0f);
                    this.lastEatenFood = Item.cake.itemID;
                }
                else {
                    if (par1EntityPlayer.inventory.hasItem(Item.pumpkinPie.itemID)) {
                        par1EntityPlayer.inventory.consumeInventoryItem(Item.pumpkinPie.itemID);
                        par1EntityPlayer.attackEntityFrom(NetherX.bite, 1.0f);
                        this.playSound("random.eat", 1.0f, 1.0f);
                        this.lastEatenFood = Item.pumpkinPie.itemID;
                    }
                    if (par1EntityPlayer.inventory.hasItem(Item.bowlSoup.itemID)) {
                        par1EntityPlayer.inventory.consumeInventoryItem(Item.bowlSoup.itemID);
                        par1EntityPlayer.attackEntityFrom(NetherX.bite, 1.0f);
                        this.playSound("random.eat", 1.0f, 1.0f);
                        this.lastEatenFood = Item.bowlSoup.itemID;
                    }
                    else if (par1EntityPlayer.inventory.hasItem(Item.bread.itemID)) {
                        par1EntityPlayer.inventory.consumeInventoryItem(Item.bread.itemID);
                        par1EntityPlayer.attackEntityFrom(NetherX.bite, 1.0f);
                        this.playSound("random.eat", 1.0f, 1.0f);
                        this.lastEatenFood = Item.bread.itemID;
                    }
                    else if (par1EntityPlayer.inventory.hasItem(Item.bakedPotato.itemID)) {
                        par1EntityPlayer.inventory.consumeInventoryItem(Item.bakedPotato.itemID);
                        par1EntityPlayer.attackEntityFrom(NetherX.bite, 1.0f);
                        this.playSound("random.eat", 1.0f, 1.0f);
                        this.lastEatenFood = Item.bakedPotato.itemID;
                    }
                    else if (par1EntityPlayer.inventory.hasItem(Item.carrot.itemID)) {
                        par1EntityPlayer.inventory.consumeInventoryItem(Item.carrot.itemID);
                        par1EntityPlayer.attackEntityFrom(NetherX.bite, 1.0f);
                        this.playSound("random.eat", 1.0f, 1.0f);
                        this.lastEatenFood = Item.carrot.itemID;
                    }
                    else if (par1EntityPlayer.inventory.hasItem(Item.potato.itemID)) {
                        par1EntityPlayer.inventory.consumeInventoryItem(Item.potato.itemID);
                        par1EntityPlayer.attackEntityFrom(NetherX.bite, 1.0f);
                        this.playSound("random.eat", 1.0f, 1.0f);
                        this.lastEatenFood = Item.potato.itemID;
                    }
                    else if (par1EntityPlayer.inventory.hasItem(Item.cookie.itemID)) {
                        par1EntityPlayer.inventory.consumeInventoryItem(Item.cookie.itemID);
                        par1EntityPlayer.attackEntityFrom(NetherX.bite, 1.0f);
                        this.playSound("random.eat", 1.0f, 1.0f);
                        this.lastEatenFood = Item.cookie.itemID;
                    }
                    else if (par1EntityPlayer.inventory.hasItem(Item.melon.itemID)) {
                        par1EntityPlayer.inventory.consumeInventoryItem(Item.melon.itemID);
                        par1EntityPlayer.attackEntityFrom(NetherX.bite, 1.0f);
                        this.playSound("random.eat", 1.0f, 1.0f);
                        this.lastEatenFood = Item.melon.itemID;
                    }
                    else if (par1EntityPlayer.inventory.hasItem(Item.rottenFlesh.itemID)) {
                        par1EntityPlayer.inventory.consumeInventoryItem(Item.rottenFlesh.itemID);
                        par1EntityPlayer.attackEntityFrom(NetherX.bite, 2.0f);
                        this.playSound("random.eat", 1.0f, 1.0f);
                        this.lastEatenFood = Item.rottenFlesh.itemID;
                    }
                    else if (par1EntityPlayer.inventory.hasItem(Item.poisonousPotato.itemID)) {
                        par1EntityPlayer.inventory.consumeInventoryItem(Item.poisonousPotato.itemID);
                        par1EntityPlayer.attackEntityFrom(NetherX.bite, 1.0f);
                        this.playSound("random.eat", 1.0f, 1.0f);
                        this.lastEatenFood = Item.poisonousPotato.itemID;
                        this.addPotionEffect(new PotionEffect(Potion.poison.id, 80, 0));
                    }
                    else if (par1EntityPlayer.inventory.hasItem(NetherX.glutroot.itemID)) {
                        par1EntityPlayer.inventory.consumeInventoryItem(NetherX.glutroot.itemID);
                        par1EntityPlayer.attackEntityFrom(NetherX.bite, 1.0f);
                        this.playSound("random.eat", 1.0f, 1.0f);
                        this.lastEatenFood = NetherX.glutroot.itemID;
                    }
                    else if (par1EntityPlayer.inventory.hasItem(NetherX.bowlGlutrootSoup.itemID)) {
                        par1EntityPlayer.inventory.consumeInventoryItem(NetherX.bowlGlutrootSoup.itemID);
                        par1EntityPlayer.attackEntityFrom(NetherX.bite, 1.0f);
                        this.playSound("random.eat", 1.0f, 1.0f);
                        this.lastEatenFood = NetherX.bowlGlutrootSoup.itemID;
                    }
                    else {
                        par1EntityPlayer.attackEntityFrom(NetherX.bite, 2.0f);
                    }
                }
            }
        }
        return true;
    }
}
