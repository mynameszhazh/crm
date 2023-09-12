//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.bjpowernode.crm.workbench.service.impl;

import com.bjpowernode.crm.workbench.domain.ClueActivityRelation;
import com.bjpowernode.crm.workbench.mapper.ClueActivityRelationMapper;
import com.bjpowernode.crm.workbench.service.ClueActivityRelationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("clueActivityRelationService")
public class ClueActivityRelationServiceImpl implements ClueActivityRelationService {
    @Autowired
    private ClueActivityRelationMapper clueActivityRelationMapper;

    public ClueActivityRelationServiceImpl() {
    }

    public int saveCreateClueActivityRelationByList(List<ClueActivityRelation> list) {
        return this.clueActivityRelationMapper.insertClueActivityRelationByList(list);
    }

    public int deleteClueActivityRelationByClueIdActivityId(ClueActivityRelation relation) {
        return this.clueActivityRelationMapper.deleteClueActivityRelationByClueIdActivityId(relation);
    }
}
