package reaper.Relics;

import reaper.Powers.MarkOfTheRose;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class Roses_Mark extends AbstractReaperRelic {
    private final static String ID = "reaper:RosesMark";
    private final static RelicTier TIER = RelicTier.RARE;
    private final static String IMG = "relics/TestRelic.png";
    private final static LandingSound SOUND = LandingSound.MAGICAL;

    public Roses_Mark() {
            super(ID, IMG, TIER, SOUND);
    }

    @Override
    public String getUpdatedDescription() { return DESCRIPTIONS[0]; }

    @Override
    public void onEquip() {
        MarkOfTheRose.AMT = 2;
    }

    @Override
    public AbstractRelic makeCopy() {return new Roses_Mark();}
}
