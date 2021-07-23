package com.cloud.mini.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.jsoup.Connection.Request;
import org.hibernate.internal.build.AllowSysOut;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.env.OriginTrackedMapPropertySource;
import org.springframework.stereotype.Service;

import com.cloud.mini.model.StoreStats;
import com.cloud.mini.repository.StoreStatsRepository;

import lombok.extern.java.Log;

@Log
@Service
public class StoreDataService {
	private Document doc = null;
	private Elements contents = null;
	private StoreStats tmpStoreStats = null;
	private List<StoreStats> gukjeStoreStats = new ArrayList<>();
	private List<StoreStats> kkangtongStoreStats = new ArrayList<>();
	private List<StoreStats> boojunStoreStats = new ArrayList<>();
	private List<StoreStats> busanjinStoreStats = new ArrayList<>();
	private List<StoreStats> gupoStoreStats = new ArrayList<>();
	private static String GUKJE_DATAS_URL = "https://gukjemarket6.modoo.at/?link=8qz31fbg";
	private static String KKANGTONG_DATAS_URL = "http://bupyeong-market.com/bs/ko/allOfStoreInfo.do";
	private static String BOOJUN_DATAS_URL = "";
	private static String BUSANJIN_DATAS_URL = "http://www.busanjinmart.co.kr/sub.php?";
	private static String GUPO_DATAS_URL = "";
	private StoreStatsRepository storeStatsRepository;
	
	@Autowired
	public StoreDataService(StoreStatsRepository storeStatsRepository) {
		this.tmpStoreStats = new StoreStats();
		this.storeStatsRepository = storeStatsRepository;
		this.storeStatsRepository.deleteAllInBatch();
	}

	@PostConstruct
	public void getKkangtongDatas() throws IOException{
		log.info("Start getKkangtongDatas() wait...");
		Integer page = 1;
		String name = "";
		String product = "";
		while(true) {
			doc = Jsoup.connect(KKANGTONG_DATAS_URL).data("pageIndex", page.toString()).get();
			contents = doc.select("table tbody tr td");
			if(contents.text().equals("등록된 데이터가 없습니다.")) {
				break;
			}
			for(int i=0; i<contents.size(); i++) {
				if(i%5 == 2) {
					name = contents.get(i).text();
				}
				else if(i%5 == 4) {
					product = contents.get(i).text();
					tmpStoreStats = new StoreStats();
					tmpStoreStats.setName(name);
					tmpStoreStats.setProducts(product);
					tmpStoreStats.setStore(1);
					kkangtongStoreStats.add(tmpStoreStats);
				}
			}
			page++;
		}
		storeStatsRepository.saveAll(kkangtongStoreStats);
		log.info("End getKkangtongDatas()");
	}

	@PostConstruct
	public void getBusanjinDatas() throws IOException{
		log.info("Start getBusanjinDatas() wait...");
		Integer page = 1;
		int index = 0;
		String name = "";
		String product = "";
		String[] floors = {"D1", "U1", "U2", "U3"};
		
		while(true) {
			doc = Jsoup.connect(BUSANJIN_DATAS_URL).data("page", page.toString()).data("m", floors[index]).get();
			contents = doc.select("table tbody td p");
			if(contents.isEmpty()) {
				if(index<floors.length-1) {
					index++;
					page=1;
					continue;
				}
				else {
					break;
				}
			}
			for(int i=0; i<contents.size(); i++) {
				if(i%7 == 1) {
					name = contents.get(i).text();
				}
				else if(i%7 == 2) {
					product = contents.get(i).text();
					tmpStoreStats = new StoreStats();
					tmpStoreStats.setName(name);
					tmpStoreStats.setProducts(product);
					tmpStoreStats.setStore(2);
					busanjinStoreStats.add(tmpStoreStats);
				}
			}
			page++;
		}
		storeStatsRepository.saveAll(busanjinStoreStats);
		log.info("End getBusanjinDatas()");
	}

	@PostConstruct
	public void getGukjeDatas() throws IOException{
//		TODO
//		동적으로 점포 데이터를 뿌리고 있어서 힘듬.
//		Request request = new Request();
//		doc = Jsoup.connect(GUKJE_DATAS_URL)
//				.request(null)
//				.data("cid", "8qz31fbg")
//				.data("categoryId", "0")
//				.data("page", "1")
//				.data("orderBy", "site_name_sort_value")
//				.post();
//		System.out.println(doc);
	}
	
	public void getBoojunDatas() throws IOException{
		//TODO
		//점포 데이터가 없음
	}
	
	@PostConstruct
	public void getGupoDatas() throws IOException{
		//TODO
		//점포 데이터가 없음
	}
}
