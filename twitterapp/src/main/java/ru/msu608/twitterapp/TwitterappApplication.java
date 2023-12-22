package ru.msu608.twitterapp;

import com.hazelcast.config.*;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;


@SpringBootApplication
public class TwitterappApplication {


	@Bean
	public RestHighLevelClient elasticsearchClient(@Value("${elasticsearch.hosts}") String elasticHosts) {
		String[] hosts = elasticHosts.split(",");
		HttpHost[] httpHosts = Arrays.stream(hosts)
				.map(HttpHost::create).toArray(HttpHost[]::new);
		return new RestHighLevelClient(RestClient.builder(httpHosts));
	}

	@Bean
	HazelcastInstance hazelcastInstance() {
		Config cfg = new Config().addMapConfig(
				new MapConfig("student")
						.setEvictionConfig(
								new EvictionConfig()
										.setEvictionPolicy(EvictionPolicy.LRU)
										.setMaxSizePolicy(MaxSizePolicy.PER_NODE)
										.setSize(1000))
						.setNearCacheConfig(new NearCacheConfig("student-near-cache"))
		);
		return Hazelcast.newHazelcastInstance(cfg);
	}

	// Инструкция по использованию:
	// Правой кнопкой мыши по main -> More Run/Debug -> Modify Run Conf
	// Program arguments через пробел:
	// 1) Путь к питону(либо просто "python"),
	// 2) Путь к папке src/main/resources
	// 3) Путь к папке директории bin с mongod.exe (скачанный mongodb)
	// 4) Путь к папке с директориями data, log нужные для запуска mongodb
	// Click Run
	// Когда закончите работу, введите что-нибудь на клавиатуре
	public static void main(String[] args) throws java.io.IOException,
									InterruptedException{
		String Python_Path = args[0];
		String Script_Path = args[1];
		String Path_To_MongoDb_Bin = args[2];
		String Path_To_Log_And_Data_Folders = args[3];
		ProcessBuilder Start_Builder =
				new ProcessBuilder(Python_Path, Script_Path + "mongodb_start.py",
				Path_To_MongoDb_Bin, Path_To_Log_And_Data_Folders)
						.inheritIO();

		Process Start_Process = Start_Builder.start();
		Start_Process.waitFor();

		SpringApplication.run(TwitterappApplication.class, args);

		BufferedReader reader = new BufferedReader(
				new InputStreamReader(System.in));
		System.out.print("Input any data to stop application: ");
		reader.readLine();

		ProcessBuilder Stop_Builder = new
				ProcessBuilder(Python_Path, Script_Path + "mongodb_stop.py",
				Path_To_MongoDb_Bin, Path_To_Log_And_Data_Folders).inheritIO();

		Process Stop_Process = Stop_Builder.start();
		Stop_Process.waitFor();
		System.exit(0);

	}

}
