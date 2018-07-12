package com.mrcoderboy345.dungeon.customenchant;

import com.mrcoderboy345.dungeon.MainClass;

import cn.nukkit.Player;
import cn.nukkit.entity.Entity;
import cn.nukkit.item.Item;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.nbt.tag.ListTag;

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
    public boolean whenHit(Entity damager, Float damage, Player victim){
        int rand = rand(1,100);
        int chance = 20 + this.getLevel()*5;
        if (rand<chance){
            rand = rand(1,100);
            // logger.info(victim.getName()+" has resisted "+damage/2+" hearts of damage");
            if (rand <50){
                float healamount = 0;
                Item[] playerarmor = victim.getInventory().getArmorContents();
                for (int i=0; i<playerarmor.length; i++){
                    if (playerarmor[i].hasCompoundTag()){
                        // logger.info("this is armor "+i+":");
                        ListTag<CompoundTag> enchants = playerarmor[i].getNamedTag().getList("customenchants",CompoundTag.class);
                        if (enchants != null){
                            for (int j=0; j<enchants.size(); j++){
                                if (enchants.get(j).getString("id").equals("enlightened")){
                                    healamount = healamount + 2;
                                }
                            }
                        }
                    }
                }
                // logger.info(victim.getName()+" has healed "+healamount/2+" hearts");
                victim.setHealth(victim.getHealth()+healamount);
            }
            return true;
        } 
        return false;
    }
}