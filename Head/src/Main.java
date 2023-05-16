public class Main {
    public static void main(String[] args) {

    }
}

//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//
//public class Main {
//        /**
//     *This function replaces spaces in a string with underscores
//     * @param input - string to be processed
//     *               The input string is a string containing spaces
//     * @return output - processed string
//     */
//    public static String Space2Underscores(String input) {
//        if (input == null) {
//            return null;
//        }
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < input.length(); i++) {
//            char c = input.charAt(i);
//            if (c == ' ') {
//                sb.append('_');
//            } else {
//                sb.append(c);
//            }
//        }
//        return sb.toString();
//    }
//    public static void main(String[] args) {
//        try {
//            // Kết nối tới trang web chứa thông tin về thời kì
//            String url = "https://vi.wikipedia.org/wiki/Thời_kỳ_Bắc_thuộc_lần_thứ_hai";
//            Document document = Jsoup.connect(url).get();
//            //Lấy phần tử HTML chứa tên thời kì
//            Elements name = document.select("h1");
//            // Lấy phần tử HTML chứa thông tin về khoảng thời gian
//            Elements timePeriod = document.select("table.infobox tbody tr:nth-child(3) td");
//
//            // In ra nội dung của phần tử HTML
//            System.out.println("Thời kì: " + name.text());
//            System.out.println("Khoảng thời gian tồn tại: " + timePeriod.text());
//            //Lấy dữ liệu của bảng thông tin
//            Elements tables = document.select("table.infobox tbody");
//            // Lấy các hàng trong bảng thông tin
//            Elements rows = tables.select("tr");
//
//            // Lấy tên các vị hoàng đế trong bảng thông tin
//            System.out.println("Các vị vua:");
//            for (Element row : rows) {
//                if (row.hasClass("mergedrow")) {
//                    if (row.attr("style").equals("display:none;"))
//                        continue;
//                    String emperorName = row.select("td a").text();
//                    String period = row.select("th div").text();
//                    String href = row.select("td a").attr("href");
//                    String eventName = row.select("th div a").text();
//                    String eventTime = row.select("td").text();
//                    if ((emperorName == null || emperorName.isEmpty())&&(period == null || period.isEmpty())&&(href == null || href.isEmpty())&&(eventName == null || eventName.isEmpty())&&(eventTime == null || eventTime.isEmpty()))
//                        continue;
//                    // In ra tÊn vua, khoị thừa vua, và link İnternet của vua
//                    System.out.print(emperorName);
//                    System.out.print(period.substring(2));
//                    System.out.print("https://vi.wikipedia.org/" + href);
//                    System.out.println("https://vi.wikipedia.org/" + Space2Underscores(emperorName));
//                    System.out.print(eventTime + " " + eventName);
//
//                    System.out.println(" ");
//
//                }
//            }
////            // Lấy phần tử chứa các sự kiện, chính sách quan trọng
////        Element content = document.select("div.mw-parser-output").first();
////        Elements events = content.select("h3:containsOwn(Sự kiện) + ul > li");
////        Elements policies = content.select("h3:containsOwn(Chính sách) + ul > li");
////
////        // In ra các sự kiện, chính sách quan trọng
////        System.out.println("Các sự kiện quan trọng:");
////        for (Element event : events) {
////            System.out.println("- " + event.text());
////        }
////
////        System.out.println("Các chính sách quan trọng:");
////        for (Element policy : policies) {
////            System.out.println("- " + policy.text());
////        }
////        }
////
//            } catch(Exception e){
//                e.printStackTrace();
//            }
//        }
//    }
