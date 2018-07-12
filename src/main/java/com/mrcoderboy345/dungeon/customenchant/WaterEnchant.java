package com.mrcoderboy345.dungeon.customenchant;

import com.mrcoderboy345.dungeon.MainClass;

import cn.nukkit.Player;
import cn.nukkit.item.Item;
import cn.nukkit.potion.Effect;

public class WaterEnchant extends CustomEnchant{
    
    public WaterEnchant(MainClass plugin, String name, int level){
        super(plugin, name, 1);
        this.displayName = "Water Breathing I";
    }

    @Override
    public String getDisplayName() {
        return this.displayName;
    }
    
    @Override 
    public boolean canBeAppliedTo(Item item) {
        // logger.info("Applying NightVision to " + item.getName() + " IsHelmet[" + item.isHelmet() + "]");
        return item.isHelmet();
    }

    @Override
    public void onArmor(Player player){

        Effect effect = Effect.getEffect(Effect.WATER_BREATHING);
        effect.setDuration(199999980);
        player.addEffect(effect);
        // this.logger.info("Night Vision effect added to " + player.getName());
    }

    @Override
    public void offArmor(Player player){
        player.removeEffect(Effect.WATER_BREATHING);
        // this.logger.info("Night Vision effect removed from " + player.getName());
    }
}