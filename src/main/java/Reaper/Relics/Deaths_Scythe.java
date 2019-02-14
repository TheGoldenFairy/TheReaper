package reaper.Relics;

import reaper.Powers.MarkOfDeath;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class Deaths_Scythe extends AbstractReaperRelic {

    private final static String ID = "reaper:DeathsScythe";
    private final static AbstractRelic.RelicTier TIER = RelicTier.UNCOMMON;
    private final static String IMG = "relics/TestRelic.png";
    private final static AbstractRelic.LandingSound SOUND = AbstractRelic.LandingSound.MAGICAL;

    private AbstractPlayer p = AbstractDungeon.player;
    private final static int AMT = 1;

    public Deaths_Scythe() {
        super(ID, IMG, TIER, SOUND);
    }

    @Override
    public void atBattleStart() {
        flash();
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (!mo.isDeadOrEscaped()) {
                AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, p, new MarkOfDeath(mo, p, AMT), AMT));
            }
        }
    }


    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {return new Deaths_Scythe();}

}
