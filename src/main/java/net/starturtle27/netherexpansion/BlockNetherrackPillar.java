//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package net.netherX;

import net.minecraft.block.*;
import net.minecraft.util.*;
import net.minecraft.block.material.*;
import net.minecraft.creativetab.*;
import cpw.mods.fml.relauncher.*;
import net.minecraft.client.renderer.texture.*;

public class BlockNetherrackPillar extends BlockRotatedPillar
{
    private Icon field_111052_c;
    private Icon pillar_top;
    
    protected BlockNetherrackPillar(final int par1) {
        super(par1, Material.rock);
        this.setCreativeTab(CreativeTabs.tabBlock);
    }
    
    @SideOnly(Side.CLIENT)
    protected Icon getEndIcon(final int par1) {
        return this.pillar_top;
    }
    
    @SideOnly(Side.CLIENT)
    public void registerIcons(final IconRegister par1IconRegister) {
        this.field_111052_c = par1IconRegister.registerIcon("netherrack_pillar");
        this.pillar_top = par1IconRegister.registerIcon("netherrack_pillar_top");
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
