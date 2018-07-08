package com.mrcoderboy345.dungeon.customenchant;

import cn.nukkit.utils.Logger;

public class CustomEnchantFactory {

    public static CustomEnchant createEnchant(Logger logger, String enchantName, int level) {

        CustomEnchant enchant = null;
        switch(enchantName) {
            case "vision":
                enchant = new NightVisionEnchant(logger, enchantName, level);
                // logger.info("NightVision enchantment created");
                break;
            default:
                //log that the passed in enchantName is invalid
                logger.info("Invalid enchantment name" + enchantName);
        }

        return enchant;
    }
}