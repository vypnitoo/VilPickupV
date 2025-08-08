package com.vypnito.vilPickupV.entity;

import org.bukkit.Bukkit;

public class NMSUtils {
    private static final String CRAFTBUKKIT_PACKAGE = Bukkit.getServer().getClass().getPackageName();

    public static String cbClass(String clazz) {
        return CRAFTBUKKIT_PACKAGE + "." + clazz;
    }
}