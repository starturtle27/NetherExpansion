//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package net.netherX;

import net.minecraft.tileentity.*;
import net.minecraft.inventory.*;
import java.util.*;
import net.minecraft.nbt.*;
import cpw.mods.fml.relauncher.*;
import net.minecraft.entity.player.*;
import net.minecraft.network.packet.*;
import net.minecraft.network.*;
import net.netherX.cursed.*;
import net.minecraft.item.*;

public class TileEntityCurse extends TileEntity implements ISidedInventory
{
    public int tickCount;
    public float pageFlip;
    public float pageFlipPrev;
    public float field_70373_d;
    public float field_70374_e;
    public float bookSpread;
    public float bookSpreadPrev;
    public float bookRotation2;
    public float bookRotationPrev;
    public float bookRotation;
    private static Random rand;
    private String field_94136_s;
    private static final int[] slots_top;
    private static final int[] slots_bottom;
    private static final int[] slots_sides;
    private ItemStack[] furnaceItemStacks;
    public int furnaceBurnTime;
    public int currentItemBurnTime;
    public int furnaceCookTime;
    private String field_94130_e;
    
    public TileEntityCurse() {
        this.furnaceItemStacks = new ItemStack[3];
    }
    
    public int getSizeInventory() {
        return this.furnaceItemStacks.length;
    }
    
    public ItemStack getStackInSlot(final int par1) {
        return this.furnaceItemStacks[par1];
    }
    
    public ItemStack decrStackSize(final int par1, final int par2) {
        if (this.furnaceItemStacks[par1] == null) {
            return null;
        }
        if (this.furnaceItemStacks[par1].stackSize <= par2) {
            final ItemStack itemstack = this.furnaceItemStacks[par1];
            this.furnaceItemStacks[par1] = null;
            return itemstack;
        }
        final ItemStack itemstack = this.furnaceItemStacks[par1].splitStack(par2);
        if (this.furnaceItemStacks[par1].stackSize == 0) {
            this.furnaceItemStacks[par1] = null;
        }
        return itemstack;
    }
    
    public ItemStack getStackInSlotOnClosing(final int par1) {
        if (this.furnaceItemStacks[par1] != null) {
            final ItemStack itemstack = this.furnaceItemStacks[par1];
            this.furnaceItemStacks[par1] = null;
            return itemstack;
        }
        return null;
    }
    
