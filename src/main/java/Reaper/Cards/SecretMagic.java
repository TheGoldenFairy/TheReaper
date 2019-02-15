package Reaper.Cards;

import Reaper.Patches.AbstractCardEnum;
import Reaper.Powers.DarkArts;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SecretMagic extends CustomCard {
    public static final String CARD_ID = "reaper:SecretRose";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(CARD_ID);
    public static final String IMG = "TheReaperResource/images/cards/attacks/Beta.png";
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.NONE;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = AbstractCardEnum.COLOR_PURPLE;

    private static final int COST = -2;
    private static final int AMT = 5;
    private static final int UPGRADE_AMT = 2;

    public SecretMagic() {
        super(CARD_ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = AMT;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {}

    public boolean canUse(AbstractPlayer p, AbstractMonster m)
    {
        cantUseMessage = EXTENDED_DESCRIPTION[0];
        return false;
    }

    public void triggerOnManualDiscard() {
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (!mo.isDeadOrEscaped()) {
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, AbstractDungeon.player, new DarkArts(mo, AbstractDungeon.player, magicNumber), magicNumber));
            }
        }
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_AMT);
            initializeDescription();
        }
    }
}
