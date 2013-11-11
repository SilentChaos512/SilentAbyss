package silentAbyss.core.util;

import silentAbyss.lib.Localizations;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class LocalizationHelper {

    public static String getLocalizedString(String key) {

        return LanguageRegistry.instance().getStringLocalization(key);
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
