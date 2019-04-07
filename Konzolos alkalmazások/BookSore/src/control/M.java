package control;

/**
 *
 * @author KissJGabi
 */
public class M {

    public static String book_notPresent() {
        return "Nincs ilyen a könyvek között";
    }

    public static String main_ask_lookingFor() {
        return "\nKeressek tovább? (i/n)";
    }

    public static String main_lookingFor() {
        return "Keresés a könyv címe alapján";
    }

    public static String main_format_equalDownloads() {
        return "A könyvekből egyformán %ddb-ot töltöttek le illegálisan is,\n"
                + " valamint legálisan is.";
    }

    public static String main_format_moreHacking() {
        return "Több könyvet (%ddb) töltöttek le illegálisan,\n"
                + " mint legálisan (%ddb).";
    }

    public static String main_format_moreLegal() {
        return "Több könyvet (%ddb) töltöttek le legálisan,\n"
                + " mint illegálisan (%ddb).";
    }

    public static String main_downloads() {
        return "Letöltések";
    }

    public static String main_format_enoughMoney() {
        return "könyvét megvásárolták %d Forintért";
    }

    public static String main_format_notEnoughMoney() {
        return "megvásárlása nem sikerült %d áron";
    }

    public static String main_format_money() {
        return "%d forinttal jöttek vásárolni:";
    }

    public static String main_shopping() {
        return "Vásárlások szimulációja...";
    }

    public static String main_orderByAuthor() {
        return "Szerző szerint rendezve";
    }

    public static String main_exeption_loadDatas() {
        return "\tHibás azonosító az adatok között: ";
    }

    public static String main_loadDatas() {
        return "Az adatok ellenőrzése...";
    }

    public static String main_inventory() {
        return "Leltár";
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
        return "Könyvesbolt";
    }

    public static String main_datasource() {
        return "/datas/konyvek.txt";
    }

    public static String main_dataresults() {
        return "results/results.txt";
    }

    public M() {

    }
}
