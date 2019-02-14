package reaper.Powers;

import reaper.Reaper;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

public class DownSide extends AbstractPower {

    private static final String POWER_ID = "reaper:DownSide";
    private static final String IMG = "powers/BetaPower.png";
    private PowerStrings strings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public final String[] DESCRIPTIONS = strings.DESCRIPTIONS;
    private boolean isRandom;

    public DownSide(AbstractCreature owner, int amount, boolean isRandom) {
        this.isRandom = isRandom;
        this.name = strings.NAME;
        this.ID = POWER_ID;
        if (isRandom) {
            this.ID += "+";
        }
        this.owner = owner;
        this.img = new Texture(Reaper.getResourcePath(IMG));
        this.amount = amount;
        this.type = AbstractPower.PowerType.DEBUFF;
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
    public void atStartOfTurnPostDraw() {
        flash();
        AbstractDungeon.effectList.add(new FlashAtkImgEffect(owner.hb.cX, owner.hb.cY, AbstractGameAction.AttackEffect.NONE));
        AbstractDungeon.actionManager.addToBottom(new DiscardAction(AbstractDungeon.player, AbstractDungeon.player, amount, isRandom));
        AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, ID));
    }

    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
        if (isRandom) {
            description += DESCRIPTIONS[2];
        } else {
            description += DESCRIPTIONS[3];
            name += "+";
        }
    }
}
