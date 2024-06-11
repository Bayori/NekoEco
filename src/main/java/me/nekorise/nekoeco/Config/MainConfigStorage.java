package me.nekorise.nekoeco.Config;

import me.nekorise.ConsoleLogs.Log;
import me.nekorise.nekoeco.NekoEco;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class MainConfigStorage
{
    public static List<ItemStack> economyItems = new ArrayList<>();
    public static ItemStack mainEconomyItem = new ItemStack(Material.DIAMOND_ORE);
    public static boolean isNotifyOnDeposit = true;
    public static boolean isNotifyOnWithdraw = true;
    public static String language = "en";

    public static void reloadData()
    {
        try
        {
            FileConfiguration cfg = Configs.getConfig("config.yml");

            language = cfg.getString("language");
            for (String material : cfg.getStringList("items.economy-items"))
            {
                economyItems.add(new ItemStack(Material.valueOf(material)));
            }

            mainEconomyItem = new ItemStack(Material.valueOf(cfg.getString("items.withdraw-item")));
            isNotifyOnDeposit = cfg.getBoolean("notifications.notify-on-deposit");
            isNotifyOnWithdraw = cfg.getBoolean("notifications.notify-on-withdraw");

        }
        catch (Exception ex)
        {
            Log.error(NekoEco.getPlugin(), "Failed to load config.yml");
            ex.printStackTrace();
        }
    }
}
