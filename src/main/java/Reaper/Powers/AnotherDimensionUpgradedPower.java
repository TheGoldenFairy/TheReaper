package Reaper.Powers;

import Reaper.Reaper;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;

public class AnotherDimensionUpgradedPower extends AbstractPower {
    private static final String POWER_ID = "reaper:AnotherDimensionUpgradedPower";
    private static final String IMG = "powers/BetaPower.png";
    private PowerStrings strings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final int DEX_AMT = -1;
    private static final int AD_AMT = 1;

    public AnotherDimensionUpgradedPower(AbstractCreature owner, int amount) {
        this.name = strings.NAME;
        this.ID = POWER_ID;
        this.description = strings.DESCRIPTIONS[0];
        this.owner = owner;
        this.img = new Texture(Reaper.getResourcePath(IMG));
        this.amount = amount;
        this.updateDescription();
        this.type = AbstractPower.PowerType.DEBUFF;
        this.isTurnBased = true;
    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        if (amount >= 999) {
            amount = 999;
        }
    }

    @Override
    public void atStartOfTurnPostDraw() {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DarkDimension(AbstractDungeon.player, AD_AMT), AD_AMT));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DexterityPower(AbstractDungeon.player, DEX_AMT), DEX_AMT));
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
