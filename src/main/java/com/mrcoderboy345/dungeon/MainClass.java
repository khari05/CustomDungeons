package com.mrcoderboy345.dungeon;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.event.Listener;
import cn.nukkit.inventory.Recipe;
import cn.nukkit.inventory.ShapedRecipe;
import cn.nukkit.inventory.ShapelessRecipe;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemBlock;
import cn.nukkit.item.ItemDye;
import cn.nukkit.item.ItemStick;
import cn.nukkit.level.Level;
import cn.nukkit.level.Position;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.TextFormat;
import cn.nukkit.math.Vector3;

public class MainClass extends PluginBase implements Listener{
    
    @Override
    public void onLoad() {
        this.getLogger().info(TextFormat.WHITE + "I've been loaded!");
    }

    @Override
    public void onEnable() {
        this.getLogger().info(TextFormat.AQUA + "I've been enabled!");
        this.getLogger().info(TextFormat.AQUA + "CustomDungeons v1.0");
        this.getLogger().info(TextFormat.AQUA + "By MrCoderBoy345");

        this.getLogger().info(String.valueOf(this.getDataFolder().mkdirs()));

        this.getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        this.getLogger().info(TextFormat.DARK_RED + "I've been disabled!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        this.getLogger().info("Command received:" + command.getName());
        if (command.getName().toLowerCase().equals("witchery")) {
            Player player = (Player) sender;
            Position pos = player.getPosition();          
        }
        return true;    
    }
}
