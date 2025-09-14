//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package net.netherX;

import net.minecraft.entity.monster.*;
import net.minecraft.world.*;
import cpw.mods.fml.relauncher.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.projectile.*;
import net.minecraft.entity.*;
import net.minecraft.util.*;
import net.minecraft.item.*;
import net.minecraft.nbt.*;

public class EntityGhastQueen extends EntityFlying implements IMob
{
    public int courseChangeCooldown;
    public double waypointX;
    public double waypointY;
    public double waypointZ;
    private Entity targetedEntity;
    private int aggroCooldown;
    public int prevAttackCounter;
    public int attackCounter;
    private int explosionStrength;
    
    public EntityGhastQueen(final World par1World) {
        super(par1World);
        this.explosionStrength = 2;
        this.setSize(4.0f, 4.0f);
        this.isImmuneToFire = true;
        this.experienceValue = 20;
    }
    
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(45.0);
    }
    
    @SideOnly(Side.CLIENT)
    public boolean func_110182_bF() {
        return this.dataWatcher.getWatchableObjectByte(16) != 0;
    }
    
    public boolean attackEntityFrom(final DamageSource par1DamageSource, final float par2) {
        if (this.isEntityInvulnerable()) {
            return false;
        }
        if ("fireball".equals(par1DamageSource.getDamageType()) && par1DamageSource.getEntity() instanceof EntityPlayer) {
            super.attackEntityFrom(par1DamageSource, 5.0f);
            return true;
        }
        return super.attackEntityFrom(par1DamageSource, par2);
    }
    
    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(16, (Object)0);
    }
    
    protected void updateEntityActionState() {
        if (!this.worldObj.isRemote && this.worldObj.difficultySetting == 0) {
            this.setDead();
        }
        this.despawnEntity();
        this.prevAttackCounter = this.attackCounter;
        final double d0 = this.waypointX - this.posX;
        final double d2 = this.waypointY - this.posY;
        final double d3 = this.waypointZ - this.posZ;
        double d4 = d0 * d0 + d2 * d2 + d3 * d3;
        if (d4 < 1.0 || d4 > 3600.0) {
            this.waypointX = this.posX + (this.rand.nextFloat() * 2.0f - 1.0f) * 16.0f;
            this.waypointY = this.posY + (this.rand.nextFloat() * 2.0f - 1.0f) * 16.0f;
            this.waypointZ = this.posZ + (this.rand.nextFloat() * 2.0f - 1.0f) * 16.0f;
        }
        if (this.courseChangeCooldown-- <= 0) {
            this.courseChangeCooldown += this.rand.nextInt(5) + 2;
            d4 = MathHelper.sqrt_double(d4);
            if (this.isCourseTraversable(this.waypointX, this.waypointY, this.waypointZ, d4)) {
                this.motionX += d0 / d4 * 0.1;
                this.motionY += d2 / d4 * 0.1;
                this.motionZ += d3 / d4 * 0.1;
            }
            else {
                this.waypointX = this.posX;
                this.waypointY = this.posY;
                this.waypointZ = this.posZ;
            }
        }
        if (this.targetedEntity != null && this.targetedEntity.isDead) {
            this.targetedEntity = null;
        }
        if (this.targetedEntity == null || this.aggroCooldown-- <= 0) {
            this.targetedEntity = (Entity)this.worldObj.getClosestVulnerablePlayerToEntity((Entity)this, 100.0);
            if (this.targetedEntity != null) {
                this.aggroCooldown = 20;
            }
        }
        final double d5 = 64.0;
        if (this.targetedEntity != null && this.targetedEntity.getDistanceSqToEntity((Entity)this) < d5 * d5) {
            final double d6 = this.targetedEntity.posX - this.posX;
            final double d7 = this.targetedEntity.boundingBox.minY + this.targetedEntity.height / 2.0f - (this.posY + this.height / 2.0f);
            final double d8 = this.targetedEntity.posZ - this.posZ;
            final float n = -(float)Math.atan2(d6, d8) * 180.0f / 3.1415927f;
            this.rotationYaw = n;
            this.renderYawOffset = n;
            if (this.canEntityBeSeen(this.targetedEntity)) {
                if (this.attackCounter == 10) {
                    this.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1007, (int)this.posX, (int)this.posY, (int)this.posZ, 0);
                }
                ++this.attackCounter;
                if (this.attackCounter == 20) {
                    this.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1008, (int)this.posX, (int)this.posY, (int)this.posZ, 0);
                    final EntityLargeFireball entitylargefireball = new EntityLargeFireball(this.worldObj, (EntityLivingBase)this, d6, d7, d8);
                    entitylargefireball.field_92057_e = this.explosionStrength;
                    final double d9 = 4.0;
                    final Vec3 vec3 = this.getLook(1.0f);
                    entitylargefireball.posX = this.posX + vec3.xCoord * d9;
                    entitylargefireball.posY = this.posY + this.height / 2.0f + 0.5;
                    entitylargefireball.posZ = this.posZ + vec3.zCoord * d9;
                    this.worldObj.spawnEntityInWorld((Entity)entitylargefireball);
                    this.attackCounter = 0;
                }
            }
            else if (this.attackCounter > 0) {
                --this.attackCounter;
            }
        }
        else {
            final float n2 = -(float)Math.atan2(this.motionX, this.motionZ) * 180.0f / 3.1415927f;
            this.rotationYaw = n2;
            this.renderYawOffset = n2;
            if (this.attackCounter > 0) {
                --this.attackCounter;
            }
        }
        if (!this.worldObj.isRemote) {
            final byte b0 = this.dataWatcher.getWatchableObjectByte(16);
            final byte b2 = (byte)((this.attackCounter > 10) ? 1 : 0);
            if (b0 != b2) {
                this.dataWatcher.updateObject(16, (Object)b2);
            }
        }
    }
    
    private boolean isCourseTraversable(final double par1, final double par3, final double par5, final double par7) {
        final double d4 = (this.waypointX - this.posX) / par7;
        final double d5 = (this.waypointY - this.posY) / par7;
        final double d6 = (this.waypointZ - this.posZ) / par7;
        final AxisAlignedBB axisalignedbb = this.boundingBox.copy();
        for (int i = 1; i < par7; ++i) {
            axisalignedbb.offset(d4, d5, d6);
            if (!this.worldObj.getCollidingBoundingBoxes((Entity)this, axisalignedbb).isEmpty()) {
                return false;
            }
        }
        return true;
    }
    
    protected String getLivingSound() {
        return "mob.ghast.moan";
    }
    
    protected String getHurtSound() {
        return "mob.ghast.scream";
    }
    
    protected String getDeathSound() {
        return "mob.ghast.death";
    }
    
    protected int getDropItemId() {
        return NetherX.ghastEgg.itemID;
    }
    
    protected void dropFewItems(final boolean par1, final int par2) {
        int j = this.rand.nextInt(3) + this.rand.nextInt(1 + par2);
        this.dropItem(NetherX.ghastEgg.itemID, 1);
        for (int k = 0; k < j; ++k) {
            this.dropItem(Item.ghastTear.itemID, 1);
        }
        j = this.rand.nextInt(3) + this.rand.nextInt(1 + par2);
        for (int k = 0; k < j; ++k) {
            this.dropItem(Item.gunpowder.itemID, 1);
        }
    }
    
    protected float getSoundVolume() {
        return 12.0f;
    }
    
    public boolean getCanSpawnHere() {
        return this.rand.nextInt(20) == 0 && super.getCanSpawnHere() && this.worldObj.difficultySetting > 0;
    }
    
    public int getMaxSpawnedInChunk() {
        return 1;
    }
    
    public void writeEntityToNBT(final NBTTagCompound par1NBTTagCompound) {
        super.writeEntityToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setInteger("ExplosionPower", this.explosionStrength);
    }
    
    public void readEntityFromNBT(final NBTTagCompound par1NBTTagCompound) {
        super.readEntityFromNBT(par1NBTTagCompound);
        if (par1NBTTagCompound.hasKey("ExplosionPower")) {
            this.explosionStrength = par1NBTTagCompound.getInteger("ExplosionPower");
        }
    }
}
