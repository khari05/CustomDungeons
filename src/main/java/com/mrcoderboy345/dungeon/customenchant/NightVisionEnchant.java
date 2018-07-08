package com.mrcoderboy345.dungeon.customenchant;

import cn.nukkit.Player;
import cn.nukkit.item.Item;
import cn.nukkit.potion.Effect;
import cn.nukkit.utils.Logger;

public class NightVisionEnchant extends CustomEnchant{
    
    public NightVisionEnchant(Logger logger, String name, int level){
        super(logger, name, 1);
        this.displayName = "Night Vision";
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

        Effect effect = Effect.getEffect(Effect.NIGHT_VISION);
        effect.setDuration(199999980);
        player.addEffect(effect);
        // this.logger.info("Night Vision effect added to " + player.getName());
    }

    @Override
    public void offArmor(Player player){
        player.removeEffect(Effect.NIGHT_VISION);
        // this.logger.info("Night Vision effect removed from " + player.getName());
    }
}