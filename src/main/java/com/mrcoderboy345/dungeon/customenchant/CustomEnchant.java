package com.mrcoderboy345.dungeon.customenchant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.mrcoderboy345.dungeon.MainClass;

import cn.nukkit.Player;
import cn.nukkit.entity.Entity;
import cn.nukkit.item.Item;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.nbt.tag.ListTag;

public abstract class CustomEnchant{
    protected String name;
    protected String displayName;
    protected int level;
    protected MainClass plugin;
    private Map<Integer,String> romanMap;
    private static final Random random = new Random(System.currentTimeMillis());


    public CustomEnchant(MainClass plugin, String name, int level){
        this.plugin = plugin;
        this.name = name;
        this.level = level;
        initRomanMap();
    }

    public String getName(){
        return name;
    }

    public abstract String getDisplayName();

    public int getLevel() {
        return level;
    }

    public void init(Item item) {
        CompoundTag tag;
        if (!item.hasCompoundTag()) {
            tag = new CompoundTag();
        } else {
            tag = item.getNamedTag();
        }
        ListTag<CompoundTag> enchants;
        if (tag.getList("customenchants") != null){
            enchants = tag.getList("customenchants",CompoundTag.class);
        } else {
            enchants = new ListTag<>("customenchants");
        }
        enchants.add(new CompoundTag()
                        .putString("id", this.getName())
                        .putInt("lvl", this.getLevel())
                );
        tag.putList(enchants);
        item.setNamedTag(tag);
        ArrayList<String> newLore = new ArrayList<>(java.util.Arrays.asList(item.getLore()));
        newLore.add(this.getDisplayName());
        item.setLore(newLore.toArray(new String[0]));
        // logger.info("Created CompoundTag-" + this.toString());

    }
    private void initRomanMap() {
        romanMap = new HashMap<Integer,String>();
        romanMap.put(1, "I");
        romanMap.put(2, "II");
        romanMap.put(3, "III");
        romanMap.put(4, "IV");
        romanMap.put(5, "V");
    }
    protected String getRoman(int level){
        return romanMap.get(level);
    }

    protected int rand(int min, int max) {
        if (min == max) {
            return max;
        }
        return min + random.nextInt(max - min);
    }

    public void onArmor(Player player){

    }

    public void offArmor(Player player){

    }

    public void onAttack(Player damager, Float damage, Entity victim){

    }

    public boolean whenHit(Entity damager, Float damage, Player victim){
        return false;
    }

    public abstract boolean canBeAppliedTo(Item item);
}