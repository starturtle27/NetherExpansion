//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package net.netherX;

import net.minecraft.world.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.potion.*;
import net.minecraft.entity.*;

public class ItemNetherArmor extends ItemArmor
{
    public ItemNetherArmor(final int par1, final EnumArmorMaterial par2EnumArmorMaterial, final int par3, final int par4) {
        super(par1, par2EnumArmorMaterial, par3, par4);
    }
    
    public void onArmorTickUpdate(final World world, final EntityPlayer player, final ItemStack stack) {
        final ItemStack plate = player.getEquipmentInSlot(3);
        final ItemStack helmet = player.getEquipmentInSlot(4);
        final ItemStack legs = player.getEquipmentInSlot(2);
        final ItemStack boots = player.getEquipmentInSlot(1);
        if (stack.itemID == NetherX.plateMalite.itemID || stack.itemID == NetherX.helmetMalite.itemID || stack.itemID == NetherX.legsMalite.itemID || stack.itemID == NetherX.bootsMalite.itemID) {
            player.removePotionEffect(Potion.poison.id);
        }
        else if (stack.itemID == NetherX.plateNecromite.itemID || stack.itemID == NetherX.helmetNecromite.itemID || stack.itemID == NetherX.legsNecromite.itemID || stack.itemID == NetherX.bootsNecromite.itemID) {
            player.removePotionEffect(Potion.wither.id);
        }
        else if ((stack.itemID == NetherX.plateMagma.itemID || stack.itemID == NetherX.helmetMagma.itemID || stack.itemID == NetherX.legsMagma.itemID || stack.itemID == NetherX.bootsMagma.itemID) && boots != null && legs != null && plate != null && helmet != null) {
            if (plate.itemID == NetherX.plateMagma.itemID && helmet.itemID == NetherX.helmetMagma.itemID && legs.itemID == NetherX.legsMagma.itemID && boots.itemID == NetherX.bootsMagma.itemID) {
                player.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 0, 0));
            }
            player.extinguish();
        }
    }
    
    public String getArmorTexture(final ItemStack stack, final Entity entity, final int slot, final int layer) {
        if (stack.itemID == NetherX.plateMalite.itemID || stack.itemID == NetherX.helmetMalite.itemID || stack.itemID == NetherX.bootsMalite.itemID) {
            return "minecraft:textures/models/armor/poison_layer_1.png";
        }
        if (stack.itemID == NetherX.legsMalite.itemID) {
            return "minecraft:textures/models/armor/poison_layer_2.png";
        }
        if (stack.itemID == NetherX.plateNecromite.itemID || stack.itemID == NetherX.helmetNecromite.itemID || stack.itemID == NetherX.bootsNecromite.itemID) {
            return "minecraft:textures/models/armor/necro_layer_1.png";
        }
        if (stack.itemID == NetherX.legsNecromite.itemID) {
            return "minecraft:textures/models/armor/necro_layer_2.png";
        }
        if (stack.itemID == NetherX.plateMagma.itemID || stack.itemID == NetherX.helmetMagma.itemID || stack.itemID == NetherX.bootsMagma.itemID) {
            return "minecraft:textures/models/armor/magma_layer_1.png";
        }
        if (stack.itemID == NetherX.legsMagma.itemID) {
            return "minecraft:textures/models/armor/magma_layer_2.png";
        }
        return null;
    }
}