    public void setInventorySlotContents(final int par1, final ItemStack par2ItemStack) {
        this.furnaceItemStacks[par1] = par2ItemStack;
        if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit()) {
            par2ItemStack.stackSize = this.getInventoryStackLimit();
        }
    }
    
    public String getInvName() {
        return this.isInvNameLocalized() ? this.field_94130_e : "Curse";
    }
    
    public boolean isInvNameLocalized() {
        return this.field_94130_e != null && this.field_94130_e.length() > 0;
    }
    
    public void setGuiDisplayName(final String par1Str) {
        this.field_94130_e = par1Str;
    }
    
    public void readFromNBT(final NBTTagCompound par1NBTTagCompound) {
        super.readFromNBT(par1NBTTagCompound);
        final NBTTagList nbttaglist = par1NBTTagCompound.getTagList("Items");
        this.furnaceItemStacks = new ItemStack[this.getSizeInventory()];
        for (int i = 0; i < nbttaglist.tagCount(); ++i) {
            final NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.tagAt(i);
            final byte b0 = nbttagcompound1.getByte("Slot");
            if (b0 >= 0 && b0 < this.furnaceItemStacks.length) {
                this.furnaceItemStacks[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }
        this.furnaceBurnTime = par1NBTTagCompound.getShort("BurnTime");
        this.furnaceCookTime = par1NBTTagCompound.getShort("CookTime");
        this.currentItemBurnTime = getItemBurnTime(this.furnaceItemStacks[1]);
        if (par1NBTTagCompound.hasKey("CustomName")) {
            this.field_94130_e = par1NBTTagCompound.getString("CustomName");
        }
    }
    
    public void writeToNBT(final NBTTagCompound par1NBTTagCompound) {
        super.writeToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setShort("BurnTime", (short)this.furnaceBurnTime);
        par1NBTTagCompound.setShort("CookTime", (short)this.furnaceCookTime);
        final NBTTagList nbttaglist = new NBTTagList();
        for (int i = 0; i < this.furnaceItemStacks.length; ++i) {
            if (this.furnaceItemStacks[i] != null) {
                final NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                this.furnaceItemStacks[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag((NBTBase)nbttagcompound1);
            }
        }
        par1NBTTagCompound.setTag("Items", (NBTBase)nbttaglist);
        if (this.isInvNameLocalized()) {
            par1NBTTagCompound.setString("CustomName", this.field_94130_e);
        }
    }
    
    public int getInventoryStackLimit() {
        return 64;
    }
    
    @SideOnly(Side.CLIENT)
    public int getCookProgressScaled(final int par1) {
        return this.furnaceCookTime * par1 / 200;
    }
    
    @SideOnly(Side.CLIENT)
    public int getBurnTimeRemainingScaled(final int par1) {
        if (this.currentItemBurnTime == 0) {
            this.currentItemBurnTime = 200;
        }
        return this.furnaceBurnTime * par1 / this.currentItemBurnTime;
    }
    
    public boolean isBurning() {
        return this.furnaceBurnTime > 0;
    }
    
    public void updateEntity() {
        final boolean flag = this.furnaceBurnTime > 0;
        boolean flag2 = false;
        if (this.furnaceBurnTime > 0) {
            --this.furnaceBurnTime;
        }
        if (!this.worldObj.isRemote) {
            if (this.furnaceBurnTime == 0 && this.canSmelt()) {
                final int itemBurnTime = getItemBurnTime(this.furnaceItemStacks[1]);
                this.furnaceBurnTime = itemBurnTime;
                this.currentItemBurnTime = itemBurnTime;
                if (this.furnaceBurnTime > 0) {
                    flag2 = true;
                    if (this.furnaceItemStacks[1] != null) {
                        final ItemStack itemStack = this.furnaceItemStacks[1];
                        --itemStack.stackSize;
                        if (this.furnaceItemStacks[1].stackSize == 0) {
                            this.furnaceItemStacks[1] = this.furnaceItemStacks[1].getItem().getContainerItemStack(this.furnaceItemStacks[1]);
                        }
                    }
                }
            }
            if (this.isBurning() && this.canSmelt()) {
                ++this.furnaceCookTime;
                if (this.furnaceCookTime == 200) {
                    this.furnaceCookTime = 0;
                    this.smeltItem();
                    flag2 = true;
                }
            }
            else {
                this.furnaceCookTime = 0;
            }
            if (flag != this.furnaceBurnTime > 0) {
                flag2 = true;
            }
        }
        if (flag2) {
            this.onInventoryChanged();
        }
        this.bookSpreadPrev = this.bookSpread;
        this.bookRotationPrev = this.bookRotation2;
        final EntityPlayer entityplayer = this.worldObj.getClosestPlayer((double)(this.xCoord + 0.5f), (double)(this.yCoord + 0.5f), (double)(this.zCoord + 0.5f), 3.0);
        if (entityplayer != null) {
            final double d0 = entityplayer.posX - (this.xCoord + 0.5f);
            final double d2 = entityplayer.posZ - (this.zCoord + 0.5f);
            this.bookRotation = (float)Math.atan2(d2, d0);
            this.bookSpread += 0.1f;
            if (this.bookSpread < 0.5f || TileEntityCurse.rand.nextInt(40) == 0) {
                final float f = this.field_70373_d;
                do {
                    this.field_70373_d += TileEntityCurse.rand.nextInt(4) - TileEntityCurse.rand.nextInt(4);
                } while (f == this.field_70373_d);
            }
        }
        else {
            this.bookRotation += 0.02f;
            this.bookSpread -= 0.1f;
        }
        while (this.bookRotation2 >= 3.141593f) {
            this.bookRotation2 -= 6.283186f;
        }
        while (this.bookRotation2 < -3.141593f) {
            this.bookRotation2 += 6.283186f;
        }
        while (this.bookRotation >= 3.141593f) {
            this.bookRotation -= 6.283186f;
        }
        while (this.bookRotation < -3.141593f) {
            this.bookRotation += 6.283186f;
        }
        float f2;
        for (f2 = this.bookRotation - this.bookRotation2; f2 >= 3.141593f; f2 -= 6.283186f) {}
        while (f2 < -3.141593f) {
            f2 += 6.283186f;
        }
        this.bookRotation2 += f2 * 0.4f;
        if (this.bookSpread < 0.0f) {
            this.bookSpread = 0.0f;
        }
        if (this.bookSpread > 1.0f) {
            this.bookSpread = 1.0f;
        }
        ++this.tickCount;
        this.pageFlipPrev = this.pageFlip;
        float f3 = (this.field_70373_d - this.pageFlip) * 0.4f;
        final float f4 = 0.2f;
        if (f3 < -f4) {
            f3 = -f4;
        }
        if (f3 > f4) {
            f3 = f4;
        }
        this.field_70374_e += (f3 - this.field_70374_e) * 0.9f;
        this.pageFlip += this.field_70374_e;
    }
    
    public Packet getDescriptionPacket() {
        final NBTTagCompound nbtTag = new NBTTagCompound();
        this.writeToNBT(nbtTag);
        return (Packet)new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 1, nbtTag);
    }
    
    public void onDataPacket(final INetworkManager net, final Packet132TileEntityData packet) {
        this.readFromNBT(packet.data);
    }
    
    private boolean canSmelt() {
        if (this.furnaceItemStacks[0] == null) {
            return false;
        }
        final ItemStack itemstack = CurseRecipes.smelting().getSmeltingResult(this.furnaceItemStacks[0]);
        if (itemstack == null) {
            return false;
        }
        if (this.furnaceItemStacks[2] == null) {
            return true;
        }
        if (!this.furnaceItemStacks[2].isItemEqual(itemstack)) {
            return false;
        }
        final int result = this.furnaceItemStacks[2].stackSize + itemstack.stackSize;
        return result <= this.getInventoryStackLimit() && result <= itemstack.getMaxStackSize();
    }
    
    public void smeltItem() {
        if (this.canSmelt()) {
            final ItemStack itemstack = CurseRecipes.smelting().getSmeltingResult(this.furnaceItemStacks[0]);
            if (this.furnaceItemStacks[2] == null) {
                this.furnaceItemStacks[2] = itemstack.copy();
            }
            else if (this.furnaceItemStacks[2].isItemEqual(itemstack)) {
                final ItemStack itemStack = this.furnaceItemStacks[2];
                itemStack.stackSize += itemstack.stackSize;
            }
            final ItemStack itemStack2 = this.furnaceItemStacks[0];
            --itemStack2.stackSize;
            if (this.furnaceItemStacks[0].stackSize <= 0) {
                this.furnaceItemStacks[0] = null;
            }
        }
    }
    
    public static int getItemBurnTime(final ItemStack par0ItemStack) {
        if (par0ItemStack == null) {
            return 0;
        }
        final int i = par0ItemStack.getItem().itemID;
        final Item item = par0ItemStack.getItem();
        if (i == NetherX.bookCursed.itemID) {
            return 200;
        }
        return 0;
    }
    
    public static boolean isItemFuel(final ItemStack par0ItemStack) {
        return getItemBurnTime(par0ItemStack) > 0;
    }
    
    public boolean isUseableByPlayer(final EntityPlayer par1EntityPlayer) {
        return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) == this;
    }
    
    public void openChest() {
    }
    
    public void closeChest() {
    }
    
    public boolean isItemValidForSlot(final int par1, final ItemStack par2ItemStack) {
        return par1 != 2;
    }
    
    public int[] getSlotsForFace(final int par1) {
        return (par1 == 1) ? TileEntityCurse.slots_top : ((par1 == 0) ? TileEntityCurse.slots_bottom : TileEntityCurse.slots_sides);
    }
    
    public boolean canInsertItem(final int par1, final ItemStack par2ItemStack, final int par3) {
        return this.isItemValidForSlot(par1, par2ItemStack);
    }
    
    public boolean canExtractItem(final int par1, final ItemStack par2ItemStack, final int par3) {
        return par3 != 0 || par1 != 1 || par2ItemStack.itemID == Item.bucketEmpty.itemID;
    }
    
    static {
        TileEntityCurse.rand = new Random();
        slots_top = new int[] { 0 };
        slots_bottom = new int[] { 2, 1 };
        slots_sides = new int[] { 1 };
    }
}
