package silentAbyss.configuration;

import net.minecraftforge.common.Configuration;
import silentAbyss.core.util.LogHelper;

public class ConfigOptionString extends ConfigOption {

    public String value;
    public String defaultValue;

    public ConfigOptionString(String name, String defaultValue) {

        this.name = name;
        value = "";
        this.defaultValue = defaultValue;
    }

    @Override
    public ConfigOption loadValue(Configuration c, String category) {

        value = c.get(category, name, defaultValue).getString();
        return this;
    }

    @Override
    public ConfigOption loadValue(Configuration c, String category, String comment) {

        value = c.get(category, name, defaultValue, comment).getString();
        return this;
    }

    @Override
    public ConfigOption validate() {

        // Enable sounds
        if (name.equals(Config.ENABLE_SOUNDS.name)) {
            boolean isValid = false;
            for (String s : Config.ENABLE_SOUNDS_VALID_OPTIONS) {
                if (s.equals(value)) {
                    isValid = true;
                }
            }
            if (!isValid) {
                value = defaultValue;
            }
        }
        // Mistake catcher
        else {
            LogHelper.warning("No validation code for config setting " + name + " found!");
        }

        return this;
    }

}
