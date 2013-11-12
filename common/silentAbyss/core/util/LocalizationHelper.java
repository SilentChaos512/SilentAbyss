package silentAbyss.core.util;

import net.minecraft.client.resources.I18n;
import silentAbyss.lib.Localizations;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class LocalizationHelper {

    public static String getLocalizedString(String key) {

        //return LanguageRegistry.instance().getStringLocalization(key);
        return I18n.getString(key);
    }
    
    public static String getMessageText(String key) {
        
        return (new StringBuilder()).append("\u00a7o").append(getLocalizedString("misc." + key)).toString();
    }
    
    public static boolean isXMLLanguageFile(String fileName) {
        
        return fileName.endsWith(".xml");
    }
    
    public static String getLocaleFromFileName(String fileName) {
        
        return fileName.substring(fileName.lastIndexOf('/') + 1, fileName.lastIndexOf('.'));
    }

    public static void init() {

        for (String localizationFile : Localizations.localeFiles) {
            LanguageRegistry.instance().loadLocalization(localizationFile, LocalizationHelper.getLocaleFromFileName(localizationFile),
                    LocalizationHelper.isXMLLanguageFile(localizationFile));
        }
    }
}
