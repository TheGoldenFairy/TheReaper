package Reaper.Cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public abstract class AbstractReaperCard extends CustomCard {

    public int ReaperSecondMagicNumber;
    public int ReaperBaseSecondMagicNumber;
    public boolean upgradedReaperSecondMagicNumber;
    public boolean isReaperSecondMagicNumberModified;

    public AbstractReaperCard(final String id, final String name, final String img, final int cost, final String rawDescription,
                               final CardType type, final CardColor color,
                               final CardRarity rarity, final CardTarget target) {
        super(id, name, img, cost, rawDescription, type, color, rarity, target);

        // Set all the things to their default values.
        isCostModified = false;
        isCostModifiedForTurn = false;
        isDamageModified = false;
        isBlockModified = false;
        isMagicNumberModified = false;
        isReaperSecondMagicNumberModified = false;
    }

    public void displayUpgrades() {

        if (upgradedReaperSecondMagicNumber) {
            ReaperSecondMagicNumber = ReaperBaseSecondMagicNumber;
            isReaperSecondMagicNumberModified = true;
        }

    }

    public void upgradeDefaultSecondMagicNumber(int amount) {
        ReaperBaseSecondMagicNumber += amount;
        ReaperSecondMagicNumber = ReaperBaseSecondMagicNumber;
        upgradedReaperSecondMagicNumber = true;
    }
}
