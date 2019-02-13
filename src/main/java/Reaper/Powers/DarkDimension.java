package Reaper.Powers;

import Reaper.Reaper;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class DarkDimension extends AbstractPower {
    private static final String POWER_ID = "reaper:DarkArts";
    private static final String IMG = "powers/BetaPower.png";
    private PowerStrings strings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final float ResistAMT = 0.6F;
    private float AMT;
    private int dmg;

    public DarkDimension(AbstractCreature owner,  int amount) {
        this.name = strings.NAME;
        this.ID = POWER_ID;
        this.description = strings.DESCRIPTIONS[0];
        this.owner = owner;
        this.img = new Texture(Reaper.getResourcePath(IMG));
        this.amount = amount;
        this.updateDescription();
        this.type = PowerType.DEBUFF;
        this.isTurnBased = true;
    }

    @Override
    public void updateDescription() {
        if (amount >= 1) {
            {
                description = (strings.DESCRIPTIONS[0]);
            }
        }
    }

    @Override
    public void stackPower(int stackAmount) {
        this.amount += stackAmount;
        if (this.amount <= 0) {
            AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, ID));
        }
    }

    @Override
    public void atEndOfRound() {
        AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, ID));
    }

    @Override
    public float atDamageReceive(float damage, DamageInfo.DamageType damageType) {
        if(this.amount > 1 && this.amount <= 24) {
            AMT = ResistAMT + (0.02F * (amount));
        }
        else if (this.amount > 24) {
            AMT = 1.0f;
        }
        else {
            AMT = ResistAMT;
        }
        dmg = (int)(damage-(damage*AMT));
        return dmg;
    }
}
