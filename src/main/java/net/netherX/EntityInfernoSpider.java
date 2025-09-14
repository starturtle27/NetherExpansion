//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package net.netherX;

import net.minecraft.entity.monster.*;
import net.minecraft.world.*;
import net.minecraft.entity.*;
import net.minecraft.item.*;

public class EntityInfernoSpider extends EntitySpider
{
    public EntityInfernoSpider(final World par1World) {
        super(par1World);
        this.setSize(0.5f, 0.5f);
        this.isImmuneToFire = true;
    }
    
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(9.0);
    }
    
    public boolean attackEntityAsMob(final Entity par1Entity) {
        if (super.attackEntityAsMob(par1Entity)) {
            if (par1Entity instanceof EntityLivingBase) {
                byte b0 = 4;
                if (this.worldObj.difficultySetting > 1) {
                    if (this.worldObj.difficultySetting == 2) {
                        b0 = 7;
                    }
                    else if (this.worldObj.difficultySetting == 3) {
                        b0 = 10;
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
    
    public EntityLivingData onSpawnWithEgg(final EntityLivingData par1EntityLivingData) {
        return par1EntityLivingData;
    }
    
    protected void dropFewItems(final boolean par1, final int par2) {
        super.dropFewItems(par1, par2);
        if (par1 && (this.rand.nextInt(3) == 0 || this.rand.nextInt(1 + par2) > 0)) {
            this.dropItem(Item.spiderEye.itemID, 1);
        }
        if (par1 && (this.rand.nextInt(3) == 0 || this.rand.nextInt(1 + par2) > 0)) {
            this.dropItem(Item.blazePowder.itemID, 1);
        }
    }
}
