//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package net.netherX;

import net.minecraft.tileentity.*;
import net.minecraft.inventory.*;
import java.util.*;
import net.minecraft.nbt.*;
import cpw.mods.fml.relauncher.*;
import net.minecraft.block.*;
import net.minecraft.item.crafting.*;
import net.minecraft.block.material.*;
import net.minecraft.item.*;
import cpw.mods.fml.common.registry.*;
import net.minecraft.entity.player.*;

public class TileEntityNetherFurnace extends TileEntity implements ISidedInventory
{
    private static final int[] slots_top;
    private static final int[] slots_bottom;
    private static final int[] slots_sides;
    Random rand;
    private ItemStack[] furnaceItemStacks;
    public int furnaceBurnTime;
    public int currentItemBurnTime;
    public int furnaceCookTime;
    private String field_94130_e;
    
    public TileEntityNetherFurnace() {
        this.rand = new Random();
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
        return this.isInvNameLocalized() ? this.field_94130_e : "Nether Furnace";
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
        this.currentItemBurnTime = 1;
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
        if (!this.worldObj.isRemote) {
            if (this.furnaceBurnTime == 0 && this.canSmelt()) {
                final int n = 1;
                this.furnaceBurnTime = n;
                this.currentItemBurnTime = n;
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
                    this.checkIfMelts();
                    flag2 = true;
                }
            }
            else {
                this.furnaceCookTime = 0;
            }
            if (flag != this.furnaceBurnTime > 0) {
                flag2 = true;
                BlockNetherFurnace.updateFurnaceBlockState(this.furnaceBurnTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
            }
        }
        if (flag2) {
            this.onInventoryChanged();
        }
    }
    
    public void checkIfMelts() {
        if (this.rand.nextInt(8) == 1 && this.getWorldObj().getBlockId(this.xCoord, this.yCoord - 1, this.zCoord) != Block.beacon.blockID && this.getWorldObj().getBlockId(this.xCoord, this.yCoord + 1, this.zCoord) != Block.beacon.blockID) {
            this.getWorldObj().setBlock(this.xCoord, this.yCoord, this.zCoord, Block.netherrack.blockID);
        }
    }
    
    private boolean canSmelt() {
        if (this.furnaceItemStacks[0] == null) {
            return false;
        }
        final ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.furnaceItemStacks[0]);
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
            final ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.furnaceItemStacks[0]);
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
        if (par0ItemStack.getItem() instanceof ItemBlock && Block.blocksList[i] != null) {
            final Block block = Block.blocksList[i];
            if (block == Block.woodSingleSlab) {
                return 150;
            }
            if (block.blockMaterial == Material.wood) {
                return 300;
            }
            if (block == Block.coalBlock) {
                return 16000;
            }
        }
        if (item instanceof ItemTool && ((ItemTool)item).getToolMaterialName().equals("WOOD")) {
            return 200;
        }
        if (item instanceof ItemSword && ((ItemSword)item).getToolMaterialName().equals("WOOD")) {
            return 200;
        }
        if (item instanceof ItemHoe && ((ItemHoe)item).getMaterialName().equals("WOOD")) {
            return 200;
        }
        if (i == Item.stick.itemID) {
            return 100;
        }
        if (i == Item.coal.itemID) {
            return 1600;
        }
        if (i == Item.bucketLava.itemID) {
            return 20000;
        }
        if (i == Block.sapling.blockID) {
            return 100;
        }
        if (i == Item.blazeRod.itemID) {
            return 2400;
        }
        return GameRegistry.getFuelValue(par0ItemStack);
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
        return (par1 == 1) ? TileEntityNetherFurnace.slots_top : ((par1 == 0) ? TileEntityNetherFurnace.slots_bottom : TileEntityNetherFurnace.slots_sides);
    }
    
    public boolean canInsertItem(final int par1, final ItemStack par2ItemStack, final int par3) {
        return this.isItemValidForSlot(par1, par2ItemStack);
    }
    
    public boolean canExtractItem(final int par1, final ItemStack par2ItemStack, final int par3) {
        return par3 != 0 || par1 != 1 || par2ItemStack.itemID == Item.bucketEmpty.itemID;
    }
    
    static {
        slots_top = new int[] { 0 };
        slots_bottom = new int[] { 2, 1 };
        slots_sides = new int[] { 1 };
    }
}
