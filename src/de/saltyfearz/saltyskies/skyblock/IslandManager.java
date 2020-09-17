package de.saltyfearz.saltyskies.skyblock;

import java.io.File;
import java.util.ArrayList;

public class IslandManager {

    private ArrayList< String > schematics = new ArrayList <>();

    public void loadSchematics () {

        File folder = new File( "plugins/SaltySkies/schematics" );

        if ( !folder.exists() ) folder.mkdirs();

    }
}
