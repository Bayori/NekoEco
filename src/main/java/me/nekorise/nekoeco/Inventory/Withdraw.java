package me.nekorise.nekoeco.Inventory;

import me.nekorise.Hex.HEX;
import me.nekorise.nekoeco.Config.LangConfigStorage;
import me.nekorise.nekoeco.Config.MainConfigStorage;
import me.nekorise.nekoeco.Utils.PlaceholdersReplace;
import me.nekorise.nekoeco.Vault.VEconomy;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.eclipse.sisu.launch.Main;

public class Withdraw
{
    Economy economy;

    public Withdraw()
    {
        this.economy = VEconomy.getEcon();
    }
    public void withdrawToInventory(Player player, int amount)
    {
        PlayerInventory playerInventory = player.getInventory();
        ItemStack itemToAdd = MainConfigStorage.mainEconomyItem;
        itemToAdd.setAmount(amount);
        int totalSpace = 0;

        int i = 0;
        for (ItemStack itemInv : playerInventory)
        {
            if (i > 35) { break; }

            if (itemInv == null)
            {
                totalSpace += 64;
                i++;
                continue;
            }

            if (itemInv.getType() == itemToAdd.getType())
            {
                totalSpace += 64 - itemInv.getAmount();
                i++;
                continue;
            }
            i++;
        }

        if (totalSpace >= amount)
        {
            economy.withdrawPlayer(player, amount);
            playerInventory.setMaxStackSize(64);
            playerInventory.addItem(itemToAdd);
            if (MainConfigStorage.isNotifyOnWithdraw)
            {
                player.sendMessage(HEX.applyColor(PlaceholdersReplace.replaceAmount(LangConfigStorage.withdrawComplete, amount)));
            }
        }
        else
        {
            player.sendMessage(HEX.applyColor(PlaceholdersReplace.replaceAmount(LangConfigStorage.withdrawFullInventory, amount)));
        }

    }
}
