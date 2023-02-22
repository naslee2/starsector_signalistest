package data.scripts.world;
import data.scripts.world.systems.SignalisModSystem;

import com.fs.starfarer.api.campaign.SectorAPI;
import com.fs.starfarer.api.impl.campaign.ids.Factions;
import com.fs.starfarer.api.campaign.FactionAPI;

public class SignalisModGen {

    public void generate(SectorAPI sector) {
        new SignalisModSystem().generate(sector);
        setFactionRelations(sector);
    }

    public static void setFactionRelations(SectorAPI sector){
        FactionAPI signalis_eusan_nation = sector.getFaction("signalis_eusan_nation");
        FactionAPI player = sector.getFaction(Factions.PLAYER);
        FactionAPI hegemony = sector.getFaction(Factions.HEGEMONY);
        FactionAPI tritachyon = sector.getFaction(Factions.TRITACHYON);
        FactionAPI pirates = sector.getFaction(Factions.PIRATES);
        FactionAPI independent = sector.getFaction(Factions.INDEPENDENT);
        FactionAPI church = sector.getFaction(Factions.LUDDIC_CHURCH);
        FactionAPI path = sector.getFaction(Factions.LUDDIC_PATH);
        FactionAPI diktat = sector.getFaction(Factions.DIKTAT);
        FactionAPI persean = sector.getFaction(Factions.PERSEAN);

        signalis_eusan_nation.setRelationship(player.getId(), 0.0f);
        signalis_eusan_nation.setRelationship(diktat.getId(), 0.4f);
        signalis_eusan_nation.setRelationship(hegemony.getId(), -0.5f);
        signalis_eusan_nation.setRelationship(tritachyon.getId(), -0.5f);
        signalis_eusan_nation.setRelationship(pirates.getId(), -0.2f);
        signalis_eusan_nation.setRelationship(independent.getId(), -0.2f);
        signalis_eusan_nation.setRelationship(church.getId(), -0.5f);
        signalis_eusan_nation.setRelationship(path.getId(), -0.5f);
        signalis_eusan_nation.setRelationship(persean.getId(), 0.3f);
    }
}
