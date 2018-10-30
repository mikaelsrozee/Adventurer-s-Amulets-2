package com.eagle.adventurersamulets.init;

import com.eagle.adventurersamulets.amp.type.*;
import com.eagle.adventurersamulets.api.AdvAmAPI;

public class ModAMP {

  public static void registerAMPTypes() {
    AdvAmAPI.registerAMPType(new AMPTypeAquatic());
    AdvAmAPI.registerAMPType(new AMPTypeEarthbound());
    AdvAmAPI.registerAMPType(new AMPTypeFairy());
    AdvAmAPI.registerAMPType(new AMPTypeFloral());
    AdvAmAPI.registerAMPType(new AMPTypeFrostbitten());
    AdvAmAPI.registerAMPType(new AMPTypeInfernal());
    AdvAmAPI.registerAMPType(new AMPTypeTransposing());
    AdvAmAPI.registerAMPType(new AMPTypeWindmakers());
  }

}
