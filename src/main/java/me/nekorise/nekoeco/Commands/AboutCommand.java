package me.nekorise.nekoeco.Commands;

import me.nekorise.Hex.HEX;
import me.nekorise.nekoeco.Config.Configs;
import me.nekorise.nekoeco.Config.LangConfigStorage;
import me.nekorise.nekoeco.NekoEco;
import me.nekorise.nekoeco.Utils.PlaceholdersReplace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class AboutCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args)
    {
        if (args.length == 1 && args[0].equals("reload") && sender.hasPermission("nekoeco.reload"))
        {
            Configs.loadConfig();
            sender.sendMessage(HEX.applyColor(LangConfigStorage.cfgReloadMessage));
            return true;
        }

        String aboutMessage1 = HEX.gradientText("NekoEco v"+ NekoEco.getPlugin().getPluginMeta().getVersion(), HEX.parseHexColor("#f2941f"), HEX.parseHexColor("#bff21f"));
        String aboutMessage2 = HEX.gradientText("\nby Nekorise", HEX.parseHexColor("#f2941f"), HEX.parseHexColor("#bff21f"));
        sender.sendMessage(aboutMessage1 + aboutMessage2);
        return true;
    }
}
