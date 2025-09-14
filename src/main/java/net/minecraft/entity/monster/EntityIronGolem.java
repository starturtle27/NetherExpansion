//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package net.minecraft.entity.monster;

import net.minecraft.village.*;
import net.minecraft.world.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.*;
import net.minecraft.nbt.*;
import net.minecraft.util.*;
import cpw.mods.fml.relauncher.*;
import net.minecraft.block.*;
import net.minecraft.item.*;

public class EntityIronGolem extends EntityGolem
{
    private int homeCheckTimer;
    Village villageObj;
    protected int attackTimer;
    private int holdRoseTick;
    
    public EntityIronGolem(final World par1World) {
        super(par1World);
        this.setSize(1.4f, 2.9f);
        this.getNavigator().setAvoidsWater(true);
        this.tasks.addTask(1, (EntityAIBase)new EntityAIAttackOnCollide((EntityCreature)this, 1.0, true));
        this.tasks.addTask(2, (EntityAIBase)new EntityAIMoveTowardsTarget((EntityCreature)this, 0.9, 32.0f));
        this.tasks.addTask(3, (EntityAIBase)new EntityAIMoveThroughVillage((EntityCreature)this, 0.6, true));
        this.tasks.addTask(4, (EntityAIBase)new EntityAIMoveTowardsRestriction((EntityCreature)this, 1.0));
        this.tasks.addTask(5, (EntityAIBase)new EntityAILookAtVillager(this));
        this.tasks.addTask(6, (EntityAIBase)new EntityAIWander((EntityCreature)this, 0.6));
        this.tasks.addTask(7, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, (Class)EntityPlayer.class, 6.0f));
        this.tasks.addTask(8, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
        this.targetTasks.addTask(1, (EntityAIBase)new EntityAIDefendVillage(this));
        this.targetTasks.addTask(2, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, false));
        this.targetTasks.addTask(3, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, (Class)EntityLiving.class, 0, false, true, IMob.mobSelector));
    }
    
    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(16, (Object)0);
    }
    
    public boolean isAIEnabled() {
        return true;
    }
    
    protected void updateAITick() {
        final int homeCheckTimer = this.homeCheckTimer - 1;
        this.homeCheckTimer = homeCheckTimer;
        if (homeCheckTimer <= 0) {
            this.homeCheckTimer = 70 + this.rand.nextInt(50);
            this.villageObj = this.worldObj.villageCollectionObj.findNearestVillage(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ), 32);
            if (this.villageObj == null) {
                this.detachHome();
            }
            else {
                final ChunkCoordinates chunkcoordinates = this.villageObj.getCenter();
                this.setHomeArea(chunkcoordinates.posX, chunkcoordinates.posY, chunkcoordinates.posZ, (int)(this.villageObj.getVillageRadius() * 0.6f));
            }
        }
        super.updateAITick();
    }
    
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(100.0);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.25);
    }
    
    protected int decreaseAirSupply(final int par1) {
        return par1;
    }
    
    protected void collideWithEntity(final Entity par1Entity) {
        if (par1Entity instanceof IMob && this.getRNG().nextInt(20) == 0) {
            this.setAttackTarget((EntityLivingBase)par1Entity);
        }
        super.collideWithEntity(par1Entity);
    }
    
    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (this.attackTimer > 0) {
            --this.attackTimer;
        }
        if (this.holdRoseTick > 0) {
            --this.holdRoseTick;
        }
        if (this.motionX * this.motionX + this.motionZ * this.motionZ > 2.500000277905201E-7 && this.rand.nextInt(5) == 0) {
            final int i = MathHelper.floor_double(this.posX);
            final int j = MathHelper.floor_double(this.posY - 0.20000000298023224 - this.yOffset);
            final int k = MathHelper.floor_double(this.posZ);
            final int l = this.worldObj.getBlockId(i, j, k);
            if (l > 0) {
                this.worldObj.spawnParticle("tilecrack_" + l + "_" + this.worldObj.getBlockMetadata(i, j, k), this.posX + (this.rand.nextFloat() - 0.5) * this.width, this.boundingBox.minY + 0.1, this.posZ + (this.rand.nextFloat() - 0.5) * this.width, 4.0 * (this.rand.nextFloat() - 0.5), 0.5, (this.rand.nextFloat() - 0.5) * 4.0);
            }
        }
    }
    
    public boolean canAttackClass(final Class par1Class) {
        return (!this.isPlayerCreated() || !EntityPlayer.class.isAssignableFrom(par1Class)) && super.canAttackClass(par1Class);
    }
    
    public void writeEntityToNBT(final NBTTagCompound par1NBTTagCompound) {
        super.writeEntityToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setBoolean("PlayerCreated", this.isPlayerCreated());
    }
    
    public void readEntityFromNBT(final NBTTagCompound par1NBTTagCompound) {
        super.readEntityFromNBT(par1NBTTagCompound);
        this.setPlayerCreated(par1NBTTagCompound.getBoolean("PlayerCreated"));
    }
    
    public boolean attackEntityAsMob(final Entity par1Entity) {
        this.attackTimer = 10;
        this.worldObj.setEntityState((Entity)this, (byte)4);
        final boolean flag = par1Entity.attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)this), (float)(7 + this.rand.nextInt(15)));
        if (flag) {
            par1Entity.motionY += 0.4000000059604645;
        }
        this.playSound("mob.irongolem.throw", 1.0f, 1.0f);
        return flag;
    }
    
    @SideOnly(Side.CLIENT)
    public void handleHealthUpdate(final byte par1) {
        if (par1 == 4) {
            this.attackTimer = 10;
            this.playSound("mob.irongolem.throw", 1.0f, 1.0f);
        }
        else if (par1 == 11) {
            this.holdRoseTick = 400;
        }
        else {
            super.handleHealthUpdate(par1);
        }
    }
    
    public Village getVillage() {
        return this.villageObj;
    }
    
    @SideOnly(Side.CLIENT)
    public int getAttackTimer() {
        return this.attackTimer;
    }
    
    public void setHoldingRose(final boolean par1) {
        this.holdRoseTick = (par1 ? 400 : 0);
        this.worldObj.setEntityState((Entity)this, (byte)11);
    }
    
    protected String getLivingSound() {
        return "none";
    }
    
    protected String getHurtSound() {
        return "mob.irongolem.hit";
    }
    
    protected String getDeathSound() {
        return "mob.irongolem.death";
    }
    
    protected void playStepSound(final int par1, final int par2, final int par3, final int par4) {
        this.playSound("mob.irongolem.walk", 1.0f, 1.0f);
    }
    
    protected void dropFewItems(final boolean par1, final int par2) {
        for (int j = this.rand.nextInt(3), k = 0; k < j; ++k) {
            this.dropItem(Block.plantRed.blockID, 1);
        }
        for (int k = 3 + this.rand.nextInt(3), l = 0; l < k; ++l) {
            this.dropItem(Item.ingotIron.itemID, 1);
        }
    }
    
    public int getHoldRoseTick() {
        return this.holdRoseTick;
    }
    
    public boolean isPlayerCreated() {
        return (this.dataWatcher.getWatchableObjectByte(16) & 0x1) != 0x0;
    }
    
    public void setPlayerCreated(final boolean par1) {
        final byte b0 = this.dataWatcher.getWatchableObjectByte(16);
        if (par1) {
            this.dataWatcher.updateObject(16, (Object)(byte)(b0 | 0x1));
        }
        else {
            this.dataWatcher.updateObject(16, (Object)(byte)(b0 & 0xFFFFFFFE));
        }
    }
    
    public void onDeath(final DamageSource par1DamageSource) {
        if (!this.isPlayerCreated() && this.attackingPlayer != null && this.villageObj != null) {
            this.villageObj.setReputationForPlayer(this.attackingPlayer.getCommandSenderName(), -5);
        }
        super.onDeath(par1DamageSource);
    }
}
