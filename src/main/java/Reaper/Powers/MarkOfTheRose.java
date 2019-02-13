package Reaper.Powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import Reaper.Reaper;

public class MarkOfTheRose extends AbstractPower {
    private static final String POWER_ID = "reaper:MarkoftheRose";
    private static final String IMG = "powers/BetaPower.png";
    private PowerStrings strings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static int AMT = 1;
    private boolean justApplied = false;

    public MarkOfTheRose(AbstractCreature owner, int AMOUNT, boolean isSourceMonster) {
        this.name = strings.NAME;
        this.ID = POWER_ID;
        this.description = strings.DESCRIPTIONS[0];
        this.owner = owner;
        this.img = new Texture(Reaper.getResourcePath(IMG));
        this.amount = AMOUNT;
        this.updateDescription();
        this.type = AbstractPower.PowerType.DEBUFF;
        if ((AbstractDungeon.actionManager.turnHasEnded) && (isSourceMonster)) {
            this.justApplied = true;
        }
        this.isTurnBased = true;
    }

    @Override
    public void atEndOfRound()
    {
        if (this.justApplied) {
            this.justApplied = false;
            return;
        }
        if (this.amount == 0) {
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
        }
        else {
            AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, POWER_ID, 1));
        }
    }


    @Override
    public void stackPower(int stackAmount) {
        this.amount += stackAmount;
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
    public int onAttacked(DamageInfo info, int damageAmount) {
        AbstractPlayer p = com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;
        if((info.owner != null) && (info.type != DamageInfo.DamageType.HP_LOSS) && (info.type != DamageInfo.DamageType.THORNS)) {
            if(p.currentHealth > 0) { p.heal(AMT); }
        }
        return damageAmount;
    }
}
