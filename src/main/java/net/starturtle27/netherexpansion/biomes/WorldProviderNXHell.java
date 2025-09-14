//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package net.netherX.biomes;

import net.minecraft.world.*;
import net.minecraft.util.*;
import cpw.mods.fml.relauncher.*;
import net.minecraft.world.chunk.*;
import net.netherX.*;
import net.minecraft.world.biome.*;

public class WorldProviderNXHell extends WorldProviderHell
{
    public void registerWorldChunkManager() {
        this.worldChunkMgr = (WorldChunkManager)new WorldChunkManagerNXHell(this.worldObj.getSeed(), this.terrainType);
        this.isHellWorld = true;
        this.hasNoSky = true;
        this.dimensionId = -1;
    }
    
    @SideOnly(Side.CLIENT)
    public Vec3 getFogColor(final float par1, final float par2) {
        return this.worldObj.getWorldVec3Pool().getVecFromPool(0.2000000029802322, 0.02999999932944775, 0.02999999932944775);
    }
    
    protected void generateLightBrightnessTable() {
        final float f = 0.1f;
        for (int i = 0; i <= 15; ++i) {
            final float f2 = 1.0f - i / 15.0f;
            this.lightBrightnessTable[i] = (1.0f - f2) / (f2 * 3.0f + 1.0f) * (1.0f - f) + f;
        }
    }
    
    public IChunkProvider createChunkGenerator() {
        return (IChunkProvider)new ChunkProviderNXHell(this.worldObj, this.worldObj.getSeed());
    }
    
    public boolean isSurfaceWorld() {
        return false;
    }
    
    public boolean canCoordinateBeSpawn(final int par1, final int par2) {
        return false;
    }
    
    public float calculateCelestialAngle(final long par1, final float par3) {
        return 0.5f;
    }
    
    public boolean canRespawnHere() {
        return NetherX.enableNetherRespawning;
    }
    
    @SideOnly(Side.CLIENT)
    public boolean doesXZShowFog(final int par1, final int par2) {
        return true;
    }
    
    public String getDimensionName() {
        return "Nether";
    }
}
