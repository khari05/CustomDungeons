package com.mrcoderboy345.dungeon.customenchant;

import cn.nukkit.Player;
import cn.nukkit.entity.Entity;
import cn.nukkit.item.Item;
import cn.nukkit.utils.Logger;

public class EnlightenedEnchant extends CustomEnchant{
    
    public EnlightenedEnchant(Logger logger, String name, int level){
        super(logger, name, Math.min(5,level));
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