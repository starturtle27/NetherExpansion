//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package net.netherX;

import net.minecraft.client.model.*;
import net.minecraft.entity.*;
import net.minecraft.util.*;

public class ModelGlutton extends ModelBase
{
    ModelRenderer Body;
    ModelRenderer Teeth1;
    ModelRenderer Teeth2;
    ModelRenderer Teeth3;
    ModelRenderer Teeth4;
    ModelRenderer Tongue;
    ModelRenderer Leg1;
    ModelRenderer Leg2;
    ModelRenderer Leg3;
    ModelRenderer Leg4;
    ModelRenderer Head;
    ModelRenderer TeethU1;
    ModelRenderer TeethU2;
    ModelRenderer TeethU3;
    ModelRenderer TeethU4;
    
    public ModelGlutton() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        (this.Body = new ModelRenderer((ModelBase)this, 2, 45)).addBox(0.0f, 0.0f, 0.0f, 15, 4, 15);
        this.Body.setRotationPoint(-7.0f, 15.0f, -7.0f);
        this.Body.setTextureSize(128, 64);
        this.Body.mirror = true;
        this.setRotation(this.Body, 0.0f, 0.0f, 0.0f);
        (this.Teeth1 = new ModelRenderer((ModelBase)this, 2, 2)).addBox(0.0f, 0.0f, 0.0f, 13, 2, 0);
        this.Teeth1.setRotationPoint(-6.0f, 13.0f, -6.0f);
        this.Teeth1.setTextureSize(128, 64);
        this.Teeth1.mirror = true;
        this.setRotation(this.Teeth1, 0.0f, 0.0f, 0.0f);
        (this.Teeth2 = new ModelRenderer((ModelBase)this, 30, 3)).addBox(0.0f, 0.0f, 0.0f, 0, 2, 13);
        this.Teeth2.setRotationPoint(7.0f, 13.0f, -6.0f);
        this.Teeth2.setTextureSize(128, 64);
        this.Teeth2.mirror = true;
        this.setRotation(this.Teeth2, 0.0f, 0.0f, 0.0f);
        (this.Teeth3 = new ModelRenderer((ModelBase)this, 30, 3)).addBox(0.0f, 0.0f, 0.0f, 0, 2, 13);
        this.Teeth3.setRotationPoint(-6.0f, 13.0f, -6.0f);
        this.Teeth3.setTextureSize(128, 64);
        this.Teeth3.mirror = true;
        this.setRotation(this.Teeth3, 0.0f, 0.0f, 0.0f);
        (this.Teeth4 = new ModelRenderer((ModelBase)this, 1, 2)).addBox(0.0f, 0.0f, 0.0f, 13, 2, 0);
        this.Teeth4.setRotationPoint(-6.0f, 13.0f, 7.0f);
        this.Teeth4.setTextureSize(128, 64);
        this.Teeth4.mirror = true;
        this.setRotation(this.Teeth4, 0.0f, 0.0f, 0.0f);
        (this.Tongue = new ModelRenderer((ModelBase)this, 92, 1)).addBox(0.0f, -0.5f, 0.5f, 7, 1, 10);
        this.Tongue.setRotationPoint(-3.0f, 14.0f, -4.0f);
        this.Tongue.setTextureSize(128, 64);
        this.Tongue.mirror = true;
        this.setRotation(this.Tongue, -0.029743f, 0.0f, 0.0f);
        (this.Leg1 = new ModelRenderer((ModelBase)this, 109, 19)).addBox(0.0f, 0.0f, 0.0f, 4, 6, 4);
        this.Leg1.setRotationPoint(4.6f, 18.0f, -7.4f);
        this.Leg1.setTextureSize(128, 64);
        this.Leg1.mirror = true;
        this.setRotation(this.Leg1, 0.0f, 0.0f, 0.0f);
        (this.Leg2 = new ModelRenderer((ModelBase)this, 88, 19)).addBox(0.0f, 0.0f, 0.0f, 4, 6, 4);
        this.Leg2.setRotationPoint(-7.6f, 18.0f, -7.4f);
        this.Leg2.setTextureSize(128, 64);
        this.Leg2.mirror = true;
        this.setRotation(this.Leg2, 0.0f, 0.0f, 0.0f);
        (this.Leg3 = new ModelRenderer((ModelBase)this, 88, 30)).addBox(0.0f, 0.0f, 0.0f, 4, 6, 4);
        this.Leg3.setRotationPoint(4.6f, 18.0f, 4.0f);
        this.Leg3.setTextureSize(128, 64);
        this.Leg3.mirror = true;
        this.setRotation(this.Leg3, 0.0f, 0.0f, 0.0f);
        (this.Leg4 = new ModelRenderer((ModelBase)this, 109, 30)).addBox(0.0f, 0.0f, 0.0f, 4, 6, 4);
        this.Leg4.setRotationPoint(-7.626667f, 17.37333f, 4.0f);
        this.Leg4.setTextureSize(128, 64);
        this.Leg4.mirror = true;
        this.setRotation(this.Leg4, 0.0f, 0.0f, 0.0f);
        (this.Head = new ModelRenderer((ModelBase)this, 75, 43)).addBox(0.0f, -14.0f, 0.0f, 15, 15, 6);
        this.Head.setRotationPoint(-7.0f, 13.0f, 9.0f);
        this.Head.setTextureSize(128, 64);
        this.Head.mirror = true;
        this.setRotation(this.Head, 0.0f, 0.0f, 0.0f);
        (this.TeethU1 = new ModelRenderer((ModelBase)this, 24, 2)).addBox(0.0f, 0.0f, 0.0f, 13, 0, 2);
        this.TeethU1.setRotationPoint(0.5f, 0.0f, -2.0f);
        this.TeethU1.setTextureSize(128, 64);
        this.TeethU1.mirror = true;
        this.setRotation(this.TeethU1, 0.0f, 0.0f, 0.0f);
        (this.TeethU2 = new ModelRenderer((ModelBase)this, 28, 2)).addBox(0.0f, 0.0f, 0.0f, 13, 0, 2);
        this.TeethU2.setRotationPoint(0.5f, -13.0f, -2.0f);
        this.TeethU2.setTextureSize(128, 64);
        this.TeethU2.mirror = true;
        this.setRotation(this.TeethU2, 0.0f, 0.0f, 0.0f);
        (this.TeethU3 = new ModelRenderer((ModelBase)this, 2, 4)).addBox(0.0f, 0.0f, 0.0f, 0, 13, 2);
        this.TeethU3.setRotationPoint(0.5f, -13.0f, -2.0f);
        this.TeethU3.setTextureSize(128, 64);
        this.TeethU3.mirror = true;
        this.setRotation(this.TeethU3, 0.0f, 0.0f, 0.0f);
        (this.TeethU4 = new ModelRenderer((ModelBase)this, 2, 4)).addBox(0.0f, 0.0f, 0.0f, 0, 13, 2);
        this.TeethU4.setRotationPoint(13.5f, -13.0f, -2.0f);
        this.TeethU4.setTextureSize(128, 64);
        this.TeethU4.mirror = true;
        this.setRotation(this.TeethU4, 0.0f, 0.0f, 0.0f);
        this.Head.addChild(this.TeethU1);
        this.Head.addChild(this.TeethU2);
        this.Head.addChild(this.TeethU3);
        this.Head.addChild(this.TeethU4);
    }
    
    public void render(final Entity entity, final float f, final float f1, final float f2, final float f3, final float f4, final float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.Body.render(f5);
        this.Teeth1.render(f5);
        this.Teeth2.render(f5);
        this.Teeth3.render(f5);
        this.Teeth4.render(f5);
        this.Tongue.render(f5);
        this.Leg1.render(f5);
        this.Leg2.render(f5);
        this.Leg3.render(f5);
        this.Leg4.render(f5);
        this.Head.render(f5);
    }
    
    private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
    
    public void setRotationAngles(final float f, final float f1, final float f2, final float f3, final float f4, final float f5, final Entity entity) {
        this.Leg1.rotateAngleX = MathHelper.cos(f * -0.6662f + 3.141593f) * 1.6f * f1;
        this.Leg2.rotateAngleX = MathHelper.cos(f * -0.6662f + 3.141593f) * -1.6f * f1;
        this.Leg3.rotateAngleX = MathHelper.cos(f * -0.6662f + 3.141593f) * -1.6f * f1;
        this.Leg4.rotateAngleX = MathHelper.cos(f * -0.6662f + 3.141593f) * 1.6f * f1;
        this.Head.rotateAngleX = (float)(Math.cos(entity.ticksExisted / 10.0f * 3.141592653589793) + 1.0) * 0.6f;
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }
}
