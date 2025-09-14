//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package net.netherX;

import net.minecraft.util.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.*;
import net.minecraft.client.renderer.texture.*;
import java.util.*;
import java.net.*;
import java.io.*;

public class CloakRenderer
{
    public static void addCape(final String username, final String url) {
        final ThreadDownloadImageData object = new ThreadDownloadImageData(url, (ResourceLocation)null, (IImageBuffer)null);
        Minecraft.getMinecraft().renderEngine.loadTexture(new ResourceLocation("cloaks/" + username), (TextureObject)object);
    }
    
    public static void addGroupedCape(final String[] group, final String url) {
        for (final String username : group) {
            addCape(username, url);
        }
    }
    
    public static String[] getArrayFromUrl(final String url) {
        final ArrayList list = new ArrayList();
        try {
            final BufferedReader urlReader = new BufferedReader(new InputStreamReader(new URL(url).openStream()));
            String line;
            while ((line = urlReader.readLine()) != null) {
                list.add(line);
            }
            return list.toArray(new String[list.size()]);
        }
        catch (Exception e) {
            e.printStackTrace();
            list.add("");
            return list.toArray(new String[list.size()]);
        }
    }
}
