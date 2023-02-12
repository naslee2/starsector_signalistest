package data.scripts.world;

import com.fs.starfarer.api.campaign.SectorAPI;
import data.scripts.world.systems.SignalisModSystem;

public class SignalisModGen {
    public void generate(SectorAPI sector) {
        new SignalisModSystem().generate(sector);
    }
}
