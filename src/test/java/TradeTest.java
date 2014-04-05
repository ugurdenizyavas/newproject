import com.deniz.newstock.piece.business.exception.*;
import com.deniz.newstock.piece.persistence.entity.OwnerEntity;
import com.deniz.newstock.piece.persistence.entity.TransactionEntity;
import junit.framework.Assert;
import org.junit.Test;

import java.util.List;

/**
 * @author: TRYavasU
 */
public class TradeTest extends ProjectTest {

    /*
     * Owner1 has piece1, piece2, piece3            and has 0 gold
     * Owner2 has piece4, piece5, piece6, piece7    and has 0 gold
     * Owner3 has piece8, piece9, piece10           and has 50 gold
     * Owner4 has no pieces                         and has 100 gold
     *
     * Owner2 wants to sell piece4 but it is not his
     * Owner1 wants to sell piece3 but it is a too lower offer
     */
    @Test
    public void shouldFailToSellInTheseCases() {
        try {
            pieceBusinessService.sell(owner1.getId(), piece4.getId(), 5d);
            Assert.fail();
        } catch (DontBeCheapException e) {
            Assert.fail();
        } catch (ItsNotYoursException e) {
        }

        Assert.assertEquals(ownerBusinessService.getById(owner1.getId()).getMoney(), 0d);
        Assert.assertEquals(pieceBusinessService.getById(piece4.getId()).getValue(), 0d);

        try {
            pieceBusinessService.sell(owner1.getId(), piece3.getId(), 0.5d);
            Assert.fail();
        } catch (DontBeCheapException e) {
        } catch (ItsNotYoursException e) {
            Assert.fail();
        }
        Assert.assertEquals(ownerBusinessService.getById(owner1.getId()).getMoney(), 0d);
        Assert.assertEquals(pieceBusinessService.getById(piece3.getId()).getValue(), 0d);

        Assert.assertTrue(true);
    }

    /*
     * Owner1 has piece1, piece2, piece3            and has 0 gold
     * Owner2 has piece4, piece5, piece6, piece7    and has 0 gold
     * Owner3 has piece8, piece9, piece10           and has 50 gold
     * Owner4 has no pieces                         and has 100 gold
     *
     * Owner1 sells piece3 for 5 gold
     * Owner1 updates to sell piece3 for 6 gold
     */
    @Test
    public void shouldSell() {
        try {
            pieceBusinessService.sell(owner1.getId(), piece3.getId(), 5d);
        } catch (DontBeCheapException e) {
            Assert.fail();
        } catch (ItsNotYoursException e) {
            Assert.fail();
        }

        Assert.assertEquals(ownerBusinessService.getById(owner1.getId()).getMoney(), 0d);
        Assert.assertEquals(pieceBusinessService.getById(piece3.getId()).getValue(), 5d);

        try {
            pieceBusinessService.sell(owner1.getId(), piece3.getId(), 6d);
        } catch (DontBeCheapException e) {
            Assert.fail();
        } catch (ItsNotYoursException e) {
            Assert.fail();
        }

        Assert.assertEquals(ownerBusinessService.getById(owner1.getId()).getMoney(), 0d);
        Assert.assertEquals(pieceBusinessService.getById(piece3.getId()).getValue(), 6d);
    }


    /*
     * Owner1 has piece1, piece2, piece3            and has 0 gold
     * Owner2 has piece4, piece5, piece6, piece7    and has 0 gold
     * Owner3 has piece8, piece9, piece10           and has 50 gold
     * Owner4 has no pieces                         and has 100 gold
     *
     * Owner1 sells piece3 for 5 gold
     *
     * Owner1 can't buy piece3 since it is his own piece
     * Owner2 can't buy piece3 since he doesn't have enough gold
     * Owner3 can't buy piece4 since piece4 is not for sale
     */
    @Test
    public void shouldFailToBuyInTheseCases() {
        try {
            pieceBusinessService.sell(owner1.getId(), piece3.getId(), 5d);
        } catch (DontBeCheapException e) {
            Assert.fail();
        } catch (ItsNotYoursException e) {
            Assert.fail();
        }

        Assert.assertEquals(ownerBusinessService.getById(owner1.getId()).getMoney(), 0d);
        Assert.assertEquals(pieceBusinessService.getById(piece3.getId()).getValue(), 5d);

        try {
            pieceBusinessService.buy(owner1.getId(), piece3.getId());
            Assert.fail();
        } catch (ItsYourPieceException e) {
        } catch (YouArePoorException e) {
            Assert.fail();
        } catch (NotForSaleException e) {
            Assert.fail();
        }

        try {
            pieceBusinessService.buy(owner2.getId(), piece3.getId());
            Assert.fail();
        } catch (ItsYourPieceException e) {
            Assert.fail();
        } catch (YouArePoorException e) {
        } catch (NotForSaleException e) {
            Assert.fail();
        }

        try {
            pieceBusinessService.buy(owner3.getId(), piece4.getId());
            Assert.fail();
        } catch (ItsYourPieceException e) {
            Assert.fail();
        } catch (YouArePoorException e) {
            Assert.fail();
        } catch (NotForSaleException e) {
        }
    }


    /*
     * Owner1 has piece1, piece2, piece3            and has 0 gold
     * Owner2 has piece4, piece5, piece6, piece7    and has 0 gold
     * Owner3 has piece8, piece9, piece10           and has 50 gold
     * Owner4 has no pieces                         and has 100 gold
     *
     * Owner1 sells piece3 for 5 gold
     *
     * Owner3 buys piece3
     *
     * Owner1 has piece1, piece2                    and has 5 gold
     * Owner3 has piece8, piece9, piece10, piece3   and has 45 gold
     */
    @Test
    public void shouldBuyOpenSale() {
        Long sellerId = owner1.getId();
        Long pieceId = piece3.getId();
        try {
            pieceBusinessService.sell(sellerId, pieceId, 5d);
        } catch (DontBeCheapException e) {
            Assert.fail();
        } catch (ItsNotYoursException e) {
            Assert.fail();
        }

        Assert.assertEquals(ownerBusinessService.getById(sellerId).getMoney(), 0d);
        Assert.assertEquals(pieceBusinessService.getById(pieceId).getValue(), 5d);

        try {
            pieceBusinessService.buy(owner3.getId(), pieceId);
        } catch (ItsYourPieceException e) {
            Assert.fail();
        } catch (YouArePoorException e) {
            Assert.fail();
        } catch (NotForSaleException e) {
            Assert.fail();
        }

        Assert.assertEquals(ownerBusinessService.getById(sellerId).getMoney(), 5d);
        OwnerEntity buyer = pieceBusinessService.getOwner(pieceId);
        Assert.assertEquals(buyer.getId(), owner3.getId());
        Assert.assertEquals(buyer.getMoney(), 45d);
        Assert.assertEquals(pieceBusinessService.getById(pieceId).getValue(), 0d);

        Assert.assertEquals(transactionBusinessService.getCount(), 1);
        TransactionEntity transaction = transactionBusinessService.getAll().get(0);
        Assert.assertEquals(transaction.getValue(), 5d);
        Assert.assertEquals(transaction.getSeller().getId(), sellerId);
        Assert.assertEquals(transaction.getBuyer().getId(), buyer.getId());
        Assert.assertEquals(transaction.getPiece().getId(), pieceId);
    }
}
