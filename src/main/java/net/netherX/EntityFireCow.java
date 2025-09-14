//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package net.netherX;

import net.minecraft.entity.monster.*;
import net.minecraft.world.*;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.entity.*;

public class EntityFireCow extends EntityMob
{
    public EntityFireCow(final World par1World) {
        super(par1World);
        this.setSize(1.4f, 0.9f);
        this.isImmuneToFire = true;
    }
    
    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(16, (Object)new Byte((byte)0));
    }
    
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(16.0);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.8);
    }
    
    public void onLivingUpdate() {
        for (int i = 0; i < 2; ++i) {
            this.worldObj.spawnParticle("flame", this.posX + (this.rand.nextDouble() - 0.5) * this.width, this.posY + this.rand.nextDouble() * this.height, this.posZ + (this.rand.nextDouble() - 0.5) * this.width, 0.0, 0.0, 0.0);
        }
        super.onLivingUpdate();
    }
    
    protected String getLivingSound() {
        return "mob.cow.hurt";
    }
    
    protected String getHurtSound() {
        return "mob.cow.hurt";
    }
    
    protected String getDeathSound() {
        return "mob.cow.hurt";
    }
    
    protected float getSoundPitch() {
        return super.getSoundPitch() * 0.55f;
    }
    
    protected void playStepSound(final int par1, final int par2, final int par3, final int par4) {
        this.playSound("mob.cow.step", 0.15f, 1.0f);
    }
    
    protected int getDropItemId() {
        return Item.leather.itemID;
    }
    
    protected void dropFewItems(final boolean par1, final int par2) {
        for (int j = this.rand.nextInt(3) + this.rand.nextInt(1 + par2), k = 0; k < j; ++k) {
            this.dropItem(Item.leather.itemID, 1);
        }
        for (int j = this.rand.nextInt(3) + 1 + this.rand.nextInt(1 + par2), k = 0; k < j; ++k) {
            this.dropItem(Item.beefCooked.itemID, 1);
        }
    }
    
    public boolean attackEntityAsMob(final Entity par1Entity) {
        this.worldObj.setEntityState((Entity)this, (byte)4);
        final boolean flag = par1Entity.attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)this), (float)(3 + this.rand.nextInt(5)));
        if (flag) {
            par1Entity.motionY += 0.4000000059604645;
        }
        this.playSound("mob.irongolem.throw", 1.0f, 1.0f);
        return flag;
    }
    
    public EnumCreatureAttribute getCreatureAttribute() {
        return EnumCreatureAttribute.UNDEFINED;
    }
}
