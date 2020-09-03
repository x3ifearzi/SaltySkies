package de.saltyfearz.saltyskies.commands;

import de.minnymin.command.Command;
import de.minnymin.command.CommandArgs;
import de.saltyfearz.saltyskies.SaltySkies;
import org.bukkit.entity.Player;

public class ShopCommand {

    final private SaltySkies plugin;

    private double money;

    public ShopCommand ( final SaltySkies plugin ) { this.plugin = plugin; }

    @Command( name = "shop", description = "§6Öffne den Shop.", usage = "§6/Shop", permission = "SaltySkies.shop.use")
    public void shop ( CommandArgs args ) {

        final Player player = args.getPlayer();


    }
}
