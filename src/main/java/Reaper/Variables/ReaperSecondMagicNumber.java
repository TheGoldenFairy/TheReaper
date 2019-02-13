package Reaper.Variables;

import Reaper.Cards.AbstractReaperCard;
import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;

import static Reaper.Reaper.makeID;

public class ReaperSecondMagicNumber extends DynamicVariable {
    @Override
    public String key() {
        return makeID("SecondMagic");
    }

    @Override
    public boolean isModified(AbstractCard card) {
        return ((AbstractReaperCard) card).isReaperSecondMagicNumberModified;
    }

    @Override
    public int value(AbstractCard card) {
        return ((AbstractReaperCard) card).ReaperSecondMagicNumber;
    }

    @Override
    public int baseValue(AbstractCard card) {
        return ((AbstractReaperCard) card).ReaperBaseSecondMagicNumber;
    }

    @Override
    public boolean upgraded(AbstractCard card) {
        return ((AbstractReaperCard) card).upgradedReaperSecondMagicNumber;
    }
}
