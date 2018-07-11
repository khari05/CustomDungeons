package com.mrcoderboy345.dungeon.customenchant;

import cn.nukkit.Player;
import cn.nukkit.entity.Entity;
import cn.nukkit.item.Item;
import cn.nukkit.utils.Logger;

public class SliceEnchant extends CustomEnchant{
    
    public SliceEnchant(Logger logger, String name, int level){
        super(logger, name, Math.min(5,level));
        this.displayName = "Slice " + getRoman(this.getLevel());
    }
    @Override
    public String getDisplayName() {
        return this.displayName;
    }
    
    @Override 
    public boolean canBeAppliedTo(Item item) {
        return item.isSword();
    }
    @Override
    public void onAttack(Player damager, Float damage, Entity victim) {
        int rand = rand(1,100);
        int chance = 10 + this.getLevel()*5;
        if (rand<chance){
            victim.setHealth(victim.getHealth()-damage);
            // logger.info(damager.getName()+" has dealt double damage to "+victim.getName());
        }
    }
}