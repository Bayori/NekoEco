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

public class Deposit
{
    Economy economy;
    public Deposit()
    {
        this.economy = VEconomy.getEcon();
    }

    public void depositFromHand(Player player)
    {
        PlayerInventory playerInventory = player.getInventory();
        ItemStack itemInMainHand = playerInventory.getItemInMainHand();

        for (ItemStack econItem : MainConfigStorage.economyItems)
        {
            if (itemInMainHand.getType() == econItem.getType())
            {
                playerInventory.setItemInMainHand(new ItemStack(Material.AIR));
                economy.depositPlayer(player, itemInMainHand.getAmount());
                if (MainConfigStorage.isNotifyOnDeposit)
                {
                    int amount = itemInMainHand.getAmount();
                    player.sendMessage(HEX.applyColor(PlaceholdersReplace.replaceAmount(LangConfigStorage.depositNotify, amount))); // Оформление
                }
                return;
            }
        }
        player.sendMessage(HEX.applyColor(LangConfigStorage.depositNullHand));
    }

    public void depositFromInventory(Player player)
    {
        PlayerInventory playerInventory = player.getInventory();
        int totalDepositAmount = 0;
        int i = 0;
        for (ItemStack invItem : playerInventory)
        {
            if (invItem == null)
            {
                i++;
                continue;
            }
            int amount = invItem.getAmount();

            for (ItemStack econItem : MainConfigStorage.economyItems)
            {
                if (invItem.getType() == econItem.getType())
                {
                    playerInventory.setItem(i, new ItemStack(Material.AIR));
                    totalDepositAmount += amount;
                    break;
                }
            }
            i++;
        }

        if (totalDepositAmount > 0)
        {
            if (MainConfigStorage.isNotifyOnDeposit)
            {
                economy.depositPlayer(player, totalDepositAmount);
                player.sendMessage(HEX.applyColor(PlaceholdersReplace.replaceAmount(LangConfigStorage.depositNotify, totalDepositAmount)));
            }
        }
        else
        {
            player.sendMessage(HEX.applyColor(LangConfigStorage.depositNullInventory)); // Нет предметов в инвенте
        }
    }
}
