package com.android.safing;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
 
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import common.CommonService;
 
@Controller
public class FileUploadController {
	
	@Autowired private CommonService service;
	
    private static final String CURR_IMAGE_REPO_PATH = 
                "D:\\A_TeachingMaterial\\6.JspSpring\\other\\images";
    
    @RequestMapping(value="/form")
    public String form() {
        return "uploadForm";
    }
    
    @RequestMapping(value="/upload", method=RequestMethod.POST)
    public ModelAndView upload(MultipartHttpServletRequest multipartRequest,
                    HttpServletResponse response, HttpSession session) throws Exception{
        multipartRequest.setCharacterEncoding("utf-8");
        Map map = new HashMap();
        Enumeration enu = multipartRequest.getParameterNames();
        
        while(enu.hasMoreElements()) {
            String name = (String)enu.nextElement();
            String value = multipartRequest.getParameter(name);
            map.put(name, value);
        }
       
        List fileList = fileProcess("product_file",multipartRequest, session);
        map.put("fileList", fileList);
        
        ModelAndView mav = new ModelAndView();
        mav.addObject("map", map);
        mav.setViewName("result");
        return mav;
    }
    
    private List<String> fileProcess(String category,MultipartHttpServletRequest multipartRequest,  HttpSession session) 
        throws Exception{
        List<String> fileList = new ArrayList<String>();
        Iterator<String> fileNames = multipartRequest.getFileNames();
        
        String resources = session.getServletContext().getRealPath("resources");
        String folder = resources + "/upload" + "/" + category + "/"
				+ new SimpleDateFormat("yyyy/MM/dd").format(new Date());
        
        while(fileNames.hasNext()) {

			
            String fileName = fileNames.next();
            MultipartFile mFile = multipartRequest.getFile(fileName);
            String originalFileName = mFile.getOriginalFilename();
            fileList.add(originalFileName);
            File file = new File(CURR_IMAGE_REPO_PATH + "\\" + fileName);
            
            
            if(mFile.getSize() != 0) {
                if(!file.exists()) {
                    if(file.getParentFile().mkdir()) {
                        file.createNewFile();
                    }
                }
                mFile.transferTo(new File(CURR_IMAGE_REPO_PATH + "\\" + originalFileName));
            }
        }
        return fileList;
    }
}
 
