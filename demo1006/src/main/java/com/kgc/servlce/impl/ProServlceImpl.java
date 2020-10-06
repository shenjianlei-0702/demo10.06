package com.kgc.servlce.impl;

import com.kgc.mapper.ProjectinfoMapper;
import com.kgc.pojo.Projectinfo;
import com.kgc.pojo.ProjectinfoExample;
import com.kgc.servlce.ProServlce;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("proService")
public class ProServlceImpl implements ProServlce{
    @Resource
    ProjectinfoMapper projectinfoMapper;
    @Override
    public List<Projectinfo> selAll(int status) {
        ProjectinfoExample example = new ProjectinfoExample();
        ProjectinfoExample.Criteria criteria = example.createCriteria();
        List<Projectinfo> projectinfos = null;
        if(status == 3){
            projectinfos = projectinfoMapper.selectByExample(null);
        }else{
            criteria.andStatusEqualTo(status);
            projectinfos = projectinfoMapper.selectByExample(example);
        }
        return projectinfos;
    }

    @Override
    public Projectinfo selById(Integer id) {
        Projectinfo projectinfo = projectinfoMapper.selectByPrimaryKey(id);
        return projectinfo;
    }

    @Override
    public int upd(Projectinfo projectinfo) {
        int i = projectinfoMapper.updateByPrimaryKeySelective(projectinfo);
        return i;
    }
}
