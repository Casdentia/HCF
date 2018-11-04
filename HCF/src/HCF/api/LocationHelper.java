package HCF.api;

import org.bukkit.Location;

public class LocationHelper {

    /**
     * Return a string version of this locations
     * block coordinates in <code>x, y, z</code> format.
     *
     * @param location The location to stringize.
     * @return A string containing this locations coordinates.
     */
    public static String asString(Location location) {

        int x = location.getBlockX();
        int y = location.getBlockY();
        int z = location.getBlockZ();

        return x + ", " + y + ", " + z;
    }
}
