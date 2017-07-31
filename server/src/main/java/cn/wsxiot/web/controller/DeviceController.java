package cn.wsxiot.web.controller;

import cn.wsxiot.web.domain.Bindinfo;
import cn.wsxiot.web.domain.Temhumlig;
import cn.wsxiot.web.service.DeviceService;
import cn.wsxiot.web.webutil.ConnectServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by wsx on 2017-07-18.
 */
@RestController
@RequestMapping(value = "/devices")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    /**
     * 由gid获取一条温湿度信息
     * @param gid
     * @return
     */
    @PostMapping(value = "/temhumlig")
    public Temhumlig getLatestRecord(int gid){
        return deviceService.getlatestTemhumligviaGid(gid);
    }

    /**
     * 由uemail获取用户网关列表
     * @param httpServletRequest
     * @return
     */
    @PostMapping(value = "/gidlist")
    public List<Bindinfo> getGidlist(HttpServletRequest httpServletRequest){
        String uemail = (String) httpServletRequest.getSession().getAttribute("uemail");
        return deviceService.getGidlistfromUemail(uemail);
    }

    /**
     * 控制客厅灯
     * @param gid
     * @param ctrl
     * @return
     */
    @PostMapping(value = "/livingled")
    public String ctrlLivingLed(int gid, int ctrl){
        String res = ConnectServer.transCmd("3/11/"+String.valueOf(3002-ctrl)+";"+String.valueOf(gid));
        if(res.equals("0/11/0010")){
            return "true";
        }
        return "false";
    }

    /**
     * 控制卧室灯
     * @param gid
     * @param ctrl
     * @return
     */
    @PostMapping(value = "/bedled")
    public String ctrlBedLed(int gid, int ctrl){
        String res = ConnectServer.transCmd("3/12/"+String.valueOf(3004-ctrl)+";"+String.valueOf(gid));
        if(res.equals("0/12/0012")){
            return "true";
        }
        return "false";
    }
}
