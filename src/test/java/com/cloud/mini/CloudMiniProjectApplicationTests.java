package com.cloud.mini;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CloudMiniProjectApplicationTests {

	@Test
	void contextLoads() {
		Document doc = null;
		try {
			doc = Jsoup.connect("https://gukjemarket6.modoo.at/?link=8qz31fbg")
					.data("cid", "3umwmaeo")
					.data("anchor", "05b7b8368280cb052ffa800c86f94484")
					.data("content-uri", "/apps/hub/entry")
					.data("adult-flag", "0")
					.data("title", "구역별 상점").post();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(doc);
	}

}
