package Reaper.Cards;

import Reaper.Patches.AbstractCardEnum;
import Reaper.Powers.DevilsDefensePower;
import Reaper.Powers.DevilsStrengthPower;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class DevilsDefense extends CustomCard {
    public static final String CARD_ID = "reaper:DevilsDefense";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(CARD_ID);
    public static final String IMG = "TheReaperResource/images/cards/skills/Beta.png";
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = AbstractCardEnum.COLOR_PURPLE;

    private static final int COST = 2;
    private static final int UPGRADE_COST = 1;
    private static final int AMT = 1;

    public DevilsDefense() {
        super(CARD_ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = AMT;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DevilsDefensePower(p, magicNumber), magicNumber));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(UPGRADE_COST);
            initializeDescription();
        }
    }
}
