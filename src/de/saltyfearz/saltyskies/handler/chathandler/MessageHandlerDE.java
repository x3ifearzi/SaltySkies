package de.saltyfearz.saltyskies.handler.chathandler;

import de.saltyfearz.saltyskies.SaltySkies;

public class MessageHandlerDE {

  final private SaltySkies plugin;

  public MessageHandlerDE ( final SaltySkies plugin ) { this.plugin = plugin; }

  public String getMessageChatDE (final String firstSection, final String secondSection ) {

    return categoryPrefix("chat") + getMessage( firstSection, secondSection );
  }

  public String getMessageErrorDE (final String firstSection, final String secondSection ) {

    return categoryPrefix("error") + getMessage( firstSection, secondSection );
  }

  public String getMessageSuccessDE (final String firstSection, final String secondSection ) {

    return categoryPrefix("success") + getMessage( firstSection, secondSection );
  }

  public String getMessageInfoDE (final String firstSection, final String secondSection ) {

    return categoryPrefix("info") + getMessage( firstSection, secondSection );
  }

  public String categoryPrefix ( final String category ) {

    return plugin.getConfigMessenger().getMessageFileConfiguration().getString( "messages.serverinfos.prefix-" + category );
  }

  public String getMessage ( final String firstSection, final String secondSection ) {

    return plugin.getConfigMessenger().getMessageFileConfiguration().getString( "messages." + firstSection + "." + secondSection );

  }

}
