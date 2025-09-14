//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package net.minecraft.block;

import cpw.mods.fml.relauncher.*;
import net.minecraft.block.material.*;
import net.minecraft.util.*;
import java.util.*;
import net.minecraftforge.common.*;
import net.minecraft.world.*;
import net.minecraft.client.renderer.texture.*;

public class BlockFire extends Block
{
    private int[] chanceToEncourageFire;
    private int[] abilityToCatchFire;
    @SideOnly(Side.CLIENT)
    private Icon[] iconArray;
    
    protected BlockFire(final int par1) {
        super(par1, Material.fire);
        this.chanceToEncourageFire = new int[256];
        this.abilityToCatchFire = new int[256];
        this.setTickRandomly(true);
    }
    
    public void initializeBlock() {
        this.abilityToCatchFire = Block.blockFlammability;
        this.chanceToEncourageFire = Block.blockFireSpreadSpeed;
        this.setBurnRate(Block.planks.blockID, 5, 20);
        this.setBurnRate(Block.woodDoubleSlab.blockID, 5, 20);
        this.setBurnRate(Block.woodSingleSlab.blockID, 5, 20);
        this.setBurnRate(Block.fence.blockID, 5, 20);
        this.setBurnRate(Block.stairsWoodOak.blockID, 5, 20);
        this.setBurnRate(Block.stairsWoodBirch.blockID, 5, 20);
        this.setBurnRate(Block.stairsWoodSpruce.blockID, 5, 20);
        this.setBurnRate(Block.stairsWoodJungle.blockID, 5, 20);
        this.setBurnRate(Block.wood.blockID, 5, 5);
        this.setBurnRate(Block.leaves.blockID, 30, 60);
        this.setBurnRate(Block.bookShelf.blockID, 30, 20);
        this.setBurnRate(Block.tnt.blockID, 15, 100);
        this.setBurnRate(Block.tallGrass.blockID, 60, 100);
        this.setBurnRate(Block.cloth.blockID, 30, 60);
        this.setBurnRate(Block.vine.blockID, 15, 100);
        this.setBurnRate(Block.coalBlock.blockID, 5, 5);
        this.setBurnRate(Block.hay.blockID, 60, 20);
    }
    
    private void setBurnRate(final int par1, final int par2, final int par3) {
        Block.setBurnProperties(par1, par2, par3);
    }
    
    public AxisAlignedBB getCollisionBoundingBoxFromPool(final World par1World, final int par2, final int par3, final int par4) {
        return null;
    }
    
    public boolean isOpaqueCube() {
        return false;
    }
    
    public boolean renderAsNormalBlock() {
        return false;
    }
    
    public int getRenderType() {
        return 3;
    }
    
    public int quantityDropped(final Random par1Random) {
        return 0;
    }
    
    public int tickRate(final World par1World) {
        return 30;
    }
    
