package datas;

/**
 *
 * @author KissJGabi
 */
public class M {

    public static String[] artBranches = {"operaénekes", "festőművész",
        "építész", "orgonaművész", "hegedűművész", "zongorista"};

    public static String artist_className() {
        return "művész";
    }

    public static String celebrity_classname() {
        return "RTL klub sztár";
    }

    public static String celebrity_nop() {
        return "Nincsensk celebek";
    }

    public static String artist_nop() {
        return "Nincsenek művészek";
    }

    public static String artist_notPresent() {
        return "Nincs ilyen a művészek között";
    }

    public static String main_exeption_lookingFor() {
        return "A megadott adat alapján nem tudok keresni...";
    }

    public static String main_ask_lookingFor() {
        return "\nKeressek még valakit? (i/n)";
    }

    public static String main_lookingFor() {
        return "Keresés művészeti ág szerint";
    }

    public static String main_ordering() {
        return "A szereplők IQ szerinti sorrendje:";
    }

    public static String main_format_artistLessPayment() {
        return "A legkevesebbet (%6d Ft-ot) kereső művész(ek):";
    }

    public static String main_format_celebMostPayment() {
        return "A legtöbbet (%6d Ft-ot) kereső celeb(ek):";
    }

    public static String main_equals_artistVsCeleb() {
        return "A két csoportról egyforma számú hír jelent meg.";
    }

    public static String main_artist() {
        return "A művészekről szólt több híradás.";
    }

    public static String main_celeb() {
        return "A celebek kapták a nagyobb hírverést.";
    }

    public static String main_statistics() {
        return "statisztikák";
    }

    public static String main_noArticle_observation() {
        return "Nem jelent meg cikk";
    }

    public static String main_format_observation() {
        return "A %d napig figyeljük az újságzikkeket";
    }

    public static String main_members() {
        return "A beválogatottak névsora";
    }

    public static String main_casting() {
        return "A jelentkezők kiválasztása...";
    }

    public static String main_readFile() {
        return "A jelentkezők adatainak beolvasása...";
    }

    public static String main_file_error() {
        return "\tHiba a fájlműveletek során!";
    }

    public static String main_exeption_casting() {
        return "\tHibás azonosító az adatok között: ";
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
        return "Sztár hírek";
    }

    public static String main_datasource() {
        return "/datas/sztarok.txt";
    }

    public static String main_dataresults() {
        return "results/results.txt";
    }

    public M() {

    }
}
