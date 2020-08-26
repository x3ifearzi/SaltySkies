package de.saltyfearz.saltyskies.handler.chathandler;

import de.saltyfearz.saltyskies.SaltySkies;

public class MessageHandlerDE {

  static SaltySkies plugin;

  public MessageHandlerDE ( final SaltySkies plugin ) { MessageHandlerDE.plugin = plugin; }

  public static String getMessageChatDE (final String firstSection, final String secondSection ) {

    return categoryPrefix("chat") + getMessage( firstSection, secondSection );
  }

  public static String getMessageErrorDE (final String firstSection, final String secondSection ) {

    return categoryPrefix("error") + getMessage( firstSection, secondSection );
  }

  public static String getMessageSuccessDE (final String firstSection, final String secondSection ) {

    return categoryPrefix("success") + getMessage( firstSection, secondSection );
  }

  public static String getMessageInfoDE (final String firstSection, final String secondSection ) {

    return categoryPrefix("info") + getMessage( firstSection, secondSection );
  }

  public static String categoryPrefix ( final String category ) {

    return plugin.getConfigMessenger().getMessageFileConfiguration().getString( "messages.serverinfos.prefix-" + category );
  }

  public static String getMessage ( final String firstSection, final String secondSection ) {

    return plugin.getConfigMessenger().getMessageFileConfiguration().getString( "messages." + firstSection + "." + secondSection );

  }

}
