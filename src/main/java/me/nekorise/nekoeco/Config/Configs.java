package me.nekorise.nekoeco.Config;

import me.nekorise.nekoeco.NekoEco;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Configs
{
    private final static String path = "./plugins/NekoEco/";

    // vvv PASTE NEW LOCALIZATION FILENAMES HERE vvv
    private final static String[] languageFiles = {"en.yml", "ru.yml"};

    public static FileConfiguration getConfig(String cfgName) throws IOException, InvalidConfigurationException
    {
        FileConfiguration fileConfiguration = new YamlConfiguration();
        fileConfiguration.options().parseComments(true);
        fileConfiguration.load(path + cfgName);

        return fileConfiguration;
    }

    private static boolean createConfigs()
    {
        File defaultConfig = new File(path, "config.yml");
        if (!defaultConfig.exists())
        {
            NekoEco.getPlugin().saveResource("config.yml", false);
        }

        List<String> langConfigFiles = new ArrayList<>(Arrays.asList(languageFiles));
        for (String langConfigName : langConfigFiles)
        {
            File langConfig = new File(path, "langs/" + langConfigName);
            if (!langConfig.exists())
            {
                NekoEco.getPlugin().saveResource("langs/" + langConfigName, false);
            }
        }
        return true;
    }

    public static void loadConfig()
    {
        createConfigs();
        MainConfigStorage.reloadData();
        LangConfigStorage.reloadData();
    }
}
