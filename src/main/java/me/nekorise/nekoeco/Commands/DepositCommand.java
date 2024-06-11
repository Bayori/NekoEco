package me.nekorise.nekoeco.Commands;

import me.nekorise.Hex.HEX;
import me.nekorise.NumericUtils.Numeric;
import me.nekorise.nekoeco.Config.LangConfigStorage;
import me.nekorise.nekoeco.Config.MainConfigStorage;
import me.nekorise.nekoeco.Inventory.Deposit;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.eclipse.sisu.launch.Main;
import org.jetbrains.annotations.NotNull;

public class DepositCommand implements CommandExecutor
{
    Economy economy = null;
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args)
    {
        if (!(sender instanceof Player)) { return false; }

        if (args.length < 1)
        {
            sender.sendMessage(HEX.applyColor(LangConfigStorage.depositUsage));
            return false;
        }
        Player player = (Player) sender;

        switch(args[0])
        {
            case "hand":
                depositFromHand(player);
                return true;
            case "all":
                depositAll(player);
                return true;
        }

        player.sendMessage(HEX.applyColor(LangConfigStorage.depositUsage));

        return false;
    }

    private void depositFromHand(Player player)
    {
        if (player.hasPermission("nekoeco.deposithand"))
        {
            Deposit deposit = new Deposit();
            deposit.depositFromHand(player);
        }
    }
    private void depositAll(Player player)
    {
        if (player.hasPermission("nekoeco.depositall"))
        {
            Deposit deposit = new Deposit();
            deposit.depositFromInventory(player);
        }
    }
}
