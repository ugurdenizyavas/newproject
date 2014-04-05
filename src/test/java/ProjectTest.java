import com.deniz.newstock.piece.business.CountryBusinessService;
import com.deniz.newstock.piece.business.TransactionBusinessService;
import com.deniz.newstock.piece.business.OwnerBusinessService;
import com.deniz.newstock.piece.business.PieceBusinessService;
import com.deniz.newstock.piece.persistence.entity.CountryEntity;
import com.deniz.newstock.piece.persistence.entity.OwnerEntity;
import com.deniz.newstock.piece.persistence.entity.PieceEntity;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author: TRYavasU
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring/com/deniz/newstock/0.spring.xml"})
public class ProjectTest extends AbstractJUnit4SpringContextTests {

    protected String VALID_COUNTRY = "USA";
    private CountryEntity country1;
    protected OwnerEntity owner1, owner2, owner3, owner4 = null;
    protected PieceEntity piece1, piece2, piece3, piece4, piece5, piece6, piece7, piece8, piece9, piece10;

    @Autowired
    protected CountryBusinessService countryBusinessService;

    @Autowired
    protected OwnerBusinessService ownerBusinessService;

    @Autowired
    protected PieceBusinessService pieceBusinessService;

    @Autowired
    protected TransactionBusinessService transactionBusinessService;

    @Before
    public void setUp() {
        country1 = createCountry(VALID_COUNTRY);

        owner1 = createOwner(0);
        owner2 = createOwner(0);
        owner3 = createOwner(50);
        owner4 = createOwner(100);

        piece1 = createPiece(owner1, country1, 1);
        piece2 = createPiece(owner1, country1, 2);
        piece3 = createPiece(owner1, country1, 3);
        piece4 = createPiece(owner2, country1, 4);
        piece5 = createPiece(owner2, country1, 5);
        piece6 = createPiece(owner2, country1, 6);
        piece7 = createPiece(owner2, country1, 7);
        piece8 = createPiece(owner3, country1, 8);
        piece9 = createPiece(owner3, country1, 9);
        piece10 = createPiece(owner3, country1, 10);

        Assert.assertEquals(1, countryBusinessService.getCount());
        Assert.assertEquals(4, ownerBusinessService.getCount());
        Assert.assertEquals(10, pieceBusinessService.getCount());
        Assert.assertEquals(0, transactionBusinessService.getCount());
    }

    private PieceEntity createPiece(OwnerEntity owner, CountryEntity country, int countryOrder) {
        PieceEntity piece = new PieceEntity(owner, country, countryOrder);
        pieceBusinessService.save(piece);
        return piece;
    }

    private CountryEntity createCountry(String countryName) {
        CountryEntity country = new CountryEntity(countryName);
        countryBusinessService.save(country);
        return country;
    }

    protected OwnerEntity createOwner(double money) {
        OwnerEntity owner = new OwnerEntity();
        owner.setMoney(money);
        ownerBusinessService.save(owner);
        return owner;
    }

    @After
    public void tearDown() {
        Assert.assertEquals(1, countryBusinessService.getCount());
        Assert.assertEquals(4, ownerBusinessService.getCount());
        Assert.assertEquals(10, pieceBusinessService.getCount());

        transactionBusinessService.deleteAll();
        pieceBusinessService.deleteAll();
        ownerBusinessService.deleteAll();
        countryBusinessService.deleteAll();

        Assert.assertEquals(0, ownerBusinessService.getCount());
        Assert.assertEquals(0, pieceBusinessService.getCount());
        Assert.assertEquals(0, transactionBusinessService.getCount());
        Assert.assertEquals(0, countryBusinessService.getCount());
    }
}
