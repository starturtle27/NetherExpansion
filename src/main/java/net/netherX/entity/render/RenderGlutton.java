//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package net.netherX.entity.render;

import net.minecraft.client.renderer.entity.*;
import cpw.mods.fml.relauncher.*;
import net.minecraft.util.*;
import net.minecraft.client.model.*;
import net.minecraft.entity.*;
import java.util.*;

@SideOnly(Side.CLIENT)
public class RenderGlutton extends RenderLiving
{
    private static final ResourceLocation texture;
    private static final ResourceLocation textureChristmas;
    
    public RenderGlutton(final ModelBase par1ModelBase, final float par2) {
        super(par1ModelBase, par2);
    }
    
    protected ResourceLocation getEntityTexture(final Entity entity) {
        final Calendar calendar = Calendar.getInstance();
        if (calendar.get(2) + 1 == 12 && calendar.get(5) >= 24 && calendar.get(5) <= 26) {
            return RenderGlutton.textureChristmas;
        }
        return RenderGlutton.texture;
    }
    
    static {
        texture = new ResourceLocation("textures/entity/nethermobs/glutton.png");
        textureChristmas = new ResourceLocation("textures/entity/nethermobs/glutton_present.png");
    }
}
