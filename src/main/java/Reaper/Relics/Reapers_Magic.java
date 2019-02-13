package Reaper.Relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import Reaper.Powers.DarkArts;

public class Reapers_Magic extends AbstractReaperRelic {
    public final static String ID = "reaper:ReapersMagic";
    private final static RelicTier TIER = RelicTier.STARTER;
    private final static String IMG = "relics/TestRelic.png";
    private final static LandingSound SOUND = LandingSound.MAGICAL;

    private final static int AMT = 5;

    public Reapers_Magic() {
        super(ID, IMG, TIER, SOUND);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void atBattleStart() {
        flash();
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (!mo.isDeadOrEscaped()) {
                AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, AbstractDungeon.player, new DarkArts(mo, AbstractDungeon.player, AMT), AMT));
            }
        }
    }

    @Override
    public AbstractRelic makeCopy() {return new Reapers_Magic();}
}
