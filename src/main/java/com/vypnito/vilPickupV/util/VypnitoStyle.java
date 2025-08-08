package com.vypnito.vilPickupV.util;

import org.bukkit.ChatColor;

public class VypnitoStyle {
    
    public static final String PREFIX = ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "Vypnito" + ChatColor.DARK_GRAY + "] " + ChatColor.GRAY;
    public static final String BRAND = ChatColor.GOLD + "Vypnito" + ChatColor.RESET;
    public static final String PLUGIN_NAME = ChatColor.GRAY + "VilPickup" + ChatColor.YELLOW + "V" + ChatColor.RESET;
    public static final String SUCCESS = ChatColor.GREEN + "";
    public static final String ERROR = ChatColor.RED + "";
    public static final String WARNING = ChatColor.YELLOW + "";
    public static final String INFO = ChatColor.AQUA + "";
    public static final String HIGHLIGHT = ChatColor.GOLD + "";
    public static final String SECONDARY = ChatColor.GRAY + "";
    
    public static String getRandomPickupMessage() {
        String[] messages = {
            "Nice catch! That villager is all yours now ✨",
            "Villager secured! Time to relocate them somewhere better 🏠",
            "Got 'em! This villager is ready for their new home 🎯",
            "Smooth pickup! Your villager collection grows stronger 💪",
            "Villager captured! They'll be much happier in their new spot 😊"
        };
        return messages[(int) (Math.random() * messages.length)];
    }
    
    public static String getRandomPlaceMessage() {
        String[] messages = {
            "Welcome to your new home, little villager! 🏡",
            "There we go! Hope they like their new neighborhood 🌟",
            "Villager deployed successfully! Time to let them settle in ✨",
            "Perfect spot! This villager should be happy here 😄",
            "Mission accomplished! Another villager finds their place 🎉"
        };
        return messages[(int) (Math.random() * messages.length)];
    }
    
    public static String formatMessage(String message) {
        return PREFIX + message;
    }
    
    public static String formatSuccess(String message) {
        return PREFIX + SUCCESS + message;
    }
    
    public static String formatError(String message) {
        return PREFIX + ERROR + message;
    }
    
    public static String formatWarning(String message) {
        return PREFIX + WARNING + message;
    }
    
    public static String formatInfo(String message) {
        return PREFIX + INFO + message;
    }
}