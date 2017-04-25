package com.elharo.gcp.demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.cloud.Page;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.Storage.BucketListOption;
import com.google.cloud.storage.StorageOptions;

public class HelloCloudStorage extends HttpServlet {

  public void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws IOException {
      
    response.setContentType("text/plain");
    PrintWriter writer = response.getWriter();
	writer.print("Your Google Cloud Storage buckets:\r\n\r\n");
    
    Storage storage = StorageOptions.getDefaultInstance().getService();
    BucketListOption options = BucketListOption.pageSize(100);
	Page<Bucket> buckets = storage.list(options);
	Iterator<Bucket> bucketIterator = buckets.iterateAll();
	for (Bucket bucket = bucketIterator.next();
	    bucketIterator.hasNext();
	    bucket = bucketIterator.next()) {
	   writer.println(bucket.getName());
	 }
  }
}