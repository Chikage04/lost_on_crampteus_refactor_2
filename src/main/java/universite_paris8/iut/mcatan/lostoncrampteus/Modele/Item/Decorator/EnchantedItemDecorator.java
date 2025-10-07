package universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Decorator;

import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Item;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Monde;

public class EnchantedItemDecorator extends ItemDecorator {
    private String enchantmentType;
    private int enchantmentLevel;

    public EnchantedItemDecorator(Item item, String enchantmentType, int enchantmentLevel) {
        super(item);
        this.enchantmentType = enchantmentType;
        this.enchantmentLevel = enchantmentLevel;
    }

    @Override
    public String getNom() {
        return wrappedItem.getNom() + " +" + enchantmentLevel + " (" + enchantmentType + ")";
    }

    @Override
    public void attaquer(int tileX, int tileY) {
        // Enhanced attack with enchantment bonus
        super.attaquer(tileX, tileY);
        // Future: Add enchantment effects here
    }

    @Override
    public void utiliser(Monde monde, int tileX, int tileY) {
        // Enhanced usage with enchantment bonus
        super.utiliser(monde, tileX, tileY);
        // Future: Add enchantment effects here
    }

    public String getEnchantmentType() {
        return enchantmentType;
    }

    public int getEnchantmentLevel() {
        return enchantmentLevel;
    }
}
