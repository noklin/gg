package rti;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.google.gson.Gson;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class Launcher {

	public static void main(String[] args) throws IOException, InterruptedException {
		Root root = new Root();
		new Thread(() -> {
			createReq(root);
		}).start();
		
//		int ii = 5;
//		while(ii --> 0) {
//			Root root = new Root();
//			int i = 20;
//			while(i --> 0) {
//				TimeUnit.SECONDS.sleep(1);
//				new Thread(() -> {
//					createReq(root);
//				}).start();
//				
//				new Thread(() -> {
//					createReq(root);
//				}).start();
//			}		
//		}
		
		

	}
	
	
	static void createReq(Root content) {
		content = content.copy(new ArrayList<>(Arrays.asList(new Service())));
		Gson gson = new Gson();
		String ss = gson.toJson(content); 
		System.out.println("send: " + ss);
		OkHttpClient client = new OkHttpClient();
		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(mediaType, ss);
		Request request = new Request.Builder().url("http://localhost:8080/api/v1/consumers/oms/requests")
				.method("POST", body).addHeader("Content-Type", "application/json").build();
		Response response = null;
		try {
			response = client.newCall(request).execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("resp:" + response);
	}

	@Data
	@Builder
	public static class DataField{
	    public String name;
	    public String value;
	    
	}

	@Data
	@Builder
	public static class Client{
	    public String contactName;
	    public String contactPhone;
	    public String email;
	    public Object inn;
	    public String orgName;
	}

	@Data
	@Builder
	public static class ServiceField{
	    public String name;
	    public String value;
	}

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Service{
	    public long orderId = new Random().nextLong();
	    public String orderNumber = "21-" + orderId;
	    public String code = "internet";
	    public String globalId = "35399716";
	    public List<ServiceField> serviceFields = new ArrayList<>();
	    {
	    	serviceFields.add(ServiceField.builder()
	    			.name("mrf")
	    			.value("{\\\"id\\\":3134334700,\\\"name\\\":\\\"Волга\\\",\\\"code\\\":\\\"VOLGA\\\"}")
	    			.build());
	    	serviceFields.add(ServiceField.builder()
	    			.name("region")
	    			.value("{\\\"id\\\":3134334700,\\\"name\\\":\\\"Волга\\\",\\\"code\\\":\\\"VOLGA\\\"}")
	    			.build());
	    }
	}

	@Data
	public static class Root{
		
		Root copy(List<Service> services) {
			Root r = new Root();
			r.client = client;
			r.dataFields = dataFields;
			r.requestId = requestId;
			r.requestNumber = requestNumber;
			r.services = services;
			return r;
		}
		
	    protected long requestId = new Random().nextLong();
		protected String requestNumber = "21-" + requestId;
		protected List<DataField> dataFields = new ArrayList<>();
	    {
	    	dataFields.add(DataField.builder()
	    			.name("mrf")
	    			.value("{\\\"id\\\":3134334700,\\\"name\\\":\\\"Волга\\\",\\\"code\\\":\\\"VOLGA\\\"}")
	    			.build());
	    	dataFields.add(DataField.builder()
	    			.name("region")
	    			.value("{\\\"id\\\":3134334700,\\\"name\\\":\\\"Волга\\\",\\\"code\\\":\\\"VOLGA\\\"}")
	    			.build());
	    	dataFields.add(DataField.builder()
	    			.name("region")
	    			.value("{\\\"id\\\":3134334700,\\\"name\\\":\\\"Волга\\\",\\\"code\\\":\\\"VOLGA\\\"}")
	    			.build());
	    	dataFields.add(DataField.builder()
	    			.name("global_id_region")
	    			.value("5200006")
	    			.build());
	    	dataFields.add(DataField.builder()
	    			.name("channel")
	    			.value("{\\\"id\\\":3134334700,\\\"name\\\":\\\"Волга\\\",\\\"code\\\":\\\"VOLGA\\\"}")
	    			.build());
	    	dataFields.add(DataField.builder()
	    			.name("sub_channel")
	    			.value("{\\\"id\\\":3134334700,\\\"name\\\":\\\"Волга\\\",\\\"code\\\":\\\"VOLGA\\\"}")
	    			.build());
	    	dataFields.add(DataField.builder()
	    			.name("system_ola_rule")
	    			.value("64")
	    			.build());
	    	
	    }
	    
	    public Client client = Client.builder()
	    		.contactName("-- Дедис")
	    		.contactPhone("9654022111")
	    		.email("vip.pevkin94@mail.ru")
	    		.orgName("-- Дедис")
	    		.contactName("ИП Ястребов Олег Валерьевич")
	    		.build();
	    public List<Service> services = new ArrayList<>();
	    {
	    	services.add(new Service());
	    }
	}
}

