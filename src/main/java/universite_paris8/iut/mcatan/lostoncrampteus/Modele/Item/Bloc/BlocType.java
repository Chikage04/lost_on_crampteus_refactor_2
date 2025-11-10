package universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Bloc;

import java.util.HashMap;
import java.util.Map;


public enum BlocType {
    GRASS("grass", true, 1),
    DIRT("dirt", true, 2),
    BOIS("bois", true, 15,16,17,18,19,20,21,22,23,24,25,26,27,28),
    ALUMINIUM("aluminium", true, 29),
    CRAMPTENIUM("cramptenium", true, 30),
    CUIVRE("cuivre", true, 31),
    FER("fer", true, 32),
    PIERRE("pierre", true, 33),
    GINTOKI("gintoki", true, 34),
    AIR("air", false, 0);

    private final String key;
    private final boolean isSolid;
    private final int[] tileIds;

    private static final Map<Integer, BlocType> TILE_MAP = new HashMap<>();

    BlocType(String key, boolean isSolid, int... tileIds) {
        this.key = key;
        this.isSolid = isSolid;
        this.tileIds = tileIds;
    }

    public String getKey() {
        return key;
    }

    public int[] getTileIds() {
        return tileIds;
    }

    public boolean isSolid() {
        return isSolid;
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
