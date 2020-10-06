package com.kgc.servlce;

import com.kgc.pojo.Projectinfo;

import java.util.List;

public interface ProServlce {
    List<Projectinfo> selAll(int status);
    Projectinfo selById(Integer id);
    int upd(Projectinfo projectinfo);

}
