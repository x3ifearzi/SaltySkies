package de.saltyfearz.saltyskies.enchantments;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

public class EnchantmentWrapper extends Enchantment {

    final private String enchantmentName;

    final private int enchantmentMaxLevel;

    public EnchantmentWrapper ( final String namespace, final String name, final int maxLevel ) {

        super(NamespacedKey.minecraft(namespace));

        this.enchantmentName = name;
        this.enchantmentMaxLevel = maxLevel;

    }

    @Override
    public String getName () {
        return enchantmentName;
    }

    @Override
    public int getMaxLevel () {
        return enchantmentMaxLevel;
    }

    @Override
    public int getStartLevel () {
        return 1;
    }

    @Override
    public EnchantmentTarget getItemTarget () {
        return null;
    }

    @Override
    public boolean isTreasure () {
        return false;
    }

    @Override
    public boolean isCursed () {
        return false;
    }

    @Override
    public boolean conflictsWith ( Enchantment enchantment) {
        return true;
    }

    @Override
    public boolean canEnchantItem ( ItemStack itemStack) {
        return true;
    }

}
