import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;

public class Parser
{
    private static Document getPage() {
        String url = "https://world-weather.ru/pogoda/russia/moscow/3days/";
        Document page = null;
        try {
            page = Jsoup.parse(new URL(url),3000);
        } catch (IOException e) {
            System.out.println("Неверный адрес сайта");
        }
        return page;
    }

    private static int printFourValues(Elements values,int index){
        int counter = 4;
        for (int i = 0; i < 4; i++) {
            Element valueLine = values.get(index + i);
            for (Element td : valueLine.select("td")){
                System.out.print(td.text() + "                ");
            }
            System.out.println();
        }
        System.out.println("");
        return counter;
    }

    public static void main( String[] args ) {
        Document page = getPage();

        // css query language
        Element tableWth = page.select("div[id = content-left]").first();
        Elements names = tableWth.select("div[class ^= dates short-d]");
        Elements values = page.select("tr[class ^= ]");

        int index = 0;
            for (Element name : names) {
                String date = name.select("div[class ^= dates short-d]").text();
                System.out.println(date + "    Температура    Ощущается как    Вероятность осадков    Давление    Скорость ветра    Влажность воздуха");
                int counter = printFourValues(values, index);
                index = index+counter;
        }
    }
}
