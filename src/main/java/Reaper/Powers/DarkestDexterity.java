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

public class DarkestDexterity extends AbstractPower {
    private static final String POWER_ID = "reaper:DarkestDexterity";
    private static final String IMG = "powers/BetaPower.png";
    private PowerStrings strings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);

    public DarkestDexterity(AbstractCreature owner, int amount) {
        this.name = strings.NAME;
        this.ID = POWER_ID;
        description = (strings.DESCRIPTIONS[0] + amount + strings.DESCRIPTIONS[1]);
        this.owner = owner;
        this.img = new Texture(Reaper.getResourcePath(IMG));
        this.amount = amount;
        this.updateDescription();
        this.type = PowerType.BUFF;
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
        flash();
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DexterityPower(AbstractDungeon.player, -amount), -amount));
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
