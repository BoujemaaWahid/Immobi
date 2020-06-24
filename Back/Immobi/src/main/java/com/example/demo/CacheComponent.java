package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CacheComponent {
	
	@Autowired
	CacheManager cacheManager;
	
	public void evictAllCaches() {
	    cacheManager.getCacheNames().stream()
	      .forEach(cacheName -> cacheManager.getCache(cacheName).clear());
	}
	
	@Scheduled(fixedRate = 1000)
	public void evictAllcachesAtIntervals() {
	    evictAllCaches();
	}
}
