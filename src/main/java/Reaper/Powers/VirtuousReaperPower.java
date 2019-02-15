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
import com.megacrit.cardcrawl.powers.PoisonPower;

public class VirtuousReaperPower extends AbstractPower{
        private static final String POWER_ID = "reaper:VirtuousReaperPower";
        private static final String IMG = "powers/BetaPower.png";
        private PowerStrings strings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
        public final String[] DESCRIPTIONS = strings.DESCRIPTIONS;

        public VirtuousReaperPower(AbstractCreature owner, int amount) {
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
        public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
            if (damageAmount > 0 && target != owner && info.type == DamageInfo.DamageType.NORMAL) {
                flash();
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(target, owner, new DarkArts(target, owner, amount), amount, true));
            }
        }

        public void updateDescription() {
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
        }
}
