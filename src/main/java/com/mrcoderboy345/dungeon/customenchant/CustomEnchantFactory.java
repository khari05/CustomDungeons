package com.mrcoderboy345.dungeon.customenchant;

import com.mrcoderboy345.dungeon.MainClass;

import cn.nukkit.utils.Logger;

public class CustomEnchantFactory {

    public static CustomEnchant createEnchant(MainClass plugin, String enchantName, int level) {

        CustomEnchant enchant = null;
        switch(enchantName) {
            case "vision":
                enchant = new NightVisionEnchant(plugin, enchantName, level);
                // logger.info("NightVision enchantment created");
                break;
            case "enlightened":
                enchant = new EnlightenedEnchant(plugin, enchantName, level);
                break;
            case "invis":
                enchant = new InvisibilityEnchant(plugin, enchantName, level);
                break;
            case "water":
                enchant = new WaterEnchant(plugin, enchantName, level);
                break;
            default:
                //log that the passed in enchantName is invalid
                plugin.getLogger().info("Invalid enchantment name" + enchantName);
        }

        return enchant;
    }
}