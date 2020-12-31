package de.saltyfearz.saltyskies.enchantments;

import de.saltyfearz.saltyskies.SaltySkies;
import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public class HemorrhageEvent implements Listener {

    private final SaltySkies plugin;

    public HemorrhageEvent ( final SaltySkies plugin ) { this.plugin = plugin; }

    @EventHandler
    public void onPlayerHit ( EntityDamageByEntityEvent event ) {

        if ( !( event.getDamager() instanceof Player ) ) return;

        final Player player = ( ( Player ) event.getDamager() ).getPlayer();

        final ItemStack iS = player.getInventory().getItemInMainHand();

        if ( ! iS.getEnchantments().containsKey( Enchantment.getByKey( CustomEnchantments.HEMORRHAGE.getKey( ) ) )) return;

        final int level = iS.getEnchantments().values().iterator().next();

        if ( Math.random() > 0.05 * level ) return;

        final LivingEntity victim = ( LivingEntity ) event.getEntity();

        player.sendMessage( plugin.getMsgDE().getMessageInfoDE( "enchant-command", "hemorrhage" ) );

        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> victim.damage(2), 20, level);

        if ( victim instanceof Player ) victim.sendMessage( plugin.getMsgDE().getMessageInfoDE( "enchant-command", "getHemorrhage" ) );

    }
}
