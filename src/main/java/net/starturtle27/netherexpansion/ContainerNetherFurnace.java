//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package net.netherX;

import net.minecraft.inventory.*;
import cpw.mods.fml.relauncher.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;

public class ContainerNetherFurnace extends Container
{
    private TileEntityNetherFurnace furnace;
    private int lastCookTime;
    private int lastBurnTime;
    private int lastItemBurnTime;
    
    public ContainerNetherFurnace(final InventoryPlayer par1InventoryPlayer, final TileEntityNetherFurnace par2TileEntityFurnace) {
        this.furnace = par2TileEntityFurnace;
        this.addSlotToContainer(new Slot((IInventory)par2TileEntityFurnace, 0, 56, 35));
        this.addSlotToContainer((Slot)new SlotFurnace(par1InventoryPlayer.player, (IInventory)par2TileEntityFurnace, 2, 116, 35));
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot((IInventory)par1InventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        for (int i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot((IInventory)par1InventoryPlayer, i, 8 + i * 18, 142));
        }
    }
    
    public void onCraftGuiOpened(final ICrafting par1ICrafting) {
        super.onCraftGuiOpened(par1ICrafting);
        par1ICrafting.sendProgressBarUpdate((Container)this, 0, this.furnace.furnaceCookTime);
        par1ICrafting.sendProgressBarUpdate((Container)this, 1, this.furnace.furnaceBurnTime);
        par1ICrafting.sendProgressBarUpdate((Container)this, 2, this.furnace.currentItemBurnTime);
    }
    
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        for (int i = 0; i < this.crafters.size(); ++i) {
            final ICrafting icrafting = this.crafters.get(i);
            if (this.lastCookTime != this.furnace.furnaceCookTime) {
                icrafting.sendProgressBarUpdate((Container)this, 0, this.furnace.furnaceCookTime);
            }
            if (this.lastBurnTime != this.furnace.furnaceBurnTime) {
                icrafting.sendProgressBarUpdate((Container)this, 1, this.furnace.furnaceBurnTime);
            }
            if (this.lastItemBurnTime != this.furnace.currentItemBurnTime) {
                icrafting.sendProgressBarUpdate((Container)this, 2, this.furnace.currentItemBurnTime);
            }
        }
        this.lastCookTime = this.furnace.furnaceCookTime;
        this.lastBurnTime = this.furnace.furnaceBurnTime;
        this.lastItemBurnTime = this.furnace.currentItemBurnTime;
    }
    
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(final int par1, final int par2) {
        if (par1 == 0) {
            this.furnace.furnaceCookTime = par2;
        }
        if (par1 == 1) {
            this.furnace.furnaceBurnTime = par2;
        }
        if (par1 == 2) {
            this.furnace.currentItemBurnTime = par2;
        }
    }
    
    public boolean canInteractWith(final EntityPlayer par1EntityPlayer) {
        return this.furnace.isUseableByPlayer(par1EntityPlayer);
    }
    
    public ItemStack transferStackInSlot(final EntityPlayer par1EntityPlayer, final int par2) {
        final ItemStack itemstack = null;
        return itemstack;
    }
}
