package com.enghack.aRIve.web;

import com.enghack.aRIve.service.TtcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by 37264 on 6/25/16.
 */
@Controller
public class RootController {

    @Autowired
    private TtcService ttcService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public  ModelAndView handleRootGetRequest() throws Exception {
        //logger.debug( "Called" );
        ModelAndView mav = new ModelAndView();
        //mav.setViewName("redirect:/index.html");


        return mav;

    }

    @RequestMapping(value = "/getit", method = RequestMethod.GET)
    public String handleHomeGetRequest() throws Exception {
        //logger.debug( "Called" );

        String address="";
        String urlAddress = address.replaceAll(" ","+");

        String apiKey="AIzaSyDBrmaanE39Yss7TR5QLnDKa8X4vNwjxPM";
        String geo = ttcService.executeGet("https://maps.googleapis.com/maps/api/geocode/xml?address="+urlAddress+"&key="+apiKey);
        String x = ttcService.executeGet("http://webservices.nextbus.com/service/publicXMLFeed?command=vehicleLocations&a=ttc&r=53&t=0");

        return x;
    }
}
