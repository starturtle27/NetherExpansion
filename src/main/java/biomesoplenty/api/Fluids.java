//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package biomesoplenty.api;

import com.google.common.base.*;
import net.minecraft.item.*;
import net.minecraft.block.*;
import net.minecraftforge.fluids.*;

public class Fluids
{
    public static Optional<? extends Item> bopBucket;
    public static Optional<? extends Block> springWater;
    public static Optional<? extends Block> liquidPoison;
    public static Optional<? extends Fluid> springWaterFluid;
    public static Optional<? extends Fluid> liquidPoisonFluid;
    
    static {
        Fluids.bopBucket = (Optional<? extends Item>)Optional.absent();
        Fluids.springWater = (Optional<? extends Block>)Optional.absent();
        Fluids.liquidPoison = (Optional<? extends Block>)Optional.absent();
        Fluids.springWaterFluid = (Optional<? extends Fluid>)Optional.absent();
        Fluids.liquidPoisonFluid = (Optional<? extends Fluid>)Optional.absent();
    }
}
