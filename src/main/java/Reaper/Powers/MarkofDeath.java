package Reaper.Powers;

import Reaper.Reaper;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class MarkofDeath extends AbstractPower {
    private static final String POWER_ID = "reaper:MarkofDeath";
    private static final String IMG = "powers/BetaPower.png";
    private PowerStrings strings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static int AMT = 10;
    private AbstractCreature source;

    public MarkofDeath(AbstractCreature owner, AbstractCreature source, int amount) {
        this.name = strings.NAME;
        this.ID = POWER_ID;
        this.description = strings.DESCRIPTIONS[0];
        this.owner = owner;
        this.img = new Texture(Reaper.getResourcePath(IMG));
        this.amount = amount;
        this.source = source;
        this.updateDescription();
        this.type = AbstractPower.PowerType.DEBUFF;
        this.isTurnBased = true;
    }

    @Override
    public void atStartOfTurn() {
        if(amount > 0) {
            if(amount > 1) {
                AMT = 10 + ((amount - 1) * 2 );
            }
            AbstractDungeon.actionManager.addToBottom(new DamageAction(this.owner, new DamageInfo(source, AMT, DamageInfo.DamageType.THORNS)));
        }
    }

    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        if (amount >= 999) {
            amount = 999;
        }
    }

    @Override
    public void updateDescription() {
        if (amount >= 1) {
            {
                description = (strings.DESCRIPTIONS[0]);
            }
        }
    }
}
