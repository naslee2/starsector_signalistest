package data.scripts;

import com.fs.starfarer.api.BaseModPlugin;
import com.fs.starfarer.api.Global;
import data.scripts.world.SignalisModGen;

public class signalistest extends BaseModPlugin{
    private static void initMyMod() {
        new SignalisModGen().generate(Global.getSector());
    }

    @Override
    public void onNewGame() {
        Global.getLogger(this.getClass()).info("signalistest is now active!");
        initMyMod();
    }
}
