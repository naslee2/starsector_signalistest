package data.scripts;
import data.scripts.world.SignalisModGen;

import com.fs.starfarer.api.BaseModPlugin;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.impl.campaign.shared.SharedData;
import exerelin.campaign.SectorManager;


public class signalistest extends BaseModPlugin{
    //private static void initMyMod() {
    //    new SignalisModGen().generate(Global.getSector());


    //}

    private static void initMyMod(){
        boolean haveNexerelin = Global.getSettings().getModManager().isModEnabled("nexerelin");
        if (!haveNexerelin || SectorManager.getManager().isCorvusMode()){
            new SignalisModGen().generate(Global.getSector());
        }
        SharedData.getData().getPersonBountyEventData().addParticipatingFaction("signalis_eusan_nation");
    }

    @Override
    public void onNewGame() {
        Global.getLogger(this.getClass()).info("signalistest is now active!");
        initMyMod();
        initPluginChecks();
    }

    public static void initPluginChecks(){
        boolean lazyLibPresent = Global.getSettings().getModManager().isModEnabled("lw_lazylib");

        if (!lazyLibPresent){
            throw new RuntimeException("This mod requires LazyLib" + "\nGet it at http://fractalsoftworks.com/forum/index.php?topic=5444");
        }

    }

}
