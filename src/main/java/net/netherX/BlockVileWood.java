//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package net.netherX;

import net.minecraft.block.*;
import net.minecraft.util.*;
import net.minecraft.block.material.*;
import net.minecraft.creativetab.*;
import java.util.*;
import cpw.mods.fml.relauncher.*;
import net.minecraft.client.renderer.texture.*;
import net.minecraft.world.*;

public class BlockVileWood extends BlockRotatedPillar
{
    private Icon field_111052_c;
    private Icon tree_top;
    
    protected BlockVileWood(final int par1) {
        super(par1, Material.wood);
        this.setCreativeTab(CreativeTabs.tabBlock);
    }
    
    public int quantityDropped(final Random par1Random) {
        return 1;
    }
    
    public int idDropped(final int par1, final Random par2Random, final int par3) {
        return NetherX.vilewood.blockID;
    }
    
    @SideOnly(Side.CLIENT)
    protected Icon getEndIcon(final int par1) {
        return this.tree_top;
    }
    
    @SideOnly(Side.CLIENT)
    public void registerIcons(final IconRegister par1IconRegister) {
        this.field_111052_c = par1IconRegister.registerIcon("log_vile");
        this.tree_top = par1IconRegister.registerIcon("log_vile_top");
    }
    
    public boolean isWood(final World world, final int x, final int y, final int z) {
        return true;
    }
    
    @SideOnly(Side.CLIENT)
    protected Icon getSideIcon(final int i) {
        return this.field_111052_c;
    }
    
    @SideOnly(Side.CLIENT)
    public Icon getIcon(final int par1, final int par2) {
        final int k = par2 & 0xC;
        final int l = par2 & 0x3;
        return (k == 8 && (par1 == 2 || par1 == 3)) ? this.getEndIcon(l) : ((k == 4 && (par1 == 5 || par1 == 4)) ? this.getEndIcon(l) : ((k == 0 && (par1 == 1 || par1 == 0)) ? this.getEndIcon(l) : this.getSideIcon(l)));
    }
}
