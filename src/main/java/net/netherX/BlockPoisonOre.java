//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package net.netherX;

import net.minecraft.block.*;
import net.minecraft.world.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.monster.*;
import net.minecraft.util.*;
import net.minecraft.entity.*;
import java.util.*;

public class BlockPoisonOre extends BlockOre
{
    int amountMined;
    Random rand;
    
    public BlockPoisonOre(final int par1) {
        super(par1);
        this.amountMined = 0;
        this.rand = new Random();
    }
    
    public void harvestBlock(final World par1World, final EntityPlayer par2EntityPlayer, final int par3, final int par4, final int par5, final int par6) {
        super.harvestBlock(par1World, par2EntityPlayer, par3, par4, par5, par6);
        if (NetherX.enableZPigAngryness) {
            if (this.amountMined != 75) {
                ++this.amountMined;
            }
            if (this.rand.nextInt(100) <= this.amountMined) {
                if (NetherX.enableZPigMessage) {
                    par2EntityPlayer.addChatMessage("The Zombie Pigmen are angry at you for mining their ores!");
                }
                final List list = par1World.getEntitiesWithinAABB((Class)EntityPigZombie.class, AxisAlignedBB.getBoundingBox((double)(par3 - 40), (double)(par4 - 40), (double)(par5 - 40), (double)(par3 + 40 + 1), (double)(par4 + 40 + 1), (double)(par5 + 40 + 1)));
                for (int i = 0; i < list.size(); ++i) {
                    final Entity entity1 = list.get(i);
                    if (entity1 instanceof EntityPigZombie) {
                        final EntityPigZombie entitypigzombie = (EntityPigZombie)entity1;
                        entitypigzombie.angerLevel = 200 + this.rand.nextInt(200);
                        entitypigzombie.randomSoundDelay = this.rand.nextInt(20);
                        entitypigzombie.entityToAttack = (Entity)par2EntityPlayer;
                    }
                }
            }
        }
    }
}
