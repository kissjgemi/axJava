package datas;

/**
 *
 * @author KissJGabi
 */
public class M {

    public static String format_toString() {
        return "%-20s TAJ-%s: ";
    }

    public static String independent_name() {
        return "nem függő";
    }

    public static String dependent_name() {
        return "függő";
    }

    public static String internet_format_name() {
        return "%-20s facebookot használt";
    }

    public static String alcohol_format_name() {
        return "%-20s megivott egy italt";
    }

    public static String internetdependency_name() {
        return "facebookfüggőség gyanúja";
    }

    public static String alcoholdependency_name() {
        return "alkohol-függőség gyanúja";
    }

    public static String main_format_alcoholVolume() {
        return "Az elfogyasztott italokban összesen %d cl alkohol volt";
    }

    public static String patient_notPresent() {
        return "Nincs ilyen a páciensek között";
    }

    public static String main_ask_lookingFor() {
        return "\nKeressek még valakit? (i/n)";
    }

    public static String main_lookingFor() {
        return "Keresés név vagy TAJ-szám alapján";
    }

    public static String main_statistic() {
        return "Statisztikák";
    }

    public static String main_format_internetUse() {
        return "Az internetet legtöbbet (%d alkalom) használó(k)";
    }

    public static String main_sorting() {
        return "A vizsgálatok eredménye névsorba rendezve";
    }

    public static String main_observation() {
        return "A páciensek követése";
    }

    public static String main_exeption_admission() {
        return "\tHibás azonosító az adatok között: ";
    }

    public static String main_admission() {
        return "A betegek adatainak az ellenőrzése...";
    }

    public static String main_members() {
        return "A páciensek névsora";
    }

    public static String main_readFile() {
        return "Az adatok beolvasása...";
    }

    public static String main_file_error() {
        return "\tHiba a fájlműveletek során!";
    }

    public static char main_char_head() {
        return '*';
    }

    public static char main_char_equalsign() {
        return '=';
    }

    public static char main_char_dotted() {
        return '.';
    }

    public static char main_char_line() {
        return '_';
    }

    public static String main_title() {
        return "Függőségi vizsgálatok";
    }

    public static String main_datasource() {
        return "/datas/paciensek.txt";
    }

    public static String main_dataresults() {
        return "results/results.txt";
    }

    public M() {

    }
}
