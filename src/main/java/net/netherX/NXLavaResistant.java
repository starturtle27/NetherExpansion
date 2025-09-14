//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package net.netherX;

import net.minecraftforge.event.entity.living.*;
import net.minecraft.entity.player.*;
import net.minecraft.util.*;
import net.minecraftforge.event.*;

public class NXLavaResistant
{
    @ForgeSubscribe
    public void onEntityAttacked(final LivingAttackEvent event) {
        if (event.entityLiving instanceof EntityPlayer && event.entityLiving.ridingEntity instanceof EntityObsidianBoat) {
            if (event.source == DamageSource.lava) {
                event.setCanceled(true);
            }
            if (event.source == DamageSource.inFire) {
                event.setCanceled(true);
            }
            if (event.source == DamageSource.onFire) {
                event.setCanceled(true);
            }
        }
    }
}
