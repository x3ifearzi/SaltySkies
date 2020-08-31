package de.saltyfearz.saltyskies.enchantments;

import de.saltyfearz.saltyskies.SaltySkies;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public class ExplosionEvent implements Listener {

    final private SaltySkies plugin;

    public ExplosionEvent ( final SaltySkies plugin ) { this.plugin = plugin; }

    @EventHandler
    public void onHitExplosion ( EntityDamageByEntityEvent event ) {

        Player player = ( ( Player ) event.getDamager() ).getPlayer();

        ItemStack iS = player.getInventory().getItemInMainHand();

        int level;

        if ( !iS.getEnchantments().containsKey( Enchantment.getByKey( CustomEnchantments.EXPLOSION.getKey( ) ) ) ) return;

        level = iS.getEnchantments().get( "EXPLOSION" );

        if ( Math.random() > ( 0.1 * level ) ) return;

        LivingEntity victim = ( LivingEntity ) event.getEntity();

        boolean maxLevel = CustomEnchantments.EXPLOSION.getMaxLevel() == level;

        player.getWorld().createExplosion( event.getEntity().getLocation(), level, maxLevel );

        player.sendMessage( plugin.getMsgDE().getMessageInfoDE( "enchant-command", "explosion" ) );

        if ( victim instanceof Player ) victim.sendMessage( plugin.getMsgDE().getMessageInfoDE( "enchant-command", "getExplosion" ) );
    }
}
