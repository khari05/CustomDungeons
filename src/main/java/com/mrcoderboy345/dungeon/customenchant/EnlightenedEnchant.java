package com.mrcoderboy345.dungeon.customenchant;

import com.mrcoderboy345.dungeon.MainClass;

import cn.nukkit.Player;
import cn.nukkit.entity.Entity;
import cn.nukkit.item.Item;


public class EnlightenedEnchant extends CustomEnchant{
    
    public EnlightenedEnchant(MainClass plugin, String name, int level){
        super(plugin, name, Math.min(5,level));
        this.displayName = "Enlightened " + getRoman(this.getLevel());
    }
    @Override
    public String getDisplayName() {
        return this.displayName;
    }
    
    @Override 
    public boolean canBeAppliedTo(Item item) {
        // logger.info("Applying NightVision to " + item.getName() + " IsHelmet[" + item.isHelmet() + "]");
        return item.isArmor();
    }
    @Override
    public void whenHit(Entity damager, Float damage, Player victim){

    }
}