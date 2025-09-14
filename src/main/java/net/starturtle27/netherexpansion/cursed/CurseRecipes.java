//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package net.netherX.cursed;

import net.minecraft.item.*;
import net.netherX.*;
import java.util.*;

public class CurseRecipes
{
    private static final CurseRecipes smeltingBase;
    private Map smeltingList;
    private Map experienceList;
    private HashMap<List<Integer>, ItemStack> metaSmeltingList;
    private HashMap<List<Integer>, Float> metaExperience;
    
    public static final CurseRecipes smelting() {
        return CurseRecipes.smeltingBase;
    }
    
    private CurseRecipes() {
        this.smeltingList = new HashMap();
        this.experienceList = new HashMap();
        this.metaSmeltingList = new HashMap<List<Integer>, ItemStack>();
        this.metaExperience = new HashMap<List<Integer>, Float>();
        this.addSmelting(NetherX.swordNetherrack.itemID, new ItemStack(NetherX.swordNetherrackCursed), 0.0f);
        this.addSmelting(NetherX.swordNetherrackCursed.itemID, new ItemStack(NetherX.swordNetherrackCursed), 0.0f);
        this.addSmelting(NetherX.pickaxeNetherrack.itemID, new ItemStack(NetherX.pickaxeNetherrackCursed), 0.0f);
        this.addSmelting(NetherX.pickaxeNetherrackCursed.itemID, new ItemStack(NetherX.pickaxeNetherrackCursed), 0.0f);
        this.addSmelting(NetherX.axeNetherrack.itemID, new ItemStack(NetherX.axeNetherrackCursed), 0.0f);
        this.addSmelting(NetherX.axeNetherrackCursed.itemID, new ItemStack(NetherX.axeNetherrackCursed), 0.0f);
        this.addSmelting(NetherX.shovelNetherrack.itemID, new ItemStack(NetherX.shovelNetherrackCursed), 0.0f);
        this.addSmelting(NetherX.shovelNetherrackCursed.itemID, new ItemStack(NetherX.shovelNetherrackCursed), 0.0f);
        this.addSmelting(NetherX.swordMalite.itemID, new ItemStack(NetherX.swordVenomiteCursed), 0.0f);
        this.addSmelting(NetherX.swordVenomiteCursed.itemID, new ItemStack(NetherX.swordVenomiteCursed), 0.0f);
        this.addSmelting(NetherX.pickaxeMalite.itemID, new ItemStack(NetherX.pickaxeVenomiteCursed), 0.0f);
        this.addSmelting(NetherX.pickaxeVenomiteCursed.itemID, new ItemStack(NetherX.pickaxeVenomiteCursed), 0.0f);
        this.addSmelting(NetherX.axeMalite.itemID, new ItemStack(NetherX.axeVenomiteCursed), 0.0f);
        this.addSmelting(NetherX.axeVenomiteCursed.itemID, new ItemStack(NetherX.axeVenomiteCursed), 0.0f);
        this.addSmelting(NetherX.shovelMalite.itemID, new ItemStack(NetherX.shovelVenomiteCursed), 0.0f);
        this.addSmelting(NetherX.shovelVenomiteCursed.itemID, new ItemStack(NetherX.shovelVenomiteCursed), 0.0f);
        this.addSmelting(NetherX.swordNecromite.itemID, new ItemStack(NetherX.swordNecromiteCursed), 0.0f);
        this.addSmelting(NetherX.swordNecromiteCursed.itemID, new ItemStack(NetherX.swordNecromiteCursed), 0.0f);
        this.addSmelting(NetherX.pickaxeNecromite.itemID, new ItemStack(NetherX.pickaxeNecromiteCursed), 0.0f);
        this.addSmelting(NetherX.pickaxeNecromiteCursed.itemID, new ItemStack(NetherX.pickaxeNecromiteCursed), 0.0f);
        this.addSmelting(NetherX.axeNecromite.itemID, new ItemStack(NetherX.axeNecromiteCursed), 0.0f);
        this.addSmelting(NetherX.axeNecromiteCursed.itemID, new ItemStack(NetherX.axeNecromiteCursed), 0.0f);
        this.addSmelting(NetherX.shovelNecromite.itemID, new ItemStack(NetherX.shovelNecromiteCursed), 0.0f);
        this.addSmelting(NetherX.shovelNecromiteCursed.itemID, new ItemStack(NetherX.shovelNecromiteCursed), 0.0f);
        this.addSmelting(NetherX.infernoStaff.itemID, new ItemStack(NetherX.infernoStaffCursed), 0.0f);
        this.addSmelting(NetherX.infernoStaffCursed.itemID, new ItemStack(NetherX.infernoStaffCursed), 0.0f);
    }
    
    public void addSmelting(final int par1, final ItemStack par2ItemStack, final float par3) {
        this.smeltingList.put(par1, par2ItemStack);
        this.experienceList.put(par2ItemStack.itemID, par3);
    }
    
    @Deprecated
    public ItemStack getSmeltingResult(final int par1) {
        return this.smeltingList.get(par1);
    }
    
    public Map getSmeltingList() {
        return this.smeltingList;
    }
    
    @Deprecated
    public float getExperience(final int par1) {
        return this.experienceList.containsKey(par1) ? this.experienceList.get(par1) : 0.0f;
    }
    
    public void addSmelting(final int itemID, final int metadata, final ItemStack itemstack, final float experience) {
        this.metaSmeltingList.put(Arrays.asList(itemID, metadata), itemstack);
        this.metaExperience.put(Arrays.asList(itemstack.itemID, itemstack.getItemDamage()), experience);
    }
    
    public ItemStack getSmeltingResult(final ItemStack item) {
        if (item == null) {
            return null;
        }
        final ItemStack ret = this.metaSmeltingList.get(Arrays.asList(item.itemID, item.getItemDamage()));
        if (ret != null) {
            return ret;
        }
        return this.smeltingList.get(item.itemID);
    }
    
    public float getExperience(final ItemStack item) {
        if (item == null || item.getItem() == null) {
            return 0.0f;
        }
        float ret = item.getItem().getSmeltingExperience(item);
        if (ret < 0.0f && this.metaExperience.containsKey(Arrays.asList(item.itemID, item.getItemDamage()))) {
            ret = this.metaExperience.get(Arrays.asList(item.itemID, item.getItemDamage()));
        }
        if (ret < 0.0f && this.experienceList.containsKey(item.itemID)) {
            ret = this.experienceList.get(item.itemID);
        }
        return (ret < 0.0f) ? 0.0f : ret;
    }
    
    public Map<List<Integer>, ItemStack> getMetaSmeltingList() {
        return this.metaSmeltingList;
    }
    
    static {
        smeltingBase = new CurseRecipes();
    }
}
