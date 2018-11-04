package HCF.api;

import org.bukkit.configuration.file.YamlConfiguration;

public interface Preservable {

    /**
     * A method used for saving custom objects.
     * Define the way you would like your object to be
     * saved using <code>configuration</code>.
     *
     * @param configuration The specified <code>YamlConfiguration</code>.
     */
    void save(YamlConfiguration configuration);
}
