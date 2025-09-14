//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package net.netherX.sounds;

import net.minecraft.block.*;

public class StepSoundNetherCrop extends StepSound
{
    public final String stepSoundName;
    public final float stepSoundVolume;
    public final float stepSoundPitch;
    
    public StepSoundNetherCrop(final String par1Str, final float par2, final float par3) {
        super(par1Str, par2, par3);
        this.stepSoundName = par1Str;
        this.stepSoundVolume = par2;
        this.stepSoundPitch = par3;
    }
    
    public float getVolume() {
        return this.stepSoundVolume;
    }
    
    public float getPitch() {
        return this.stepSoundPitch;
    }
    
    public String getBreakSound() {
        return "netherx:nethercrop";
    }
    
    public String getStepSound() {
        return "netherx:nethercrop";
    }
    
    public String getPlaceSound() {
        return this.getBreakSound();
    }
}
