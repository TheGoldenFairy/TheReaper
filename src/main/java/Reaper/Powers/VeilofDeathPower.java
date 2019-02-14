package Reaper.Powers;

import Reaper.Reaper;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ThornsPower;

public class VeilofDeathPower extends AbstractPower {
    private static final String POWER_ID = "reaper:VeilofDeathPower";
    private static final String IMG = "powers/BetaPower.png";
    private PowerStrings strings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);

    public VeilofDeathPower(AbstractCreature owner, int amount) {
        this.name = strings.NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        description = (strings.DESCRIPTIONS[0] + amount + strings.DESCRIPTIONS[1]);
        this.img = new Texture(Reaper.getResourcePath(IMG));
        this.amount = amount;
        this.updateDescription();
        this.type = AbstractPower.PowerType.BUFF;
        this.isTurnBased = true;
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new ThornsPower(AbstractDungeon.player, amount), amount));
        return damageAmount;
    }

    @Override
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
                description = (strings.DESCRIPTIONS[0] + amount + strings.DESCRIPTIONS[1]);
            }
        }
    }
}
