//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package net.minecraft.entity;

import java.util.*;
import net.minecraft.pathfinding.*;
import net.minecraft.world.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.ai.attributes.*;
import net.minecraft.util.*;
import net.minecraft.entity.passive.*;

public abstract class EntityCreature extends EntityLiving
{
    public static final UUID field_110179_h;
    public static final AttributeModifier field_110181_i;
    private PathEntity pathToEntity;
    public Entity entityToAttack;
    protected boolean hasAttacked;
    protected int fleeingTick;
    private ChunkCoordinates homePosition;
    private float maximumHomeDistance;
    private EntityAIBase field_110178_bs;
    private boolean field_110180_bt;
    
    public EntityCreature(final World par1World) {
        super(par1World);
        this.homePosition = new ChunkCoordinates(0, 0, 0);
        this.maximumHomeDistance = -1.0f;
        this.field_110178_bs = (EntityAIBase)new EntityAIMoveTowardsRestriction(this, 1.0);
    }
    
    protected boolean isMovementCeased() {
        return false;
    }
    
    protected void updateEntityActionState() {
        this.worldObj.theProfiler.startSection("ai");
        if (this.fleeingTick > 0 && --this.fleeingTick == 0) {
            final AttributeInstance attributeinstance = this.getEntityAttribute(SharedMonsterAttributes.movementSpeed);
            attributeinstance.removeModifier(EntityCreature.field_110181_i);
        }
        this.hasAttacked = this.isMovementCeased();
        final float f = 16.0f;
        if (this.entityToAttack == null) {
            this.entityToAttack = this.findPlayerToAttack();
            if (this.entityToAttack != null) {
                this.pathToEntity = this.worldObj.getPathEntityToEntity((Entity)this, this.entityToAttack, f, true, false, false, true);
            }
        }
        else if (this.entityToAttack.isEntityAlive()) {
            final float f2 = this.entityToAttack.getDistanceToEntity((Entity)this);
            if (this.canEntityBeSeen(this.entityToAttack)) {
                this.attackEntity(this.entityToAttack, f2);
            }
        }
        else {
            this.entityToAttack = null;
        }
        this.worldObj.theProfiler.endSection();
        if (!this.hasAttacked && this.entityToAttack != null && (this.pathToEntity == null || this.rand.nextInt(20) == 0)) {
            this.pathToEntity = this.worldObj.getPathEntityToEntity((Entity)this, this.entityToAttack, f, true, false, false, true);
        }
        else if (!this.hasAttacked && ((this.pathToEntity == null && this.rand.nextInt(180) == 0) || this.rand.nextInt(120) == 0 || this.fleeingTick > 0) && this.entityAge < 100) {
            this.updateWanderPath();
        }
        final int i = MathHelper.floor_double(this.boundingBox.minY + 0.5);
        final boolean flag = this.isInWater();
        final boolean flag2 = this.handleLavaMovement();
        this.rotationPitch = 0.0f;
        if (this.pathToEntity != null && this.rand.nextInt(100) != 0) {
            this.worldObj.theProfiler.startSection("followpath");
            Vec3 vec3 = this.pathToEntity.getPosition((Entity)this);
            final double d0 = this.width * 2.0f;
            while (vec3 != null && vec3.squareDistanceTo(this.posX, vec3.yCoord, this.posZ) < d0 * d0) {
                this.pathToEntity.incrementPathIndex();
                if (this.pathToEntity.isFinished()) {
                    vec3 = null;
                    this.pathToEntity = null;
                }
                else {
                    vec3 = this.pathToEntity.getPosition((Entity)this);
                }
            }
            this.isJumping = false;
            if (vec3 != null) {
                final double d2 = vec3.xCoord - this.posX;
                final double d3 = vec3.zCoord - this.posZ;
                final double d4 = vec3.yCoord - i;
                final float f3 = (float)(Math.atan2(d3, d2) * 180.0 / 3.141592653589793) - 90.0f;
                float f4 = MathHelper.wrapAngleTo180_float(f3 - this.rotationYaw);
                this.moveForward = (float)this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue();
                if (f4 > 30.0f) {
                    f4 = 30.0f;
                }
                if (f4 < -30.0f) {
                    f4 = -30.0f;
                }
                this.rotationYaw += f4;
                if (this.hasAttacked && this.entityToAttack != null) {
                    final double d5 = this.entityToAttack.posX - this.posX;
                    final double d6 = this.entityToAttack.posZ - this.posZ;
                    final float f5 = this.rotationYaw;
                    this.rotationYaw = (float)(Math.atan2(d6, d5) * 180.0 / 3.141592653589793) - 90.0f;
                    f4 = (f5 - this.rotationYaw + 90.0f) * 3.1415927f / 180.0f;
                    this.moveStrafing = -MathHelper.sin(f4) * this.moveForward * 1.0f;
                    this.moveForward = MathHelper.cos(f4) * this.moveForward * 1.0f;
                }
                if (d4 > 0.0) {
                    this.isJumping = true;
                }
            }
            if (this.entityToAttack != null) {
                this.faceEntity(this.entityToAttack, 30.0f, 30.0f);
            }
            if (this.isCollidedHorizontally && !this.hasPath()) {
                this.isJumping = true;
            }
            if (this.rand.nextFloat() < 0.8f && (flag || flag2)) {
                this.isJumping = true;
            }
            this.worldObj.theProfiler.endSection();
        }
        else {
            super.updateEntityActionState();
            this.pathToEntity = null;
        }
    }
    
