package me.nekorise.nekoeco.Commands;

import me.nekorise.Hex.HEX;
import me.nekorise.NumericUtils.Numeric;
import me.nekorise.nekoeco.Config.LangConfigStorage;
import me.nekorise.nekoeco.Inventory.Withdraw;
import me.nekorise.nekoeco.Utils.PlaceholdersReplace;
import me.nekorise.nekoeco.Vault.VEconomy;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class WithdrawCommand implements CommandExecutor
{
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args)
    {
        if (!(sender instanceof Player) || !sender.hasPermission("nekoeco.withdraw")) { return false; }
        if (args.length < 1)
        {
            sender.sendMessage(HEX.applyColor(LangConfigStorage.withdrawUsage));
            return false;
        }

        int amount = 0;
        Withdraw withdraw = new Withdraw();
        Economy economy = VEconomy.getEcon();
        Player player = (Player) sender;

        if (Numeric.isNumeric(args[0]))
        {
            try
            {
                amount = Math.abs(Integer.parseInt(args[0]));
                if (amount < 1)
                {
                    amount = 1;
                }
            }
            catch (Exception ex)
            {
                sender.sendMessage(HEX.applyColor(LangConfigStorage.withdrawUsage));
                return false;
            }
        }
        else if (args[0].equals("all"))
        {
            amount = (int) economy.getBalance(player);
            if (amount < 1)
            {
                player.sendMessage(HEX.applyColor(PlaceholdersReplace.replaceAmount(LangConfigStorage.withdrawLowBalance, amount)));
                return false;
            }
        }
        else
        {
            sender.sendMessage(HEX.applyColor(LangConfigStorage.withdrawUsage));
            return false;
        }


        if (amount > economy.getBalance(player))
        {
            player.sendMessage(HEX.applyColor(PlaceholdersReplace.replaceAmount(LangConfigStorage.withdrawLowBalance, amount)));
            return false;
        }


        withdraw.withdrawToInventory(player, amount);
        return true;
    }
}
