package reaper.Relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;

import reaper.Reaper;

public abstract class AbstractReaperRelic extends CustomRelic {
    AbstractReaperRelic(String id, String img, RelicTier tier, LandingSound sfx) {
        super(id, new Texture(Reaper.getResourcePath(img)), tier, sfx);
    }
}
