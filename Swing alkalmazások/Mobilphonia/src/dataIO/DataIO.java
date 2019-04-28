package dataIO;

import basis.Gadget;
import basis.Human;
import java.util.List;

/**
 *
 * @author KissJGabi
 */
public interface DataIO {

    public List<Human> humanList() throws Exception;

    public List<Gadget> gadgetList() throws Exception;
}