    public void updateTick(final World par1World, final int par2, final int par3, final int par4, final Random par5Random) {
        if (par1World.getGameRules().getGameRuleBooleanValue("doFireTick")) {
            final Block base = Block.blocksList[par1World.getBlockId(par2, par3 - 1, par4)];
            final boolean flag = base != null && base.isFireSource(par1World, par2, par3 - 1, par4, par1World.getBlockMetadata(par2, par3 - 1, par4), ForgeDirection.UP);
            if (!this.canPlaceBlockAt(par1World, par2, par3, par4)) {
                par1World.setBlockToAir(par2, par3, par4);
            }
            if (!flag && par1World.isRaining() && (par1World.canLightningStrikeAt(par2, par3, par4) || par1World.canLightningStrikeAt(par2 - 1, par3, par4) || par1World.canLightningStrikeAt(par2 + 1, par3, par4) || par1World.canLightningStrikeAt(par2, par3, par4 - 1) || par1World.canLightningStrikeAt(par2, par3, par4 + 1))) {
                par1World.setBlockToAir(par2, par3, par4);
            }
            else {
                final int l = par1World.getBlockMetadata(par2, par3, par4);
                if (l < 15) {
                    par1World.setBlockMetadata(par2, par3, par4, l + par5Random.nextInt(3) / 2, 4);
                }
                par1World.scheduleBlockUpdate(par2, par3, par4, this.blockID, this.tickRate(par1World) + par5Random.nextInt(10));
                if (!flag && !this.canNeighborBurn(par1World, par2, par3, par4)) {
                    if (!par1World.doesBlockHaveSolidTopSurface(par2, par3 - 1, par4) || l > 3) {
                        par1World.setBlockToAir(par2, par3, par4);
                    }
                }
                else if (!flag && !this.canBlockCatchFire((IBlockAccess)par1World, par2, par3 - 1, par4, ForgeDirection.UP) && l == 15 && par5Random.nextInt(4) == 0) {
                    par1World.setBlockToAir(par2, par3, par4);
                }
                else {
                    final boolean flag2 = par1World.isBlockHighHumidity(par2, par3, par4);
                    byte b0 = 0;
                    if (flag2) {
                        b0 = -50;
                    }
                    this.tryToCatchBlockOnFire(par1World, par2 + 1, par3, par4, 300 + b0, par5Random, l, ForgeDirection.WEST);
                    this.tryToCatchBlockOnFire(par1World, par2 - 1, par3, par4, 300 + b0, par5Random, l, ForgeDirection.EAST);
                    this.tryToCatchBlockOnFire(par1World, par2, par3 - 1, par4, 250 + b0, par5Random, l, ForgeDirection.UP);
                    this.tryToCatchBlockOnFire(par1World, par2, par3 + 1, par4, 250 + b0, par5Random, l, ForgeDirection.DOWN);
                    this.tryToCatchBlockOnFire(par1World, par2, par3, par4 - 1, 300 + b0, par5Random, l, ForgeDirection.SOUTH);
                    this.tryToCatchBlockOnFire(par1World, par2, par3, par4 + 1, 300 + b0, par5Random, l, ForgeDirection.NORTH);
                    for (int i1 = par2 - 1; i1 <= par2 + 1; ++i1) {
                        for (int j1 = par4 - 1; j1 <= par4 + 1; ++j1) {
                            for (int k1 = par3 - 1; k1 <= par3 + 4; ++k1) {
                                if (i1 != par2 || k1 != par3 || j1 != par4) {
                                    int l2 = 100;
                                    if (k1 > par3 + 1) {
                                        l2 += (k1 - (par3 + 1)) * 100;
                                    }
                                    final int i2 = this.getChanceOfNeighborsEncouragingFire(par1World, i1, k1, j1);
                                    if (i2 > 0) {
                                        int j2 = (i2 + 40 + par1World.difficultySetting * 7) / (l + 30);
                                        if (flag2) {
                                            j2 /= 2;
                                        }
                                        if (j2 > 0 && par5Random.nextInt(l2) <= j2 && (!par1World.isRaining() || !par1World.canLightningStrikeAt(i1, k1, j1)) && !par1World.canLightningStrikeAt(i1 - 1, k1, par4) && !par1World.canLightningStrikeAt(i1 + 1, k1, j1) && !par1World.canLightningStrikeAt(i1, k1, j1 - 1) && !par1World.canLightningStrikeAt(i1, k1, j1 + 1)) {
                                            int k2 = l + par5Random.nextInt(5) / 4;
                                            if (k2 > 15) {
                                                k2 = 15;
                                            }
                                            par1World.setBlock(i1, k1, j1, this.blockID, k2, 3);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    public boolean func_82506_l() {
        return false;
    }
    
    @Deprecated
    private void tryToCatchBlockOnFire(final World par1World, final int par2, final int par3, final int par4, final int par5, final Random par6Random, final int par7) {
        this.tryToCatchBlockOnFire(par1World, par2, par3, par4, par5, par6Random, par7, ForgeDirection.UP);
    }
    
    protected void tryToCatchBlockOnFire(final World par1World, final int par2, final int par3, final int par4, final int par5, final Random par6Random, final int par7, final ForgeDirection face) {
        int j1 = 0;
        final Block block = Block.blocksList[par1World.getBlockId(par2, par3, par4)];
        if (block != null) {
            j1 = block.getFlammability((IBlockAccess)par1World, par2, par3, par4, par1World.getBlockMetadata(par2, par3, par4), face);
        }
        if (par6Random.nextInt(par5) < j1) {
            final boolean flag = par1World.getBlockId(par2, par3, par4) == Block.tnt.blockID;
            if (par6Random.nextInt(par7 + 10) < 5 && !par1World.canLightningStrikeAt(par2, par3, par4)) {
                int k1 = par7 + par6Random.nextInt(5) / 4;
                if (k1 > 15) {
                    k1 = 15;
                }
                par1World.setBlock(par2, par3, par4, this.blockID, k1, 3);
            }
            else {
                par1World.setBlockToAir(par2, par3, par4);
            }
            if (flag) {
                Block.tnt.onBlockDestroyedByPlayer(par1World, par2, par3, par4, 1);
            }
        }
    }
    
    protected boolean canNeighborBurn(final World par1World, final int par2, final int par3, final int par4) {
        return this.canBlockCatchFire((IBlockAccess)par1World, par2 + 1, par3, par4, ForgeDirection.WEST) || this.canBlockCatchFire((IBlockAccess)par1World, par2 - 1, par3, par4, ForgeDirection.EAST) || this.canBlockCatchFire((IBlockAccess)par1World, par2, par3 - 1, par4, ForgeDirection.UP) || this.canBlockCatchFire((IBlockAccess)par1World, par2, par3 + 1, par4, ForgeDirection.DOWN) || this.canBlockCatchFire((IBlockAccess)par1World, par2, par3, par4 - 1, ForgeDirection.SOUTH) || this.canBlockCatchFire((IBlockAccess)par1World, par2, par3, par4 + 1, ForgeDirection.NORTH);
    }
    
    protected int getChanceOfNeighborsEncouragingFire(final World par1World, final int par2, final int par3, final int par4) {
        final byte b0 = 0;
        if (!par1World.isAirBlock(par2, par3, par4)) {
            return 0;
        }
        int l = this.getChanceToEncourageFire(par1World, par2 + 1, par3, par4, b0, ForgeDirection.WEST);
        l = this.getChanceToEncourageFire(par1World, par2 - 1, par3, par4, l, ForgeDirection.EAST);
        l = this.getChanceToEncourageFire(par1World, par2, par3 - 1, par4, l, ForgeDirection.UP);
        l = this.getChanceToEncourageFire(par1World, par2, par3 + 1, par4, l, ForgeDirection.DOWN);
        l = this.getChanceToEncourageFire(par1World, par2, par3, par4 - 1, l, ForgeDirection.SOUTH);
        l = this.getChanceToEncourageFire(par1World, par2, par3, par4 + 1, l, ForgeDirection.NORTH);
        return l;
    }
    
    public boolean isCollidable() {
        return false;
    }
    
    @Deprecated
    public boolean canBlockCatchFire(final IBlockAccess par1IBlockAccess, final int par2, final int par3, final int par4) {
        return this.canBlockCatchFire(par1IBlockAccess, par2, par3, par4, ForgeDirection.UP);
    }
    
    @Deprecated
    public int getChanceToEncourageFire(final World par1World, final int par2, final int par3, final int par4, final int par5) {
        return this.getChanceToEncourageFire(par1World, par2, par3, par4, par5, ForgeDirection.UP);
    }
    
    public boolean canPlaceBlockAt(final World par1World, final int par2, final int par3, final int par4) {
        return par1World.doesBlockHaveSolidTopSurface(par2, par3 - 1, par4) || this.canNeighborBurn(par1World, par2, par3, par4);
    }
    
    public void onNeighborBlockChange(final World par1World, final int par2, final int par3, final int par4, final int par5) {
        if (!par1World.doesBlockHaveSolidTopSurface(par2, par3 - 1, par4) && !this.canNeighborBurn(par1World, par2, par3, par4)) {
            par1World.setBlockToAir(par2, par3, par4);
        }
    }
    
    public void onBlockAdded(final World par1World, final int par2, final int par3, final int par4) {
        if (par1World.provider.dimensionId > 0 || par1World.getBlockId(par2, par3 - 1, par4) != Block.obsidian.blockID || !Block.portal.tryToCreatePortal(par1World, par2, par3, par4)) {
            if (!par1World.doesBlockHaveSolidTopSurface(par2, par3 - 1, par4) && !this.canNeighborBurn(par1World, par2, par3, par4)) {
                par1World.setBlockToAir(par2, par3, par4);
            }
            else {
                par1World.scheduleBlockUpdate(par2, par3, par4, this.blockID, this.tickRate(par1World) + par1World.rand.nextInt(10));
            }
        }
    }
    
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(final World par1World, final int par2, final int par3, final int par4, final Random par5Random) {
        if (par5Random.nextInt(24) == 0) {
            par1World.playSound((double)(par2 + 0.5f), (double)(par3 + 0.5f), (double)(par4 + 0.5f), "fire.fire", 1.0f + par5Random.nextFloat(), par5Random.nextFloat() * 0.7f + 0.3f, false);
        }
        if (!par1World.doesBlockHaveSolidTopSurface(par2, par3 - 1, par4) && !Block.fire.canBlockCatchFire((IBlockAccess)par1World, par2, par3 - 1, par4, ForgeDirection.UP)) {
            if (Block.fire.canBlockCatchFire((IBlockAccess)par1World, par2 - 1, par3, par4, ForgeDirection.EAST)) {
                for (int l = 0; l < 2; ++l) {
                    final float f = par2 + par5Random.nextFloat() * 0.1f;
                    final float f2 = par3 + par5Random.nextFloat();
                    final float f3 = par4 + par5Random.nextFloat();
                    par1World.spawnParticle("largesmoke", (double)f, (double)f2, (double)f3, 0.0, 0.0, 0.0);
                }
            }
            if (Block.fire.canBlockCatchFire((IBlockAccess)par1World, par2 + 1, par3, par4, ForgeDirection.WEST)) {
                for (int l = 0; l < 2; ++l) {
                    final float f = par2 + 1 - par5Random.nextFloat() * 0.1f;
                    final float f2 = par3 + par5Random.nextFloat();
                    final float f3 = par4 + par5Random.nextFloat();
                    par1World.spawnParticle("largesmoke", (double)f, (double)f2, (double)f3, 0.0, 0.0, 0.0);
                }
            }
            if (Block.fire.canBlockCatchFire((IBlockAccess)par1World, par2, par3, par4 - 1, ForgeDirection.SOUTH)) {
                for (int l = 0; l < 2; ++l) {
                    final float f = par2 + par5Random.nextFloat();
                    final float f2 = par3 + par5Random.nextFloat();
                    final float f3 = par4 + par5Random.nextFloat() * 0.1f;
                    par1World.spawnParticle("largesmoke", (double)f, (double)f2, (double)f3, 0.0, 0.0, 0.0);
                }
            }
            if (Block.fire.canBlockCatchFire((IBlockAccess)par1World, par2, par3, par4 + 1, ForgeDirection.NORTH)) {
                for (int l = 0; l < 2; ++l) {
                    final float f = par2 + par5Random.nextFloat();
                    final float f2 = par3 + par5Random.nextFloat();
                    final float f3 = par4 + 1 - par5Random.nextFloat() * 0.1f;
                    par1World.spawnParticle("largesmoke", (double)f, (double)f2, (double)f3, 0.0, 0.0, 0.0);
                }
            }
            if (Block.fire.canBlockCatchFire((IBlockAccess)par1World, par2, par3 + 1, par4, ForgeDirection.DOWN)) {
                for (int l = 0; l < 2; ++l) {
                    final float f = par2 + par5Random.nextFloat();
                    final float f2 = par3 + 1 - par5Random.nextFloat() * 0.1f;
                    final float f3 = par4 + par5Random.nextFloat();
                    par1World.spawnParticle("largesmoke", (double)f, (double)f2, (double)f3, 0.0, 0.0, 0.0);
                }
            }
        }
        else {
            for (int l = 0; l < 3; ++l) {
                final float f = par2 + par5Random.nextFloat();
                final float f2 = par3 + par5Random.nextFloat() * 0.5f + 0.5f;
                final float f3 = par4 + par5Random.nextFloat();
                par1World.spawnParticle("largesmoke", (double)f, (double)f2, (double)f3, 0.0, 0.0, 0.0);
            }
        }
    }
    
    @SideOnly(Side.CLIENT)
    public void registerIcons(final IconRegister par1IconRegister) {
        this.iconArray = new Icon[] { par1IconRegister.registerIcon(this.getTextureName() + "_layer_0"), par1IconRegister.registerIcon(this.getTextureName() + "_layer_1") };
    }
    
    @SideOnly(Side.CLIENT)
    public Icon getFireIcon(final int par1) {
        return this.iconArray[par1];
    }
    
    @SideOnly(Side.CLIENT)
    public Icon getIcon(final int par1, final int par2) {
        return this.iconArray[0];
    }
    
    public boolean canBlockCatchFire(final IBlockAccess world, final int x, final int y, final int z, final ForgeDirection face) {
        final Block block = Block.blocksList[world.getBlockId(x, y, z)];
        return block != null && block.isFlammable(world, x, y, z, world.getBlockMetadata(x, y, z), face);
    }
    
    public int getChanceToEncourageFire(final World world, final int x, final int y, final int z, final int oldChance, final ForgeDirection face) {
        int newChance = 0;
        final Block block = Block.blocksList[world.getBlockId(x, y, z)];
        if (block != null) {
            newChance = block.getFireSpreadSpeed(world, x, y, z, world.getBlockMetadata(x, y, z), face);
        }
        return (newChance > oldChance) ? newChance : oldChance;
    }
}
