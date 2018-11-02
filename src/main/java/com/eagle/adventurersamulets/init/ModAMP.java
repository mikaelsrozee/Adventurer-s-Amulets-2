package com.eagle.adventurersamulets.init;

import com.eagle.adventurersamulets.amp.type.AMPTypeAquatic;
import com.eagle.adventurersamulets.amp.type.AMPTypeEarthbound;
import com.eagle.adventurersamulets.amp.type.AMPTypeFairy;
import com.eagle.adventurersamulets.amp.type.AMPTypeFloral;
import com.eagle.adventurersamulets.amp.type.AMPTypeFrostbitten;
import com.eagle.adventurersamulets.amp.type.AMPTypeInfernal;
import com.eagle.adventurersamulets.amp.type.AMPTypeTransposing;
import com.eagle.adventurersamulets.amp.type.AMPTypeWindmakers;
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
