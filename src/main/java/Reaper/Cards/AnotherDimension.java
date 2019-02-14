package Reaper.Cards;

import Reaper.Patches.AbstractCardEnum;
import Reaper.Powers.AnotherDimensionPower;
import Reaper.Powers.AnotherDimensionUpgradedPower;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class AnotherDimension extends CustomCard {
    public static final String CARD_ID = "reaper:AnotherDimension";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(CARD_ID);
    public static final String IMG = "TheReaperResource/images/cards/attacks/Beta.png";
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = AbstractCardEnum.COLOR_PURPLE;

    private static final int COST = 3;
    private static final int DEXTERITY_AMT = 2;
    private static final int UPGRADE_DEXTERITY_AMT = -1;
    private static final int AMT = 1;


    public AnotherDimension() {
        super(CARD_ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        baseMagicNumber = magicNumber = DEXTERITY_AMT;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if(!upgraded) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new AnotherDimensionPower(p, AMT), AMT));
        }
        else {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new AnotherDimensionUpgradedPower(p, AMT), AMT));
        }
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_DEXTERITY_AMT);
            initializeDescription();
        }
    }
}
