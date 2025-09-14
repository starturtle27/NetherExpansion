//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package net.netherX;

import net.minecraft.entity.monster.*;
import net.minecraft.world.*;
import net.minecraft.potion.*;
import net.minecraft.entity.*;

public class EntityNetherSpider extends EntitySpider
{
    public EntityNetherSpider(final World par1World) {
        super(par1World);
        this.setSize(1.7f, 1.5f);
        this.isImmuneToFire = true;
    }
    
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(20.0);
    }
    
    public boolean attackEntityAsMob(final Entity par1Entity) {
        if (super.attackEntityAsMob(par1Entity)) {
            if (par1Entity instanceof EntityLivingBase) {
                byte b0 = 1;
                if (this.worldObj.difficultySetting > 1) {
                    if (this.worldObj.difficultySetting == 2) {
                        b0 = 6;
                    }
                    else if (this.worldObj.difficultySetting == 3) {
                        b0 = 15;
                    }
                }
                if (b0 > 0) {
                    ((EntityLivingBase)par1Entity).addPotionEffect(new PotionEffect(Potion.poison.id, b0 * 20, 0));
                }
            }
            return true;
        }
        return false;
    }
    
    public EntityLivingData onSpawnWithEgg(final EntityLivingData par1EntityLivingData) {
        return par1EntityLivingData;
    }
}
