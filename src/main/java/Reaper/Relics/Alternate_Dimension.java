package reaper.Relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import reaper.Powers.DarkDimension;

public class Alternate_Dimension extends AbstractReaperRelic {

    private final static String ID = "reaper:AlternateDimension";
    private final static AbstractRelic.RelicTier TIER = AbstractRelic.RelicTier.RARE;
    private final static String IMG = "relics/TestRelic.png";
    private final static AbstractRelic.LandingSound SOUND = AbstractRelic.LandingSound.MAGICAL;

    private AbstractPlayer p = AbstractDungeon.player;
    private final static int AMT = 1;

    public Alternate_Dimension() {
        super(ID, IMG, TIER, SOUND);
    }

    @Override
    public void atBattleStart() {
        flash();
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DarkDimension(p, AMT), AMT));
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {return new Alternate_Dimension();}

}
