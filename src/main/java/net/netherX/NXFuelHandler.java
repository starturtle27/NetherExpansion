//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package net.netherX;

import cpw.mods.fml.common.*;
import net.minecraft.item.*;

public class NXFuelHandler implements IFuelHandler
{
    public int getBurnTime(final ItemStack fuel) {
        final int id = fuel.itemID;
        if (id == NetherX.vilewood.blockID) {
            return 450;
        }
        return 0;
    }
}
