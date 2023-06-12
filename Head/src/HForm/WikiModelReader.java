package HForm;

import info.bliki.wiki.filter.PlainTextConverter;
import info.bliki.wiki.model.IWikiModel;
import info.bliki.wiki.model.WikiModel;
import info.bliki.wiki.template.AbstractTemplateFunction;
import info.bliki.wiki.template.ITemplateFunction;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.imageio.plugins.jpeg.JPEGImageReadParam;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public class WikiModelReader {
    static class TemplateFunctionProvider extends AbstractTemplateFunction {
        public ITemplateFunction[] getFunctions(String name) {
            return new ITemplateFunction[]{this};
        }

        public String parseFunction(char[] src, int beginIndex, int endIndex, char[] rawName, char[][] params,
                                   WikiModel wikiModel) {
            // Custom handling of template functions if needed
            return "";
        }

        @Override
        public String parseFunction(List<String> list, IWikiModel iWikiModel, char[] chars, int i, int i1, boolean b) throws IOException {
            return null;
        }
    }
    public static String wikimarkupReader(String fileName) throws IOException, ParseException {
        File filePath = new File("data//"+fileName+"_revisions.json");
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(filePath));
        String wikiMarkup = (String) jsonObject.get(fileName);
        //String wikiMarkup = "Đây là danh sách các tập phát sóng của chương trình '''''[[2 ngày 1 đêm (chương trình truyền hình Việt Nam)|2 ngày 1 đêm]]''''' phiên bản Việt Nam, một chương trình truyền hình trải nghiệm thực tế do [[Đài Truyền hình Thành phố Hồ Chí Minh]] và công ty [[Đông Tây Promotion]] phối hợp thực hiện, được phát sóng trên kênh [[HTV7]] và nền tảng [[VieON]] từ ngày 19 tháng 6 năm 2022. Các tập phát sóng của chương trình sau khi được lên sóng trên truyền hình cũng được công chiếu cùng ngày trên kênh [[YouTube]] chính thức của nhà sản xuất và của chương trình.<ref name=\"2N1D:02\">{{Chú thích web|url=https://voh.com.vn/show/2-ngay-1-dem-tap-1-rvt-439444.html|tựa đề='2 Ngày 1 Đêm' lên sóng liệu có vượt mặt 'Chạy Đi Chờ Chi'?|họ=Lê Thị Thùy Trang, tổng hợp|ngày=2022-06-27|website=[[Đài Tiếng nói Nhân dân Thành phố Hồ Chí Minh|Đài tiếng nói nhân dân Thành phố Hồ Chí Minh]]|ngôn ngữ=vi|ngày truy cập=2022-08-16|archive-date=2022-08-12|archive-url=https://web.archive.org/web/20220812184934/https://voh.com.vn/show/2-ngay-1-dem-tap-1-rvt-439444.html|url-status=live}}</ref>\n\n== Tổng quan ==\n{| class=\"wikitable\"\n! colspan=\"2\" rowspan=\"2\" |Mùa\n! rowspan=\"2\" |Tập\n! colspan=\"2\" |Phát sóng gốc\n|-\n!Phát sóng lần đầu\n!Phát sóng lần cuối\n|-\n| style=\"background: #5599df;\" |\n| style=\"vertical-align: middle; text-align: center\" |[[2 ngày 1 đêm (mùa 1)|1]]\n| style=\"vertical-align: middle; text-align: center\" |20\n|{{Start date|2022|6|19}}\n|{{End date|2022|10|30}}\n|-\n| style=\"background: red;\" |\n| style=\"vertical-align: middle; text-align: center\" |[[2 ngày 1 đêm (mùa 1)#Mùa lễ hội|Lễ hội]]\n| style=\"vertical-align: middle; text-align: center\" |11\n|{{Start date|2022|12|18}}\n|26 tháng 2 năm 2023\n|}\n\n: ''Click vào số thứ tự của từng mùa để xem thông tin chi tiết của các mùa đó.''\n\n== Danh sách các tập ==\n=== Mùa 1 ===\n{{Bài chi tiết|2 ngày 1 đêm (mùa 1)}}\nMùa thứ nhất của chương trình được phát sóng từ ngày 19 tháng 6 năm 2022, với 20 tập chính phát sóng đến hết ngày 30 tháng 10 năm 2022.<ref name=\"2N1D:02\"/> Sau đó, một phiên bản đặc biệt có tên là \"Mùa lễ hội\" tiếp tục phát sóng từ ngày 18 tháng 12 năm 2022 đến ngày 26 tháng 2 năm 2023 với 11 tập.<ref>{{Chú thích web|url=https://viez.vn/2-ngay-1-dem-chinh-thuc-tro-lai-vao-18-12-nhung-khong-phai-mua-2-RZbi4jOzP11N.html|tựa đề=Chính thức: \"2 Ngày 1 Đêm\" sẽ trở lại vào 18/12 nhưng không phải mùa 2|họ=Liên Nguyễn|ngày=2022-12-05|website=VieZ|ngôn ngữ=vi|ngày truy cập=2022-12-07}}</ref>\n\n== Tham khảo ==\n{{Tham khảo|30em}}\n\n{{2 ngày 1 đêm (chương trình truyền hình Việt Nam)}}\n\n[[Thể loại:Danh sách tập chương trình truyền hình]]\n[[Thể loại:2 ngày 1 đêm (Việt Nam)]]";
        //System.out.println(wikiMarkup);

        // Create a WikiModel instance
        WikiModel wikiModel = new WikiModel("https://en.wikipedia.org/wiki/${image}", "https://en.wikipedia.org/wiki/${title}");

        // Transform the wiki markup to HTML
        String htmlContent = wikiModel.render(wikiMarkup);

        System.out.println(htmlContent);
        return htmlContent;
    }
    public static String wikimarkupReader2Text(String fileName) throws IOException, ParseException {
        File filePath = new File("data//"+fileName+"_revisions.json");
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(filePath));
        String wikiMarkup = (String) jsonObject.get(fileName);
        //String wikiMarkup = "Đây là danh sách các tập phát sóng của chương trình '''''[[2 ngày 1 đêm (chương trình truyền hình Việt Nam)|2 ngày 1 đêm]]''''' phiên bản Việt Nam, một chương trình truyền hình trải nghiệm thực tế do [[Đài Truyền hình Thành phố Hồ Chí Minh]] và công ty [[Đông Tây Promotion]] phối hợp thực hiện, được phát sóng trên kênh [[HTV7]] và nền tảng [[VieON]] từ ngày 19 tháng 6 năm 2022. Các tập phát sóng của chương trình sau khi được lên sóng trên truyền hình cũng được công chiếu cùng ngày trên kênh [[YouTube]] chính thức của nhà sản xuất và của chương trình.<ref name=\"2N1D:02\">{{Chú thích web|url=https://voh.com.vn/show/2-ngay-1-dem-tap-1-rvt-439444.html|tựa đề='2 Ngày 1 Đêm' lên sóng liệu có vượt mặt 'Chạy Đi Chờ Chi'?|họ=Lê Thị Thùy Trang, tổng hợp|ngày=2022-06-27|website=[[Đài Tiếng nói Nhân dân Thành phố Hồ Chí Minh|Đài tiếng nói nhân dân Thành phố Hồ Chí Minh]]|ngôn ngữ=vi|ngày truy cập=2022-08-16|archive-date=2022-08-12|archive-url=https://web.archive.org/web/20220812184934/https://voh.com.vn/show/2-ngay-1-dem-tap-1-rvt-439444.html|url-status=live}}</ref>\n\n== Tổng quan ==\n{| class=\"wikitable\"\n! colspan=\"2\" rowspan=\"2\" |Mùa\n! rowspan=\"2\" |Tập\n! colspan=\"2\" |Phát sóng gốc\n|-\n!Phát sóng lần đầu\n!Phát sóng lần cuối\n|-\n| style=\"background: #5599df;\" |\n| style=\"vertical-align: middle; text-align: center\" |[[2 ngày 1 đêm (mùa 1)|1]]\n| style=\"vertical-align: middle; text-align: center\" |20\n|{{Start date|2022|6|19}}\n|{{End date|2022|10|30}}\n|-\n| style=\"background: red;\" |\n| style=\"vertical-align: middle; text-align: center\" |[[2 ngày 1 đêm (mùa 1)#Mùa lễ hội|Lễ hội]]\n| style=\"vertical-align: middle; text-align: center\" |11\n|{{Start date|2022|12|18}}\n|26 tháng 2 năm 2023\n|}\n\n: ''Click vào số thứ tự của từng mùa để xem thông tin chi tiết của các mùa đó.''\n\n== Danh sách các tập ==\n=== Mùa 1 ===\n{{Bài chi tiết|2 ngày 1 đêm (mùa 1)}}\nMùa thứ nhất của chương trình được phát sóng từ ngày 19 tháng 6 năm 2022, với 20 tập chính phát sóng đến hết ngày 30 tháng 10 năm 2022.<ref name=\"2N1D:02\"/> Sau đó, một phiên bản đặc biệt có tên là \"Mùa lễ hội\" tiếp tục phát sóng từ ngày 18 tháng 12 năm 2022 đến ngày 26 tháng 2 năm 2023 với 11 tập.<ref>{{Chú thích web|url=https://viez.vn/2-ngay-1-dem-chinh-thuc-tro-lai-vao-18-12-nhung-khong-phai-mua-2-RZbi4jOzP11N.html|tựa đề=Chính thức: \"2 Ngày 1 Đêm\" sẽ trở lại vào 18/12 nhưng không phải mùa 2|họ=Liên Nguyễn|ngày=2022-12-05|website=VieZ|ngôn ngữ=vi|ngày truy cập=2022-12-07}}</ref>\n\n== Tham khảo ==\n{{Tham khảo|30em}}\n\n{{2 ngày 1 đêm (chương trình truyền hình Việt Nam)}}\n\n[[Thể loại:Danh sách tập chương trình truyền hình]]\n[[Thể loại:2 ngày 1 đêm (Việt Nam)]]";
        //System.out.println(wikiMarkup);

        // Create a WikiModel instance
        WikiModel wikiModel = new WikiModel("https://en.wikipedia.org/wiki/${image}", "https://en.wikipedia.org/wiki/${title}");

        PlainTextConverter textConverter = new PlainTextConverter();
        String htmlContent = new String();
        try {
            String text = new String();
            StringBuilder result = new StringBuilder();
            WikiModel.toText(wikiModel, textConverter,wikiMarkup, result, true, true);
            htmlContent = result.toString();
        } catch (IOException e){
            e.printStackTrace();
        }
        // Transform the wiki markup to HTML

        System.out.println(htmlContent);
        return htmlContent;
    }

    public static String wikimarkupReader2HTML(String fileName) throws IOException, ParseException {
        File filePath = new File("data//"+fileName+"_revisions.json");
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(filePath));
        String wikiMarkup = (String) jsonObject.get(fileName);
        //String wikiMarkup = "Đây là danh sách các tập phát sóng của chương trình '''''[[2 ngày 1 đêm (chương trình truyền hình Việt Nam)|2 ngày 1 đêm]]''''' phiên bản Việt Nam, một chương trình truyền hình trải nghiệm thực tế do [[Đài Truyền hình Thành phố Hồ Chí Minh]] và công ty [[Đông Tây Promotion]] phối hợp thực hiện, được phát sóng trên kênh [[HTV7]] và nền tảng [[VieON]] từ ngày 19 tháng 6 năm 2022. Các tập phát sóng của chương trình sau khi được lên sóng trên truyền hình cũng được công chiếu cùng ngày trên kênh [[YouTube]] chính thức của nhà sản xuất và của chương trình.<ref name=\"2N1D:02\">{{Chú thích web|url=https://voh.com.vn/show/2-ngay-1-dem-tap-1-rvt-439444.html|tựa đề='2 Ngày 1 Đêm' lên sóng liệu có vượt mặt 'Chạy Đi Chờ Chi'?|họ=Lê Thị Thùy Trang, tổng hợp|ngày=2022-06-27|website=[[Đài Tiếng nói Nhân dân Thành phố Hồ Chí Minh|Đài tiếng nói nhân dân Thành phố Hồ Chí Minh]]|ngôn ngữ=vi|ngày truy cập=2022-08-16|archive-date=2022-08-12|archive-url=https://web.archive.org/web/20220812184934/https://voh.com.vn/show/2-ngay-1-dem-tap-1-rvt-439444.html|url-status=live}}</ref>\n\n== Tổng quan ==\n{| class=\"wikitable\"\n! colspan=\"2\" rowspan=\"2\" |Mùa\n! rowspan=\"2\" |Tập\n! colspan=\"2\" |Phát sóng gốc\n|-\n!Phát sóng lần đầu\n!Phát sóng lần cuối\n|-\n| style=\"background: #5599df;\" |\n| style=\"vertical-align: middle; text-align: center\" |[[2 ngày 1 đêm (mùa 1)|1]]\n| style=\"vertical-align: middle; text-align: center\" |20\n|{{Start date|2022|6|19}}\n|{{End date|2022|10|30}}\n|-\n| style=\"background: red;\" |\n| style=\"vertical-align: middle; text-align: center\" |[[2 ngày 1 đêm (mùa 1)#Mùa lễ hội|Lễ hội]]\n| style=\"vertical-align: middle; text-align: center\" |11\n|{{Start date|2022|12|18}}\n|26 tháng 2 năm 2023\n|}\n\n: ''Click vào số thứ tự của từng mùa để xem thông tin chi tiết của các mùa đó.''\n\n== Danh sách các tập ==\n=== Mùa 1 ===\n{{Bài chi tiết|2 ngày 1 đêm (mùa 1)}}\nMùa thứ nhất của chương trình được phát sóng từ ngày 19 tháng 6 năm 2022, với 20 tập chính phát sóng đến hết ngày 30 tháng 10 năm 2022.<ref name=\"2N1D:02\"/> Sau đó, một phiên bản đặc biệt có tên là \"Mùa lễ hội\" tiếp tục phát sóng từ ngày 18 tháng 12 năm 2022 đến ngày 26 tháng 2 năm 2023 với 11 tập.<ref>{{Chú thích web|url=https://viez.vn/2-ngay-1-dem-chinh-thuc-tro-lai-vao-18-12-nhung-khong-phai-mua-2-RZbi4jOzP11N.html|tựa đề=Chính thức: \"2 Ngày 1 Đêm\" sẽ trở lại vào 18/12 nhưng không phải mùa 2|họ=Liên Nguyễn|ngày=2022-12-05|website=VieZ|ngôn ngữ=vi|ngày truy cập=2022-12-07}}</ref>\n\n== Tham khảo ==\n{{Tham khảo|30em}}\n\n{{2 ngày 1 đêm (chương trình truyền hình Việt Nam)}}\n\n[[Thể loại:Danh sách tập chương trình truyền hình]]\n[[Thể loại:2 ngày 1 đêm (Việt Nam)]]";
        //System.out.println(wikiMarkup);

        // Create a WikiModel instance
        WikiModel wikiModel = new WikiModel("https://en.wikipedia.org/wiki/${image}", "https://en.wikipedia.org/wiki/${title}");

        PlainTextConverter textConverter = new PlainTextConverter();
        String htmlContent = new String();
        try {
            String text = new String();
            StringBuilder result = new StringBuilder();
            WikiModel.toHtml(wikiMarkup, result, "https://en.wikipedia.org/wiki/${image}", "https://en.wikipedia.org/wiki/${title}");
            htmlContent = result.toString();
        } catch (IOException e){
            e.printStackTrace();
        }
        // Transform the wiki markup to HTML

        System.out.println(htmlContent);
        return htmlContent;
    }
    public static String wikimarkupReader2HTMLTemplate(String fileName) throws IOException, ParseException {
        File filePath = new File("data//"+fileName+"_revisions.json");
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(filePath));
        String wikiMarkup = (String) jsonObject.get(fileName);
        //String wikiMarkup = "Đây là danh sách các tập phát sóng của chương trình '''''[[2 ngày 1 đêm (chương trình truyền hình Việt Nam)|2 ngày 1 đêm]]''''' phiên bản Việt Nam, một chương trình truyền hình trải nghiệm thực tế do [[Đài Truyền hình Thành phố Hồ Chí Minh]] và công ty [[Đông Tây Promotion]] phối hợp thực hiện, được phát sóng trên kênh [[HTV7]] và nền tảng [[VieON]] từ ngày 19 tháng 6 năm 2022. Các tập phát sóng của chương trình sau khi được lên sóng trên truyền hình cũng được công chiếu cùng ngày trên kênh [[YouTube]] chính thức của nhà sản xuất và của chương trình.<ref name=\"2N1D:02\">{{Chú thích web|url=https://voh.com.vn/show/2-ngay-1-dem-tap-1-rvt-439444.html|tựa đề='2 Ngày 1 Đêm' lên sóng liệu có vượt mặt 'Chạy Đi Chờ Chi'?|họ=Lê Thị Thùy Trang, tổng hợp|ngày=2022-06-27|website=[[Đài Tiếng nói Nhân dân Thành phố Hồ Chí Minh|Đài tiếng nói nhân dân Thành phố Hồ Chí Minh]]|ngôn ngữ=vi|ngày truy cập=2022-08-16|archive-date=2022-08-12|archive-url=https://web.archive.org/web/20220812184934/https://voh.com.vn/show/2-ngay-1-dem-tap-1-rvt-439444.html|url-status=live}}</ref>\n\n== Tổng quan ==\n{| class=\"wikitable\"\n! colspan=\"2\" rowspan=\"2\" |Mùa\n! rowspan=\"2\" |Tập\n! colspan=\"2\" |Phát sóng gốc\n|-\n!Phát sóng lần đầu\n!Phát sóng lần cuối\n|-\n| style=\"background: #5599df;\" |\n| style=\"vertical-align: middle; text-align: center\" |[[2 ngày 1 đêm (mùa 1)|1]]\n| style=\"vertical-align: middle; text-align: center\" |20\n|{{Start date|2022|6|19}}\n|{{End date|2022|10|30}}\n|-\n| style=\"background: red;\" |\n| style=\"vertical-align: middle; text-align: center\" |[[2 ngày 1 đêm (mùa 1)#Mùa lễ hội|Lễ hội]]\n| style=\"vertical-align: middle; text-align: center\" |11\n|{{Start date|2022|12|18}}\n|26 tháng 2 năm 2023\n|}\n\n: ''Click vào số thứ tự của từng mùa để xem thông tin chi tiết của các mùa đó.''\n\n== Danh sách các tập ==\n=== Mùa 1 ===\n{{Bài chi tiết|2 ngày 1 đêm (mùa 1)}}\nMùa thứ nhất của chương trình được phát sóng từ ngày 19 tháng 6 năm 2022, với 20 tập chính phát sóng đến hết ngày 30 tháng 10 năm 2022.<ref name=\"2N1D:02\"/> Sau đó, một phiên bản đặc biệt có tên là \"Mùa lễ hội\" tiếp tục phát sóng từ ngày 18 tháng 12 năm 2022 đến ngày 26 tháng 2 năm 2023 với 11 tập.<ref>{{Chú thích web|url=https://viez.vn/2-ngay-1-dem-chinh-thuc-tro-lai-vao-18-12-nhung-khong-phai-mua-2-RZbi4jOzP11N.html|tựa đề=Chính thức: \"2 Ngày 1 Đêm\" sẽ trở lại vào 18/12 nhưng không phải mùa 2|họ=Liên Nguyễn|ngày=2022-12-05|website=VieZ|ngôn ngữ=vi|ngày truy cập=2022-12-07}}</ref>\n\n== Tham khảo ==\n{{Tham khảo|30em}}\n\n{{2 ngày 1 đêm (chương trình truyền hình Việt Nam)}}\n\n[[Thể loại:Danh sách tập chương trình truyền hình]]\n[[Thể loại:2 ngày 1 đêm (Việt Nam)]]";
        //System.out.println(wikiMarkup);

        // Create a WikiModel instance
        WikiModel wikiModel = new WikiModel("https://en.wikipedia.org/wiki/${image}", "https://en.wikipedia.org/wiki/${title}");

        PlainTextConverter textConverter = new PlainTextConverter();
        String htmlContent = new String();
        try {
            String text = new String();
            StringBuilder result = new StringBuilder();
            WikiModel.toHtml(wikiMarkup, result, "https://en.wikipedia.org/wiki/${image}", "https://en.wikipedia.org/wiki/${title}");
            htmlContent = result.toString();
        } catch (IOException e){
            e.printStackTrace();
        }
        // Transform the wiki markup to HTML
        Document doc = Jsoup.parse(htmlContent);
        Elements ps = doc.select("p");
        for(Element p:ps){
            String templateContent = p.text();
            if(templateContent.contains("{{")&&templateContent.contains("=")&&templateContent.contains("|")){
                System.out.println(templateContent);
                return templateContent;
            }
        }
        return null;
    }
    public static void wikiMarkupTemplatesReader(String fileName) throws IOException, ParseException {
        File filePath = new File("data//"+fileName+"_revisions.json");
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(filePath));
        String wikiMarkup = (String) jsonObject.get(fileName);
        //String wikiMarkup = "Đây là danh sách các tập phát sóng của chương trình '''''[[2 ngày 1 đêm (chương trình truyền hình Việt Nam)|2 ngày 1 đêm]]''''' phiên bản Việt Nam, một chương trình truyền hình trải nghiệm thực tế do [[Đài Truyền hình Thành phố Hồ Chí Minh]] và công ty [[Đông Tây Promotion]] phối hợp thực hiện, được phát sóng trên kênh [[HTV7]] và nền tảng [[VieON]] từ ngày 19 tháng 6 năm 2022. Các tập phát sóng của chương trình sau khi được lên sóng trên truyền hình cũng được công chiếu cùng ngày trên kênh [[YouTube]] chính thức của nhà sản xuất và của chương trình.<ref name=\"2N1D:02\">{{Chú thích web|url=https://voh.com.vn/show/2-ngay-1-dem-tap-1-rvt-439444.html|tựa đề='2 Ngày 1 Đêm' lên sóng liệu có vượt mặt 'Chạy Đi Chờ Chi'?|họ=Lê Thị Thùy Trang, tổng hợp|ngày=2022-06-27|website=[[Đài Tiếng nói Nhân dân Thành phố Hồ Chí Minh|Đài tiếng nói nhân dân Thành phố Hồ Chí Minh]]|ngôn ngữ=vi|ngày truy cập=2022-08-16|archive-date=2022-08-12|archive-url=https://web.archive.org/web/20220812184934/https://voh.com.vn/show/2-ngay-1-dem-tap-1-rvt-439444.html|url-status=live}}</ref>\n\n== Tổng quan ==\n{| class=\"wikitable\"\n! colspan=\"2\" rowspan=\"2\" |Mùa\n! rowspan=\"2\" |Tập\n! colspan=\"2\" |Phát sóng gốc\n|-\n!Phát sóng lần đầu\n!Phát sóng lần cuối\n|-\n| style=\"background: #5599df;\" |\n| style=\"vertical-align: middle; text-align: center\" |[[2 ngày 1 đêm (mùa 1)|1]]\n| style=\"vertical-align: middle; text-align: center\" |20\n|{{Start date|2022|6|19}}\n|{{End date|2022|10|30}}\n|-\n| style=\"background: red;\" |\n| style=\"vertical-align: middle; text-align: center\" |[[2 ngày 1 đêm (mùa 1)#Mùa lễ hội|Lễ hội]]\n| style=\"vertical-align: middle; text-align: center\" |11\n|{{Start date|2022|12|18}}\n|26 tháng 2 năm 2023\n|}\n\n: ''Click vào số thứ tự của từng mùa để xem thông tin chi tiết của các mùa đó.''\n\n== Danh sách các tập ==\n=== Mùa 1 ===\n{{Bài chi tiết|2 ngày 1 đêm (mùa 1)}}\nMùa thứ nhất của chương trình được phát sóng từ ngày 19 tháng 6 năm 2022, với 20 tập chính phát sóng đến hết ngày 30 tháng 10 năm 2022.<ref name=\"2N1D:02\"/> Sau đó, một phiên bản đặc biệt có tên là \"Mùa lễ hội\" tiếp tục phát sóng từ ngày 18 tháng 12 năm 2022 đến ngày 26 tháng 2 năm 2023 với 11 tập.<ref>{{Chú thích web|url=https://viez.vn/2-ngay-1-dem-chinh-thuc-tro-lai-vao-18-12-nhung-khong-phai-mua-2-RZbi4jOzP11N.html|tựa đề=Chính thức: \"2 Ngày 1 Đêm\" sẽ trở lại vào 18/12 nhưng không phải mùa 2|họ=Liên Nguyễn|ngày=2022-12-05|website=VieZ|ngôn ngữ=vi|ngày truy cập=2022-12-07}}</ref>\n\n== Tham khảo ==\n{{Tham khảo|30em}}\n\n{{2 ngày 1 đêm (chương trình truyền hình Việt Nam)}}\n\n[[Thể loại:Danh sách tập chương trình truyền hình]]\n[[Thể loại:2 ngày 1 đêm (Việt Nam)]]";
        //System.out.println(wikiMarkup);

        // Create a WikiModel instance
        WikiModel wikiModel = new WikiModel("https://en.wikipedia.org/wiki/${image}", "https://en.wikipedia.org/wiki/${title}");

        String parsedText = wikiModel.render(wikiMarkup);

        // Get the list of templates
        Set<String> templates = wikiModel.getTemplates();

        // Print the templates
        for (String template : templates) {
            System.out.println(template);
        }
    }
    public static void wikiMarkupTemplatesReaderV1(String fileName) throws IOException, ParseException {
        File filePath = new File("data//"+fileName+"_revisions.json");
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(filePath));
        String wikiMarkup = (String) jsonObject.get(fileName);
        //String wikiMarkup = "Đây là danh sách các tập phát sóng của chương trình '''''[[2 ngày 1 đêm (chương trình truyền hình Việt Nam)|2 ngày 1 đêm]]''''' phiên bản Việt Nam, một chương trình truyền hình trải nghiệm thực tế do [[Đài Truyền hình Thành phố Hồ Chí Minh]] và công ty [[Đông Tây Promotion]] phối hợp thực hiện, được phát sóng trên kênh [[HTV7]] và nền tảng [[VieON]] từ ngày 19 tháng 6 năm 2022. Các tập phát sóng của chương trình sau khi được lên sóng trên truyền hình cũng được công chiếu cùng ngày trên kênh [[YouTube]] chính thức của nhà sản xuất và của chương trình.<ref name=\"2N1D:02\">{{Chú thích web|url=https://voh.com.vn/show/2-ngay-1-dem-tap-1-rvt-439444.html|tựa đề='2 Ngày 1 Đêm' lên sóng liệu có vượt mặt 'Chạy Đi Chờ Chi'?|họ=Lê Thị Thùy Trang, tổng hợp|ngày=2022-06-27|website=[[Đài Tiếng nói Nhân dân Thành phố Hồ Chí Minh|Đài tiếng nói nhân dân Thành phố Hồ Chí Minh]]|ngôn ngữ=vi|ngày truy cập=2022-08-16|archive-date=2022-08-12|archive-url=https://web.archive.org/web/20220812184934/https://voh.com.vn/show/2-ngay-1-dem-tap-1-rvt-439444.html|url-status=live}}</ref>\n\n== Tổng quan ==\n{| class=\"wikitable\"\n! colspan=\"2\" rowspan=\"2\" |Mùa\n! rowspan=\"2\" |Tập\n! colspan=\"2\" |Phát sóng gốc\n|-\n!Phát sóng lần đầu\n!Phát sóng lần cuối\n|-\n| style=\"background: #5599df;\" |\n| style=\"vertical-align: middle; text-align: center\" |[[2 ngày 1 đêm (mùa 1)|1]]\n| style=\"vertical-align: middle; text-align: center\" |20\n|{{Start date|2022|6|19}}\n|{{End date|2022|10|30}}\n|-\n| style=\"background: red;\" |\n| style=\"vertical-align: middle; text-align: center\" |[[2 ngày 1 đêm (mùa 1)#Mùa lễ hội|Lễ hội]]\n| style=\"vertical-align: middle; text-align: center\" |11\n|{{Start date|2022|12|18}}\n|26 tháng 2 năm 2023\n|}\n\n: ''Click vào số thứ tự của từng mùa để xem thông tin chi tiết của các mùa đó.''\n\n== Danh sách các tập ==\n=== Mùa 1 ===\n{{Bài chi tiết|2 ngày 1 đêm (mùa 1)}}\nMùa thứ nhất của chương trình được phát sóng từ ngày 19 tháng 6 năm 2022, với 20 tập chính phát sóng đến hết ngày 30 tháng 10 năm 2022.<ref name=\"2N1D:02\"/> Sau đó, một phiên bản đặc biệt có tên là \"Mùa lễ hội\" tiếp tục phát sóng từ ngày 18 tháng 12 năm 2022 đến ngày 26 tháng 2 năm 2023 với 11 tập.<ref>{{Chú thích web|url=https://viez.vn/2-ngay-1-dem-chinh-thuc-tro-lai-vao-18-12-nhung-khong-phai-mua-2-RZbi4jOzP11N.html|tựa đề=Chính thức: \"2 Ngày 1 Đêm\" sẽ trở lại vào 18/12 nhưng không phải mùa 2|họ=Liên Nguyễn|ngày=2022-12-05|website=VieZ|ngôn ngữ=vi|ngày truy cập=2022-12-07}}</ref>\n\n== Tham khảo ==\n{{Tham khảo|30em}}\n\n{{2 ngày 1 đêm (chương trình truyền hình Việt Nam)}}\n\n[[Thể loại:Danh sách tập chương trình truyền hình]]\n[[Thể loại:2 ngày 1 đêm (Việt Nam)]]";
        //System.out.println(wikiMarkup);

        // Create a WikiModel instance
        WikiModel wikiModel = new WikiModel("https://en.wikipedia.org/wiki/${image}", "https://en.wikipedia.org/wiki/${title}");

        String parsedText = wikiModel.render(wikiMarkup);

        // Get the list of templates
        String templates = String.valueOf(wikiModel.getNamespace());

        System.out.println(templates);
    }
    public static void main(String[] args) throws IOException, ParseException {
//        JFrame app = new JFrame();
//        app.setSize(new Dimension(500, 800));
//        JLabel label = new JLabel("<html>"+wikimarkupReader("Nhạc Phi")+"</html>");
//        JScrollPane scroll = new JScrollPane(label);
//        app.add(scroll);
//        app.setVisible(true);

    wikimarkupReader2HTMLTemplate("Nhạc Phi");
    }
}
