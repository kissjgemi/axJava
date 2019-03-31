package animals02;

/**
 *
 * @author KissJGabi
 */
public class Cat extends Animal {

    private boolean hasContainer;

    public boolean isHasContainer() {
        return hasContainer;
    }

    public void setHasContainer(boolean hasContainer) {
        this.hasContainer = hasContainer;
    }

    public Cat(String name, int birthYear, boolean hasContainer) {
        super(name, birthYear);
        this.hasContainer = hasContainer;
    }

    @Override
    public void takePoints(int beauty, int behavior) {
        if (hasContainer) {
            super.takePoints(beauty, behavior);
        }
    }
}
