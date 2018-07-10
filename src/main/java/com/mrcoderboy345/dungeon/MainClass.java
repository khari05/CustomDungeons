package com.mrcoderboy345.dungeon;

import com.mrcoderboy345.dungeon.customenchant.CustomEnchant;
import com.mrcoderboy345.dungeon.customenchant.CustomEnchantFactory;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
// import cn.nukkit.entity.Entity;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.entity.EntityArmorChangeEvent;
import cn.nukkit.event.entity.EntityDamageByEntityEvent;
import cn.nukkit.item.Item;
// import cn.nukkit.level.Position;
// import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;
// import cn.nukkit.nbt.tag.DoubleTag;
// import cn.nukkit.nbt.tag.FloatTag;
import cn.nukkit.nbt.tag.ListTag;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.TextFormat;

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
        // if (command.getName().toLowerCase().equals("summon")) {
        //     Player player = (Player) sender;
        //     Position pos = player.getPosition();
        //     FullChunk chunk = player.getLevel().getChunk((int) pos.x >> 4, (int) pos.z >> 4, true);
        //     CompoundTag nbt = new CompoundTag().putList(new ListTag<DoubleTag>("Pos").add(new DoubleTag("", pos.x)).add(new DoubleTag("", pos.y)).add(new DoubleTag("", pos.z)))
        //             .putList(new ListTag<DoubleTag>("Motion").add(new DoubleTag("", 0)).add(new DoubleTag("", 0)).add(new DoubleTag("", 0)))
        //             .putList(new ListTag<FloatTag>("Rotation").add(new FloatTag("", 0)).add(new FloatTag("", 0)));
        //     if (args[0].toLowerCase().equals("zombie")){
        //        Entity boss = Entity.createEntity("Zombie", chunk, nbt);
        //         int maxhealth = Integer.valueOf(args[1]);
        //        boss.setMaxHealth(maxhealth);
        //        boss.setNameTagAlwaysVisible();
        //        boss.setNameTag(args[3]);
        //        player.createBossBar(boss.getNameTag(), (int) boss.getHealth()/boss.getMaxHealth()*100);
        //        //TODO:
        //        //on player move event if player.x player.z and player.z are <=10 createbossbar
        //        //if player moves >10 blocks away remove bossbar
        //        //update bossbar on everyone who is within 10 blocks when boss takes damage
        //     }            
        // }
        if (command.getName().toLowerCase().equals("customenchant")) {
            Player player = (Player) sender;
            Item enchantable = player.getInventory().getItemInHand();
            // this.getLogger().info("enchantable is null:" + (enchantable == null));
            int level = 1;
            if (args.length==2){
            level = Integer.valueOf(args[1]);
            }
            CustomEnchant enchantment = CustomEnchantFactory.createEnchant(this.getLogger(),args[0],level);
            if (enchantable != null && enchantment.canBeAppliedTo(enchantable) ) {
                enchantment.init(enchantable);
                player.getInventory().setItemInHand(enchantable);
            }
            
            
        }
        return true;    
    }
    @EventHandler
    public void onArmorEvent(EntityArmorChangeEvent ev){
        Item newarmor = ev.getNewItem();
        Item oldarmor = ev.getOldItem();
        Player player = (Player) ev.getEntity();
        // this.getLogger().info("Old Armor is Armor[" + oldarmor.isArmor() + "], Has CompoundTag[" + oldarmor.hasCompoundTag()+ "]");
        // this.getLogger().info("New Armor is Armor[" + newarmor.isArmor() + "], Has CompoundTag[" + newarmor.hasCompoundTag()+ "]");
        
        if (oldarmor.isArmor() && oldarmor.hasCompoundTag()) {
            // this.getLogger().info("Old Armor[" + oldarmor.getName() + "]");
            ListTag<CompoundTag> oldenchants = oldarmor.getNamedTag().getList("customenchants",CompoundTag.class);
            if (oldenchants != null){
                for (int i=0; i<oldenchants.size(); i++){
                    CustomEnchantFactory.createEnchant(this.getLogger(), oldenchants.get(i).getString("id"), oldenchants.get(i).getInt("lvl")).offArmor(player);
                }
            }
        }
        if (newarmor.isArmor() && newarmor.hasCompoundTag()){
            // this.getLogger().info("New Armor[" + newarmor.getName() + "]");
            ListTag<CompoundTag> newenchants = newarmor.getNamedTag().getList("customenchants",CompoundTag.class);
            if(newenchants != null){
                for (int i=0; i<newenchants.size(); i++){
                    CustomEnchantFactory.createEnchant(this.getLogger(),newenchants.get(i).getString("id"), newenchants.get(i).getInt("lvl")).onArmor(player);
                }
            }      
        } 
    }
    @EventHandler
    public void onHit(EntityDamageByEntityEvent ev){
        if (ev.getEntity() instanceof Player){
            Player damagee = (Player) ev.getEntity();
            Item[] damageearmor = damagee.getInventory().getArmorContents();
            for (int i=0; i<damageearmor.length; i++){
                if (damageearmor[i].hasCompoundTag()){
                    ListTag<CompoundTag> enchants = damageearmor[i].getNamedTag().getList("customenchants",CompoundTag.class);
                    if (enchants != null){
                        for (int j=0; j<enchants.size(); j++){
                            CustomEnchant enchant = CustomEnchantFactory.createEnchant(this.getLogger(), enchants.get(j).getString("id"), enchants.get(j).getInt("lvl"));
                            if (enchant.whenHit(ev.getDamager(), ev.getDamage(),damagee) == true){
                                ev.setCancelled();
                            }
                            // CustomEnchantFactory.createEnchant(this.getLogger(), enchants.get(j).getString("id"), enchants.get(j).getInt("lvl")).whenHit(ev.getDamager(), ev.getDamage(),(Player) ev.getEntity());
                        }
                    }
                }
            }
        }
        if (ev.getDamager() instanceof Player){
            Player damager = (Player) ev.getDamager();
            Item damagerweapon = damager.getInventory().getItemInHand();
            if (damagerweapon.hasCompoundTag()){
                ListTag<CompoundTag> enchants = damagerweapon.getNamedTag().getList("customenchants",CompoundTag.class);
                if (enchants != null){
                    for (int i=0; i<enchants.size(); i++){
                        CustomEnchantFactory.createEnchant(this.getLogger(), enchants.get(i).getString("id"), enchants.get(i).getInt("lvl")).onAttack(damager, ev.getDamage(), ev.getEntity());;
                    }
                }
            }
        }
    }
}
