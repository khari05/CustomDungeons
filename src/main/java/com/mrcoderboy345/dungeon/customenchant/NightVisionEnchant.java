package com.mrcoderboy345.dungeon.customenchant;

import com.mrcoderboy345.dungeon.MainClass;

import cn.nukkit.Player;
import cn.nukkit.item.Item;
import cn.nukkit.potion.Effect;

public class NightVisionEnchant extends CustomEnchant{
    
    public NightVisionEnchant(MainClass plugin, String name, int level){
        super(plugin, name, 1);
        this.displayName = "Night Vision I";
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