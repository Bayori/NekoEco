package me.nekorise.nekoeco;

import me.nekorise.ConsoleLogs.Log;
import me.nekorise.nekoeco.Commands.*;
import me.nekorise.nekoeco.Config.Configs;
import me.nekorise.nekoeco.Vault.VEconomy;
import org.bukkit.plugin.java.JavaPlugin;

public final class NekoEco extends JavaPlugin
{
    private static NekoEco plugin;
    @Override
    public void onEnable()
    {
        plugin = this;
        VEconomy.setupEconomy();
        registerCommands();
        registerTabCompleters();
        Configs.loadConfig();
    }

    private void registerCommands()
    {
        getCommand("deposit").setExecutor(new DepositCommand());
        getCommand("withdraw").setExecutor(new WithdrawCommand());
        getCommand("nekoeco").setExecutor(new AboutCommand());
    }
    private void registerTabCompleters()
    {
        getCommand("deposit").setTabCompleter(new DepositTabCompleter());
        getCommand("withdraw").setTabCompleter(new WithdrawTabCompleter());
        getCommand("nekoeco").setTabCompleter(new AboutTabCompleter());
    }

    public static NekoEco getPlugin()
    {
        return plugin;
    }
}
