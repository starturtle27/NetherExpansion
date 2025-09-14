//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package net.netherX;

import net.minecraft.entity.monster.*;
import net.minecraft.world.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.ai.*;
import net.minecraft.util.*;
import net.minecraft.block.*;
import net.minecraft.entity.*;

public class EntityBrimspell extends EntityMob
{
    public EntityBrimspell(final World par1World) {
        super(par1World);
        this.isImmuneToFire = true;
        this.targetTasks.addTask(1, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, (Class)EntityPlayer.class, 0, true, false));
    }
    
    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (this.isWet()) {
            this.worldObj.playSoundEffect(this.posX + 0.5, this.posY + 0.5, this.posZ + 0.5, "fireworks.blast", 1.0f, this.rand.nextFloat() * 0.4f + 0.8f);
            this.setDead();
        }
        int i = MathHelper.floor_double(this.posX);
        int j = MathHelper.floor_double(this.posZ);
        for (i = 0; i < 4; ++i) {
            j = MathHelper.floor_double(this.posX + (i % 2 * 2 - 1) * 0.25f);
            final int k = MathHelper.floor_double(this.posY);
            final int l = MathHelper.floor_double(this.posZ + (i / 2 % 2 * 2 - 1) * 0.25f);
            if (this.hurtResistantTime == 0 && this.getHealth() > 0.0f) {
                if (this.worldObj.isAirBlock(j, k, l) && Block.snow.canPlaceBlockAt(this.worldObj, j, k, l)) {
                    if (NetherX.laglessBrimspell) {
                        this.worldObj.spawnParticle("flame", this.posX + (this.rand.nextDouble() - 0.5) * this.width, this.posY + this.rand.nextDouble() * this.height, this.posZ + (this.rand.nextDouble() - 0.5) * this.width, 0.0, 0.0, 0.0);
                    }
                    else {
                        this.worldObj.setBlock(j, k, l, NetherX.brimfire.blockID);
                    }
                }
                if (this.rand.nextInt(4) == 0) {
                    this.worldObj.spawnParticle("largesmoke", this.posX + (this.rand.nextDouble() - 0.5) * this.width, this.posY + this.rand.nextDouble() * this.height, this.posZ + (this.rand.nextDouble() - 0.5) * this.width, 0.0, 0.0, 0.0);
                }
            }
            else {
                if (this.worldObj.getBlockId(j, k, l) == NetherX.brimfire.blockID) {
                    this.worldObj.setBlock(j, k, l, 0);
                }
                this.worldObj.spawnParticle("largesmoke", this.posX + (this.rand.nextDouble() - 0.5) * this.width, this.posY + this.rand.nextDouble() * this.height, this.posZ + (this.rand.nextDouble() - 0.5) * this.width, 0.0, 0.0, 0.0);
            }
        }
    }
    
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(14.0);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.75);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setAttribute(0.0);
    }
    
    protected int getDropItemId() {
        return NetherX.blackpaper.itemID;
    }
    
    protected void dropFewItems(final boolean par1, final int par2) {
        for (int j = this.rand.nextInt(8), k = 0; k < j; ++k) {
            this.dropItem(NetherX.blackpaper.itemID, 1);
        }
    }
    
    protected String getLivingSound() {
        return "netherx:brimspell";
    }
    
    protected String getHurtSound() {
        return "netherx:brimspell";
    }
    
    protected String getDeathSound() {
        return "sound.mob.horse.skeleton.death";
    }
    
    public boolean attackEntityAsMob(final Entity par1Entity) {
        if (NetherX.laglessBrimspell && super.attackEntityAsMob(par1Entity)) {
            if (par1Entity instanceof EntityLivingBase) {
                byte b0 = 6;
                if (this.worldObj.difficultySetting > 1) {
                    if (this.worldObj.difficultySetting == 2) {
                        b0 = 9;
                    }
                    else if (this.worldObj.difficultySetting == 3) {
                        b0 = 12;
                    }
                }
                if (b0 > 0) {
                    ((EntityLivingBase)par1Entity).setFire((int)b0);
                }
            }
            return true;
        }
        return false;
    }
}
