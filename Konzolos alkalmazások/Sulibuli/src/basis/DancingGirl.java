package basis;

/**
 *
 * @author KissJGabi
 */
public class DancingGirl extends Dancer {
    
    private int numberOfVotes;

    public int getNumberOfVotes() {
        return numberOfVotes;
    }

    public DancingGirl(String name, int pocketMoney) {
        super(name, pocketMoney);
    }
    
    public void receiveVote(){
        numberOfVotes++;
    }

    @Override
    public String className() {
        return "lány";
    }

}
