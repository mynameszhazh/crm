//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.bjpowernode.crm.workbench.mapper;

import com.bjpowernode.crm.workbench.domain.Contacts;

public interface ContactsMapper {
    int deleteByPrimaryKey(String var1);

    int insert(Contacts var1);

    int insertSelective(Contacts var1);

    Contacts selectByPrimaryKey(String var1);

    int updateByPrimaryKeySelective(Contacts var1);

    int updateByPrimaryKey(Contacts var1);

    int insertContacts(Contacts var1);
}
