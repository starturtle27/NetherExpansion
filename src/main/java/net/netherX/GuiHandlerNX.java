//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package net.netherX;

import cpw.mods.fml.common.network.*;
import net.minecraft.entity.player.*;
import net.minecraft.world.*;
import net.minecraft.tileentity.*;

public class GuiHandlerNX implements IGuiHandler
{
    public Object getServerGuiElement(final int id, final EntityPlayer player, final World world, final int x, final int y, final int z) {
        final TileEntity tile_entity = world.getBlockTileEntity(x, y, z);
        switch (id) {
            case 0: {
                return new ContainerNetherFurnace(player.inventory, (TileEntityNetherFurnace)tile_entity);
            }
            case 1: {
                return new ContainerCurse(player.inventory, (TileEntityCurse)tile_entity);
            }
            default: {
                return null;
            }
        }
    }
    
    public Object getClientGuiElement(final int id, final EntityPlayer player, final World world, final int x, final int y, final int z) {
        final TileEntity tile_entity = world.getBlockTileEntity(x, y, z);
        switch (id) {
            case 0: {
                return new GuiNetherFurnace(player.inventory, (TileEntityNetherFurnace)tile_entity);
            }
            case 1: {
                return new GuiCurse(player.inventory, (TileEntityCurse)tile_entity);
            }
            default: {
                return null;
            }
        }
    }
}
