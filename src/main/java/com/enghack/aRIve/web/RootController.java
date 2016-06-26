package com.enghack.aRIve.web;

import com.enghack.aRIve.service.SendService;
import com.enghack.aRIve.service.TtcService;
import com.enghack.aRIve.service.XMLParser;
import org.apache.http.client.methods.HttpGet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 37264 on 6/25/16.
 */
@Controller
public class RootController {

    @Autowired
    private TtcService ttcService;
    @Autowired
    private XMLParser xmlParser;
    @Autowired
    private SendService sendService;

    @RequestMapping(value = "/index.html", method = RequestMethod.GET)

    public ModelAndView handleRootGetRequest(HttpServletRequest request) throws Exception {
        //logger.debug( "Called" );
        String dest = request.getParameter("destination");
        String busID = request.getParameter("busNumber");
        ModelAndView mav = new ModelAndView();
        mav.setViewName( "index" );



        return mav;

    }

    @RequestMapping(value = "/getit", method = RequestMethod.GET)
    public double[] handleHomeGetRequest() throws Exception {
        //logger.debug( "Called" );
        long prev = System.currentTimeMillis();
        boolean far = true;
        while (far) {
            if (System.currentTimeMillis()-prev<3000){
                continue;
            }
            prev = System.currentTimeMillis();
            String address = "finch station ontario";
            String urlAddress = address.replaceAll(" ", "+");

            String apiKey = "AIzaSyDBrmaanE39Yss7TR5QLnDKa8X4vNwjxPM";
            double[] destPos = ttcService.executeDestGet("https://maps.googleapis.com/maps/api/geocode/xml?address=" + urlAddress + "&key=" + apiKey);
            double[] busPos = ttcService.executeGet("http://webservices.nextbus.com/service/publicXMLFeed?command=vehicleLocations&a=ttc&r=53&t=0", "1774");
            System.out.println(distance(busPos,destPos));
            if (distance(busPos,destPos)<300){
                far = false;
            }
        }

        System.out.println("done");
        return null;
    }

    private double distance(double[] busPos, double[] destPos){
        double dlon = (busPos[0]-destPos[0])*0.01745329252;
        double dlat = (busPos[1]-destPos[1])*0.01745329252;
        double a = Math.pow(Math.sin(dlat/2),2.0)+Math.cos(busPos[0]*0.01745329252)*Math.cos(destPos[0]*0.01745329252)*Math.pow(Math.sin(dlon/2),2.0);
        double c = 2* Math.atan2(Math.sqrt(a),Math.sqrt(1-a));
        double d = 6373000 * c;
        return d;
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    private void handleSendPostRequest(HttpServletRequest request) throws Exception {

        String dest = request.getParameter("destination");
        String busID = request.getParameter("busNumber");

        sendService.sendSms();
        sendService.receiveSms();
    }
}