//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package net.netherX.worldgen;

import net.minecraft.world.gen.feature.*;
import net.minecraft.util.*;
import java.util.*;
import net.minecraft.world.*;
import net.netherX.*;
import net.minecraft.block.*;
import net.minecraft.inventory.*;
import net.minecraft.tileentity.*;
import net.minecraft.block.material.*;
import net.minecraft.item.*;

public class WorldGenNetherDungeons extends WorldGenerator
{
    public static final WeightedRandomChestContent[] field_111189_a;
    Random random;
    
    public WorldGenNetherDungeons() {
        this.random = new Random();
    }
    
    public boolean generate(final World par1World, final Random par2Random, final int par3, final int par4, final int par5) {
        final byte b0 = 3;
        final int l = par2Random.nextInt(2) + 2;
        final int i1 = par2Random.nextInt(2) + 2;
        int j1 = 0;
        for (int k1 = par3 - l - 1; k1 <= par3 + l + 1; ++k1) {
            for (int l2 = par4 - 1; l2 <= par4 + b0 + 1; ++l2) {
                for (int i2 = par5 - i1 - 1; i2 <= par5 + i1 + 1; ++i2) {
                    final Material material = par1World.getBlockMaterial(k1, l2, i2);
                    if (l2 == par4 - 1 && !material.isSolid()) {
                        return false;
                    }
                    if (l2 == par4 + b0 + 1 && !material.isSolid()) {
                        return false;
                    }
                    if ((k1 == par3 - l - 1 || k1 == par3 + l + 1 || i2 == par5 - i1 - 1 || i2 == par5 + i1 + 1) && l2 == par4 && par1World.isAirBlock(k1, l2, i2) && par1World.isAirBlock(k1, l2 + 1, i2)) {
                        ++j1;
                    }
                }
            }
        }
        if (j1 >= 1 && j1 <= 5) {
            for (int k1 = par3 - l - 1; k1 <= par3 + l + 1; ++k1) {
                for (int l2 = par4 + b0; l2 >= par4 - 1; --l2) {
                    for (int i2 = par5 - i1 - 1; i2 <= par5 + i1 + 1; ++i2) {
                        if (k1 != par3 - l - 1 && l2 != par4 - 1 && i2 != par5 - i1 - 1 && k1 != par3 + l + 1 && l2 != par4 + b0 + 1 && i2 != par5 + i1 + 1) {
                            par1World.setBlockToAir(k1, l2, i2);
                        }
                        else if (l2 >= 0 && !par1World.getBlockMaterial(k1, l2 - 1, i2).isSolid()) {
                            par1World.setBlockToAir(k1, l2, i2);
                        }
                        else if (par1World.getBlockMaterial(k1, l2, i2).isSolid()) {
                            if (l2 == par4 - 1 && par2Random.nextInt(4) != 0) {
                                par1World.setBlock(k1, l2, i2, NetherX.chiseledNetherrackCharred.blockID, 0, 2);
                            }
                            else {
                                par1World.setBlock(k1, l2, i2, NetherX.chiseledNetherrack.blockID, 0, 2);
                            }
                        }
                    }
                }
            }
            for (int k1 = 0; k1 < 2; ++k1) {
                for (int l2 = 0; l2 < 3; ++l2) {
                    final int i2 = par3 + par2Random.nextInt(l * 2 + 1) - l;
                    final int j2 = par5 + par2Random.nextInt(i1 * 2 + 1) - i1;
                    if (par1World.isAirBlock(i2, par4, j2)) {
                        int k2 = 0;
                        if (par1World.getBlockMaterial(i2 - 1, par4, j2).isSolid()) {
                            ++k2;
                        }
                        if (par1World.getBlockMaterial(i2 + 1, par4, j2).isSolid()) {
                            ++k2;
                        }
                        if (par1World.getBlockMaterial(i2, par4, j2 - 1).isSolid()) {
                            ++k2;
                        }
                        if (par1World.getBlockMaterial(i2, par4, j2 + 1).isSolid()) {
                            ++k2;
                        }
                        if (k2 == 1) {
                            par1World.setBlock(i2, par4, j2, Block.chest.blockID, 0, 2);
                            final TileEntityChest tileentitychest = (TileEntityChest)par1World.getBlockTileEntity(i2, par4, j2);
                            if (tileentitychest == null) {
                                break;
                            }
                            WeightedRandomChestContent.generateChestContents(par2Random, WorldGenNetherDungeons.field_111189_a, (IInventory)tileentitychest, 8);
                            break;
                        }
                    }
                }
            }
            par1World.setBlock(par3, par4, par5, Block.mobSpawner.blockID, 0, 2);
            final TileEntityMobSpawner tileentitymobspawner = (TileEntityMobSpawner)par1World.getBlockTileEntity(par3, par4, par5);
            if (tileentitymobspawner != null) {
                tileentitymobspawner.getSpawnerLogic().setMobID("Skeleton");
            }
            else {
                System.err.println("Failed to fetch mob spawner entity at (" + par3 + ", " + par4 + ", " + par5 + ")");
            }
            return true;
        }
        return false;
    }
    
    static {
        field_111189_a = new WeightedRandomChestContent[] { new WeightedRandomChestContent(Item.saddle.itemID, 0, 1, 1, 1), new WeightedRandomChestContent(NetherX.malite.itemID, 0, 1, 2, 5), new WeightedRandomChestContent(Item.ingotGold.itemID, 0, 1, 2, 5), new WeightedRandomChestContent(Item.bread.itemID, 0, 1, 1, 3), new WeightedRandomChestContent(Item.fermentedSpiderEye.itemID, 0, 1, 2, 7), new WeightedRandomChestContent(Item.gunpowder.itemID, 0, 1, 4, 10), new WeightedRandomChestContent(Item.silk.itemID, 0, 1, 4, 10), new WeightedRandomChestContent(NetherX.poisonbucketEmpty.itemID, 0, 1, 1, 8), new WeightedRandomChestContent(Item.poisonousPotato.itemID, 0, 1, 1, 4), new WeightedRandomChestContent(Item.glowstone.itemID, 0, 1, 4, 3), new WeightedRandomChestContent(NetherX.shovelNetherrack.itemID, 0, 1, 1, 4), new WeightedRandomChestContent(NetherX.axeNetherrack.itemID, 0, 1, 1, 4), new WeightedRandomChestContent(NetherX.pickaxeNetherrack.itemID, 0, 1, 1, 2), new WeightedRandomChestContent(NetherX.swordNetherrack.itemID, 0, 1, 1, 3), new WeightedRandomChestContent(NetherX.shovelMalite.itemID, 0, 1, 1, 1), new WeightedRandomChestContent(NetherX.axeMalite.itemID, 0, 1, 1, 1), new WeightedRandomChestContent(NetherX.bowlGlutrootSoup.itemID, 0, 1, 1, 2), new WeightedRandomChestContent(Item.goldenCarrot.itemID, 0, 1, 1, 2), new WeightedRandomChestContent(Item.bowlSoup.itemID, 0, 1, 1, 3) };
    }
}
