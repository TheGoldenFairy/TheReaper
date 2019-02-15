package Reaper.Powers;

import Reaper.Reaper;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;

public class DevilsDefensePower extends AbstractPower {
    private static final String POWER_ID = "reaper:DevilsDefensePower";
    private static final String IMG = "powers/BetaPower.png";
    private PowerStrings strings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public final String[] DESCRIPTIONS = strings.DESCRIPTIONS;

    private boolean ActivatePower = true;

    public DevilsDefensePower(AbstractCreature owner, int amount) {
        this.name = strings.NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.img = new Texture(Reaper.getResourcePath(IMG));
        this.amount = amount;
        this.type = PowerType.BUFF;
        this.isTurnBased = true;
        this.updateDescription();
    }

    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        if (amount >= 999) {
            amount = 999;
        }
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if(card.type == AbstractCard.CardType.ATTACK) {
            ActivatePower = false;
        }
    }

    @Override
    public void atStartOfTurn() {
        if(ActivatePower) {
            flash();
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(owner, owner, new DexterityPower(owner, amount), amount));
        }
        ActivatePower = true;
    }

    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}
