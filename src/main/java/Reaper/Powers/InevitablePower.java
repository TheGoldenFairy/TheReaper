package Reaper.Powers;

import Reaper.Reaper;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnCardDrawPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class InevitablePower extends AbstractPower implements OnCardDrawPower{
    private static final String POWER_ID = "reaper:InevitablePower";
    private static final String IMG = "powers/BetaPower.png";
    private PowerStrings strings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public final String[] DESCRIPTIONS = strings.DESCRIPTIONS;

    public InevitablePower(AbstractCreature owner, int amount) {
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
    public void onCardDraw(AbstractCard abstractCard) {
        AbstractDungeon.actionManager.addToBottom(new DamageRandomEnemyAction(new DamageInfo(AbstractDungeon.player, amount, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.NONE));
    }

    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}
