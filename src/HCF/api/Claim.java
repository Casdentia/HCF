package HCF.api;

import javafx.util.Pair;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.Arrays;
import java.util.List;

/**
 * This class represents a two-dimensional plane
 * a player has claimed using a claiming wand. The
 * location the player has claimed is expanded vertically
 * from bedrock to the sky limit automatically.
 */
public class Claim implements Preservable {

    private String owner;
    private Location firstLocation, secondLocation;

    /**
     * Creates a claim using <code>firstLocation</code>
     * as the first location selected and <code>secondLocation</code>
     * as the second location selected, then assigns the locations
     * to <code>owner</code>.
     *
     * @param owner
     * @param firstLocation
     * @param secondLocation
     */
    public Claim(String owner, Location firstLocation, Location secondLocation) {
        this.owner = owner;
        this.firstLocation = firstLocation;
        this.secondLocation = secondLocation;
    }

    /**
     * Gets the owner of this claim.
     * @return
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Gets the locations selected to create the claim.
     * @return
     */
    public Pair<Location, Location> getLocations() {
        return new Pair<>(firstLocation, secondLocation);
    }

    @Override
    public void save(YamlConfiguration configuration) {

        ConfigurationSection section = configuration.getConfigurationSection("claims");

        String firstLocation = LocationHelper.asString(this.firstLocation);
        String secondLocation = LocationHelper.asString(this.secondLocation);

        List<String> locations = Arrays.asList(firstLocation, secondLocation);

        section.set(owner + ".locations", locations);
    }

}
