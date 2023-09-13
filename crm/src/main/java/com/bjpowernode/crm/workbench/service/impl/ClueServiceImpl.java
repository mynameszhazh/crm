package com.bjpowernode.crm.workbench.service.impl;

import com.bjpowernode.crm.commons.utils.DateUtils;
import com.bjpowernode.crm.commons.utils.UUIDUtils;
import com.bjpowernode.crm.settings.domain.User;
import com.bjpowernode.crm.workbench.domain.Clue;
import com.bjpowernode.crm.workbench.domain.Contacts;
import com.bjpowernode.crm.workbench.domain.Customer;
import com.bjpowernode.crm.workbench.mapper.ClueMapper;
import com.bjpowernode.crm.workbench.mapper.ContactsMapper;
import com.bjpowernode.crm.workbench.mapper.CustomerMapper;
import com.bjpowernode.crm.workbench.service.ClueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service("clueService")
public class ClueServiceImpl implements ClueService {

    @Autowired
    private ClueMapper clueMapper;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private ContactsMapper contactsMapper;

    @Override
    public int saveCreateClue(Clue clue) {
        return clueMapper.insertClue(clue);
    }

    @Override
    public Clue queryClueForDetailById(String id) {
        return clueMapper.selectClueForDetailById(id);
    }

    @Override
    public void saveConvertClue(Map<String, Object> map) {
        String clueId = (String) map.get("clueId");
        User user = (User) map.get("sessionUser");
        String isCreateTran = (String) map.get("isCreateTran");
        // 1.根据id线索信息
        Clue clue = clueMapper.selectClueById(clueId);
        // 2.把该线索中有关公司的信息转换到客户表中(线索中的很多数据可以在公司中使用)
        Customer c = new Customer();
        c.setAddress(clue.getAddress());
        c.setContactSummary(clue.getContactSummary());
        c.setCreateBy(user.getId());
        c.setCreateTime(DateUtils.formatDateTime(new Date()));
        c.setDescription(clue.getDescription());
        c.setId(UUIDUtils.getUUID());
        c.setName(clue.getCompany());
        c.setNextContactTime(clue.getNextContactTime());
        c.setOwner(user.getId());
        c.setPhone(clue.getPhone());
        c.setWebsite(clue.getWebsite());
        customerMapper.insertCustomer(c);

        // 3.有关个人信息转换到联系人表中
        Contacts co = new Contacts();
        co.setAddress(clue.getAddress());
        co.setAppellation(clue.getAppellation());
        co.setContactSummary(clue.getContactSummary());
        co.setCreateBy(user.getId());
        co.setCreateTime(DateUtils.formatDateTime(new Date()));
        co.setCustomerId(c.getId());
        co.setDescription(clue.getDescription());
        co.setEmail(clue.getEmail());
        co.setFullname(clue.getFullname());
        co.setId(UUIDUtils.getUUID());
        co.setJob(clue.getJob());
        co.setMphone(clue.getMphone());
        co.setNextContactTime(clue.getNextContactTime());
        co.setOwner(user.getId());
        co.setSource(clue.getSource());
        contactsMapper.insertContacts(co);

        // 4.查询线索的备注
//        List<ClueRemark> crList = this.clueRemarkMapper.selectClueRemarkByClueId(clueId);
//        ContactsRemark coar;
//        ArrayList tr;
//        ArrayList trList;
//        Iterator var13;
//        ClueRemark cr;
//        if (crList != null && crList.size() > 0) {
//            CustomerRemark cur = null;
//            coar = null;
//            tr = new ArrayList();
//            trList = new ArrayList();
//            var13 = crList.iterator();
//
//            while (var13.hasNext()) {
//                cr = (ClueRemark) var13.next();
//                cur = new CustomerRemark();
//                cur.setCreateBy(cr.getCreateBy());
//                cur.setCreateTime(cr.getCreateTime());
//                cur.setCustomerId(c.getId());
//                cur.setEditBy(cr.getEditBy());
//                cur.setEditFlag(cr.getEditFlag());
//                cur.setEditTime(cr.getEditTime());
//                cur.setId(UUIDUtils.getUUID());
//                cur.setNoteContent(cr.getNoteContent());
//                tr.add(cur);
//                coar = new ContactsRemark();
//                coar.setContactsId(co.getId());
//                coar.setCreateBy(cr.getCreateBy());
//                coar.setCreateTime(cr.getCreateTime());
//                coar.setEditBy(cr.getEditBy());
//                coar.setEditFlag(cr.getEditFlag());
//                coar.setEditTime(cr.getEditTime());
//                coar.setId(UUIDUtils.getUUID());
//                coar.setNoteContent(cr.getNoteContent());
//                trList.add(coar);
//            }
//
//            // 5- [ ] 备注转到客户备注表
//            this.customerRemarkMapper.insertCustomerRemarkByList(tr);
//            // 6.备注到联系人备注表
//            this.contactsRemarkMapper.insertContactsRemarkByList(trList);
//
//            // 6.获取市场活动和关系
//            List<ClueActivityRelation> carList = this.clueActivityRelationMapper.selectClueActivityRelationByClueId(clueId);
//            if (carList != null && carList.size() > 0) {
//                coar = null;
//                tr = new ArrayList();
//                Iterator var19 = carList.iterator();
//
//                while (var19.hasNext()) {
//                    ClueActivityRelation car = (ClueActivityRelation) var19.next();
//                    ContactsActivityRelation coar = new ContactsActivityRelation();
//                    coar.setActivityId(car.getActivityId());
//                    coar.setContactsId(co.getId());
//                    coar.setId(UUIDUtils.getUUID());
//                    tr.add(coar);
//                }
//
//                // 6.获取市场活动和关系处理
//                this.contactsActivityRelationMapper.insertContactsActivityRelationByList(tr);
//            }
//
//            // 7.创建交易
//            if ("true".equals(isCreateTran)) {
//                Tran tran = new Tran();
//                tran.setActivityId((String) map.get("activityId"));
//                tran.setContactsId(co.getId());
//                tran.setCreateBy(user.getId());
//                tran.setCreateTime(DateUtils.formateDateTime(new Date()));
//                tran.setCustomerId(c.getId());
//                tran.setExpectedDate((String) map.get("expectedDate"));
//                tran.setId(UUIDUtils.getUUID());
//                tran.setMoney((String) map.get("money"));
//                tran.setName((String) map.get("name"));
//                tran.setOwner(user.getId());
//                tran.setStage((String) map.get("stage"));
//
//                // 线索信息加入交易
//                this.tranMapper.insertTran(tran);
//                if (crList != null && crList.size() > 0) {
//                    tr = null;
//                    trList = new ArrayList();
//                    var13 = crList.iterator();
//
//                    while (var13.hasNext()) {
//                        cr = (ClueRemark) var13.next();
//                        TranRemark tr = new TranRemark();
//                        tr.setCreateBy(cr.getCreateBy());
//                        tr.setCreateTime(cr.getCreateTime());
//                        tr.setEditBy(cr.getEditBy());
//                        tr.setEditFlag(cr.getEditFlag());
//                        tr.setEditTime(cr.getEditTime());
//                        tr.setId(UUIDUtils.getUUID());
//                        tr.setNoteContent(cr.getNoteContent());
//                        tr.setTranId(tran.getId());
//                        trList.add(tr);
//                    }
//                    // 备注信息也是一样
//                    this.tranRemarkMapper.insertTranRemarkByList(trList);
//                }
//            }
//
//            // 8.删除线索的相关的信息
//            this.clueRemarkMapper.deleteClueRemarkByClueId(clueId);
//            this.clueActivityRelationMapper.deleteClueActivityRelationByClueId(clueId);
//            this.clueMapper.deleteClueById(clueId);
//        }

    }
}
