//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package net.netherX.proxies;

import net.minecraft.client.model.*;
import net.minecraft.client.renderer.entity.*;
import net.netherX.entity.models.*;
import net.netherX.entity.render.*;
import cpw.mods.fml.client.registry.*;
import net.minecraft.client.renderer.tileentity.*;
import cpw.mods.fml.common.network.*;
import net.minecraftforge.common.*;
import net.netherX.sounds.*;
import net.minecraftforge.client.*;
import java.util.*;
import cpw.mods.fml.relauncher.*;
import cpw.mods.fml.common.registry.*;
import cpw.mods.fml.common.*;
import net.netherX.*;

public class ClientProxyNX extends CommonProxyNX
{
    public static boolean capeEnabled;
    public static int renderID;
    String capeURLDev;
    String capeURL;
    
    public ClientProxyNX() {
        this.capeURLDev = "http://i.imgur.com/zyFhOhT.png";
        this.capeURL = "http://i.imgur.com/4u4kSqY.png";
    }
    
    @Override
    public void registerRenderThings() {
        RenderingRegistry.registerEntityRenderingHandler((Class)EntityGlutton.class, (Render)new RenderGlutton((ModelBase)new ModelGlutton(), 0.6f));
        RenderingRegistry.registerEntityRenderingHandler((Class)EntityFireCow.class, (Render)new RenderFireCow((ModelBase)new ModelFireCow(), 0.6f));
        RenderingRegistry.registerEntityRenderingHandler((Class)EntityNetherSpider.class, (Render)new RenderNetherSpider());
        RenderingRegistry.registerEntityRenderingHandler((Class)EntityPigman.class, (Render)new RenderPigman((ModelBiped)new ModelZombie(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler((Class)EntityPigZombieConvertable.class, (Render)new RenderZombie());
        RenderingRegistry.registerEntityRenderingHandler((Class)EntityGhastQueen.class, (Render)new RenderGhastQueen());
        RenderingRegistry.registerEntityRenderingHandler((Class)EntityBrimspell.class, (Render)new RenderBrimspell((ModelBase)new ModelBrimspell(), 0.0f));
        RenderingRegistry.registerEntityRenderingHandler((Class)EntityInfernoSpider.class, (Render)new RenderInfernoSpider());
        RenderingRegistry.registerEntityRenderingHandler((Class)EntityObsidianBoat.class, (Render)new RenderObsidianBoat());
        RenderingRegistry.registerBlockHandler(ClientProxyNX.renderID, (ISimpleBlockRenderingHandler)new RenderCrystalCauldron());
        ClientRegistry.bindTileEntitySpecialRenderer((Class)TileEntityCurse.class, (TileEntitySpecialRenderer)new TileEntityCurseRenderer());
        NetworkRegistry.instance().registerGuiHandler((Object)this, (IGuiHandler)new GuiHandlerNX());
        MinecraftForge.EVENT_BUS.register((Object)new CloakRenderer());
        CloakRenderer.addCape("Greenshoes101", this.capeURLDev);
        CloakRenderer.addCape("Dylan4ever", this.capeURLDev);
        CloakRenderer.addCape("EmperorOfTigers", this.capeURL);
        CloakRenderer.addCape("IStoleThePies", this.capeURL);
        CloakRenderer.addCape("_shadow_killer_", this.capeURL);
        CloakRenderer.addCape("hageri", this.capeURL);
        CloakRenderer.addCape("lucapergue", this.capeURL);
        CloakRenderer.addCape("artic_creeper", this.capeURL);
        CloakRenderer.addCape("robotthunder500", this.capeURL);
        CloakRenderer.addCape("JearBear05", this.capeURL);
        CloakRenderer.addCape("ninjabee2000", this.capeURL);
        MinecraftForge.EVENT_BUS.register((Object)new NXSoundHandler());
        MinecraftForgeClient.registerItemRenderer(NetherX.infernoStaff.itemID, (IItemRenderer)new StaffRenderer());
        MinecraftForgeClient.registerItemRenderer(NetherX.infernoStaffCursed.itemID, (IItemRenderer)new StaffRenderer());
        TickRegistry.registerTickHandler((ITickHandler)new NXPlayerTickHandler(EnumSet.of(TickType.PLAYER)), Side.CLIENT);
    }
    
    static {
        ClientProxyNX.capeEnabled = true;
        ClientProxyNX.renderID = BlockCrystalCauldron.renderID;
    }
}
