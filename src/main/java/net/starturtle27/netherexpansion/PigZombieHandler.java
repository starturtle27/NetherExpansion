//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package net.netherX;

import net.minecraftforge.event.entity.living.*;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.*;
import net.minecraftforge.event.*;

public class PigZombieHandler
{
    @ForgeSubscribe
    public void onEntityLivingSpawn(final LivingSpawnEvent event) {
        if (!event.world.isRemote && event.entityLiving instanceof EntityPigZombie) {
            final EntityPigZombie pz = (EntityPigZombie)event.entityLiving;
            if (pz.getCanSpawnHere()) {
                final EntityPigZombieConvertable replacement = new EntityPigZombieConvertable(event.world);
                replacement.setLocationAndAngles(pz.posX, pz.posY, pz.posZ, pz.rotationYaw, pz.rotationPitch);
                replacement.addRandomArmor();
                event.world.spawnEntityInWorld((Entity)replacement);
                pz.setDead();
            }
        }
    }
}
