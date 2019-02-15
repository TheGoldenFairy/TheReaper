package Reaper.Cards;

import Reaper.Patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Stab extends AbstractReaperCard {
    public static final String CARD_ID = "reaper:Stab";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(CARD_ID);
    public static final String IMG = "TheReaperResource/images/cards/attacks/Beta.png";
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;

    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = AbstractCardEnum.COLOR_PURPLE;

    private static final int COST = 0;
    private static final int DAMAGE = 5;
    private static final int UPGRADE_PLUS_DMG = 2;
    private static final int AMT = 3;
    private static final int UPGRADE_AMT = 1;

    public Stab() {
        super(CARD_ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = AMT;
        baseDamage = DAMAGE;
    }

    @Override
    public void triggerWhenDrawn() {
        AbstractDungeon.actionManager.addToBottom(new DamageRandomEnemyAction(new DamageInfo(AbstractDungeon.player, AMT, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.NONE));
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            upgradeMagicNumber(UPGRADE_AMT);
            initializeDescription();
        }
    }
}
