package data.scripts.world;

import data.scripts.world.systems.SignalisModSystem;
import com.fs.starfarer.api.campaign.SectorAPI;

public class SignalisModGen {

    public void generate(SectorAPI sector) {
        new SignalisModSystem().generate(sector);
    }
}
