//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package net.netherX;

import cpw.mods.fml.relauncher.*;
import net.minecraft.world.*;
import net.minecraft.entity.player.*;
import net.minecraft.block.material.*;
import net.minecraft.util.*;
import net.minecraft.entity.*;
import net.minecraft.block.*;
import java.util.*;
import net.minecraft.nbt.*;

public class EntityObsidianBoat extends Entity
{
    private boolean field_70279_a;
    private double speedMultiplier;
    private int boatPosRotationIncrements;
    private double boatX;
    private double boatY;
    private double boatZ;
    private double boatYaw;
    private double boatPitch;
    @SideOnly(Side.CLIENT)
    private double velocityX;
    @SideOnly(Side.CLIENT)
    private double velocityY;
    @SideOnly(Side.CLIENT)
    private double velocityZ;
    
    public EntityObsidianBoat(final World par1World) {
        super(par1World);
        this.field_70279_a = true;
        this.speedMultiplier = 0.07;
        this.preventEntitySpawning = true;
        this.setSize(1.5f, 0.6f);
        this.yOffset = this.height / 1.2f;
        this.isImmuneToFire = true;
    }
    
    protected boolean canTriggerWalking() {
        return false;
    }
    
    protected void entityInit() {
        this.dataWatcher.addObject(17, (Object)new Integer(0));
        this.dataWatcher.addObject(18, (Object)new Integer(1));
        this.dataWatcher.addObject(19, (Object)new Float(0.0f));
    }
    
    public AxisAlignedBB getCollisionBox(final Entity par1Entity) {
        return par1Entity.boundingBox;
    }
    
    public AxisAlignedBB getBoundingBox() {
        return this.boundingBox;
    }
    
    public boolean canBePushed() {
        return true;
    }
    
    public EntityObsidianBoat(final World par1World, final double par2, final double par4, final double par6) {
        this(par1World);
        this.setPosition(par2, par4 + this.yOffset, par6);
        this.motionX = 0.0;
        this.motionY = 0.0;
        this.motionZ = 0.0;
        this.prevPosX = par2;
        this.prevPosY = par4;
        this.prevPosZ = par6;
    }
    
    public double getMountedYOffset() {
        return this.height * 0.0;
    }
    
    public boolean attackEntityFrom(final DamageSource par1DamageSource, final float par2) {
        if (this.isEntityInvulnerable()) {
            return false;
        }
        if (!this.worldObj.isRemote && !this.isDead) {
            this.setForwardDirection(-this.getForwardDirection());
            this.setTimeSinceHit(10);
            this.setDamageTaken(this.getDamageTaken() + par2 * 10.0f);
            this.setBeenAttacked();
            final boolean flag = par1DamageSource.getEntity() instanceof EntityPlayer && ((EntityPlayer)par1DamageSource.getEntity()).capabilities.isCreativeMode;
            if (flag || this.getDamageTaken() > 50.0f) {
                if (this.riddenByEntity != null) {
                    this.riddenByEntity.mountEntity((Entity)this);
                }
                if (!flag) {
                    this.dropItemWithOffset(NetherX.obsidianBoat.itemID, 1, 0.0f);
                }
                this.setDead();
            }
            return true;
        }
        return true;
    }
    
    @SideOnly(Side.CLIENT)
    public void performHurtAnimation() {
        this.setForwardDirection(-this.getForwardDirection());
        this.setTimeSinceHit(10);
        this.setDamageTaken(this.getDamageTaken() * 11.0f);
    }
    
    public boolean canBeCollidedWith() {
        return !this.isDead;
    }
    
    @SideOnly(Side.CLIENT)
    public void setPositionAndRotation2(final double par1, final double par3, final double par5, final float par7, final float par8, final int par9) {
        if (this.field_70279_a) {
            this.boatPosRotationIncrements = par9 + 5;
        }
        else {
            final double d3 = par1 - this.posX;
            final double d4 = par3 - this.posY;
            final double d5 = par5 - this.posZ;
            final double d6 = d3 * d3 + d4 * d4 + d5 * d5;
            if (d6 <= 1.0) {
                return;
            }
            this.boatPosRotationIncrements = 3;
        }
        this.boatX = par1;
        this.boatY = par3;
        this.boatZ = par5;
        this.boatYaw = par7;
        this.boatPitch = par8;
        this.motionX = this.velocityX;
        this.motionY = this.velocityY;
        this.motionZ = this.velocityZ;
    }
    
    @SideOnly(Side.CLIENT)
    public void setVelocity(final double par1, final double par3, final double par5) {
        this.motionX = par1;
        this.velocityX = par1;
        this.motionY = par3;
        this.velocityY = par3;
        this.motionZ = par5;
        this.velocityZ = par5;
    }
    
