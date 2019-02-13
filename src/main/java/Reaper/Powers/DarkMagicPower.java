package Reaper.Powers;

import Reaper.Reaper;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class DarkMagicPower extends AbstractPower {
    private static final String POWER_ID = "reaper:DarkMagicPower";
    private static final String IMG = "powers/BetaPower.png";
    private PowerStrings strings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public final String[] DESCRIPTIONS = strings.DESCRIPTIONS;


    public DarkMagicPower(AbstractCreature owner, int AMT) {
        this.name = strings.NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.img = new Texture(Reaper.getResourcePath(IMG));
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
        this.amount = AMT;
        this.updateDescription();
        this.type = AbstractPower.PowerType.BUFF;
    }
    public void atStartOfTurnPostDraw() {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.flash();
            for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, AbstractDungeon.player, new DarkArts(mo, AbstractDungeon.player, amount), amount));
            }
        }
    }

    @Override
    public void updateDescription() {
        if (amount >= 1) {
            {
                description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
            }
        }
    }
}
