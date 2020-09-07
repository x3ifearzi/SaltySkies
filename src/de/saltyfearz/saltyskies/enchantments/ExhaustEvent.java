package de.saltyfearz.saltyskies.enchantments;

import de.saltyfearz.saltyskies.SaltySkies;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ExhaustEvent implements Listener {

    final private SaltySkies plugin;

    public ExhaustEvent ( final SaltySkies plugin ) { this.plugin = plugin; }

    @EventHandler
    public void onDamage ( EntityDamageByEntityEvent event ) {

        if ( ! ( event.getDamager() instanceof Player ) ) return;

        Player player = ( ( Player ) event.getDamager() ).getPlayer();

        ItemStack iS = player.getInventory().getItemInMainHand();

        int level;

        if ( !iS.getEnchantments().containsKey( Enchantment.getByKey( CustomEnchantments.EXHAUST.getKey( ) ) ) ) return;

        level = iS.getEnchantments().get( Enchantment.getByKey( CustomEnchantments.EXPHUNTER.getKey( ) ) );

        if ( Math.random() > ( 0.2 * level ) ) return;

        LivingEntity victim = ( LivingEntity ) event.getEntity();

        player.sendMessage( plugin.getMsgDE().getMessageInfoDE( "enchant-command", "exhaust" ) );

        victim.addPotionEffect(new PotionEffect( PotionEffectType.WEAKNESS, 3, level));
        victim.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 3, level));

        if ( victim instanceof Player ) victim.sendMessage( plugin.getMsgDE().getMessageInfoDE( "enchant-command", "getExhaust" ) );
    }
}
