package cn.wsxiot.web.service;

import cn.wsxiot.web.domain.Bindinfo;
import cn.wsxiot.web.domain.Temhumlig;
import cn.wsxiot.web.domain.User;
import cn.wsxiot.web.repository.BindinfoRepository;
import cn.wsxiot.web.repository.TemhumligRepository;
import cn.wsxiot.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wsx on 2017-07-19.
 */
@Service
public class DeviceService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TemhumligRepository temhumligRepository;

    @Autowired
    private BindinfoRepository bindinfoRepository;

    public Temhumlig getlatestTemhumligviaGid(int gid){
        if(gid <= 0)
            return null;
        return temhumligRepository.findFirstByGidOrderByThlidDesc(gid);
    }

    public List<Bindinfo> getGidlistfromUemail(String uemail){
        List<User> userList = userRepository.findByUemail(uemail);
        if(userList == null){
            return null;
        }
        return bindinfoRepository.findByUid(userList.get(0).getUid());
    }
}
