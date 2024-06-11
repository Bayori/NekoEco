package me.nekorise.nekoeco.Utils;

public class PlaceholdersReplace
{
    public static String replaceAmount(String message, int amount)
    {
        return message.replace("{total}", String.valueOf(amount));
    }
}
