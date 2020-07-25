package Home;

public class Main2 {
    public static void main(String[] args) {
        String st = "1%1.1A1......2";

        String re = "((\\d){0,4} \\.? \\d)?";
        st.replaceAll("([^\\" + re + "])", "");
        System.out.println(st.replaceAll("([^\\" + re + "])", ""));
        double d = 0;
        try {
            d = Double.parseDouble(st);
        } catch (Exception e) {
            System.out.println(d);
        }
    }
}
