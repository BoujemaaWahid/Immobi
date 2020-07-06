package com.example.demo;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cache.CacheManager;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.demo.entitys.TypeLocal;
import com.example.demo.repositorys.TypeRepository;

@Component
public class CacheComponent {
	
	@Autowired
	CacheManager cacheManager;
	
	@Autowired
	TypeRepository typeRepo;
	
	@EventListener
    public void appReady(ApplicationReadyEvent event) {
		try {
			typeRepo.saveAll(Arrays.asList(
					new TypeLocal(1, "appartement"),
					new TypeLocal(2, "maison"),
					new TypeLocal(3, "parking"),
					new TypeLocal(4, "terrain")
					));
		}catch(Exception ex) {}
    }
	
	public CacheComponent() {
		try {
			evictAllcachesAtIntervals();
		}catch(Exception ex) {}
	}
	
	public void evictAllCaches() {
	    cacheManager.getCacheNames().stream()
	      .forEach(cacheName -> cacheManager.getCache(cacheName).clear());
	}
	
	@Scheduled(fixedRate = 5000)
	public void evictAllcachesAtIntervals() {
	    evictAllCaches();
	}
}
