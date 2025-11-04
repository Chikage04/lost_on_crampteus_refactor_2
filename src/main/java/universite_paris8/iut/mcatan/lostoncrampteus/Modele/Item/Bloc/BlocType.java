package universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Bloc;

import java.util.HashMap;
import java.util.Map;


public enum BlocType {
    GRASS("grass", 1),
    DIRT("dirt", 2),
    BOIS("bois", 15,16,17,18,19,20,21,22,23,24,25,26,27,28),
    ALUMINIUM("aluminium", 29),
    CRAMPTENIUM("cramptenium", 30),
    CUIVRE("cuivre", 31),
    FER("fer", 32),
    PIERRE("pierre", 33),
    GINTOKI("gintoki", 34);

    private final String key;
    private final int[] tileIds;

    private static final Map<Integer, BlocType> TILE_MAP = new HashMap<>();

    BlocType(String key, int... tileIds) {
        this.key = key;
        this.tileIds = tileIds;
    }

    public String getKey() {
        return key;
    }

    public int[] getTileIds() {
        return tileIds;
    }

    static {
        for (BlocType type : values()) {
            for (int id : type.tileIds) {
                TILE_MAP.put(id, type);
            }
        }
    }

    public static BlocType fromTileType(int tile) {
        return TILE_MAP.get(tile);
    }


    public static BlocType fromKey(String key) {
        if (key == null) return null;
        String k = key.toLowerCase();
        for (BlocType t : values()) {
            if (t.key.equalsIgnoreCase(k)) return t;
        }
        return null;
    }
}
