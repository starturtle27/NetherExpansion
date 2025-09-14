//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package net.netherX;

import net.minecraft.world.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.ai.*;
import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.entity.*;

public class EntityPigman extends EntityIronGolem
{
    public EntityPigman(final World par1World) {
        super(par1World);
        this.getNavigator().setAvoidsWater(true);
        this.setSize(1.0f, 2.0f);
        this.tasks.addTask(1, (EntityAIBase)new EntityAIAttackOnCollide((EntityCreature)this, 1.0, true));
        this.tasks.addTask(2, (EntityAIBase)new EntityAIMoveTowardsTarget((EntityCreature)this, 0.9, 32.0f));
        this.tasks.addTask(3, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, (Class)EntityPlayer.class, 6.0f));
        this.tasks.addTask(1, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.isImmuneToFire = true;
        this.targetTasks.addTask(1, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, (Class)EntityLiving.class, 0, false, true, IMob.mobSelector));
    }
    
    protected String getLivingSound() {
        return "mob.pig.say";
    }
    
    protected String getHurtSound() {
        return "mob.pig.say";
    }
    
    protected String getDeathSound() {
        return "mob.zombiepig.zpigdeath";
    }
    
    protected void playStepSound(final int par1, final int par2, final int par3, final int par4) {
        StepSound stepsound = Block.blocksList[par4].stepSound;
        if (this.worldObj.getBlockId(par1, par2 + 1, par3) == Block.snow.blockID) {
            stepsound = Block.snow.stepSound;
            this.playSound(stepsound.getStepSound(), stepsound.getVolume() * 0.15f, stepsound.getPitch());
        }
        else if (!Block.blocksList[par4].blockMaterial.isLiquid()) {
            this.playSound(stepsound.getStepSound(), stepsound.getVolume() * 0.15f, stepsound.getPitch());
        }
    }
    
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(28.0);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setAttribute(32.0);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setAttribute(0.0);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.3);
    }
    
    protected void dropFewItems(final boolean par1, final int par2) {
        for (int j = this.rand.nextInt(2 + par2), k = 0; k < j; ++k) {
            this.dropItem(Item.porkRaw.itemID, 1);
        }
        for (int j = this.rand.nextInt(2 + par2), k = 0; k < j; ++k) {
            this.dropItem(Item.goldNugget.itemID, 1);
        }
    }
    
    protected void dropRareDrop(final int par1) {
        this.dropItem(Item.ingotGold.itemID, 1);
    }
    
    protected int getDropItemId() {
        return Item.porkRaw.itemID;
    }
    
    protected boolean canDespawn() {
        return false;
    }
    
    protected void addRandomArmor() {
        this.setCurrentItemOrArmor(0, new ItemStack(Item.swordGold));
    }
    
    public boolean attackEntityAsMob(final Entity par1Entity) {
        final boolean flag = par1Entity.attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)this), (float)(4 + this.rand.nextInt(5)));
        return flag;
    }
    
    public EntityLivingData onSpawnWithEgg(final EntityLivingData par1EntityLivingData) {
        super.onSpawnWithEgg(par1EntityLivingData);
        this.setCurrentItemOrArmor(0, new ItemStack(Item.swordGold));
        this.setCanPickUpLoot(true);
        return par1EntityLivingData;
    }
    
    public void onLivingUpdate() {
        super.onLivingUpdate();
        this.attackTimer = 0;
    }
}
