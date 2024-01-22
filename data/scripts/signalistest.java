package data.scripts;
import data.scripts.world.SignalisModGen;

import com.fs.starfarer.api.BaseModPlugin;
import com.fs.starfarer.api.Global;

public class signalistest extends BaseModPlugin{

    private static void initMyMod(){
        new SignalisModGen().generate(Global.getSector());
    }

    @Override
    public void onNewGame() {
        Global.getLogger(this.getClass()).info("signalistest is now active!");
        initMyMod();
    }

}
