package com.mrcoderboy345.dungeon.customenchant;

import com.mrcoderboy345.dungeon.MainClass;

import cn.nukkit.Player;
import cn.nukkit.item.Item;
import cn.nukkit.potion.Effect;
import cn.nukkit.utils.Logger;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.nbt.tag.ListTag;

public class InvisibilityEnchant extends CustomEnchant{
    
    public InvisibilityEnchant(MainClass plugin, String name, int level){
        super(plugin, name, 1);
        this.displayName = "Invisibility I";
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
    public void onArmor(Player player){
        
        Effect effect = Effect.getEffect(Effect.INVISIBILITY);
        effect.setDuration(199999980);
        player.addEffect(effect);
        Item[] playerarmor = player.getInventory().getArmorContents();
        int a = 0;
        for (int i=0; i<playerarmor.length; i++){
            if (playerarmor[i].hasCompoundTag()){
                // logger.info("this is armor "+i+":");
                ListTag<CompoundTag> enchants = playerarmor[i].getNamedTag().getList("customenchants",CompoundTag.class);
                if (enchants != null){
                    for (int j=0; j<enchants.size(); j++){
                        if (enchants.get(j).getString("id").equals("invis")){
                            a=a+1;
                        }
                    }
                }
            }
        }
        plugin.getLogger().info("The value of a after onarmor: "+ a);
        if (a == 4){
            for (Player p : plugin.getServer().getOnlinePlayers().values()) {
                p.hidePlayer(player);
            }
        }
        // this.logger.info("Night Vision effect added to " + player.getName());
    }

    @Override
    public void offArmor(Player player){
        // this.logger.info("Night Vision effect removed from " + player.getName());
        Item[] playerarmor = player.getInventory().getArmorContents();
        int a = 0;
        for (int i=0; i<playerarmor.length; i++){
            if (playerarmor[i].hasCompoundTag()){
                // logger.info("this is armor "+i+":");
                ListTag<CompoundTag> enchants = playerarmor[i].getNamedTag().getList("customenchants",CompoundTag.class);
                if (enchants != null){
                    for (int j=0; j<enchants.size(); j++){
                        if (enchants.get(j).getString("id").equals("invis")){
                            a=a+1;
                        }
                    }
                }
            }
        }
        if (a != 4){
            for (Player p : plugin.getServer().getOnlinePlayers().values()) {
                p.showPlayer(player);
            }
        if (a < 1){
            player.removeEffect(Effect.INVISIBILITY);
        }
        }
    }
}