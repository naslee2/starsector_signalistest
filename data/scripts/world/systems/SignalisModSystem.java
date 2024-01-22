package data.scripts.world.systems;

import java.awt.Color;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.*;
import com.fs.starfarer.api.impl.campaign.ids.Conditions;
import com.fs.starfarer.api.impl.campaign.ids.Factions;
import com.fs.starfarer.api.impl.campaign.ids.Terrain;
import com.fs.starfarer.api.impl.campaign.procgen.NebulaEditor;
import com.fs.starfarer.api.impl.campaign.procgen.ProcgenUsedNames;
import com.fs.starfarer.api.impl.campaign.terrain.HyperspaceTerrainPlugin;
import com.fs.starfarer.api.util.Misc;

public class SignalisModSystem {
    
    public void generate(SectorAPI sector) {
        StarSystemAPI system = sector.createStarSystem("Alatyr");

        system.getLocation().set(-34000, 35000);
        system.setBackgroundTextureFilename("graphics/backgrounds/background5.jpg");

        PlanetAPI signalisStar = system.initStar("Die Sonne", "star_yellow", 650f, 450); //initeStar( Unique starId, planettype from planets.json, radius, corona radius from star edge)

        system.addCorona(signalisStar, 250f, 5f, 2f, 2f); //addCorona(SectorEntityToken star, float extraRadius, float windBurnLevel, float flareProbability, float crLossMult) 

        //Stable Location 1, 2 and 3
        SectorEntityToken stableLoc1 = system.addCustomEntity("signalisStar_loc1", "Stable Location", "stable_location", Factions.NEUTRAL);
        SectorEntityToken stableLoc2 = system.addCustomEntity("signalisStar_loc2", "Stable Location", "stable_location", Factions.NEUTRAL);
        SectorEntityToken stableLoc3 = system.addCustomEntity("signalisStar_loc3", "Stable Location", "stable_location", Factions.NEUTRAL);

        //Setting stable locations setCircularOrbitPointingDown(location/star/planet, angle, orbit raidus/range, orbit period)
        stableLoc1.setCircularOrbitPointingDown(signalisStar, 40f, 5200f, 220f);
        stableLoc2.setCircularOrbitPointingDown(signalisStar, 310f, 8000f, 235f);
        stableLoc3.setCircularOrbitPointingDown(signalisStar, 178f, 16600f, 921f); 

        //PlanetAPI stream = system.addPlanet("stream"[ID], star[What it orbits], "Stream"[Name you will see in game], "gas_giant"[Planet type], 230[Starring angle of the orbit], 350[Size in pixels, 5000[Orbit Radius], 150[Number of ingame days to complete orbit]);

        //-BUYAN - Toxic World-
        PlanetAPI buyan = system.addPlanet("signalis_buyan", signalisStar , "Buyan", "toxic", 30f, 170f, 2000f, 225f);
        ProcgenUsedNames.notifyUsed("Buyan");
        buyan.getMarket().addCondition(Conditions.TOXIC_ATMOSPHERE);
        buyan.getMarket().addCondition(Conditions.RARE_ORE_MODERATE);
        buyan.getMarket().addCondition(Conditions.ORE_MODERATE);
        buyan.getMarket().addCondition(Conditions.HOT);
        buyan.getMarket().addCondition(Conditions.ORGANICS_COMMON);
        buyan.getMarket().addCondition(Conditions.VOLATILES_DIFFUSE);
        buyan.getMarket().addCondition(Conditions.RUINS_EXTENSIVE);
        buyan.setCustomDescriptionId("signalis_buyan_planet");

        //-VINETA AND THE SHATTERED MOON AND DEBRIS RING- Terran world? Water World?
        PlanetAPI vineta = system.addPlanet("signalis_vineta", signalisStar, "Vineta", "water", 88f, 190f, 6000f, 360f);
        vineta.getMarket().addCondition(Conditions.HABITABLE);
        vineta.getMarket().addCondition(Conditions.MILD_CLIMATE);
        vineta.getMarket().addCondition(Conditions.RUINS_EXTENSIVE);
        vineta.getMarket().addCondition(Conditions.ORE_MODERATE);
        vineta.getMarket().addCondition(Conditions.ORGANICS_ABUNDANT);
        vineta.getMarket().addCondition(Conditions.WATER_SURFACE);
        vineta.setCustomDescriptionId("signalis_vineta_planet");

        PlanetAPI shattered_moon = system.addPlanet("signalis_shattered_moon", vineta, "Shattered Moon", "barren", 270f, 90f, 750f, 31f);
        shattered_moon.getMarket().addCondition(Conditions.LOW_GRAVITY);
        shattered_moon.getMarket().addCondition(Conditions.NO_ATMOSPHERE);
        shattered_moon.getMarket().addCondition(Conditions.METEOR_IMPACTS);
        shattered_moon.setCustomDescriptionId("signalis_shattered_moon");

        system.addRingBand(vineta, "misc", "rings_asteroids0", 256f, 3, Color.gray, 256f, 740f, 305f, null, null);

        //Inner Jump Point related to Vineta
        JumpPointAPI innerJumpPoint = Global.getFactory().createJumpPoint("alatyr_jump_point", "Inner Jump Point");
        innerJumpPoint.setCircularOrbit(signalisStar, 266, 4200f, 296f);
        innerJumpPoint.setRelatedPlanet(vineta);
        system.addEntity(innerJumpPoint);
        
        //KITZEH - Arid World
        PlanetAPI kitezh = system.addPlanet("signalis_kitezh", signalisStar, "Kitezh", "arid", 310f, 120f, 8000f, 687f);
        ProcgenUsedNames.notifyUsed("Kitezh");
        kitezh.getMarket().addCondition(Conditions.HABITABLE);
        kitezh.getMarket().addCondition(Conditions.FARMLAND_POOR);
        kitezh.getMarket().addCondition(Conditions.ORE_MODERATE);
        kitezh.getMarket().addCondition(Conditions.RARE_ORE_SPARSE);
        kitezh.getMarket().addCondition(Conditions.LOW_GRAVITY);
        kitezh.setCustomDescriptionId("signalis_kitezh_planet");

        //-AsteroidBelt- //starsector-core\graphics\planets
        system.addAsteroidBelt(signalisStar, 1000, 10000f, 256f, 300f, 350f, Terrain.ASTEROID_BELT, "Asteroid Ring");
        system.addRingBand(signalisStar, "misc", "rings_dust0", 256f, 0, Color.gray, 256f, 10200f, 400f);
        system.addRingBand(signalisStar, "misc", "rings_asteroids0", 256f, 0, Color.gray, 256f, 10000f, 400f);
        system.addRingBand(signalisStar, "misc", "rings_dust0", 256f, 2, Color.gray, 256f, 9800f, 400f);

        //GAS GIANT 1, DUST RINGS AND ROTFRONT
        PlanetAPI gas_giant1 = system.addPlanet("signalis_gas_giant1", signalisStar, "Jove", "gas_giant", 234f, 400f, 12500f, 4330f);
        gas_giant1.getMarket().addCondition(Conditions.DENSE_ATMOSPHERE);
        gas_giant1.getMarket().addCondition(Conditions.VOLATILES_DIFFUSE);
        gas_giant1.getMarket().addCondition(Conditions.HIGH_GRAVITY);
        gas_giant1.setCustomDescriptionId("signalis_gas_giant1_planet");

        PlanetAPI rotfront = system.addPlanet("signalis_rotfront", gas_giant1, "Rotfront", "tundra", 0f, 90f, 800f, 22f);
        rotfront.getMarket().addCondition(Conditions.HABITABLE);
        rotfront.getMarket().addCondition(Conditions.COLD);
        rotfront.getMarket().addCondition(Conditions.FARMLAND_POOR);
        rotfront.getMarket().addCondition(Conditions.ORGANICS_COMMON);
        rotfront.getMarket().addCondition(Conditions.ORE_MODERATE);
        rotfront.getMarket().addCondition(Conditions.LOW_GRAVITY);
        rotfront.setCustomDescriptionId("signalis_rotfront_planet");

        system.addRingBand(gas_giant1, "misc", "rings_dust0", 256f, 2, Color.white, 256f, 1000f, 300f);

        
        //-GAS GIANT 2, PRETTY RINGS AND HEIMAT-
        PlanetAPI gas_giant2 = system.addPlanet("signalis_gas_giant2", signalisStar, "Saturnus", "gas_giant", 90f, 370f, 14500f, 1076f);
        gas_giant2.getMarket().addCondition(Conditions.DENSE_ATMOSPHERE);
        gas_giant2.getMarket().addCondition(Conditions.VOLATILES_DIFFUSE);
        gas_giant2.getMarket().addCondition(Conditions.HIGH_GRAVITY);
        gas_giant2.setCustomDescriptionId("signalis_gas_giant2_planet");

        PlanetAPI heimat = system.addPlanet("signalis_heimat", gas_giant2, "Heimat", "toxic", 50f, 90f, 1100f, 56f);
        heimat.getMarket().addCondition(Conditions.ORGANICS_PLENTIFUL);
        heimat.getMarket().addCondition(Conditions.VOLATILES_DIFFUSE);
        heimat.getMarket().addCondition(Conditions.ORE_SPARSE);
        heimat.getMarket().addCondition(Conditions.RARE_ORE_SPARSE);
        heimat.getMarket().addCondition(Conditions.LOW_GRAVITY);
        heimat.getMarket().addCondition(Conditions.TOXIC_ATMOSPHERE);
        heimat.setCustomDescriptionId("signalis_heimat_planet");

        system.addRingBand(gas_giant2, "misc", "rings_special0", 256f, 0, Color.white, 256f, 500f, 60f);
        system.addRingBand(gas_giant2, "misc", "rings_special0", 256f, 1, Color.white, 256f, 700f, 60f);

        //LENG
        PlanetAPI leng = system.addPlanet("signalis_leng", signalisStar, "Leng", "barren", 180, 80f, 16400f, 3050f);
        ProcgenUsedNames.notifyUsed("Leng");
        leng.getMarket().addCondition(Conditions.COLD);
        leng.getMarket().addCondition(Conditions.LOW_GRAVITY);
        leng.getMarket().addCondition(Conditions.ORE_ABUNDANT);
        leng.getMarket().addCondition(Conditions.NO_ATMOSPHERE);
        leng.getMarket().addCondition(Conditions.RARE_ORE_MODERATE);
        leng.setCustomDescriptionId("signalis_leng_planet");
    
        cleanup(system);
    }

    //Jump point generator
    void cleanup(StarSystemAPI system) {
        system.autogenerateHyperspaceJumpPoints(true, true);

        HyperspaceTerrainPlugin plugin = (HyperspaceTerrainPlugin) Misc.getHyperspaceTerrain().getPlugin();
        NebulaEditor editor = new NebulaEditor(plugin);
        float minRadius = plugin.getTileSize() * 2f;

        float radius = system.getMaxRadiusInHyperspace();
        editor.clearArc(system.getLocation().x, system.getLocation().y, 0, radius + minRadius * 0.5f, 0, 360f);
        editor.clearArc(system.getLocation().x, system.getLocation().y, 0, radius + minRadius, 0, 360f, 0.25f);
    }

}
