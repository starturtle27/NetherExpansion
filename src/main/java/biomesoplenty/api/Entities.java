//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package biomesoplenty.api;

public class Entities
{
    public static Class Mudball;
    public static Class Dart;
    public static Class JungleSpider;
    public static Class Rosester;
    public static Class Glob;
    
    public static Class getClass(final String inputstring) {
        Class foundclass = null;
        try {
            foundclass = Class.forName(inputstring);
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return foundclass;
    }
    
    static {
        Entities.Mudball = getClass("biomesoplenty.entities.projectiles.EntityMudball");
        Entities.Dart = getClass("biomesoplenty.entities.projectiles.EntityDart");
        Entities.JungleSpider = getClass("biomesoplenty.entities.EntityJungleSpider");
        Entities.Rosester = getClass("biomesoplenty.entities.EntityRosester");
        Entities.Glob = getClass("biomesoplenty.entities.EntityGlob");
    }
}
