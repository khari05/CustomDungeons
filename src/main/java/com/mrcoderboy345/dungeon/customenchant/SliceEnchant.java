package com.mrcoderboy345.dungeon.customenchant;

import com.mrcoderboy345.dungeon.MainClass;

import cn.nukkit.Player;
import cn.nukkit.entity.Entity;
import cn.nukkit.item.Item;

public class SliceEnchant extends CustomEnchant{
    
    public SliceEnchant(MainClass plugin, String name, int level){
        super(plugin, name, Math.min(5,level));
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
        }
    }
}