    public void onUpdate() {
        super.onUpdate();
        if (this.getTimeSinceHit() > 0) {
            this.setTimeSinceHit(this.getTimeSinceHit() - 1);
        }
        if (this.getDamageTaken() > 0.0f) {
            this.setDamageTaken(this.getDamageTaken() - 1.0f);
        }
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        final byte b0 = 5;
        double d0 = 0.0;
        for (int i = 0; i < b0; ++i) {
            final double d2 = this.boundingBox.minY + (this.boundingBox.maxY - this.boundingBox.minY) * (i + 0) / b0 - 0.125;
            final double d3 = this.boundingBox.minY + (this.boundingBox.maxY - this.boundingBox.minY) * (i + 1) / b0 - 0.125;
            final AxisAlignedBB axisalignedbb = AxisAlignedBB.getAABBPool().getAABB(this.boundingBox.minX, d2, this.boundingBox.minZ, this.boundingBox.maxX, d3, this.boundingBox.maxZ);
            if (this.worldObj.isAABBInMaterial(axisalignedbb, Material.lava)) {
                d0 += 1.0 / b0;
            }
        }
        final double d4 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
        if (d4 > 0.2625) {
            final double d5 = Math.cos(this.rotationYaw * 3.141592653589793 / 180.0);
            final double d6 = Math.sin(this.rotationYaw * 3.141592653589793 / 180.0);
            for (int j = 0; j < 1.0 + d4 * 60.0; ++j) {
                final double d7 = this.rand.nextFloat() * 2.0f - 1.0f;
                final double d8 = (this.rand.nextInt(2) * 2 - 1) * 0.7;
                if (this.rand.nextBoolean()) {
                    final double d9 = this.posX - d5 * d7 * 0.8 + d6 * d8;
                    final double d10 = this.posZ - d6 * d7 * 0.8 - d5 * d8;
                    this.worldObj.spawnParticle("lava", d9, this.posY - 0.125, d10, this.motionX, this.motionY, this.motionZ);
                }
                else {
                    final double d9 = this.posX + d5 + d6 * d7 * 0.7;
                    final double d10 = this.posZ + d6 - d5 * d7 * 0.7;
                    this.worldObj.spawnParticle("lava", d9, this.posY - 0.125, d10, this.motionX, this.motionY, this.motionZ);
                }
            }
        }
        if (this.worldObj.isRemote && this.field_70279_a) {
            if (this.boatPosRotationIncrements > 0) {
                final double d5 = this.posX + (this.boatX - this.posX) / this.boatPosRotationIncrements;
                final double d6 = this.posY + (this.boatY - this.posY) / this.boatPosRotationIncrements;
                final double d11 = this.posZ + (this.boatZ - this.posZ) / this.boatPosRotationIncrements;
                final double d12 = MathHelper.wrapAngleTo180_double(this.boatYaw - this.rotationYaw);
                this.rotationYaw += (float)(d12 / this.boatPosRotationIncrements);
                this.rotationPitch += (float)((this.boatPitch - this.rotationPitch) / this.boatPosRotationIncrements);
                --this.boatPosRotationIncrements;
                this.setPosition(d5, d6, d11);
                this.setRotation(this.rotationYaw, this.rotationPitch);
            }
            else {
                final double d5 = this.posX + this.motionX;
                final double d6 = this.posY + this.motionY;
                final double d11 = this.posZ + this.motionZ;
                this.setPosition(d5, d6, d11);
                if (this.onGround) {
                    this.motionX *= 0.5;
                    this.motionY *= 0.5;
                    this.motionZ *= 0.5;
                }
                this.motionX *= 0.9900000095367432;
                this.motionY *= 0.949999988079071;
                this.motionZ *= 0.9900000095367432;
            }
        }
        else {
            if (d0 < 1.0) {
                final double d5 = d0 * 2.0 - 1.0;
                this.motionY += 0.03999999910593033 * d5;
            }
            else {
                if (this.motionY < 0.0) {
                    this.motionY /= 2.0;
                }
                this.motionY += 0.007000000216066837;
            }
            if (this.riddenByEntity != null && this.riddenByEntity instanceof EntityLivingBase) {
                final double d5 = ((EntityLivingBase)this.riddenByEntity).moveForward;
                if (d5 > 0.0) {
                    final double d6 = -Math.sin(this.riddenByEntity.rotationYaw * 3.141593f / 180.0f);
                    final double d11 = Math.cos(this.riddenByEntity.rotationYaw * 3.141593f / 180.0f);
                    this.motionX += d6 * this.speedMultiplier * 0.0500000007450581;
                    this.motionZ += d11 * this.speedMultiplier * 0.0500000007450581;
                }
            }
            double d5 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
            if (d5 > 0.35) {
                final double d6 = 0.35 / d5;
                this.motionX *= d6;
                this.motionZ *= d6;
                d5 = 0.35;
            }
            if (d5 > d4 && this.speedMultiplier < 0.35) {
                this.speedMultiplier += (0.35 - this.speedMultiplier) / 35.0;
                if (this.speedMultiplier > 0.35) {
                    this.speedMultiplier = 0.35;
                }
            }
            else {
                this.speedMultiplier -= (this.speedMultiplier - 0.07) / 35.0;
                if (this.speedMultiplier < 0.07) {
                    this.speedMultiplier = 0.07;
                }
            }
            if (this.onGround) {
                this.motionX *= 0.5;
                this.motionY *= 0.5;
                this.motionZ *= 0.5;
            }
            this.moveEntity(this.motionX, this.motionY, this.motionZ);
            if (this.isCollidedHorizontally && d4 > 0.2) {
                if (!this.worldObj.isRemote && !this.isDead) {
                    this.setDead();
                    for (int k = 0; k < 3; ++k) {
                        this.dropItemWithOffset(Block.obsidian.blockID, 1, 0.0f);
                    }
                }
            }
            else {
                this.motionX *= 0.9900000095367432;
                this.motionY *= 0.949999988079071;
                this.motionZ *= 0.9900000095367432;
            }
            this.rotationPitch = 0.0f;
            double d6 = this.rotationYaw;
            final double d11 = this.prevPosX - this.posX;
            final double d12 = this.prevPosZ - this.posZ;
            if (d11 * d11 + d12 * d12 > 0.001) {
                d6 = (float)(Math.atan2(d12, d11) * 180.0 / 3.141592653589793);
            }
            double d13 = MathHelper.wrapAngleTo180_double(d6 - this.rotationYaw);
            if (d13 > 20.0) {
                d13 = 20.0;
            }
            if (d13 < -20.0) {
                d13 = -20.0;
            }
            this.setRotation(this.rotationYaw += (float)d13, this.rotationPitch);
            if (!this.worldObj.isRemote) {
                final List list = this.worldObj.getEntitiesWithinAABBExcludingEntity((Entity)this, this.boundingBox.expand(0.2000000029802322, 0.0, 0.2000000029802322));
                if (list != null && !list.isEmpty()) {
                    for (int l = 0; l < list.size(); ++l) {
                        final Entity entity = list.get(l);
                        if (entity != this.riddenByEntity && entity.canBePushed() && entity instanceof EntityObsidianBoat) {
                            entity.applyEntityCollision((Entity)this);
                        }
                    }
                }
                for (int l = 0; l < 4; ++l) {
                    final int i2 = MathHelper.floor_double(this.posX + (l % 2 - 0.5) * 0.8);
                    final int j2 = MathHelper.floor_double(this.posZ + (l / 2 - 0.5) * 0.8);
                    for (int k2 = 0; k2 < 2; ++k2) {
                        final int l2 = MathHelper.floor_double(this.posY) + k2;
                        final int i3 = this.worldObj.getBlockId(i2, l2, j2);
                        if (i3 == Block.snow.blockID) {
                            this.worldObj.setBlockToAir(i2, l2, j2);
                        }
                        else if (i3 == Block.waterlily.blockID) {
                            this.worldObj.destroyBlock(i2, l2, j2, true);
                        }
                    }
                }
                if (this.riddenByEntity != null && this.riddenByEntity.isDead) {
                    this.riddenByEntity = null;
                }
            }
        }
    }
    
