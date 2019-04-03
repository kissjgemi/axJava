package basis;

import datas.M;

/**
 *
 * @author KissJGabi
 */
public class MedievalStudent extends Student {

    private int debateSkills;
    private static double debateMultiplier;

    public int getDebateSkills() {
        return debateSkills;
    }

    public static double getDebateMultiplier() {
        return debateMultiplier;
    }

    public static void setDebateMultiplier(double debateMultiplier) {
        MedievalStudent.debateMultiplier = debateMultiplier;
    }

    public MedievalStudent(String ID) {
        super(ID);
    }

    @Override
    public void hasLesson() {
        debateSkills++;
    }

    @Override
    public String className() {
        return M.medievalStudent_className();
    }

    public int debateComponent() {
        return (int) (this.debateSkills * MedievalStudent.debateMultiplier);
    }

    @Override
    public int knowledgeValue() {
        return super.knowledgeValue() + debateComponent();
    }
}
