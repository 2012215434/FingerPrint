package nth.FingerPrint.common;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class PageFromJson {
	public int getPage(String pageJson){
		Gson gson = new Gson();
		Type listType = new TypeToken<Page>(){}.getType();
		Page pageC = new Page();
		pageC  = gson.fromJson(pageJson,listType);
		String acpage = pageC.getPage();
		int page = Integer.parseInt(acpage);
		return page;
	}
}