    protected void updateWanderPath() {
        this.worldObj.theProfiler.startSection("stroll");
        boolean flag = false;
        int i = -1;
        int j = -1;
        int k = -1;
        float f = -99999.0f;
        for (int l = 0; l < 10; ++l) {
            final int i2 = MathHelper.floor_double(this.posX + this.rand.nextInt(13) - 6.0);
            final int j2 = MathHelper.floor_double(this.posY + this.rand.nextInt(7) - 3.0);
            final int k2 = MathHelper.floor_double(this.posZ + this.rand.nextInt(13) - 6.0);
            final float f2 = this.getBlockPathWeight(i2, j2, k2);
            if (f2 > f) {
                f = f2;
                i = i2;
                j = j2;
                k = k2;
                flag = true;
            }
        }
        if (flag) {
            this.pathToEntity = this.worldObj.getEntityPathToXYZ((Entity)this, i, j, k, 10.0f, true, false, false, true);
        }
        this.worldObj.theProfiler.endSection();
    }
    
    protected void attackEntity(final Entity par1Entity, final float par2) {
    }
    
    public float getBlockPathWeight(final int par1, final int par2, final int par3) {
        return 0.0f;
    }
    
    protected Entity findPlayerToAttack() {
        return null;
    }
    
    public boolean getCanSpawnHere() {
        final int i = MathHelper.floor_double(this.posX);
        final int j = MathHelper.floor_double(this.boundingBox.minY);
        final int k = MathHelper.floor_double(this.posZ);
        return super.getCanSpawnHere() && this.getBlockPathWeight(i, j, k) >= 0.0f;
    }
    
    public boolean hasPath() {
        return this.pathToEntity != null;
    }
    
    public void setPathToEntity(final PathEntity par1PathEntity) {
        this.pathToEntity = par1PathEntity;
    }
    
    public Entity getEntityToAttack() {
        return this.entityToAttack;
    }
    
    public void setTarget(final Entity par1Entity) {
        this.entityToAttack = par1Entity;
    }
    
    public boolean func_110173_bK() {
        return this.func_110176_b(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ));
    }
    
    public boolean func_110176_b(final int par1, final int par2, final int par3) {
        return this.maximumHomeDistance == -1.0f || this.homePosition.getDistanceSquared(par1, par2, par3) < this.maximumHomeDistance * this.maximumHomeDistance;
    }
    
    public void setHomeArea(final int par1, final int par2, final int par3, final int par4) {
        this.homePosition.set(par1, par2, par3);
        this.maximumHomeDistance = (float)par4;
    }
    
    public ChunkCoordinates getHomePosition() {
        return this.homePosition;
    }
    
    public float func_110174_bM() {
        return this.maximumHomeDistance;
    }
    
    public void detachHome() {
        this.maximumHomeDistance = -1.0f;
    }
    
    public boolean hasHome() {
        return this.maximumHomeDistance != -1.0f;
    }
    
    protected void func_110159_bB() {
        super.func_110159_bB();
        if (this.getLeashed() && this.getLeashedToEntity() != null && this.getLeashedToEntity().worldObj == this.worldObj) {
            final Entity entity = this.getLeashedToEntity();
            this.setHomeArea((int)entity.posX, (int)entity.posY, (int)entity.posZ, 5);
            final float f = this.getDistanceToEntity(entity);
            if (this instanceof EntityTameable && ((EntityTameable)this).isSitting()) {
                if (f > 10.0f) {
                    this.clearLeashed(true, true);
                }
                return;
            }
            if (!this.field_110180_bt) {
                this.tasks.addTask(2, this.field_110178_bs);
                this.getNavigator().setAvoidsWater(false);
                this.field_110180_bt = true;
            }
            this.func_142017_o(f);
            if (f > 4.0f) {
                this.getNavigator().tryMoveToEntityLiving(entity, 1.0);
            }
            if (f > 6.0f) {
                final double d0 = (entity.posX - this.posX) / f;
                final double d2 = (entity.posY - this.posY) / f;
                final double d3 = (entity.posZ - this.posZ) / f;
                this.motionX += d0 * Math.abs(d0) * 0.4;
                this.motionY += d2 * Math.abs(d2) * 0.4;
                this.motionZ += d3 * Math.abs(d3) * 0.4;
            }
            if (f > 10.0f) {
                this.clearLeashed(true, true);
            }
        }
        else if (!this.getLeashed() && this.field_110180_bt) {
            this.field_110180_bt = false;
            this.tasks.removeTask(this.field_110178_bs);
            this.getNavigator().setAvoidsWater(true);
            this.detachHome();
        }
    }
    
    protected void func_142017_o(final float par1) {
    }
    
    static {
        field_110179_h = UUID.fromString("E199AD21-BA8A-4C53-8D13-6182D5C69D3A");
        field_110181_i = new AttributeModifier(EntityCreature.field_110179_h, "Fleeing speed bonus", 2.0, 2).setSaved(false);
    }
}
