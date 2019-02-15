package Reaper.Cards;

import Reaper.Patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SlyStrike extends CustomCard {
    public static final String CARD_ID = "reaper:SlyStrike";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(CARD_ID);
    public static final String IMG = "TheReaperResource/images/cards/attacks/Beta.png";
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.NONE;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = AbstractCardEnum.COLOR_PURPLE;

    private static final int COST = -2;
    private static final int DAMAGE = 7;
    private static final int UPGRADE_PLUS_DMG = 3;

    public SlyStrike() {
        super(CARD_ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {}

    public boolean canUse(AbstractPlayer p, AbstractMonster m)
    {
        cantUseMessage = EXTENDED_DESCRIPTION[0];
        return false;
    }

    public void triggerOnManualDiscard() {
        AbstractDungeon.actionManager.addToBottom(new DamageRandomEnemyAction(new DamageInfo(AbstractDungeon.player, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            initializeDescription();
        }
    }
}
