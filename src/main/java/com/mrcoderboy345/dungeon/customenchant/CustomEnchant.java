package com.mrcoderboy345.dungeon.customenchant;

import cn.nukkit.Player;
import cn.nukkit.item.Item;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.nbt.tag.ListTag;
import cn.nukkit.utils.Logger;

public abstract class CustomEnchant{
    protected String name;
    protected int level;
    protected Logger logger;

    public CustomEnchant(Logger logger, String name, int level){
        this.logger = logger;
        this.name = name;
        this.level = level;
    }
    public String getName(){
        return name;
    }

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
        // logger.info("Created CompoundTag-" + this.toString());

    }
    public void onArmor(Player player){

    }
    public void offArmor(Player player){

    }
    public void onAttack(Player player, int damage){

    }
    public void whenHit(Player player){

    }
    public abstract boolean canBeAppliedTo(Item item);

}