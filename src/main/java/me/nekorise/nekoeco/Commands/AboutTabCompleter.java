package me.nekorise.nekoeco.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class AboutTabCompleter implements TabCompleter
{
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args)
    {
        List<String> tabs = new ArrayList<>();

        if (args.length == 1 && sender.hasPermission("nekoeco.reload"))
        {
            tabs.add("reload");
        }

        if (tabs.size() < 1)
        {
            tabs.add("");
        }

        return tabs;
    }
}
