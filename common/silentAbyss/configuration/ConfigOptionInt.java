package silentAbyss.configuration;

import net.minecraftforge.common.Configuration;
import silentAbyss.core.util.LogHelper;

public class ConfigOptionInt extends ConfigOption {

    public int value;
    public int defaultValue;

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

        // Shards per gem
        if (name.equals(Config.SHARDS_PER_GEM.name)) {
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
