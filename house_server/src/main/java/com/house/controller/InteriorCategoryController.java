package com.house.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.house.model.Data;
import com.house.model.Interior;
import com.house.model.InteriorCategory;
import com.house.service.InteriorService;
import com.house.util.BeanUtils;
import com.house.util.JacksonUtils;

@Controller
public class InteriorCategoryController {
	@Autowired
	InteriorService interiorService;
	
	@RequestMapping(value = "/add/interiorcategory", method = RequestMethod.POST)
	public @ResponseBody
	Data addInteriorCategory(@RequestBody Data data) {
		boolean isCategory = false;
		HashMap<String, Object> body = data.body;

		InteriorCategory interiorCategory =  JacksonUtils.jsonToObject(
				JacksonUtils.objectToJson(body.get("interiorcategory")), InteriorCategory.class);
		
		isCategory =  interiorService.addInteriorCategory(interiorCategory);
		
		Data responseData = new Data();
		if(isCategory)
			responseData.setStatus(Data.Status.OK);
		else
			responseData.setStatus(Data.Status.FAIL);
		return responseData;
	}
	
	@RequestMapping(value = "/modify/interiorcategory", method = RequestMethod.POST)
	public @ResponseBody
	Data modifyInteriorCategory(@RequestBody Data data) {
		HashMap<String, Object> body = data.body;

		InteriorCategory interiorCategory =  JacksonUtils.jsonToObject(
				JacksonUtils.objectToJson(body.get("interiorcategory")), InteriorCategory.class);
		
		System.out.println("status : " + interiorService.modifyInteriorCategory(interiorCategory));
		
		Data responseData = new Data();
		responseData.setStatus(Data.Status.OK);
		return responseData;
	}
	
	@RequestMapping(value = "/remove/interiorcategory", method = RequestMethod.POST)
	public @ResponseBody
	Data removeInteriorCategory(@RequestParam("icno") int cateNo) {
		boolean isCategory = false;
		
		isCategory = interiorService.removeInteriorCategory(cateNo);
		
		Data responseData = new Data();
		if(isCategory)
			responseData.setStatus(Data.Status.OK);
		else
			responseData.setStatus(Data.Status.FAIL);
		return responseData;
	}
	
	@RequestMapping(value = "/get/interiorcategory", method = RequestMethod.POST)
	public @ResponseBody
	Data getInteriorCategoryByNo(@RequestParam("icno") int cateNo) {
		InteriorCategory interiorCategory = interiorService.getInteriorCategoryByNo(cateNo);
//		try {
//			System.out.println(BeanUtils.getBeanGetValue(interiorCategory));
//		} catch(Exception e){
//			e.printStackTrace();
//		}
		Data responseData = new Data();
		responseData.body.put("interiorcategory", interiorCategory);
		responseData.setStatus(Data.Status.OK);
		return responseData;
	}
}
