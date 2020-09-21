package de.saltyfearz.saltyskies.commands;

import de.minnymin.command.Command;
import de.minnymin.command.CommandArgs;
import de.saltyfearz.saltyskies.SaltySkies;
import org.bukkit.entity.Player;

public class RankCommand {

    final private SaltySkies plugin;

    public RankCommand( final SaltySkies plugin ) { this.plugin = plugin; }

    @Command( name = "rank", aliases = "rang", description = "§6Rangsystem V1.0", usage = "§6/rank", permission = "SaltySkies.ranks.*")
    public void ranks( CommandArgs args ) {

        Player player = args.getPlayer( );

        String[] arg = args.getArgs( );

        
    }
}
