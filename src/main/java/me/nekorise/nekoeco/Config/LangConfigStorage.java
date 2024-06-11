package me.nekorise.nekoeco.Config;

import me.nekorise.ConsoleLogs.Log;
import me.nekorise.nekoeco.NekoEco;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;

public class LangConfigStorage
{
    public static String cfgReloadMessage = "Конфиг перезагружен";
    public static String depositUsage = "Используй /deposit <hand/all>";
    public static String depositNotify = "Ты пополнил баланс на {total} АРов";
    public static String depositNullHand = "Нужно держать АРы в руке";
    public static String depositNullInventory = "У тебя не хватает АРов в инвентаре";
    public static String withdrawUsage = "Используй /withdraw <Кол-во/all>";
    public static String withdrawLowBalance = "На твоём балансе недостаточно средств для вывода";
    public static String withdrawFullInventory = "В твоём инвентаре не хватает места";
    public static String withdrawComplete = "Успешно выведено {total} АРов";
    public static void reloadData()
    {
        try
        {
            FileConfiguration cfg = Configs.getConfig("langs/" + MainConfigStorage.language + ".yml");

            cfgReloadMessage = cfg.getString("cfg-reload");
            depositUsage = cfg.getString("deposit-usage");
            depositNotify = cfg.getString("deposit-notify");
            depositNullHand = cfg.getString("deposit-null-hand");
            depositNullInventory = cfg.getString("deposit-null-inventory");
            withdrawUsage = cfg.getString("withdraw-usage");
            withdrawLowBalance = cfg.getString("withdraw-low-balance");
            withdrawFullInventory = cfg.getString("withdraw-full-inventory");
            withdrawComplete = cfg.getString("withdraw-complete");

        }
        catch (Exception ex)
        {
            Log.error(NekoEco.getPlugin(), "Failed to load config.yml");
            ex.printStackTrace();
        }
    }

}
