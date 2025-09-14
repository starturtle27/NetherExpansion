//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package net.netherX;

import net.minecraftforge.fluids.*;

public class PoisonFluid extends Fluid
{
    public PoisonFluid(final String fluidName) {
        super("poison");
        this.setDensity(1000);
        this.setViscosity(1000);
        FluidRegistry.registerFluid((Fluid)this);
    }
}
