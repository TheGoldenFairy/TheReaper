package Reaper.Cards;

import Reaper.Patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;
import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class CurseofDeath extends CustomCard {
    public static final String CARD_ID = "reaper:CurseofDeath";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(CARD_ID);
    public static final String IMG = "TheReaperResource/images/cards/attacks/Beta.png";
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String POWER = "reaper:MarkofDeath";

    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = AbstractCardEnum.COLOR_PURPLE;

    private static final int COST = 2;
    private static final int DAMAGE = 12;
    private static final int UPGRADE_PLUS_DMG = 4;

    public CurseofDeath() {
        super(CARD_ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);

        baseDamage = DAMAGE;
        tags.add(BaseModCardTags.BASIC_STRIKE);
        tags.add(CardTags.STRIKE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        if(m.hasPower(POWER)){
            AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        }
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
