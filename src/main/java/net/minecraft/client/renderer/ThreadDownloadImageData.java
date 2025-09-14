//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package net.minecraft.client.renderer;

import cpw.mods.fml.relauncher.*;
import java.awt.image.*;
import net.minecraft.util.*;
import net.minecraft.client.renderer.texture.*;
import net.minecraft.client.resources.*;
import java.io.*;

@SideOnly(Side.CLIENT)
public class ThreadDownloadImageData extends AbstractTexture
{
    private final String imageUrl;
    private final IImageBuffer imageBuffer;
    public BufferedImage bufferedImage;
    private Thread imageThread;
    private SimpleTexture imageLocation;
    private boolean textureUploaded;
    
    public ThreadDownloadImageData(final String par1Str, final ResourceLocation par2ResourceLocation, final IImageBuffer par3IImageBuffer) {
        this.imageUrl = par1Str;
        this.imageBuffer = par3IImageBuffer;
        this.imageLocation = ((par2ResourceLocation != null) ? new SimpleTexture(par2ResourceLocation) : null);
    }
    
    public int getGlTextureId() {
        final int i = super.getGlTextureId();
        if (!this.textureUploaded && this.bufferedImage != null) {
            TextureUtil.uploadTextureImage(i, this.bufferedImage);
            this.textureUploaded = true;
        }
        return i;
    }
    
    public void getBufferedImage(final BufferedImage par1BufferedImage) {
        this.bufferedImage = par1BufferedImage;
    }
    
    public void loadTexture(final ResourceManager par1ResourceManager) throws IOException {
        if (this.bufferedImage == null) {
            if (this.imageLocation != null) {
                this.imageLocation.loadTexture(par1ResourceManager);
                this.glTextureId = this.imageLocation.getGlTextureId();
            }
        }
        else {
            TextureUtil.uploadTextureImage(this.getGlTextureId(), this.bufferedImage);
        }
        if (this.imageThread == null) {
            (this.imageThread = (Thread)new ThreadDownloadImageDataINNER1(this)).setDaemon(true);
            this.imageThread.setName("Skin downloader: " + this.imageUrl);
            this.imageThread.start();
        }
    }
    
    public boolean isTextureUploaded() {
        this.getGlTextureId();
        return this.textureUploaded;
    }
    
    static String getImageUrl(final ThreadDownloadImageData par0ThreadDownloadImageData) {
        return par0ThreadDownloadImageData.imageUrl;
    }
    
    static IImageBuffer getImageBuffer(final ThreadDownloadImageData par0ThreadDownloadImageData) {
        return par0ThreadDownloadImageData.imageBuffer;
    }
}
