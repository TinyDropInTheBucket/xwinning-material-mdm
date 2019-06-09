package com.winning.material.mdm.util;

import com.winning.base.akso.common.page.WinPagedList;
import com.winning.material.mdm.domain.constant.DelFlagEmu;
import com.winning.material.mdm.domain.constant.FieldNameConstant;
import com.winning.material.mdm.domain.constant.LimitSizeConstant;
import com.winning.material.mdm.domain.constant.SelectStepConstant;
import com.winning.material.mdm.service.repository.CommonRepository;
import com.winning.pts.utils.collection.CollectionUtil;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigInteger;
import java.util.*;

/**
 * 查询帮助类
 *  @author cuibx
 *  @date 2019/5/25
 */
public class CommonNativeSqlUtil {

    /**
     * 按条件拼装条件规则
     * @param specMap  规则条件
     * @param isDel 可传空则查询全部
     * @param <T>
     * @return
     */
    public static <T> Specification<T> initSpecification(Map<String,Object> specMap, Boolean isDel){
		return new Specification<T>() {
			private static final long serialVersionUID = 1L;
			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates=new ArrayList<>();
					for (Map.Entry<String,Object> entry : specMap.entrySet()) {
					    Object  data=entry.getValue();
					    /**集合类型**/
					    if(data instanceof Collection)
                        {
                            Predicate predicateIsDel=root.get(entry.getKey()).in((List)data);
                            predicates.add(predicateIsDel);
                        }
					    else if(data instanceof  LikePredicate){
					        /**模糊查找类型**/
                            String value=((LikePredicate) data).getLikeValue();
                            Predicate predicateIsDel=criteriaBuilder.like(root.get(entry.getKey()),value+"");
                            predicates.add(predicateIsDel);
                        }
					    else {
					        /**基础类型**/
                            Predicate predicateIsDel=criteriaBuilder.equal(root.get(entry.getKey()),data);
                            predicates.add(predicateIsDel);
                        }
					}
                    predicates.add(criteriaBuilder.equal(root.get(FieldNameConstant.IS_DEL_FIELD_NAME), DelFlagEmu.IS_NOT_DEL.getValue()));
                    if(null!=isDel&&isDel)
                    {
                        predicates.add(criteriaBuilder.equal(root.get(FieldNameConstant.IS_DEL_FIELD_NAME), DelFlagEmu.IS_DEL.getValue()));
                    }
				return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
			}
		};
	}

    /**
     * 按规则查询集合数据（如按指定规则查询单个对象可进行变形get(0)）
     * * @param dao
     * @param specMap  条件规则
     * @param isDel 可传空则查询全部
     *  @param sort
     * @param <T>
     * @return
     */
    public static <T> List<T>  specFindList(CommonRepository<T> dao, Map<String,Object> specMap, Sort sort, Boolean isDel){
        if(null!=sort)
        {
            return dao.findAll(initSpecification(specMap,isDel),sort);
        }
        List<T> data= dao.findAll(initSpecification(specMap,isDel));
        return CollectionUtil.isEmpty(data)? Collections.emptyList():data;
    }

    /**
     *按规则分页查询
     * @param dao
     * @param specMap 条件规则
     * @param pageable  分页规则可传子类PageRequest
     * @param isDel 可传空则查询全部
     * @param <T>
     * @return
     */
    public static <T> WinPagedList<T>  specPageFindList(CommonRepository<T> dao, Map<String,Object> specMap, Pageable pageable, Boolean isDel){
        Page<T> page= dao.findAll(initSpecification(specMap,isDel),pageable);
        WinPagedList<T> pagedList = new WinPagedList<>();
        pagedList.setData(page.getContent());
        pagedList.setCount(page.getTotalElements());
        return pagedList;
    }

    /**
     * 批量按指定单键范围查询
     * @param dao
     * @param ids
     * @param idName
     * @param isDel 可传空则查询全部
     * @param <T>
     * @return
     */
    public static <T> List<T>  findAllInList(CommonRepository<T> dao,List<Long> ids,String idName,Boolean isDel)
    {
        Specification<T> spec=new Specification<T>() {
            private static final long serialVersionUID = 1L;
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates=new ArrayList<>();
                if(null!=isDel)
                {
                    if(isDel)
                    {
                        predicates.add(criteriaBuilder.equal(root.get("isDel"), DelFlagEmu.IS_DEL.getValue()));
                    }
                    else {
                        predicates.add(criteriaBuilder.equal(root.get("isDel"), DelFlagEmu.IS_NOT_DEL.getValue()));
                    }
                }
                Predicate predicateIds=root.get(idName).in(ids);
                predicates.add(predicateIds);
                return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
            }
        };
        return dao.findAll(spec);
    }

    /**
     * 批量按指定单键范围查询---按系统指定步长分批执行
     * @param dao
     * @param list
     * @param idName
     *  @param isDel 可传空则查询全部
     * @param <T>
     * @return
     */
    public static <T> List<T>  findAllInListStep(CommonRepository<T> dao,List<Long> list,String idName,Boolean isDel,Integer step)
    {
        if(null==step)
        {
            /**不传时默认步长**/
            step= SelectStepConstant.SELECT_STEP;
        }
        List<T> clinicalServiceEncounterTypeList=new ArrayList<>();
        int size = list.size();
        int count = (size % step == 0) ? (size / step) : (1 + size / step);
        int startIndex = 0;
        int endIndex = 0;
        for (int i = 0; i < count; i++) {
            startIndex = endIndex;
            endIndex += step;
            if (endIndex > (size)) {
                endIndex = (size);
            }
            List<T> encounterTypeList= CommonNativeSqlUtil.findAllInList(dao,list.subList(startIndex, endIndex),idName,isDel);
            clinicalServiceEncounterTypeList.addAll(encounterTypeList);
        }
        return clinicalServiceEncounterTypeList;
    }



    /**
     * 执行本地脚本返回分页对象
     * @param querySql
     * @param pageable 如果为空则查询默认  可传子类PageRequest
     * @param parameters  如果没有参数可以不传
     * @param resultClass  如果未传入则返回List<Map>
     * @param <T>
     * @return
     */
    public static <T> WinPagedList<T> findPageListByNativeSql(String querySql, Pageable pageable, Map<String,Object> parameters, Class<T> resultClass, EntityManager manager) {
        Query query = manager.createNativeQuery(querySql,resultClass);
        setParameters(query,parameters);
        /**总记录数处理**/
        Long countsiz=handlePageCount(querySql,parameters,manager);
        WinPagedList<T> pagedList = new WinPagedList<>();
        /**处理判断是否需要查询分页数据，如果超出页数直接返回空**/
        if(!isPage(pageable,countsiz))
        {
            pagedList.setCount(0L);
            pagedList.setData(Collections.emptyList());
            return pagedList;
        }
        setPageable(query,pageable,countsiz);
        List<T> lsit= query.getResultList();
        pagedList.setData(lsit);
        pagedList.setCount(countsiz);
        return pagedList;
    }

    /**
     * 执行本地脚本返回分页对象返回List<Map<String,Object>>
     * @param querySql
     * @param pageable
     * @param parameters
     * @param manager
     * @return
     */
    public static WinPagedList<Map<String,Object>> findPageListMapByNativeSql(String querySql, Pageable pageable, Map<String,Object> parameters, EntityManager manager) {
        Query query = manager.createNativeQuery(querySql);

        setParameters(query,parameters);
        /**总记录数处理**/
        Long countsiz=handlePageCount(querySql,parameters,manager);
        /**处理判断是否需要查询分页数据，如果超出页数直接返回空**/
        WinPagedList<Map<String,Object>> pagedList = new WinPagedList<>();
        if(!isPage(pageable,countsiz))
        {
            pagedList.setCount(0L);
            pagedList.setData(Collections.emptyList());
            return pagedList;
        }
        setPageable(query,pageable,countsiz);
        /**将查询结果转换成List<Map>**/
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List<Map<String,Object>> lsit= query.getResultList();
        convertBigInteger(lsit);
        pagedList.setData(lsit);
        pagedList.setCount(countsiz);
        return pagedList;
    }

    public  static Long handlePageCount(String sql,Map<String,Object> parameters,EntityManager manager)
    {
        StringBuilder countsql=new StringBuilder();
        countsql.append("select count(1) from (").append(sql).append(") a");
        Query queryCount = manager.createNativeQuery(countsql.toString());
        setParameters(queryCount,parameters);
        Object coutData=queryCount.getSingleResult();
        Long countsiz=0L;
        if(null!=coutData) {
            countsiz=Long.valueOf(coutData+"");
        }
        return countsiz;
    }

    /**
     * 执行本地代码返回List<Map<String,Object>>
     * @param querySql
     * @param parameters
     * @param manager
     * @return
     */
    public static List<Map<String,Object>> findListMapByNativeSql(String querySql,Map<String,Object> parameters, EntityManager manager) {
        Query query = manager.createNativeQuery(querySql);
        setParameters(query,parameters);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List<Map<String,Object>> listdata= query.getResultList();
        convertBigInteger(listdata);
        return CollectionUtil.isEmpty(listdata)? Collections.emptyList():listdata;
    }
    /**
     * 执行本地代码返回list
     * @param querySql      需要执行的sql语句
     * @param resultClass  返回数据对象
     * @param  parameters 需要绑定的参数
     * @param <T>
     * @return
     */
    public static <T> List<T> findListByNativeSql(String querySql, Class<T> resultClass,Map<String,Object> parameters, EntityManager manager) {
        Query query = manager.createNativeQuery(querySql,resultClass);
        setParameters(query,parameters);
        List<T> listdata= query.getResultList();
        return CollectionUtil.isEmpty(listdata)? Collections.emptyList():listdata;
    }

    /**
     * 执行本地sql时设置条件参数
     * @param query
     * @param parameters
     */
    public static void setParameters(Query query, Map<String,Object> parameters)
    {
        if(null!=parameters)
        {
            for(Map.Entry<String,Object> data:parameters.entrySet())
            {
                query.setParameter(data.getKey(),data.getValue());
            }
        }
    }

    /**
     * 执行本地sql时设置分页信息---执行本地sql分页时需要
     * @param query
     * @param pageable
     *  @param  count 总记录数
     */
    public static void setPageable(Query query, Pageable pageable, Long count)
    {
        if(pageable!=null) {
            //执行本地sql时con第一页开始
            int page=((pageable.getPageNumber())*pageable.getPageSize());
            if(0==page)
            {
                page=1;
            }
            query.setFirstResult(page);
            int maxsize=page+pageable.getPageSize();
            if(maxsize>count)
            {

                query.setMaxResults((int)(maxsize-((maxsize-count)+page)));
            }
            query.setMaxResults(pageable.getPageSize());
        }
        else{
            query.setFirstResult(1);
            query.setMaxResults(LimitSizeConstant.LIMIT_SIZE);
        }
    }

    /**
     * 是否需要分页查询---执行本地sql分页时需要
     * @param pageable
     * @param count
     * @return
     */
    public static boolean isPage(Pageable pageable, Long count)
    {
        if(null==pageable)
        {
            return true;
        }
        if(count%pageable.getPageSize()==0){
            //说明整除，正好每页显示pageSize条数据，没有多余一页要显示少于pageSize条数据的
             long totalPage = (count / pageable.getPageSize());
             if((pageable.getPageNumber()+1)>totalPage)
             {
               return false;
             }
        }else{
            //不整除，就要在加一页，来显示多余的数据。
            long totalPage = (count / pageable.getPageSize())+1;
            if((pageable.getPageNumber()+1)>totalPage)
            {
                return false;
            }
        }
        return true;
    }

    /**
     * 分页页码处理
     * @param pageNo
     * @return
     */
    public static int getCorrectPageNo(int pageNo) {
        if (pageNo < 0) {
            pageNo = 0;
        } else if (pageNo > 0) {
            pageNo -= 1;
        }
        return pageNo;
    }

    /**
     * 将单个条件组合成sql in格式
     * @param objects
     * @return
     */
    public static String transformationSqlListIn(List<Object> objects)
    {
      StringBuilder objectids=new StringBuilder();
      if(null!=objects)
      {
         for(Object obj:objects)
         {
             objectids.append("'"+obj+"',");
         }
         String reobj=objectids.toString();
          reobj=reobj.substring(0,reobj.length()-1);
          return reobj;
      }
      return "";
    }

    /**Predicate Like辅助类**/
    public class  LikePredicate
    {
        private String likeValue;

        public  LikePredicate(String likeValue)
        {
            this.likeValue=likeValue;
        }
        public String getLikeValue() {
            return likeValue;
        }
    }

    /**
     * 执行本地sql查询数据返回Map时进行数据类型转换
     * @param convertData
     */
    public static void convertBigInteger(List<Map<String,Object>> convertData){
     if(null!=convertData&&convertData.size()>0)
     {
         for(Map<String,Object> data:convertData)
         {
            for(Map.Entry<String,Object> covdata:data.entrySet())
            {
                if(covdata.getValue() instanceof BigInteger)
                {
                    covdata.setValue(Long.valueOf(covdata.getValue().toString()));
                }
            }
         }
     }
    }


    public static LikePredicate ofLikePredicate(String value)
    {
        CommonNativeSqlUtil commonNativeSqlUtil=new CommonNativeSqlUtil();
        return commonNativeSqlUtil.new LikePredicate(value);
    }



}
