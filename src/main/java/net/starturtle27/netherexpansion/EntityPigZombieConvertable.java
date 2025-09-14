//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package net.netherX;

import net.minecraft.world.*;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.ai.attributes.*;
import net.minecraft.nbt.*;
import net.minecraft.util.*;
import net.minecraft.entity.player.*;
import java.util.*;
import net.minecraft.item.*;
import net.minecraft.entity.*;
import net.minecraft.potion.*;

public class EntityPigZombieConvertable extends EntityPigZombie
{
    private static final UUID field_110189_bq;
    private static final AttributeModifier field_110190_br;
    public int angerLevel;
    public int randomSoundDelay;
    private Entity field_110191_bu;
    
    public EntityPigZombieConvertable(final World par1World) {
        super(par1World);
        this.isImmuneToFire = true;
    }
    
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(EntityZombie.field_110186_bp).setAttribute(0.0);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.5);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(5.0);
    }
    
    protected boolean isAIEnabled() {
        return false;
    }
    
    public void onUpdate() {
        if (this.field_110191_bu != this.entityToAttack && !this.worldObj.isRemote) {
            final AttributeInstance attributeinstance = this.getEntityAttribute(SharedMonsterAttributes.movementSpeed);
            attributeinstance.removeModifier(EntityPigZombieConvertable.field_110190_br);
            if (this.entityToAttack != null) {
                attributeinstance.applyModifier(EntityPigZombieConvertable.field_110190_br);
            }
        }
        this.field_110191_bu = this.entityToAttack;
        if (this.randomSoundDelay > 0 && --this.randomSoundDelay == 0) {
            this.playSound("mob.zombiepig.zpigangry", this.getSoundVolume() * 2.0f, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2f + 1.0f) * 1.8f);
        }
        super.onUpdate();
    }
    
    public boolean getCanSpawnHere() {
        return this.worldObj.difficultySetting > 0 && this.worldObj.checkNoEntityCollision(this.boundingBox) && this.worldObj.getCollidingBoundingBoxes((Entity)this, this.boundingBox).isEmpty() && !this.worldObj.isAnyLiquid(this.boundingBox);
    }
    
    public void writeEntityToNBT(final NBTTagCompound par1NBTTagCompound) {
        super.writeEntityToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setShort("Anger", (short)this.angerLevel);
    }
    
    public void readEntityFromNBT(final NBTTagCompound par1NBTTagCompound) {
        super.readEntityFromNBT(par1NBTTagCompound);
        this.angerLevel = par1NBTTagCompound.getShort("Anger");
    }
    
    protected Entity findPlayerToAttack() {
        return (this.angerLevel == 0) ? null : super.findPlayerToAttack();
    }
    
    public boolean attackEntityFrom(final DamageSource par1DamageSource, final float par2) {
        if (this.isEntityInvulnerable()) {
            return false;
        }
        final Entity entity = par1DamageSource.getEntity();
        if (entity instanceof EntityPlayer) {
            final List list = this.worldObj.getEntitiesWithinAABBExcludingEntity((Entity)this, this.boundingBox.expand(32.0, 32.0, 32.0));
            for (int i = 0; i < list.size(); ++i) {
                final Entity entity2 = list.get(i);
                if (entity2 instanceof EntityPigZombieConvertable) {
                    final EntityPigZombieConvertable entitypigzombie = (EntityPigZombieConvertable)entity2;
                    entitypigzombie.becomeAngryAt(entity);
                }
            }
            this.becomeAngryAt(entity);
        }
        return super.attackEntityFrom(par1DamageSource, par2);
    }
    
    public void becomeAngryAt(final Entity par1Entity) {
        this.entityToAttack = par1Entity;
        this.angerLevel = 400 + this.rand.nextInt(400);
        this.randomSoundDelay = this.rand.nextInt(40);
    }
    
    protected String getLivingSound() {
        return "mob.zombiepig.zpig";
    }
    
    protected String getHurtSound() {
        return "mob.zombiepig.zpighurt";
    }
    
    protected String getDeathSound() {
        return "mob.zombiepig.zpigdeath";
    }
    
    protected void dropFewItems(final boolean par1, final int par2) {
        for (int j = this.rand.nextInt(2 + par2), k = 0; k < j; ++k) {
            this.dropItem(Item.rottenFlesh.itemID, 1);
        }
        for (int j = this.rand.nextInt(2 + par2), k = 0; k < j; ++k) {
            this.dropItem(Item.goldNugget.itemID, 1);
        }
    }
    
    protected void dropRareDrop(final int par1) {
        switch (this.rand.nextInt(3)) {
            case 0: {
                this.dropItem(Item.ingotGold.itemID, 1);
                break;
            }
            case 1: {
                this.dropItem(Item.goldenCarrot.itemID, 1);
                break;
            }
        }
    }
    
    protected int getDropItemId() {
        return Item.rottenFlesh.itemID;
    }
    
    protected void addRandomArmor() {
        this.setCurrentItemOrArmor(0, new ItemStack(Item.swordGold));
    }
    
    public EntityLivingData onSpawnWithEgg(final EntityLivingData par1EntityLivingData) {
        super.onSpawnWithEgg(par1EntityLivingData);
        this.setVillager(false);
        return par1EntityLivingData;
    }
    
    public boolean interact(final EntityPlayer par1EntityPlayer) {
        final ItemStack itemstack = par1EntityPlayer.getCurrentEquippedItem();
        if (itemstack != null && itemstack.getItem() == Item.goldenCarrot && itemstack.getItemDamage() == 0 && !this.isChild() && this.isPotionActive(Potion.weakness)) {
            if (!par1EntityPlayer.capabilities.isCreativeMode) {
                final ItemStack itemStack = itemstack;
                --itemStack.stackSize;
            }
            if (itemstack.stackSize <= 0) {
                par1EntityPlayer.inventory.setInventorySlotContents(par1EntityPlayer.inventory.currentItem, (ItemStack)null);
            }
            if (!this.worldObj.isRemote) {
                this.startConversion(this.rand.nextInt(2401) + 3600);
            }
            return true;
        }
        return false;
    }
    
    protected void convertToVillager() {
        final EntityPigman entitypigman = new EntityPigman(this.worldObj);
        entitypigman.copyLocationAndAnglesFrom((Entity)this);
        entitypigman.onSpawnWithEgg((EntityLivingData)null);
        this.worldObj.removeEntity((Entity)this);
        this.worldObj.spawnEntityInWorld((Entity)entitypigman);
        entitypigman.addPotionEffect(new PotionEffect(Potion.confusion.id, 200, 0));
        this.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1017, (int)this.posX, (int)this.posY, (int)this.posZ, 0);
    }
    
    static {
        field_110189_bq = UUID.fromString("49455A49-7EC5-45BA-B886-3B90B23A1718");
        field_110190_br = new AttributeModifier(EntityPigZombieConvertable.field_110189_bq, "Attacking speed boost", 0.45, 0).setSaved(false);
    }
}
