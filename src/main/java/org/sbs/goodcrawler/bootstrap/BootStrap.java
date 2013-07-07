/**
 * ##########################  GoodCrawler  ############################
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.sbs.goodcrawler.bootstrap;

import java.io.File;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.sbs.goodcrawler.bootstrap.foreman.ExtractForeman;
import org.sbs.goodcrawler.bootstrap.foreman.FetchForeman;
import org.sbs.goodcrawler.bootstrap.foreman.StoreForeman;
import org.sbs.goodcrawler.conf.jobconf.JobConfiguration;
import org.sbs.goodcrawler.conf.jobconf.JobConfigurationManager;
import org.sbs.goodcrawler.exception.ConfigurationException;
import org.sbs.goodcrawler.fetcher.DefaultFetchWorker;
import org.sbs.goodcrawler.fetcher.FetchWorker;
import org.sbs.goodcrawler.fetcher.PageFetcher;

/**
 * @author shenbaise(shenbaise@outlook.com)
 * @date 2013-7-3
 * bootstrap
 */
public class BootStrap {
	private Log log = LogFactory.getLog(this.getClass());
	/**
	 * @desc 启动咯
	 */
	public void start(){
		
	}
	/**
	 * @param args
	 * @desc 
	 */
	public static void main(String[] args) {
		JobConfigurationManager manager = new JobConfigurationManager();
		List<JobConfiguration> jobs;
		try {
			jobs = manager.loadJobConfigurations(
					new File("D:\\pioneer\\goodcrawler\\src\\main\\resources\\job_conf.xml"));
			for(JobConfiguration conf:jobs){
				// fetch
				FetchForeman.start(conf);
				// extract
				ExtractForeman.start(conf);
				// store
				StoreForeman.start(conf);
			}
		} catch (ConfigurationException e) {
			 e.getMessage();
		}
	}

}