    public void updateRiderPosition() {
        if (this.riddenByEntity != null) {
            final double d0 = Math.cos(this.rotationYaw * 3.141592653589793 / 180.0) * 0.4;
            final double d2 = Math.sin(this.rotationYaw * 3.141592653589793 / 180.0) * 0.4;
            this.riddenByEntity.setPosition(this.posX + d0, this.posY + this.getMountedYOffset() + this.riddenByEntity.getYOffset(), this.posZ + d2);
        }
    }
    
    protected void writeEntityToNBT(final NBTTagCompound par1NBTTagCompound) {
    }
    
    protected void readEntityFromNBT(final NBTTagCompound par1NBTTagCompound) {
    }
    
    @SideOnly(Side.CLIENT)
    public float getShadowSize() {
        return 0.0f;
    }
    
    public boolean interactFirst(final EntityPlayer par1EntityPlayer) {
        if (this.riddenByEntity != null && this.riddenByEntity instanceof EntityPlayer && this.riddenByEntity != par1EntityPlayer) {
            return true;
        }
        if (!this.worldObj.isRemote) {
            par1EntityPlayer.mountEntity((Entity)this);
        }
        return true;
    }
    
    public void setDamageTaken(final float par1) {
        this.dataWatcher.updateObject(19, (Object)par1);
    }
    
    public float getDamageTaken() {
        return this.dataWatcher.getWatchableObjectFloat(19);
    }
    
    public void setTimeSinceHit(final int par1) {
        this.dataWatcher.updateObject(17, (Object)par1);
    }
    
    public int getTimeSinceHit() {
        return this.dataWatcher.getWatchableObjectInt(17);
    }
    
    public void setForwardDirection(final int par1) {
        this.dataWatcher.updateObject(18, (Object)par1);
    }
    
    public int getForwardDirection() {
        return this.dataWatcher.getWatchableObjectInt(18);
    }
    
    @SideOnly(Side.CLIENT)
    public void func_70270_d(final boolean par1) {
        this.field_70279_a = par1;
    }
    
    public void onCollideWithPlayer(final EntityPlayer player) {
        if (this.riddenByEntity != null && this.riddenByEntity == player) {
            player.extinguish();
        }
        super.onCollideWithPlayer(player);
    }
}
