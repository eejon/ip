public class Athena {
    /* Class Level Constants */
    private static final String LINE_BREAK = "\t____________________________________________________________\n";
    private static final String LOGO =
          "\t    ___  __  __                \n"
        + "\t   / _ |/ /_/ /  ___ ___  ___ _\n"
        + "\t  / __ / __/ _ \\/ -_) _ \\/ _ `/\n"
        + "\t /_/ |_\\__/_//_/\\__/_//_/\\_,_/ \n";

    private static void printGreeting() {
        StringBuilder sb = new StringBuilder(LINE_BREAK).append(LOGO).append(LINE_BREAK);
        sb.append("\n\t Greetings! I am Athena!\n\t What's on your mind?\n").append(LINE_BREAK);
        System.out.println(sb);
    }

    private static void printExit() {
        System.out.println("\t Until next time.");
        System.out.println(LINE_BREAK);
    }

    public static void main(String[] args) {
        // Greetings
        printGreeting();
        // Exit
        printExit();
    }
}
