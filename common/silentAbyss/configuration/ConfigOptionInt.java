package silentAbyss.configuration;

import net.minecraftforge.common.Configuration;
import silentAbyss.core.util.LogHelper;

public class ConfigOptionInt extends ConfigOption {

    public int value;
    public final int defaultValue;

    public ConfigOptionInt(String name, int defaultValue) {

        this.name = name;
        value = 0;
        this.defaultValue = defaultValue;
    }

    @Override
    public ConfigOption loadValue(Configuration c, String category) {

        value = c.get(category, name, defaultValue).getInt(defaultValue);
        return this;
    }

    @Override
    public ConfigOption loadValue(Configuration c, String category, String comment) {

        value = c.get(category, name, defaultValue, comment).getInt(defaultValue);
        return this;
    }

    @Override
    public ConfigOption validate() {

        if (name.equals(Config.DIMENSION.name)) {
            // Check that value is not the same as a vanilla dimension id.
            if (value == -1 || value == 0 || value == 1) {
                LogHelper.warning(String.format("Dimension ID in config file was %d! Resetting to %d", value, defaultValue));
                value = defaultValue;
            }
        }
        else if (name.equals(Config.SHARDS_PER_GEM.name)) {
            if (value != 9) {
                value = 4;
            }
        }
        // Mistake catcher
        else {
            LogHelper.warning("No validation code for config setting " + name + " found!");
        }

        return this;
    }

}
