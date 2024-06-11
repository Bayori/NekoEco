package me.nekorise.nekoeco.Vault;

import me.nekorise.ConsoleLogs.Log;
import me.nekorise.nekoeco.NekoEco;
import org.bukkit.plugin.RegisteredServiceProvider;

public class VEconomy
{
    private static net.milkbowl.vault.economy.Economy econ = null;
    private static boolean checkVault() {
        if (NekoEco.getPlugin().getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<net.milkbowl.vault.economy.Economy> rsp = NekoEco.getPlugin().getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    public static void setupEconomy()
    {
        if (!checkVault())
        {
            Log.error(NekoEco.getPlugin(),"Disabled due to no Vault dependency found!");
            NekoEco.getPlugin().getServer().getPluginManager().disablePlugin(NekoEco.getPlugin());
        }
    }
    public static net.milkbowl.vault.economy.Economy getEcon()
    {
        return econ;
    }
}
