package com.agoda;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class InMemoryRateLimiter {
	
	private Map<String, RateLimiterBucket> buckets = new HashMap<String, RateLimiterBucket>();
	 public InMemoryRateLimiter() {
		    }
	 
	 public void accept(String bucketId, RateBucketPeriod period, int limit) {
		           RateLimiterBucket bucket = null;
		          synchronized (buckets) {
		             bucket = buckets.get(bucketId);
		               if (bucket == null) {
		                  bucket = new RateLimiterBucket();
		                   buckets.put(bucketId, bucket);
		               }
		           }
		          synchronized (bucket.mutex) {
		        	             bucket.resetIfNecessary(period);
		        	                if (bucket.count >= limit) {
		        	                  //  handler.handle(AsyncResultImpl.<Boolean>create(Boolean.FALSE));
		        	                } else {
		        	                    bucket.count++;
		        	                    bucket.last = System.currentTimeMillis();
		        	                    //handler.handle(AsyncResultImpl.<Boolean>create(Boolean.TRUE));
		        	                }
		        	            }
		        	        }
		        	        
		        	       private static class RateLimiterBucket {
		        	          public int count = 0;
		        	           public long last = System.currentTimeMillis();
		        	          public Object mutex = new Object();
		        	           
		        	          /**
		        	           * Resets the count if the period boundary has been crossed.
		        	           * @param period
		        	         */
		        	          public void resetIfNecessary(RateBucketPeriod period) {
		        	            long periodBoundary = getLastPeriodBoundary(period);
		        	              if (System.currentTimeMillis() >= periodBoundary) {
		        		                 count = 0;
		        		              }
		        		         }
		        	  
		        	  private long getLastPeriodBoundary(RateBucketPeriod period) {
		        		              Calendar lastCal = Calendar.getInstance();
		        		             lastCal.setTimeInMillis(last);
		        		                switch (period) {
		        		              case Second:
		        		                    lastCal.set(Calendar.MILLISECOND, 0);
		        		                   lastCal.add(Calendar.SECOND, 1);
		        		                  return lastCal.getTimeInMillis();
		        		               case Minute:
		        		                   lastCal.set(Calendar.MILLISECOND, 0);
		        		                  lastCal.set(Calendar.SECOND, 0);
		        		                  lastCal.add(Calendar.MINUTE, 1);
		        		                  return lastCal.getTimeInMillis();
		        		            case Hour:
		        		                  lastCal.set(Calendar.MILLISECOND, 0);
		        		                  lastCal.set(Calendar.SECOND, 0);
		        		                 lastCal.set(Calendar.MINUTE, 0);
		        		                 lastCal.add(Calendar.HOUR_OF_DAY, 1);
		        		               return lastCal.getTimeInMillis();
		        		  case Day:
		        			                   lastCal.set(Calendar.MILLISECOND, 0);
		        			                   lastCal.set(Calendar.SECOND, 0);
		        			                   lastCal.set(Calendar.MINUTE, 0);
		        			                   lastCal.set(Calendar.HOUR_OF_DAY, 0);
		        			                   lastCal.add(Calendar.DAY_OF_YEAR, 1);
		        			                   return lastCal.getTimeInMillis();
		        			  
		        		  case Month:
		        			                   lastCal.set(Calendar.MILLISECOND, 0);
		        			                  lastCal.set(Calendar.SECOND, 0);
		        			                   lastCal.set(Calendar.MINUTE, 0);
		        			                   lastCal.set(Calendar.HOUR_OF_DAY, 0);
		        			                  lastCal.add(Calendar.DAY_OF_MONTH, 1);
		        			                   return lastCal.getTimeInMillis();
		        			               case Year:
		        			                   lastCal.set(Calendar.MILLISECOND, 0);
		        			                  lastCal.set(Calendar.SECOND, 0);
		        			                   lastCal.set(Calendar.MINUTE, 0);
		        			                   lastCal.set(Calendar.HOUR_OF_DAY, 0);
		        			                  lastCal.set(Calendar.DAY_OF_YEAR, 0);
		        			                   lastCal.add(Calendar.YEAR, 1);
		        			                   return lastCal.getTimeInMillis();
		        			               }
		        			               return Long.MAX_VALUE;
		        			           }
		        	       }
}
