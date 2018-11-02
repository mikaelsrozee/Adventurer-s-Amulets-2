package com.eagle.adventurersamulets;

public class Dictionary {

  public static class Core {
    public static final String MOD_ID = "adventurersamulets";
    public static final String MOD_NAME = "Adventurer's Amulets";
    public static final String VERSION = "2.0.0-ALPHA1";
    public static final String DEPENDENCIES = "required-after:baubles";
    public static final String COMMON_PROXY_CLASS = "com.eagle.adventurersamulets.proxy.CommonProxy";
    public static final String CLIENT_PROXY_CLASS = "com.eagle.adventurersamulets.proxy.ClientProxy";
  }

  public static class Item {
    public static final String PESTLE = "pestle";
    public static final String ASH = "ash";
  }

  public static class Block {
    public static final String SLATE = "slate";
  }

  public static class AMP {
    public static final String AQUATIC_ID = "aquatic";
    public static final String EARTHBOUND_ID = "earthbound";
    public static final String FAIRY_ID = "fairy";
    public static final String FLORAL_ID = "floral";
    public static final String FROSTBITTEN_ID = "frostbitten";
    public static final String INFERNAL_ID = "infernal";
    public static final String TRANSPOSING_ID = "transposing";
    public static final String WINDMAKERS_ID = "windmakers";

    public static final String AQUATIC_KEY = "amptype.aquatic.name";
    public static final String EARTHBOUND_KEY = "amptype.earthbound.name";
    public static final String FAIRY_KEY = "amptype.fairy.name";
    public static final String FLORAL_KEY = "amptype.floral.name";
    public static final String FROSTBITTEN_KEY = "amptype.frostbitten.name";
    public static final String INFERNAL_KEY = "amptype.infernal.name";
    public static final String TRANSPOSING_KEY = "amptype.transposing.name";
    public static final String WINDMAKERS_KEY = "amptype.windmakers.name";
  }

}
