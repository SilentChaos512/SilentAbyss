package silentAbyss.core.util;

import net.minecraft.client.resources.I18n;
import net.minecraft.util.EnumChatFormatting;
import silentAbyss.lib.Localizations;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class LocalizationHelper {

    public static String getLocalizedString(String key) {

        //return LanguageRegistry.instance().getStringLocalization(key);
        return I18n.getString(key);
    }
    
    public static String getMessageText(String key) {
        
        return getMessageText(key, EnumChatFormatting.ITALIC);
    }
    
    public static String getMessageText(String key, EnumChatFormatting format) {
        
        return getMessageText(key, format.toString());
    }
    
    public static String getMessageText(String key, String format) {
        
        return (new StringBuilder()).append(format).append(getLocalizedString("misc.silentabyss:" + key)).toString();
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
