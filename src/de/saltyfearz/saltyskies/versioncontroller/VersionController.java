package de.saltyfearz.saltyskies.versioncontroller;

import de.saltyfearz.saltyskies.interfaces.differentVersionSystem;
import de.saltyfearz.saltyskies.items.v_1_16_2;
import org.bukkit.Bukkit;

public class VersionController {

    private String version;

    private differentVersionSystem dVS;

    private static VersionController instance;

    public VersionController() { this.setupVersion(); }

    private void setupVersion() {

        this.version = Bukkit.getServer( ).getClass().getPackage().getName().replace( ".", "," ).split( "," )[3];

        final String version = this.version;

        switch ( version ) {

            case "v_1_16_2" : {

                this.dVS = new v_1_16_2();

            }

            default: {

                this.dVS = new v_1_16_2();

            }

        }

    }

    public differentVersionSystem getdVS() {

        if ( this.version == null ) this.setupVersion();

        return this.dVS;

    }

    public static VersionController getInstance() {

        if ( instance == null ) instance = new VersionController();

        return instance;

    }
}
