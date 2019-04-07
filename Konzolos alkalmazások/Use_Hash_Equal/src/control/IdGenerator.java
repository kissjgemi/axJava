package control;

/**
 *
 * @author KissJGabi
 */
public class IdGenerator {

    private static final String SIGN_A = "Sign-A";
    private static final String SIGN_B = "Sign-B";

    private static IdGenerator copy = null;

//Musician.basis.idgenerator
    private IdGenerator() {
    }

    public static IdGenerator getInstance() {
        if (copy == null) {
            copy = new IdGenerator();
        }
        return copy;
    }

    private int nextID_A = 0;
    private int nextID_B = 0;

    public String getUniqueId(TestClass t) {
        String uniqueId = null;
        if (t instanceof ExtendedATest) {
            nextID_A++;
            uniqueId = SIGN_A + nextID_A;
        }
        if (t instanceof ExtendedBTest) {
            nextID_B++;
            uniqueId = SIGN_B + nextID_B;
        }
        return uniqueId;
    }
}
