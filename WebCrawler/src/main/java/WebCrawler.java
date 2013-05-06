import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import redis.clients.jedis.Jedis;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zhangxu
 * Date: 13-5-2
 * Time: 下午12:21
 * To change this template use File | Settings | File Templates.
 */
public class WebCrawler implements Runnable{
	private static int PageId = 0;
	private static int PageMax = 500;
	//private URL url;
	private String report = "";
	private static int threadNum = 10;
	private List<String> urls = new ArrayList<String>() ;

	public WebCrawler (String url) {

		urls.add(url);
	}

	public synchronized void addReport(String s)
	{
		try
		{
			report += s;
			PrintWriter pwReport = new PrintWriter(new FileOutputStream("report.txt"));
			pwReport.println(report);
			pwReport.close();
		}
		catch(Exception e)
		{
			System.out.println("生成报告文件失败!");
		}
	}

	public void run()  {
		Jedis jedis = new Jedis("localhost");

		while  (PageId < PageMax ) {
			String url = urls.get(PageId);
			if (!url.isEmpty()) {
				//get content, urls
				StringBuffer sff = new StringBuffer();
				String content;
				Document doc = null;
				try {
					doc = Jsoup.connect(url).get();
				} catch (IOException e) {
					e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
				}
				sff.append(doc.body().text());
				System.out.println(doc.body().text());

				content = sff.toString();
				Elements links = doc.select("a[href]");
				//extract urls
				for (Element link:links) {

					if (!urls.contains(link.attr("abs:href"))) {
						urls.add(link.attr("abs:href"));
					}
				}
				//store url and content
				jedis.set(url, content);
				addReport(url);
				PageId ++;
			}
			else {
				System.out.println("no more urls");
			}
		}

	}
	public static void main  (String [] args) {

			WebCrawler crawler =  new WebCrawler("http://www.sina.com") ;
			new Thread(crawler,"A").start() ;
	}
}
